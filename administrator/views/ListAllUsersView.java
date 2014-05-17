package administrator.views;

import administrator.controllers.Controller;

import javax.swing.*;

/**
 * Created by Rickard Zrinski on 2014-05-16.
 */
public class ListAllUsersView extends View
{
    public ListAllUsersView()
    {
        this.add(new JButton("List all users!"));
    }

    public void registerListener(Controller controller)
    {

    }
}
