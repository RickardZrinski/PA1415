package casino.events;

import casino.Event;

/**
 * @author  Dino Opijac
 * @since   18/05/2014
 */
public class MenuEvent implements Event {
    private Object deselected;
    private Object selected;

    public MenuEvent() {
        this.deselected = -1;
        this.selected = -1;
    }
    public MenuEvent(Object itemFrom, Object itemTo) {
        this.deselected = itemFrom;
        this.selected = itemTo;
    }

    public Object getDeselected() {
        return this.deselected;
    }

    public Object getSelected() {
        return this.selected;
    }

    public void setDeselected(Object deselected) {
        this.deselected = deselected;
    }

    public void setSelected(Object selected) {
        this.selected = selected;
    }

    @Override
    public String toString() {
        return String.format("selected: %s, deselected: %s", this.deselected, this.selected);
    }
}
