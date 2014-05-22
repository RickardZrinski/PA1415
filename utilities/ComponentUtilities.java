package utilities;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;

/**
 * @author  Dino Opijac
 * @since   19/05/2014
 */
public class ComponentUtilities {
    /**
     * Toggles a component from a visible state to a hidden state.
     * @param component the component to toggle
     */
    public static void toggleComponent(Component component) {
        if (component.isVisible())
            component.setVisible(false);
        else
            component.setVisible(true);
    }

    /**
     * Changes only the width of the component, leaves the height to the preferredSize.
     * @param width     the new width
     * @param component the component to operate on
     */
    public static void setPreferredWidth(int width, Component component) {
        Dimension size = component.getPreferredSize();
        component.setPreferredSize(new Dimension(width, (int) size.getHeight()));
    }

    /**
     * Changes only the height of the component, leaves the width to the preferredSize.
     * @param height    the new width
     * @param component the component to operate on
     */
    public static void setPreferredHeight(int height, Component component) {
        Dimension size = component.getPreferredSize();
        component.setPreferredSize(new Dimension((int) size.getWidth(), height));
    }

    /**
     * Changes only the width of the component, leaves the height to the size.
     * @param width     the new width
     * @param component the component to operate on
     */
    public static void setWidth(int width, Component component) {
        Dimension size = component.getSize();
        component.setSize(width, (int)size.getHeight());
    }

    /**
     * Changes only the height of the component, leaves the width to the size.
     * @param height    the new width
     * @param component the component to operate on
     */
    public static void setHeight(int height, Component component) {
        Dimension size = component.getSize();
        component.setSize((int)size.getWidth(), height);
    }

    /**
     * Creates a number format for JFormattedTextField
     * @param string    the format to use
     * @return          a MaskFormatter format
     */
    public static MaskFormatter createMaskFormat(String string) {
        MaskFormatter formatter = null;

        try {
            formatter = new MaskFormatter(string);
        } catch(Exception e) {
            System.out.println("Bad format");
        }

        return formatter;
    }

    /**
     * Prints the component size
     * @param component the component to print the sizes from
     */
    public static void printDimensions(Component component) {
        Dimension size = component.getSize();
        Dimension pref = component.getPreferredSize();
        Dimension max  = component.getMaximumSize();

        System.out.println( String.format("%s: size(%d, %d), preferred(%d, %d), maximum(%d, %d)",
                component.getClass().getSimpleName(),
                (int)size.getWidth(), (int)size.getHeight(), (int)pref.getWidth(),
                (int)pref.getHeight(), (int)max.getWidth(), (int)max.getHeight())
        );
    }

    /**
     * Groups together multiple components using FlowLayout
     * @param   align           how to align the buttons
     * @param   components      a collection of components to group
     * @return                  a JPanel with the components
     */
    public static JPanel group(int align, Component... components) {
        JPanel group = new JPanel(new FlowLayout(align, 0, 0));

        for(Component component: components) {
            group.add(component);
        }

        return group;
    }
}
