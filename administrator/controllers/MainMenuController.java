package administrator.controllers;

import administrator.GUI;
import administrator.models.GamesModel;
import administrator.models.UsersModel;
import administrator.views.MainMenuView;

import java.awt.event.ActionEvent;

/**
 * Created by Rickard Zrinski on 2014-05-17.
 */
public class MainMenuController extends Controller
{
    private UsersModel m_usersModel;
    private GamesModel m_gamesModel;
    private MainMenuView m_view;

    public MainMenuController(GUI gui, UsersModel usersModel, GamesModel gamesModel)
    {
        super(gui);

        m_usersModel = usersModel;
        m_gamesModel = gamesModel;

        displayMenu();
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
