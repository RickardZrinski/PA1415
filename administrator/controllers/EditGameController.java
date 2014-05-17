package administrator.controllers;

import administrator.views.EditGameView;
import administrator.GUI;

import java.awt.event.ActionEvent;

/**
 * Created by Rickard Zrinski on 2014-05-17.
 */
public class EditGameController extends Controller
{
    private EditGameView m_view;
    private int m_gameIndex;

    public EditGameController(GUI gui, /*GamesModel gamesModel,*/ int gameIndex)
    {
        super(gui);

        m_gameIndex = gameIndex;

        showGameData();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {

    }

    private void showGameData()
    {
        m_view = new EditGameView();
        m_view.registerListener(this);

        this.getGui().addView(m_view, "EditGameView");
        this.getGui().showView("EditGameView");
    }
}
