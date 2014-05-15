package utilities;

import javax.swing.*;
import java.lang.reflect.Field;
import java.util.*;

/**
 * Transfers data from one object to another via commonly used Swing components such
 * as JComboBox, JTextField, JTextArea and JPasswordField.
 *
 * One can also add custom components by using {@link Form#addMatchType(String)}. These
 * are normally components that are inherited from JTextField or any other mentioned
 * above (MyCustomTextField extends JTextField etc.).
 *
 * @author  Dino Opijac
 * @since   14/05/14
 */
public class Form {
    IForm form;

    /**
     * Contains all swing components to match with the
     * object
     */
    HashSet<String> matchTypes = new HashSet<>();

    /**
     * Default constructor
     * @param form The form to retrieve all data from
     */
    public Form(IForm form) {
        this.form = form;

        this.addMatchType("JComboBox");
        this.addMatchType("JTextField");
        this.addMatchType("JTextArea");
        this.addMatchType("JPasswordField");
    }

    /**
     * Fill a object with values from the form
     * @param obj The object to fill
     * @return The modified object
     */
    public Object fill(Object obj) {
        // Make both fields accessible so we can reach them
        // weather or not they're private or protected
        Map<String, Object> theForm = this.map(this.getFields(this.form));

        // Iterate through each field in the object and check against values inside the form
        for(Field field: this.getFields(obj)) {
            String type = this.getType(field);
            String attribute = this.getAttributeName(field);
            Object value = theForm.get(attribute);

            if (theForm.containsKey(attribute) && !value.toString().isEmpty()) {
                field.setAccessible(true);

                try {
                    switch(type) {
                        case "int":     field.set(obj, Integer.parseInt((String)value)); break;
                        case "float":   field.set(obj, Float.parseFloat((String)value)); break;
                        case "double":  field.set(obj, Double.parseDouble((String)value)); break;
                        case "char":    field.set(obj, ((String) value).charAt(0)); break;
                        default:        field.set(obj, value); break;
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        return obj;
    }

    /**
     * Adds support for different type
     * @param type The type of object to match, for example "JTextField" or "JTextArea"
     */
    public void addMatchType(String type) {
        this.matchTypes.add(type);
    }

    /**
     * Returns all types that are matchable
     * @return A binary search tree with all matching types
     */
    public HashSet<String> getMatchTypes() {
        return this.matchTypes;
    }

    /**
     * Retrieves all declared attributes (or "fields") in a given object
     * @param obj The object to retrieve the declared attributes from
     * @return A collection of attributes
     */
    private Field[] getFields(Object obj) {
        return obj.getClass().getDeclaredFields();
    }

    /**
     * Retrieves the what type an attribute ("field") has, this could be
     * JTextField, JComboBox or any other object
     * @param field The field to retrieve the type from
     * @return A string representation of the type
     */
    private String getType(Field field) {
        return field.getType().getSimpleName();
    }

    /**
     * Retrieves a attribute name from a field.
     * @param field The field to retrieve the attribute name from
     * @return A string representation of the attribute name
     */
    private String getAttributeName(Field field) {
        return field.getName();
    }

    /**
     * Maps given attributes (or "fields") to a hash map. Hashes increase the
     * performance For objects with many attributes since hashes have a O(1)
     * complexity
     * @param fields The fields to map
     * @return A hash map with the field name and its value
     */
    private Map<String, Object> map(Field[] fields) {
        HashMap<String, Object> map = new HashMap<>();

        for(Field field: fields) {
            String type = this.getType(field);

            // Check if this field is observed and add it
            if (this.matchTypes.contains(type)) {
                field.setAccessible(true);
                map.put(field.getName(), this.getValue(field));
            }
        }

        return map;
    }

    /**
     * Retrieves a value for a type. Some types such as
     * "JComboBox" and "JPasswordField" have different methods
     * of retrieving values, these must be caught and properly
     * dispatched.
     * @param field The field to retrieve the value from
     * @return A Object containing the value of the field
     */
    private Object getValue(Field field) {
        Object ret = null;

        try {
            Object object = field.get(this.form);

            switch(this.getType(field)) {
                case "JComboBox":
                    ret = ((JComboBox<?>)object).getSelectedItem();
                    break;

                case "JTextField":
                    ret = ((JTextField)object).getText();
                    break;

                case "JTextArea":
                    ret = ((JTextArea)object).getText();
                    break;

                case "JPasswordField":
                    ret = String.valueOf(((JPasswordField) object).getPassword());
                    break;
            }

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return ret;
    }
}
