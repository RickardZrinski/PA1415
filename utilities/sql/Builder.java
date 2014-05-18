package utilities.sql;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @author  Dino Opijac
 * @since   17/05/2014
 */
public class Builder {
    /**
     * Generates a custom equals string. Every odd argument represents the column name, every
     * even argument sets the value.
     *
     * @param   separator   the separator for each column/value pair
     * @param   objects     objects to add as column names
     * @return              a partial string for the query
     */
    public static String equals(String separator, Object... objects) {
        int num = 0;

        StringBuilder builder = new StringBuilder();

        for(Object o: objects) {
            if ((num++ & 1) == 0)
                builder.append(String.format("%s = ", o));
            else
                builder.append(String.format("?%s", separator));
        }

        return builder.substring(0, builder.toString().length() - separator.length());
    }

    /**
     * Generates a custom equals string. Where every object represents a column name.
     * @param   separator   the separator for each column
     * @param   objects     objects to add as column names
     * @return              a partial string for the query
     */
    public static String linear(String separator, Object... objects) {
        StringBuilder builder = new StringBuilder();

        for(Object o: objects) {
            if (o instanceof Field)
                builder.append(((Field) o).getName());
            else
                builder.append(o);

            builder.append(" = ?");
            builder.append(separator);
        }

        return builder.substring(0, builder.toString().length() - separator.length());
    }

    /**
     * Generates a custom equals string with AND as separator.
     * Often used with DELETE keyword
     * @param   objects     arguments to add to the query
     * @return              a string of the query
     */
    public static String and(Object... objects) {
        return Builder.equals(" AND ", objects);
    }

    /**
     * Generates a custom equals string with a comma as separator.
     * Often used with UPDATE or SET keyword(s)
     * @param   objects     arguments to add to the query
     * @return              a string of the query
     */
    public static String comma(Object... objects) {
        return Builder.equals(", ", objects);
    }

    /**
     * Returns a collection of value objects. Every odd argument represents the column name, every
     * even argument sets the value.
     *
     * @param   objects     arguments to add to the query
     * @return              a collection of values
     */
    public static Map<Object, Object> computeObjectMap(Object... objects) {
        int num = 0;

        Vector<Object> columns = new Vector<>();
        Vector<Object> values  = new Vector<>();

        Hashtable<Object, Object> table = new Hashtable<>();

        for(Object o: objects)
            if ((num++ & 1) == 0)
                columns.add(o);
            else
                values.add(o);

        for (int i = 0; i < columns.size(); i++)
            table.put(columns.get(i), values.get(i));

        return table;
    }
}
