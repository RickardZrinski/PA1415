package administrator.controllers;

import administrator.GUI;
import administrator.models.GamesModel;
import administrator.views.AddGameView;

import java.awt.event.ActionEvent;

/**
 * Created by Rickard Zrinski on 2014-05-18.
 */
public class AddGameController extends Controller
{
    private GamesModel m_gamesModel;
    private AddGameView m_view;

    public AddGameController(GUI gui, GamesModel gamesModel)
    {
        super(gui);

        m_gamesModel = gamesModel;

        initialize();
        displayView();
    }

    private void initialize()
    {
        m_view = new AddGameView();
    }

    private void displayView()
    {
        m_view.registerListener(this);

        this.getGui().addView(m_view, "AddGameView");
        this.getGui().showView("AddGameView", "Administrator - Add game");
    }

    private void addGame(String title)
    {
        m_gamesModel.addGame(title);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        String action = e.getActionCommand();

        switch(action)
        {
            case "Save":
                addGame(m_view.getTitle());
                break;
        }
    }
}
