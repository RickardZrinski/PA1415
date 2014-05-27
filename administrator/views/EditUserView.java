package administrator.views;

import administrator.controllers.EditUserController;
import administrator.utilities.gui.DefaultButton;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Rickard Zrinski on 2014-05-27.
 */
public class EditUserView extends View
{
    private EditUserController m_controller;
    private JTextField m_userNameField;
    private JTextField m_firstNameField;
    private JTextField m_lastNameField;
    private JPasswordField m_passwordField;
    private DefaultButton m_saveBtn;

    public EditUserView()
    {
        initialize();
        configureView();
        configureComponents();
        addComponents();
    }

    private void initialize()
    {
        m_userNameField = new JTextField();
        m_firstNameField = new JTextField();
        m_lastNameField = new JTextField();
        m_passwordField = new JPasswordField();
        m_saveBtn = new DefaultButton("Save");
    }

    private void configureView()
    {
        this.setLayout(new GridBagLayout());
    }

    private void configureComponents()
    {
        m_userNameField.setColumns(10);
        m_firstNameField.setColumns(10);
        m_lastNameField.setColumns(10);
        m_passwordField.setColumns(10);
    }

    private void addComponents()
    {
        // Configure the overall layout and add components
        GridBagConstraints constraints;
        JPanel cellPanel;

        // The username label
        constraints = new GridBagConstraints();
        constraints.gridy = 0;
        constraints.gridx = 0;
        constraints.anchor = GridBagConstraints.WEST;

        cellPanel = new JPanel();
        cellPanel.add(new JLabel("Username"));
        this.add(cellPanel, constraints);

        // The username text field
        constraints = new GridBagConstraints();
        constraints.gridy = 1;
        constraints.gridx = 0;
        constraints.anchor = GridBagConstraints.WEST;

        cellPanel = new JPanel();
        cellPanel.add(m_userNameField);
        this.add(cellPanel, constraints);

        // The firstname label
        constraints = new GridBagConstraints();
        constraints.gridy = 2;
        constraints.gridx = 0;
        constraints.anchor = GridBagConstraints.WEST;

        cellPanel = new JPanel();
        cellPanel.add(new JLabel("Firstname"));
        this.add(cellPanel, constraints);

        // The firstname text field
        constraints = new GridBagConstraints();
        constraints.gridy = 3;
        constraints.gridx = 0;
        constraints.anchor = GridBagConstraints.WEST;

        cellPanel = new JPanel();
        cellPanel.add(m_firstNameField);
        this.add(cellPanel, constraints);

        // The lastname label
        constraints = new GridBagConstraints();
        constraints.gridy = 4;
        constraints.gridx = 0;
        constraints.anchor = GridBagConstraints.WEST;

        cellPanel = new JPanel();
        cellPanel.add(new JLabel("Lastname"));
        this.add(cellPanel, constraints);

        // The lastname text field
        constraints = new GridBagConstraints();
        constraints.gridy = 5;
        constraints.gridx = 0;
        constraints.anchor = GridBagConstraints.WEST;

        cellPanel = new JPanel();
        cellPanel.add(m_lastNameField);
        this.add(cellPanel, constraints);

        // The password label
        constraints = new GridBagConstraints();
        constraints.gridy = 6;
        constraints.gridx = 0;
        constraints.anchor = GridBagConstraints.WEST;

        cellPanel = new JPanel();
        cellPanel.add(new JLabel("Password"));
        this.add(cellPanel, constraints);

        // The password text field
        constraints = new GridBagConstraints();
        constraints.gridy = 7;
        constraints.gridx = 0;
        constraints.anchor = GridBagConstraints.WEST;

        cellPanel = new JPanel();
        cellPanel.add(m_passwordField);
        this.add(cellPanel, constraints);

        // The "Save" button
        constraints = new GridBagConstraints();
        constraints.gridy = 8;
        constraints.gridx = 0;
        constraints.anchor = GridBagConstraints.WEST;

        cellPanel = new JPanel();
        cellPanel.add(m_saveBtn);
        this.add(cellPanel, constraints);
    }

    public void setController(EditUserController controller)
    {
        m_controller = controller;
    }
}
