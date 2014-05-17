package administrator.views;

import administrator.controllers.Controller;

import javax.swing.*;

/**
 * Created by Rickard Zrinski on 2014-05-16.
 */
public abstract class View extends JPanel
{
    public View()
    {

    }

    public abstract void registerListener(Controller controller);
}
