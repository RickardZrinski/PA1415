package administrator.controllers;

import administrator.GUI;
import administrator.models.GamesModel;
import administrator.models.UsersModel;
import administrator.views.MainMenuView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Rickard Zrinski on 2014-05-17.
 */
public class MainMenuController extends Controller
{
    private UsersModel m_usersModel;
    private GamesModel m_gamesModel;
    private MainMenuView m_view;

    // A listener for the our "default" Menu Bar
    private class MenuBarListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            String item = e.getActionCommand();

            switch(item)
            {
                case "Main Menu":
                    getGui().showView("MainMenuView");
                    break;
            }
        }
    }

    public MainMenuController(GUI gui, UsersModel usersModel, GamesModel gamesModel)
    {
        super(gui);

        m_usersModel = usersModel;
        m_gamesModel = gamesModel;

        makeMenuBar();
        displayMenu();
    }

    private void makeMenuBar()
    {
        GUI gui = this.getGui();

        // Create menu bar
        JMenuBar menuBar = new JMenuBar();

        // Create main menu item and add listener
        JMenuItem mainMenu = new JMenuItem("Main Menu");
        mainMenu.addActionListener(new MenuBarListener());
        mainMenu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Add main menu to menu bar
        menuBar.add(mainMenu);

        gui.setJMenuBar(menuBar);
    }

    private void displayMenu()
    {
        m_view = new MainMenuView();
        m_view.registerListener(this);

        getGui().addView(m_view, "MainMenuView");
        getGui().showView("MainMenuView");
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        String menuItem = e.getActionCommand();

        switch(menuItem)
        {
            case "ListAllGames":
                new ListAllGamesController(this.getGui());
                break;
        }
    }
}