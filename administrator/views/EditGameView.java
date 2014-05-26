package administrator.views;

import administrator.controllers.EditGameController;
import administrator.utilities.gui.DefaultButton;
import administrator.views.subviews.WinningConditionView;
import shared.game.WinningCondition;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Rickard Zrinski on 2014-05-17.
 */
public class EditGameView extends View
{
    private JLabel m_title;
    private JTextField m_nrOfThrowsField;
    private JTextField m_nrOfDicesField;
    private ArrayList<WinningConditionView> m_winningCondViews;
    private DefaultButton m_addWinCondBtn;
    private DefaultButton m_saveBtn;
    private int m_nextGridPosY;
    private EditGameController m_controller;

    public EditGameView()
    {
        initialize();
        configureView();
        configureComponents();
        addComponents();
    }

    private void initialize()
    {
        m_title = new JLabel();
        m_nrOfThrowsField = new JTextField();
        m_nrOfDicesField = new JTextField();
        m_winningCondViews = new ArrayList<WinningConditionView>();
        m_addWinCondBtn = new DefaultButton("Add winning condition");
        m_saveBtn = new DefaultButton("Save");
        m_nextGridPosY = 0;
    }

    private void configureView()
    {
        this.setLayout(new GridBagLayout());
    }

    private void configureComponents()
    {
        m_nrOfThrowsField.setColumns(10);
        m_nrOfDicesField.setColumns(10);

        m_addWinCondBtn.addActionListener(new AddWinningConditionListener());
        m_saveBtn.addActionListener(new SaveButtonListener());
    }

    private void addComponents()
    {
        // Configure the overall layout and add components
        GridBagConstraints constraints;
        JPanel cellPanel;

        // The "Save" button
        constraints = new GridBagConstraints();
        constraints.gridy = m_nextGridPosY;
        m_nextGridPosY++;
        constraints.anchor = GridBagConstraints.WEST;

        cellPanel = new JPanel();
        cellPanel.add(m_saveBtn);
        this.add(cellPanel, constraints);

        // A label for the title
        constraints = new GridBagConstraints();
        constraints.gridy = m_nextGridPosY;
        m_nextGridPosY++;
        constraints.anchor = GridBagConstraints.WEST;

        cellPanel = new JPanel();
        cellPanel.add(m_title);
        this.add(cellPanel, constraints);

        // A label for the number of throws text field
        constraints = new GridBagConstraints();
        constraints.gridy = m_nextGridPosY;
        m_nextGridPosY++;
        constraints.anchor = GridBagConstraints.WEST;

        cellPanel = new JPanel();
        cellPanel.add(new JLabel("Number of throws"));
        this.add(cellPanel, constraints);

        // The number of throws text field
        constraints = new GridBagConstraints();
        constraints.gridy = m_nextGridPosY;
        m_nextGridPosY++;
        constraints.anchor = GridBagConstraints.WEST;

        cellPanel = new JPanel();
        cellPanel.add(m_nrOfThrowsField);
        this.add(cellPanel, constraints);

        // A label for the number of dices text field
        constraints = new GridBagConstraints();
        constraints.gridy = m_nextGridPosY;
        m_nextGridPosY++;
        constraints.anchor = GridBagConstraints.WEST;

        cellPanel = new JPanel();
        cellPanel.add(new JLabel("Number of dices"));
        this.add(cellPanel, constraints);

        // The number of dices text field
        constraints = new GridBagConstraints();
        constraints.gridy = m_nextGridPosY;
        m_nextGridPosY++;
        constraints.anchor = GridBagConstraints.WEST;

        cellPanel = new JPanel();
        cellPanel.add(m_nrOfDicesField);
        this.add(cellPanel, constraints);

        // The "Add winning condition" button
        constraints = new GridBagConstraints();
        constraints.gridy = m_nextGridPosY;
        m_nextGridPosY++;
        constraints.anchor = GridBagConstraints.WEST;

        cellPanel = new JPanel();
        cellPanel.add(m_addWinCondBtn);
        this.add(cellPanel, constraints);
    }

    public void addWinningCondition(WinningCondition winningCondition)
    {
        WinningConditionView view = this.addWinningCondition();
        view.setData(winningCondition);
    }

    public void setTitle(String title)
    {
        m_title.setText(title);
    }

    public void setNrOfThrows(int nrOfThrows)
    {
        m_nrOfThrowsField.setText(Integer.toString(nrOfThrows));
    }

    public void setNrOfDices(int nrOfDices)
    {
        m_nrOfDicesField.setText(Integer.toString(nrOfDices));
    }

    public void setController(EditGameController controller)
    {
        m_controller = controller;
    }

    private WinningConditionView addWinningCondition()
    {
        WinningConditionView view = new WinningConditionView();

        // A winning conditions view
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridy = m_nextGridPosY;
        m_nextGridPosY++;
        constraints.anchor = GridBagConstraints.WEST;

        m_winningCondViews.add(view);
        this.add(view, constraints);

        return view;
    }

    public int getNrOfThrows()
    {
        return Integer.parseInt(m_nrOfThrowsField.getText());
    }

    public int getNrOfDices()
    {
        return Integer.parseInt(m_nrOfDicesField.getText());
    }

    private class AddWinningConditionListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            addWinningCondition();
            revalidate();
        }
    }

    private class SaveButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            // Show confirmation window
            int answer = showSaveConfirmationWindow();

            if(answer == JOptionPane.OK_OPTION)
            {
                ArrayList<WinningCondition> winningConditions = new ArrayList<>();
                WinningCondition winningCondition;
                for (WinningConditionView winCondView : m_winningCondViews) {
                    winningCondition = winCondView.getAsWinningCondition();
                    winningConditions.add(winningCondition);
                }

                m_controller.editGame(getNrOfThrows(), getNrOfDices(), winningConditions);
            }
        }
    }

    private int showSaveConfirmationWindow()
    {
        int answer = JOptionPane.showConfirmDialog(this, "Do you want to save the game parameters?",
                "Confirm save?", JOptionPane.OK_CANCEL_OPTION);

        return answer;
    }
}
