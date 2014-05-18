package administrator.views;

import administrator.controllers.MainMenuController;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Rickard Zrinski on 2014-05-17.
 */
public class MainMenuView extends View
{
    private GridLayout m_gridLayout;
    private JPanel m_menu;
    private ArrayList<JButton> m_menuItems;

    public MainMenuView()
    {
        initialize();
        configure();
        addComponents();
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

    private void addMenuItems()
    {
        for(JButton item: m_menuItems)
        {
            m_menu.add(item);
        }
    }

    private static JButton createMenuItem(String text)
    {
        JButton button = new JButton(text);

        button.setFocusPainted(false);
        button.setBackground(Color.WHITE);
        Border border = new LineBorder(Color.BLACK);
        Border margin = new EmptyBorder(5, 15, 5, 15);
        Border compound = new CompoundBorder(border, margin);
        button.setBorder(compound);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        return button;
    }

    public void registerListener(MainMenuController controller)
    {
        for(JButton item: m_menuItems)
        {
            item.addActionListener(controller);
        }
    }
}
