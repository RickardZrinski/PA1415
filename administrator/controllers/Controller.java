package administrator.controllers;

import administrator.GUI;

/**
 * Created by Martin on 2014-05-15.
 */
public class Controller
{
    private GUI m_gui;

    public Controller(GUI gui)
    {
        m_gui = gui;
    }

    public GUI getGui()
    {
        return m_gui;
    }
}
