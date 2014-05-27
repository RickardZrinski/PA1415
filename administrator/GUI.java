package administrator;

import administrator.views.View;
import casino.MainFrame;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Rickard Zrinski on 2014-05-16.
 */
public class GUI
{
    private JPanel m_mainPanel;
    private CardLayout m_layout;
    private Container m_contentPane;
    private JScrollPane m_scrollPane;
    private JFrame m_frame;

    public GUI()
    {
        this.initialize();
        this.configure();
        this.addComponents();
    }

    private void initialize()
    {
        m_frame = MainFrame.getInstance();
        m_layout = new CardLayout();
        m_mainPanel = new JPanel(m_layout);
        m_contentPane = m_frame.getContentPane();
        m_scrollPane = new JScrollPane();
    }

    private void configure()
    {
        m_frame.setSize(600, 480);
        m_frame.setLocationRelativeTo(null);
        m_frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        m_frame.setVisible(true);
    }

    private void addComponents()
    {
        m_scrollPane.setViewportView(m_mainPanel);
        m_contentPane.add(m_scrollPane);
    }

    public void addView(View view, String name)
    {
        m_mainPanel.add(view, name);
    }

    public void showView(String name, String frameTitle)
    {
        m_frame.setTitle(frameTitle);

        m_layout.show(m_mainPanel, name);
    }

    public void setMenuBar(JMenuBar menuBar)
    {
        m_frame.setJMenuBar(menuBar);
    }
}
