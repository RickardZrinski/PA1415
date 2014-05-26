package administrator.views;

import administrator.controllers.AddGameController;
import administrator.utilities.gui.DefaultButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Rickard Zrinski on 2014-05-18.
 */
public class AddGameView extends View
{
    private JTextField m_fieldTitle;
    private DefaultButton m_saveBtn;
    private AddGameController m_controller;

    public AddGameView()
    {
        initialize();
        configureView();
        configureComponents();
        addComponents();
    }

    private void initialize()
    {
        m_fieldTitle = new JTextField();
        m_saveBtn = new DefaultButton("Save");
    }

    private void configureView()
    {
        this.setLayout(new GridBagLayout());
    }

    private void configureComponents()
    {
        m_fieldTitle.setColumns(10);

        m_saveBtn.addActionListener(new AddGameButtonListener());
    }

    private void addComponents()
    {
        // Configure the overall layout and add components
        GridBagConstraints constraints;
        JPanel cellPanel;

        // A label for the text field
        constraints = new GridBagConstraints();
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.WEST;

        cellPanel = new JPanel();
        cellPanel.add(new JLabel("Enter the title of the game"));
        this.add(cellPanel, constraints);

        // The title text field
        constraints = new GridBagConstraints();
        constraints.gridy = 1;
        constraints.anchor = GridBagConstraints.WEST;

        cellPanel = new JPanel();
        cellPanel.add(m_fieldTitle);
        this.add(cellPanel, constraints);

        // The save button
        constraints = new GridBagConstraints();
        constraints.gridy = 2;
        constraints.anchor = GridBagConstraints.WEST;

        cellPanel = new JPanel();
        cellPanel.add(m_saveBtn);
        this.add(cellPanel, constraints);
    }

    public String getTitle()
    {
        return m_fieldTitle.getText();
    }

    public void setController(AddGameController controller)
    {
        m_controller = controller;
    }

    private class AddGameButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            m_controller.addGame(getTitle());
        }
    }
}
