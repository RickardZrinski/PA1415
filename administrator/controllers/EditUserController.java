package administrator.controllers;

import administrator.GUI;
import administrator.models.UsersModel;
import administrator.views.EditUserView;
import shared.users.User;

import java.awt.event.ActionEvent;

/**
 * Created by Rickard Zrinski on 2014-05-27.
 */
public class EditUserController extends Controller
{
    private EditUserView m_view;
    private UsersModel m_usersModel;
    private int m_userIndex;

    public EditUserController(GUI gui, UsersModel usersModel, int userIndex)
    {
        super(gui);

        m_usersModel = usersModel;
        m_userIndex = userIndex;

        showUserData();
    }

    private void showUserData()
    {
        m_view = new EditUserView();
        m_view.setController(this);

        User user = m_usersModel.getUser(m_userIndex);
        m_view.setUserName(user.getUsername());
        m_view.setFirstName(user.getFirstName());
        m_view.setLastName(user.getLastName());
        m_view.setPassword(user.getPassword());

        this.getGui().addView(m_view, "EditGameView");
        this.getGui().showView("EditGameView", "Administrator - Edit a user");
    }

    public void editUser(String userName, String firstName, String lastName, String password)
    {
        if(userName != "" && firstName != "" && lastName != "" && password != "")
        {
            m_usersModel.editUser(m_userIndex, userName, password, firstName, lastName);
        }
    }
}
