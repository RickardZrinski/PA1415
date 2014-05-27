package administrator.controllers;

import administrator.GUI;
import administrator.models.UsersModel;
import administrator.views.ListAllGamesView;
import administrator.views.ListAllUsersView;
import shared.users.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Rickard Zrinski on 2014-05-15.
 */
public class ListAllUsersController extends Controller
{
    private UsersModel m_usersModel;
    private ListAllUsersView m_view;

    public ListAllUsersController(GUI gui, UsersModel usersModel)
    {
        super(gui);

        m_usersModel = usersModel;

        listAllUsers();
    }

    public void listAllUsers()
    {
        // Create view and set controller
        m_view = new ListAllUsersView();
        m_view.setController(this);

        // Add view to GUI and show view
        this.getGui().addView(m_view, "ListAllUsersView");
        this.getGui().showView("ListAllUsersView", "Administrator - List all users");

        // Add rows to view
        for(int i = 0; i < m_usersModel.getNrOfUsers(); i++)
        {
            User user = m_usersModel.getUser(i);

            m_view.addRow(user.getUsername(), user.getFirstName(), user.getLastName());
        }
    }

    public void editUser(int index)
    {
        new EditUserController(this.getGui(), m_usersModel, index);
    }
}
