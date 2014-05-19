package casino;

import casino.events.MenuEvent;

/**
 * @author  Dino Opijac
 * @since   18/05/2014
 */
public interface MenuListener extends Listener {
    public void menuItemChanged(MenuEvent e);
}
