package casino.events;

/**
 * @author  Dino Opijac
 * @since   24/05/2014
 */
public interface GameListener {
    public void selectGame(int index);
    public void bet(double amount);
    public void toss();
    public void saveDie(int index);
    public void end();
    public void playAgain();
}
