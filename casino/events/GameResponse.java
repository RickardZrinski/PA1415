package casino.events;

import shared.game.Die;

/**
 * @author  John Mogensen
 * @since   26/05/2014
 */
public interface GameResponse {
    public void displayRules(String rules);
    public void betSuccessful();
    public void betUnSuccessful();
    public void updateDie(int index, Die die);
    public void updateNumberOfThrows(int numberOfThrows);
    public void displayResult(String result);

}
