package utilities.sql;

import utilities.sql.annotations.Ignore;
import utilities.sql.annotations.PrimaryKey;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.*;

/**
 * This object is a data wrapper
 *
 * The Dapper is responsible for operations on the table level.
 * Its sole purpose is to bind objects to data in a SQL-database,
 * also known as Object-Relational-Mapping (ORM).
 *
 * This Dapper has been tailored to work with MySQL, MariaDB and
 * other forked MySQL databases.
 *
 * @param <AnyType>   Generic object to use for tables
 */
public class Dapper<AnyType> extends Connector {
    /**
     * Used for debugging purposes.
     * If set to true:  Statements will be printed.
     * If set to false: Statements will not be printed.
     */
    public static boolean PRINT_STATEMENTS = false;

    /**
     * Used for debugging purposes.
     * If set to true:  Statements will be executed.
     * If set to false: Statements will not be executed.
     */
    public static boolean EXECUTE_STATEMENTS = true;

    /**
     * Sorting Orders
     */
    public final String SORT_ASC = "ASC";
    public final String SORT_DESC = "DESC";

    private final String sqlInsert = "INSERT INTO %s (%s) VALUES(%s);";
    private final String sqlUpdate = "UPDATE %s SET %s WHERE %s = ?;";
    private final String sqlUpdateSingle = "UPDATE %s SET %s = ? WHERE id = ?;";
    private final String sqlDelete = "DELETE FROM %s WHERE id = ?;";
    private final String sqlTruncate = "TRUNCATE TABLE %s;";
    private final String sqlDrop = "DROP TABLE IF EXISTS %s;";
    private final String sqlCount = "SELECT COUNT(*) FROM %s;";
    private final String sqlCountWhere = "SELECT COUNT(*) FROM %s WHERE %s = ?;";
    private final String sqlSelect = "SELECT %s FROM %s ORDER BY %s LIMIT %s;";
    private final String sqlSelectId = "SELECT * FROM %s WHERE %s = ?;";
    private Connection connection = null;

    /**
     * Name of the primary key field. This should be set using the annotation @PrimaryKey
     */
    private String primaryKeyFieldName;
    private final Class<AnyType> tableType;
    private int lastInsertId;

