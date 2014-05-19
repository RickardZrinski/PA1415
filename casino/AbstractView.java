package casino;


import javax.swing.*;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * @author  Dino Opijac
 * @since   18/05/14
 */
public abstract class AbstractView<AnyListener> extends JPanel {
    private ArrayList<AnyListener> listeners = new ArrayList<>();

    /**
     * Adds any listener to the view. The listener must implement
     * AnyListener interface.
     * @param listener the listener to add
     */
    public void addListener(AnyListener listener) {
        this.listeners.add(listener);
    }

    /**
     * Removes a listener from the view.
     * @param listener the listener to remove
     */
    public void removeListener(AnyListener listener) {
        this.listeners.remove(listener);
    }

    /**
     * Fires an event inside a method that wishes to be notified
     * @param name  the name of the event
     * @param event the event itself
     */
    public void fireEvent(Object name, Object event) {
        for (AnyListener listener: this.listeners) {
            try {
                // Call the method with the event
                Method method = listener.getClass().getMethod((String) name, new Class[]{event.getClass()});
                method.invoke(listener, event);
            } catch (Exception ex) {
                System.out.println(String.format("fireEvent: %s not found in %s. Attempted to send:\n%s", name, listener.getClass().getName(), event));
            }
        }
    }
}
