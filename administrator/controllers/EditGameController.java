package administrator.controllers;

import administrator.models.GamesModel;
import administrator.views.EditGameView;
import administrator.GUI;
import shared.game.Combination;
import shared.game.GameData;
import shared.game.WinningCondition;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

/**
 * Created by Rickard Zrinski on 2014-05-17.
 */
public class EditGameController extends Controller
{
    private EditGameView m_view;
    private int m_gameIndex;
    private GamesModel m_gamesModel;

    public EditGameController(GUI gui, GamesModel gamesModel, int gameIndex)
    {
        super(gui);

        m_gameIndex = gameIndex;
        m_gamesModel = gamesModel;

        showGameData();
    }

    private void showGameData()
    {
        m_view = new EditGameView();
        m_view.setController(this);

        GameData game = m_gamesModel.getGame(m_gameIndex);

        m_view.setTitle(game.getGameName());
        m_view.setNrOfThrows(game.getNumberOfThrows());
        m_view.setNrOfDices(game.getNumberOfDice());

        // Add already existing winning conditions
        int nrOfWinningConditions = game.getNumberOfWinningConditions();
        for(int i = 0; i < nrOfWinningConditions; i++)
        {
            m_view.addWinningCondition(game.getWinningCondition(i));
        }

        this.getGui().addView(m_view, "EditGameView");
        this.getGui().showView("EditGameView", "Administrator - Edit game");
    }

    public void editGame(int nrOfThrows, int nrOfDices,
                         ArrayList<WinningCondition> winningConditions)
    {
        m_gamesModel.sendEditedParameters(m_gameIndex, nrOfThrows, nrOfDices, winningConditions);
    }
}
