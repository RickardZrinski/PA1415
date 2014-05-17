package administrator.controllers;

import administrator.GUI;

import java.awt.event.ActionListener;

/**
 * Created by Martin on 2014-05-15.
 */
public abstract class Controller implements ActionListener
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
