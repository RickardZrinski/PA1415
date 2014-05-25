package shared;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author  Dino Opijac
 * @since   18/05/14
 */
public abstract class View<V> extends JPanel {
    private Collection<V> observers = new ArrayList<>();
    /**
     * Subscribes a new subject to the observer
     * @param subject the subject that wishes to subscribe
     */
    public void subscribe(V subject) {
        this.observers.add(subject);
    }

    /**
     * Unsubscribes a new subject from the observer
     * @param subject the subject that wishes to unsubscribe
     */
    public void unsubscribe(V subject) {
        this.observers.remove(subject);
    }

    public Collection<V> getObservers() {
        return this.observers;
    }
}
