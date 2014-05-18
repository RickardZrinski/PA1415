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
public class ListAllUsersController extends Controller implements MouseListener
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
        m_view.registerListener(this);

        this.getGui().addView(m_view, "ListAllGamesView");
        this.getGui().showView("ListAllGamesView", "Administrator - List all users");


        // Only for testing, remove later
        m_view.addRow("Test");
        m_view.addRow("Test1");
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {

    }

    public void mouseClicked(MouseEvent e)
    {

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
