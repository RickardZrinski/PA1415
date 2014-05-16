package administrator;

import administrator.views.View;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Rickard Zrinski on 2014-05-16.
 */
public class GUI extends JFrame
{
    private JPanel m_mainPanel;
    private CardLayout m_layout;
    private Container m_contentPane;

    public GUI()
    {
        this.initialize();
        this.configure();
        this.addComponents();
    }

    private void initialize()
    {
        m_layout = new CardLayout();
        m_mainPanel = new JPanel(m_layout);
        m_contentPane = this.getContentPane();
    }

    private void configure()
    {
        this.setSize(600, 480);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    private void addComponents()
    {
        m_contentPane.add(m_mainPanel);
    }

    public void addView(View view, String name)
    {
        m_mainPanel.add(view, name);
    }

    public void showView(String name)
    {
        m_layout.show(m_mainPanel, name);
    }
}
