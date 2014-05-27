package administrator.views;

import administrator.controllers.ListAllUsersController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Rickard Zrinski on 2014-05-16.
 */

public class ListAllUsersView extends View
{
    private JTable m_userTable;
    private JScrollPane m_userTablePane;
    private UserTableModel m_userTableModel;
    private ListAllUsersController m_controller;
    
    private class UserTableModel extends DefaultTableModel
    {
        @Override
        public boolean isCellEditable(int row, int column)
        {
            return false;
        }
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
        m_userTableModel.addColumn("Username");
        m_userTableModel.addColumn("Firstname");
        m_userTableModel.addColumn("Surname");
        m_userTableModel.addColumn("Edit");

        // Set some attributes
        m_userTable.getColumnModel().getColumn(1).setMaxWidth(70);
        m_userTable.getTableHeader().setReorderingAllowed(false);

        // Add table to a Scroll Pane
        m_userTablePane.setViewportView(m_userTable);

        // Add listener to table
        m_userTable.addMouseListener(new EditUserListener());
    }

    private void addComponents()
    {
        // Add Scroll Pane
        this.add(m_userTablePane);
    }

    public void addRow(String userName, String firstName, String lastName)
    {
        // Construct row
        Object[] row = new Object[] {userName, firstName, lastName, "Edit"};

        // Add row to table model
        m_userTableModel.addRow(row);
    }

    public void setController(ListAllUsersController controller)
    {
        m_controller = controller;
    }

    private class EditUserListener implements MouseListener
    {
        @Override
        public void mouseClicked(MouseEvent e)
        {
            int selectedCol = m_userTable.getSelectedColumn();
            int selectedRow = m_userTable.getSelectedRow();

            if(selectedCol == 3)
            {
                m_controller.editUser(selectedRow);
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
}
