package administrator.views;

import administrator.controllers.ListAllGamesController;
import administrator.events.ListAllGamesListener;
import shared.game.GameData;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

/**
 * Created by Rickard Zrinski on 2014-05-16.
 */
public class ListAllGamesView extends View implements ListAllGamesListener
{
    // Table of games
    private JTable m_gameTable;
    private JScrollPane m_gameTablePane;
    private GameTableModel m_gameTableModel;

    // A custom table model for the table of games
    private class GameTableModel extends DefaultTableModel
    {
        @Override
        public boolean isCellEditable(int row, int column)
        {
            return false;
        }
    }

    public ListAllGamesView()
    {
        initialize();
        configureTable();
        addComponents();
    }

    private void initialize()
    {
        m_gameTable = new JTable();
        m_gameTablePane = new JScrollPane();
        m_gameTableModel = new GameTableModel();
    }

    private void configureTable()
    {
        // Set model
        m_gameTable.setModel(m_gameTableModel);

        // Add columns
        m_gameTableModel.addColumn("Game Title");
        m_gameTableModel.addColumn("Edit");

        // Set some attributes
        m_gameTable.getColumnModel().getColumn(1).setMaxWidth(70);
        m_gameTable.getTableHeader().setReorderingAllowed(false);

        // Add table to a Scroll Pane
        m_gameTablePane.setViewportView(m_gameTable);
    }

    private void addComponents()
    {
        // Add Scroll Pane
        this.add(m_gameTablePane);
    }

    public void registerListener(ListAllGamesController controller)
    {
        m_gameTable.addMouseListener(controller);
    }

    public void addRow(String title)
    {
        // Construct row
        Object[] row = new Object[] {title, "Edit"};

        // Add row to table model
        m_gameTableModel.addRow(row);
    }

    @Override
    public void listAllGamesResponse(ArrayList<GameData> gameData)
    {
        this.clearTable();

        for(GameData game: gameData)
        {
            this.addRow(game.getGameName());
        }
    }

    private void clearTable()
    {
        m_gameTableModel.setRowCount(0);
    }
}
