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

        this.getGui().addView(m_view, "EditGameView");
        this.getGui().showView("EditGameView", "Administrator - Edit game");
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {

    }
}
