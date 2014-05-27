package administrator.controllers;

import administrator.GUI;
import administrator.views.ListAllGamesView;
import administrator.views.ListAllUsersView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Rickard Zrinski on 2014-05-15.
 */
public class ListAllUsersController extends Controller
{
    private ListAllUsersView m_view;

    public ListAllUsersController(GUI gui)
    {
        super(gui);
        listAllUsers();
    }

    public void listAllUsers()
    {
        m_view = new ListAllUsersView();
        m_view.setController(this);

        this.getGui().addView(m_view, "ListAllUsersView");
        this.getGui().showView("ListAllUsersView", "Administrator - List all users");

        // Only for testing, remove later
        m_view.addRow("Admin", "Rickard", "Zrinski");
        m_view.addRow("Test", "Test1", "Test2");
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {

    }

    public void editUser(int index)
    {

    }
}
