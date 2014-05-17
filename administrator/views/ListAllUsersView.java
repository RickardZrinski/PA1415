package administrator.views;

import administrator.controllers.Controller;
import administrator.controllers.ListAllGamesController;
import administrator.controllers.ListAllUsersController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * Created by Rickard Zrinski on 2014-05-16.
 */

public class ListAllUsersView extends View
{
    private class UserTableModel extends DefaultTableModel
    {
        @Override
        public boolean isCellEditable(int row, int column)
        {
            return false;
        }
    }

    public void registerListener(ListAllUsersController controller)
    {

    }

    public ListAllUsersView()
    {
        initialize();
        configureTable();
        addComponents();
    }

    private void initialize()
    {
       m_userTable = new JTable();
       m_userTablePane = new JScrollPane();
       m_userTableModel = new UserTableModel();
    }

    private void configureTable()
    {
        // Set model
        m_userTable.setModel(m_userTableModel);

        // Add columns

        m_userTableModel.addColumn("Number");
        m_userTableModel.addColumn("Username");
        m_userTableModel.addColumn("Firstname");
        m_userTableModel.addColumn("Surname");
        m_userTableModel.addColumn("Edit");


        // Set some attributes
        m_userTable.getColumnModel().getColumn(1).setMaxWidth(70);
        m_userTable.getTableHeader().setReorderingAllowed(false);

        // Add table to a Scroll Pane
        m_userTablePane.setViewportView(m_userTable);
    }

    private void addComponents()
    {
        // Add Scroll Pane
        this.add(m_userTablePane);
    }

    public void registerListener(ListAllGamesController controller)
    {
        m_userTable.addMouseListener(controller);
    }

    public void addRow(String title)
    {
        // Construct row
        Object[] row = new Object[] {title, "Edit"};

        // Add row to table model
        m_userTableModel.addRow(row);
    }



    private JTable m_userTable;
    private JScrollPane m_userTablePane;
    private UserTableModel m_userTableModel;
}
