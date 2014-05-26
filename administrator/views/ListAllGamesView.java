package administrator.views;

import administrator.controllers.EditGameController;
import administrator.controllers.ListAllGamesController;
import administrator.events.ListAllGamesListener;
import shared.game.GameData;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/**
 * Created by Rickard Zrinski on 2014-05-16.
 */
public class ListAllGamesView extends View
{
    // Table of games
    private JTable m_gameTable;
    private JScrollPane m_gameTablePane;
    private GameTableModel m_gameTableModel;
    private ListAllGamesController m_controller;

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

    public void setController(ListAllGamesController controller)
    {
        m_controller = controller;
    }

    public void addRow(String title)
    {
        // Construct row
        Object[] row = new Object[] {title, "Edit"};

        // Add row to table model
        m_gameTableModel.addRow(row);
    }

    private void clearTable()
    {
        m_gameTableModel.setRowCount(0);
    }

    private class EditGameListener implements MouseListener
    {
        @Override
        public void mouseClicked(MouseEvent e)
        {
            /*Object obj = e.getSource();

            if(obj instanceof JTable)
            {
                JTable table = (JTable)obj;

                int selectedCol = table.getSelectedColumn();
                int selectedRow = table.getSelectedRow();

                if(selectedCol == 1)
                {
                    new EditGameController(this.getGui(), m_gamesModel, selectedRow);
                }
            }*/
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
}
