package utilities;

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
    public static void setWidth(int width, Component component) {
        Dimension size = component.getPreferredSize();
        component.setSize(width, (int)size.getHeight());
    }

    /**
     * Changes only the height of the component, leaves the width to the preferredSize.
     * @param height    the new width
     * @param component the component to operate on
     */
    public static void setHeight(int height, Component component) {
        Dimension size = component.getPreferredSize();
        component.setSize((int)size.getWidth(), height);
    }
}