    public Dapper(Class<AnyType> type) {
        try {
            this.connection = Connector.getInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.tableType = type;
        this.lastInsertId = -1;

        if (Dapper.PRINT_STATEMENTS)
            System.out.println("Dapper: Statements will be printed");

        if (!Dapper.EXECUTE_STATEMENTS)
            System.out.println("Dapper: Statements will NOT execute.");

        this.computeAnnotations(type);
    }

    /**
     * Compute all annotations to get data from them
     * @param clazz the class to retrieve all annotations from
     */
    private void computeAnnotations(Class<AnyType> clazz) {
        for (Field field: this.getInheritedFields(clazz, false)) {
            if (field.isAnnotationPresent(PrimaryKey.class)) {
                // Annotation value
                String value = field.getAnnotation(PrimaryKey.class).value();

                // If the annotation is empty, use the attribute name instead.
                this.primaryKeyFieldName = value.isEmpty() ? field.getName() : value;
            }
        }
    }

    /**
     * @return the primary key field name as set by @PrimaryKey
     */
    public String getPrimaryKeyFieldName() {
        return this.primaryKeyFieldName;
    }

    /**
     * Returns the name of the table
     * @return Name of the table
     */
    public String getTableName() {
        return this.tableType.getSimpleName();
    }

    /**
     * Inserts data to the database using the object class name as
     * the table name and its properties as table columns.
     * @param data          The object to inject into the database
     */
    public void insert(Object data) {
        try {
            Field[] fields = this.getInheritedFields();
            String columns = "";
            String values = "";

            for (Field field: fields) {
                columns += ", " + field.getName();
                values  += ", ?";
            }

            columns = columns.substring(2);
            values = values.substring(2);

            PreparedStatement statement = connection.prepareStatement(String.format(this.sqlInsert, this.getTableName(), columns, values), Statement.RETURN_GENERATED_KEYS);

            // Bind all data to the statement
            for (int i = 0; i < fields.length; i++) {
                fields[i].setAccessible(true);

                try {
                    statement.setObject(i + 1, fields[i].get(data));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

            // Execute insertion and generate key
            this.execute(statement, true);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates data in the database using the object class name as
     * the table name and its properties as table columns.
     * @param primaryKey    Primary key used for the update
     * @param data          The object used to update the table row
     */
    public void update(int primaryKey, Object data) {
        try {
            Field[] fields = this.getInheritedFields(this.tableType, true);
            PreparedStatement statement = connection.prepareStatement(String.format(this.sqlUpdate, this.getTableName(), Builder.linear(", ", fields), this.getPrimaryKeyFieldName()), Statement.RETURN_GENERATED_KEYS);

            // Bind all properties to the statement
            for (int i = 1; i <= fields.length; i++)
                statement.setObject(i, fields[i-1].get(data));

            // Bind the primary key to the statement to the last WHERE = ?
            statement.setObject(fields.length + 1, primaryKey);

            System.out.println(statement);

            // Execute insertion and generate key
            this.execute(statement, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates the value of a specified column
     * @param primaryKey    primaryKey of the row
     * @param columnName    column name of the table
     * @param value value to be inserted
     */
    public void updateColumnValue(int primaryKey, String columnName, int value){
        try {
            PreparedStatement statement = connection.prepareStatement(String.format(this.sqlUpdateSingle, this.getTableName(),
                    columnName));
            statement.setInt(1, value);
            statement.setInt(2, primaryKey);
            statement.executeUpdate();
        }
        catch (SQLException e1){
            e1.printStackTrace();
        }
    }

    /**
     * Deletes data in the database using the object class name as
     * table name.
     * @param   primaryKey    Primary key used for the deletion
     */
    public void delete(int primaryKey) {
        this.delete(this.primaryKeyFieldName, primaryKey);
    }

    /**
     * Deletes data in the database using object class name as a table name and
     * a collection of objects to determine which columns to target. Every odd argument
     * represents the column name, every even argument sets the value.
     * @param   args  collection of columns and values
     */
    public void delete(Object... args) {
        try {
            // Required by PreparedStatement for setObject
            Integer num  = 1;

            // The query to execute
            String query = String.format("DELETE FROM %s WHERE %s;", this.getTableName(), Builder.and(args));

            PreparedStatement statement = connection.prepareStatement(query);
            for(Map.Entry<Object, Object> map: Builder.computeObjectMap(args).entrySet()) {
                statement.setObject(num++, map.getValue());

                // If the last inserted key is deleted,
                // then reflect this in the dapper
                if (map.getKey().equals(this.getPrimaryKeyFieldName()) && map.getKey().equals(this.getLastInsertId()))
                    this.lastInsertId = - 1;
            }

            this.execute(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Truncates data in table using the object class name as
     * table name
     */
    public void truncate() {
        try {
            PreparedStatement statement = connection.prepareStatement(String.format(this.sqlTruncate, this.getTableName()));
            this.execute(statement);
            this.lastInsertId = -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * Destroys data in table using the object class name as
     * table name
     */
    public void drop() {
        try {
            PreparedStatement statement = connection.prepareStatement(String.format(this.sqlDrop, this.getTableName()));
            this.execute(statement);
            this.lastInsertId = -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieve a count of rows in a table
     * @return the total amounts of rows in a table
     */
    public int count() {
        int count = 0;

        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(String.format(this.sqlCount, this.getTableName()));

            while(result.next())
                count = result.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count;
    }

    /**
     * Retrieves a count of rows matching the argument
     * @param columnName    the column name
     * @param arg   the argument
     * @return  the total amount of rows
     */
    public int count(String columnName, String arg){
        int count = 0;

        try {
            PreparedStatement statement = connection.prepareStatement(String.format(this.sqlCountWhere, this.getTableName(), columnName));
            statement.setString(1, arg);
            ResultSet result = statement.executeQuery();

            while(result.next())
                count = result.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count;
    }

    @Deprecated
    public void create() {}


    /**
     * Returns the last inserted key LAST_INSERT_KEY
     * @return  Returns the last key inserted to the table
     */
    public int getLastInsertId() {
        return this.lastInsertId;
    }

    /**
     * Retrieves all properties from a class via reflection.
     * Fields with the annotation {@link utilities.sql.annotations.Ignore} are
     * ignored
     * @param clazz     the class type to check (table)
     * @param filter    boolean that determines if filtering is applied
     * @return All fields
     */
    private Field[] getInheritedFields(Class<?> clazz, boolean filter) {
        ArrayList<Field> fields = new ArrayList<>();

        for (Class<?> c = clazz; c != null; c = c.getSuperclass()) {
            for (Field field: c.getDeclaredFields())
            {
                // Setting the field to accessible (used only internally by Dapper)
                field.setAccessible(true);

                if (!filter)
                    fields.add(field); // Add all
                else
                    if (!field.isAnnotationPresent(Ignore.class))
                        fields.add(field); // Only add those without filters
            }
        }

        return fields.toArray(new Field[fields.size()]);
    }

    private Field[] getInheritedFields() {
        return this.getInheritedFields(this.tableType, true);
    }

    /**
     * Executes a statement and closes it properly
     * @param statement         The statement to execute.
     * @param generateLastKey   Set to true to generate last insert id
     */
    private void execute(PreparedStatement statement, boolean generateLastKey) {
        try {


            // Print statement
            if (Dapper.PRINT_STATEMENTS)
                System.out.println(statement);

            // Attempts to execute the statement
            if (Dapper.EXECUTE_STATEMENTS)
                statement.execute();

            // Are we supposed to generate a key?
            if (generateLastKey) {
                // Get the last inserted key
                ResultSet generatedKeys = statement.getGeneratedKeys();

                if (generatedKeys.next()) {
                    this.lastInsertId = generatedKeys.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * Executes a statement and closes it properly. Does not generate key.
     * @param statement The statement to execute.
     */
    private void execute(PreparedStatement statement) {
        this.execute(statement, false);
    }

    /**
     * Maps a result set to a object
     * @param   result The result set to map
     * @return  a collection with mapped objects
     */
    private Collection<AnyType> map(ResultSet result) {
        ArrayList<AnyType> collection = new ArrayList<>();

        try {
            while (result.next()) {
                AnyType object = this.tableType.getConstructor().newInstance();

                for(Field field: this.getInheritedFields(object.getClass(), true)) {
                    // Set this field to accessible
                    field.setAccessible(true);

                    // Get the object data from the resultset
                    field.set(object, result.getObject(field.getName()));
                }

                // Add to our array
                collection.add(object);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (result != null) {
                try {
                    result.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return collection;
    }

    /**
     * Returns a collection of objects using a predefined key
     * @param   column      the column to match
     * @param   key         the key to match with
     * @return              a collection with mapped objects
     */
    public Collection<AnyType> getCollectionUsingForeignKey(String column, Object key) {
        Collection<AnyType> collection = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            statement = connection.prepareStatement(String.format(this.sqlSelectId, this.getTableName(), column));
            statement.setObject(1, key);
            result = statement.executeQuery();

            collection = this.map(result);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (result != null) {
                try {
                    result.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return collection;
    }

    /**
     * Returns a collection of objects with the primaryKey
     * @param   primaryKey      The primary key
     * @return                  a collection with generic objects
     */
    public Collection<AnyType> getCollectionUsingPrimaryKey(int primaryKey) {
        return this.getCollectionUsingForeignKey(this.primaryKeyFieldName, primaryKey);
    }

    /**
     * Returns a single instance of the mapped object using the
     * foreign key
     * @param   column  the column to match
     * @param   key     the key to get
     * @return          instance of the mapped object
     */
    public AnyType getUsingForeignKey(String column, Object key) {
        return ((ArrayList<AnyType>)this.getCollectionUsingForeignKey(column, key)).get(0);
    }

    /**
     * Returns a single instance of the mapped object using the
     * primary key
     * @param   key     the key to get
     * @return          instance of the mapped object
     */
    public AnyType getUsingPrimaryKey(int key) {
        return ((ArrayList<AnyType>)this.getCollectionUsingPrimaryKey(key)).get(0);
    }

    /**
     * @see Dapper#getUsingPrimaryKey(int) this is only here for legacy purposes
     * @param   primaryKey  the id of the primary key
     * @return              the mapped object
     */
    @Deprecated
    public AnyType getId(int primaryKey) {
        return this.getUsingPrimaryKey(primaryKey);
    }

    /**
     * Returns a collection of objects using a specific query
     * @param   query   The query to the database
     * @return          A collection of objects of type AnyType
     */
    public Collection<AnyType> query(String query) {
        PreparedStatement statement = null;
        Collection<AnyType> collection = new ArrayList<>();

        try {
            statement = connection.prepareStatement(query);
            ResultSet result = statement.executeQuery();

            collection = this.map(result);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return collection;
    }


    /**
     * Retrieves an array with objects using default values. These values
     * are {@link Dapper#primaryKeyFieldName} ASCENDING with no limit
     * @return          ArrayList with generic objects
     */
    public Collection<AnyType> getCollection() {
        return this.getCollection(0, this.primaryKeyFieldName, Sort.ASC);
    }


    /**
     * Retrieves an array with objects using default values with an limit.
     * @see             Dapper#getCollection() for default values
     * @param limit     Amount of results to retrieve
     * @return          ArrayList with generic objects
     */
    public Collection<AnyType> getCollection(int limit) {
        return this.getCollection(limit, this.primaryKeyFieldName, Sort.ASC);
    }


    /**
     * Retrieves an array with objects with given column and order. There
     * is no limit set
     * @param column    Name of the column to retrieve. Must be an valid
     *                  AnyType property.
     * @param order     The order to retrieve values in. Can be "ASC" or "DESC"
     * @param limit     Amount of results to retrieve
     * @return          Collection with generic objects
     */
    public Collection<AnyType> getCollection(int limit, String column, String order) {
        Collection<AnyType> collection = new ArrayList<>();

        PreparedStatement statement = null;
        ResultSet result = null;

        try {

            // If no limit is set, retrieve all rows
            if (limit == 0)
                statement = connection.prepareStatement(String.format("SELECT * FROM %s ORDER BY %s %s", this.getTableName(), column, order));
            else
            {
                statement = connection.prepareStatement(String.format("SELECT * FROM %s ORDER BY %s %s LIMIT ?", this.getTableName(), column, order));
                statement.setInt(1, limit);
            }

            result = statement.executeQuery();
            collection = this.map(result);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (result != null) {
                try {
                    result.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return collection;
    }


    /**
     * Retrieves an array with objects with given column and order. There
     * is no limit set
     * @param column    Name of the column to retrieve. Must be an valid
     *                  AnyType property.
     * @param order     The order to retrieve values in. Can be "ASC" or "DESC"
     * @return          ArrayList with generic objects
     */
    public Collection<AnyType> getCollection(String column, String order) {
        return this.getCollection(0, column, order);
    }

}
