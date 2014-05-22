package administrator.controllers;

import administrator.GUI;
import administrator.models.GamesModel;
import administrator.views.ListAllGamesView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Martin on 2014-05-15.
 */
public class ListAllGamesController extends Controller implements MouseListener
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
        m_view.registerListener(this);

        this.getGui().addView(m_view, "ListAllGamesView");
        this.getGui().showView("ListAllGamesView", "Administrator - List all games");

        // Subscribe view to model
        m_gamesModel.subscribe(m_view);

        // Request model to send all games to view
        m_gamesModel.requestAllGames();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {

    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        Object obj = e.getSource();

        if(obj instanceof JTable)
        {
            JTable table = (JTable)obj;

            int selectedCol = table.getSelectedColumn();
            int selectedRow = table.getSelectedRow();

            if(selectedCol == 1)
            {
                new EditGameController(this.getGui(), m_gamesModel, selectedRow);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e)
    {

    }

    @Override
    public void mouseReleased(MouseEvent e)
    {

    }

    @Override
    public void mouseEntered(MouseEvent e)
    {

    }

    @Override
    public void mouseExited(MouseEvent e)
    {

    }
}
