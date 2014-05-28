package casino.events;

import shared.game.GameData;

import java.util.Collection;

/**
 * @author  John Mogensen
 * @since   26/05/2014
 */
public interface GameResponse {
    public void displayRules(String rules);
    public void betSuccessful();
    public void betUnSuccessful();
    public void updateDie(int index, int face);
    public void updateNumberOfThrows(int numberOfThrows);
    public void updateNumberOfDice(int numberOfDice);
    public void displayResult(String result);
    public void showAllGames(Collection<GameData> games);
}
