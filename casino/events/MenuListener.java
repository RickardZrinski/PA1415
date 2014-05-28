package casino.events;

/**
 * @author  Dino Opijac
 * @since   18/05/2014
 */
public interface MenuListener {
    public void gameAction();
    public void depositAction();
    public void withdrawAction();
    public void messageAction();
    public void signOutAction();
}
