package administrator.controllers;

import administrator.GUI;
import administrator.models.GamesModel;
import administrator.views.ListAllGamesView;
import shared.game.GameData;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Martin on 2014-05-15.
 */
public class ListAllGamesController extends Controller
{
    private GamesModel m_gamesModel;
    private ListAllGamesView m_view;

    public ListAllGamesController(GUI gui, GamesModel gamesModel)
    {
        super(gui);

        m_gamesModel = gamesModel;

        listAllGames();
    }

    public void listAllGames()
    {
        m_view = new ListAllGamesView();
        m_view.setController(this);

        this.getGui().addView(m_view, "ListAllGamesView");
        this.getGui().showView("ListAllGamesView", "Administrator - List all games");

        // Request model to send all games to view
        for(int i = 0; i < m_gamesModel.getNrOfGames(); i++)
        {
            GameData gameData = m_gamesModel.getGame(i);

            m_view.addRow(gameData.getGameName());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {

    }
}
