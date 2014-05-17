package utilities.sql;

import java.util.*;

/**
 * @author  Dino Opijac
 * @since   17/05/2014
 */
public class Builder {

    /**
     * Generates a custom delete String. Every odd argument represents the column name, every
     * even argument sets the value.
     *
     * @param   objects     arguments to add to the query
     * @return              a string of the query
     */
    public static String equals(Object... objects) {
        int num = 0;

        StringBuilder builder = new StringBuilder();

        for(Object arg: objects) {
            if ((num++ & 1) == 0)
                builder.append(String.format("%s = ", arg));
            else
                builder.append("? AND ");
        }

        return builder.substring(0, builder.toString().length() - 5) + ";";
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
