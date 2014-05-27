package administrator.views;

import administrator.controllers.AddGameController;
import administrator.controllers.ListAllGamesController;
import administrator.controllers.MainMenuController;
import administrator.utilities.gui.DefaultButton;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Rickard Zrinski on 2014-05-17.
 */
public class MainMenuView extends View
{
    private GridLayout m_gridLayout;
    private JPanel m_menu;
    private ArrayList<JButton> m_menuItems;
    private MainMenuController m_controller;

    public MainMenuView()
    {
        initialize();
        configure();
        addComponents();
        configureComponents();
    }

    private void initialize()
    {
        m_gridLayout = new GridLayout(1, 3, 40, 40);
        m_menu = new JPanel();
        m_menuItems = new ArrayList<JButton>();

        initMenuItems();
    }

    private void initMenuItems()
    {
        JButton btn;

        btn = this.createMenuItem("List all users");
        btn.setActionCommand("ListAllUsers");
        m_menuItems.add(btn);

        btn = this.createMenuItem("List all games");
        btn.setActionCommand("ListAllGames");
        m_menuItems.add(btn);

        btn = this.createMenuItem("Add a new game");
        btn.setActionCommand("AddGame");
        m_menuItems.add(btn);
    }

    private void configure()
    {
        m_menu.setLayout(m_gridLayout);
    }

    private void addComponents()
    {
        this.addMenuItems();

        this.add(m_menu);
    }

    private void configureComponents()
    {
        MenuItemListener listener = new MenuItemListener();
        for(JButton item: m_menuItems)
        {
            item.addActionListener(listener);
        }
    }

    private void addMenuItems()
    {
        for(JButton item: m_menuItems)
        {
            m_menu.add(item);
        }
    }

    private static JButton createMenuItem(String text)
    {
        DefaultButton button = new DefaultButton(text);

        return button;
    }

    public void setController(MainMenuController controller)
    {
        m_controller = controller;
    }

    private class MenuItemListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            String menuItem = e.getActionCommand();

            switch(menuItem)
            {
                case "ListAllGames":
                    m_controller.showListAllGames();
                    break;
                case "AddGame":
                    m_controller.showAddGame();
                    break;
                case "ListAllUsers":
                    m_controller.showListAllUsers();
                    break;
            }
        }
    }
}
