package administrator.views;

import administrator.controllers.Controller;

import javax.swing.*;

/**
 * Created by Rickard Zrinski on 2014-05-16.
 */
public class ListAllGamesView extends View
{
    public ListAllGamesView()
    {
        this.add(new JButton("List all games!"));
    }

    public void registerListener(Controller controller)
    {

    }
}
