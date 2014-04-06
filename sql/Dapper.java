package sql;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.ArrayList;

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
public class Dapper<AnyType> {
    /**
     * Fields in objects prefixed with the given value
     * will be ignored by the dapper.
     */
    public final static String FIELD_IGNORE_PREFIX = "_";

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
    private final String sqlUpdate = "UPDATE %s SET %s WHERE id = ?;";
    private final String sqlDelete = "DELETE FROM %s WHERE id = ?;";
    private final String sqlTruncate = "TRUNCATE TABLE %s;";
    private final String sqlDrop = "DROP TABLE IF EXISTS %s;";
    private final String sqlCount = "SELECT COUNT(*) FROM %s;";
    private final String sqlSelect = "SELECT %s FROM %s ORDER BY %s LIMIT %s;";
    private final String sqlSelectId = "SELECT * FROM %s WHERE id = ?;";

    /**
     * Name of the primary key field
     */
    private String primaryKeyFieldName;

    private final Connection connection;
    private final Class<AnyType> tableType;
    private int lastInsertId;


    public Dapper(Class<AnyType> type) {
        this(type, "id", Connector.getConnection());
    }

    public Dapper(Class<AnyType> type, String primaryKeyFieldName) {
        this(type, primaryKeyFieldName, Connector.getConnection());
    }

    public Dapper(Class<AnyType> type, Connection connection) {
        this(type, "id", connection);
    }

    public Dapper(Class<AnyType> type, String primaryKeyFieldName, Connection connection) {
        this.connection = connection;
        this.primaryKeyFieldName = primaryKeyFieldName;
        this.tableType = type;
        this.lastInsertId = -1;

        if (Dapper.PRINT_STATEMENTS)
            System.out.println("Dapper: Statements will be printed");

        if (!Dapper.EXECUTE_STATEMENTS)
            System.out.println("Dapper: Statements will NOT execute.");
    }


    /**
     * Sets the name of the primary key column
     * @param primaryKeyFieldName String representation of the primary key column name
     */
    public void setPrimaryKeyFieldName(String primaryKeyFieldName) {
        this.primaryKeyFieldName = getPrimaryKeyFieldName();
    }

    /**
     * @return String representation of the column
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


        } catch (SQLException e1) {
            e1.printStackTrace();
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
            Field[] fields = this.getInheritedFields();

            String values = "";

            for (Field field: fields) {
                field.setAccessible(true);

                // Skip the primary key
                if (!field.getName().equals(this.primaryKeyFieldName))
                    values += ", " + field.getName() + " = ?";
            }

            values = values.substring(2);

            PreparedStatement statement = connection.prepareStatement(String.format(this.sqlUpdate, this.getTableName(), values), Statement.RETURN_GENERATED_KEYS);

            // Bind all properties to the statement
            for (int i = 0; i < fields.length - 1; i++)
                try {
                    statement.setObject(i+1, fields[i].get(data));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

            // Bind the primary key to the statement
            statement.setObject(fields.length, primaryKey);

            // Execute insertion and generate key
            this.execute(statement, true);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    /**
     * Deletes data in the database using the object class name as
     * table name.
     * @param primaryKey    Primary key used for the deletion
     */
    public void delete(int primaryKey) {
        try {
            PreparedStatement statement = connection.prepareStatement(String.format(this.sqlDelete, this.getTableName()));
            statement.setInt(1, primaryKey);
            this.execute(statement);

            // If the last inserted key is deleted,
            // then reflect this in the dapper
            if (primaryKey == this.getLastInsertId())
                this.lastInsertId = -1;

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
     * Returns the total amount of items in a table
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
     * Fields with the {@link Dapper#FIELD_IGNORE_PREFIX} prefix are ignored!
     * @return      Array of fields
     */
    private Field[] getInheritedFields(Class<?> table) {

        ArrayList<Field> fields = new ArrayList<>();

        for (Class<?> c = table; c != null; c = c.getSuperclass()) {
            for (Field field: c.getDeclaredFields())
                /** Fields with the {@link Dapper#FIELD_IGNORE_PREFIX} prefix are ignored! **/
                if (!field.getName().substring(0, 1).equals(FIELD_IGNORE_PREFIX))
                    fields.add(field);
        }

        return fields.toArray(new Field[fields.size()]);
    }

    private Field[] getInheritedFields() {
        return this.getInheritedFields(this.tableType);
    }


    /**
     * Executes a statement and closes it properly
     * @param statement         The statement to execute.
     * @param generateLastKey   Set to true to generate last insert id
     */
    private void execute(PreparedStatement statement, boolean generateLastKey) {
        try {
            // Attempts to execute the statement
            if (Dapper.EXECUTE_STATEMENTS)
                statement.execute();

            // Print statement
            if (Dapper.PRINT_STATEMENTS)
                System.out.println(statement);

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
     * Retrieves an row from the given table and returns an genric object
     * @param primaryKey    The primary key
     * @return              Generic object
     */
    public AnyType getId(int primaryKey) {
        AnyType type = null;
        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            statement = connection.prepareStatement(String.format(this.sqlSelectId, this.getTableName()));
            statement.setInt(1, primaryKey);

            result = statement.executeQuery();

            if (result.next()) {
                type = this.tableType.getConstructor().newInstance();

                for(Field field: this.getInheritedFields(type.getClass())) {
                    // Set this field to accessible
                    field.setAccessible(true);

                    // Get the object data from the resultset
                    field.set(type, result.getObject(field.getName()));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
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

        return type;
    }


    /**
     * Retrieves an array with objects using default values. These values
     * are {@link Dapper#primaryKeyFieldName} ASCENDING with no limit
     * @return          ArrayList with generic objects
     */
    public ArrayList<AnyType> getList() {
        return this.getList(0, this.getPrimaryKeyFieldName(), Sort.ASC);
    }


    /**
     * Retrieves an array with objects using default values with an limit.
     * @see             Dapper#getList() for default values
     * @param limit     Amount of results to retrieve
     * @return          ArrayList with generic objects
     */
    public ArrayList<AnyType> getList(int limit) {
        return this.getList(limit, this.getPrimaryKeyFieldName(), Sort.ASC);
    }


    /**
     * Retrieves an array with objects with given column and order. There
     * is no limit set
     * @param column    Name of the column to retrieve. Must be an valid
     *                  AnyType property.
     * @param order     The order to retrieve values in. Can be "ASC" or "DESC"
     * @param limit     Amount of results to retrieve
     * @return          ArrayList with generic objects
     */
    public ArrayList<AnyType> getList(int limit, String column, String order) {
        ArrayList<AnyType> list = new ArrayList<>();

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

            while (result.next()) {
                AnyType object = this.tableType.getConstructor().newInstance();

                for(Field field: this.getInheritedFields(object.getClass())) {
                    // Set this field to accessible
                    field.setAccessible(true);

                    // Get the object data from the resultset
                    field.set(object, result.getObject(field.getName()));
                }

                // Add to our array
                list.add(object);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
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

        return list;
    }


    /**
     * Retrieves an array with objects with given column and order. There
     * is no limit set
     * @param column    Name of the column to retrieve. Must be an valid
     *                  AnyType property.
     * @param order     The order to retrieve values in. Can be "ASC" or "DESC"
     * @return          ArrayList with generic objects
     */
    public ArrayList<AnyType> getList(String column, String order) {
        return this.getList(0, column, order);
    }
}
