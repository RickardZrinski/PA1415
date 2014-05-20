package casino;

import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * @author  Dino Opijac
 * @since   18/05/14
 */
public abstract class AbstractModel {
    private ArrayList<Object> observers = new ArrayList<>();
    /**
     * Subscribes a new subject to the observer
     * @param subject the subject that wishes to subscribe
     */
    public void subscribe(Object subject) {
        this.observers.add(subject);
    }

    /**
     * Unsubscribes a new subject from the observer
     * @param subject the subject that wishes to unsubscribe
     */
    public void unsubscribe(Object subject) {
        this.observers.remove(subject);
    }

    /**
     * Invokes a method inside a list of observers
     * @param name  the name of the event
     * @param event the event itself
     */
    public void notify(Object name, Object event) {
        for (Object listener: this.observers) {
            try {
                // Call the method with the event
                Method method = listener.getClass().getMethod((String) name, new Class[]{event.getClass()});
                method.invoke(listener, event);
            } catch (Exception ex) {
                System.out.println(String.format("notify: %s not found in %s. Attempted to send:\n%s", name, listener.getClass().getName(), event));
            }
        }
    }
}
