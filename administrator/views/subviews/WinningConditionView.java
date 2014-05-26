package administrator.views.subviews;

import administrator.utilities.gui.DefaultButton;
import administrator.views.View;
import shared.game.Combination;
import shared.game.WinningCondition;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Rickard Zrinski on 2014-05-22.
 */
public class WinningConditionView extends View
{
    private JTextField m_nameField;
    private JTextField m_rewardField;
    private ArrayList<CombinationView> m_combViews;
    private DefaultButton m_addCombBtn;
    private int m_nextGridPosY;

    public WinningConditionView()
    {
        initialize();
        configureView();
        configureComponents();
        addComponents();
    }

    private void initialize()
    {
        m_nameField = new JTextField();
        m_rewardField = new JTextField();
        m_combViews = new ArrayList<CombinationView>();
        m_addCombBtn = new DefaultButton("Add combination");
        m_nextGridPosY = 0;
    }

    private void configureView()
    {
        this.setLayout(new GridBagLayout());
    }

    private void configureComponents()
    {
        m_nameField.setColumns(10);
        m_rewardField.setColumns(10);

        m_addCombBtn.addActionListener(new AddCombinationListener());
    }

    private void addComponents()
    {
        // Configure the overall layout and add components
        GridBagConstraints constraints;
        JPanel cellPanel;

        // A label for the winning condition
        constraints = new GridBagConstraints();
        constraints.gridy = m_nextGridPosY;
        m_nextGridPosY++;
        constraints.anchor = GridBagConstraints.WEST;

        cellPanel = new JPanel();
        cellPanel.add(new JLabel("Winning Condition"));
        this.add(cellPanel, constraints);

        // A label for the name text field
        constraints = new GridBagConstraints();
        constraints.gridy = m_nextGridPosY;
        constraints.gridx = 0;
        constraints.anchor = GridBagConstraints.WEST;

        cellPanel = new JPanel();
        cellPanel.add(new JLabel("Name"));
        this.add(cellPanel, constraints);

        // A label for the reward text field
        constraints = new GridBagConstraints();
        constraints.gridy = m_nextGridPosY;
        m_nextGridPosY++;
        constraints.gridx = 1;
        constraints.anchor = GridBagConstraints.WEST;

        cellPanel = new JPanel();
        cellPanel.add(new JLabel("Reward"));
        this.add(cellPanel, constraints);

        // The name text field
        constraints = new GridBagConstraints();
        constraints.gridy = m_nextGridPosY;
        constraints.gridx = 0;
        constraints.anchor = GridBagConstraints.WEST;

        cellPanel = new JPanel();
        cellPanel.add(m_nameField);
        this.add(cellPanel, constraints);

        // The reward text field
        constraints = new GridBagConstraints();
        constraints.gridy = m_nextGridPosY;
        m_nextGridPosY++;
        constraints.gridx = 1;
        constraints.anchor = GridBagConstraints.WEST;

        cellPanel = new JPanel();
        cellPanel.add(m_rewardField);
        this.add(cellPanel, constraints);

        // The "Add combination" button
        constraints = new GridBagConstraints();
        constraints.gridy = m_nextGridPosY;
        m_nextGridPosY++;
        constraints.gridx = 0;
        constraints.anchor = GridBagConstraints.WEST;

        cellPanel = new JPanel();
        cellPanel.add(m_addCombBtn);
        this.add(cellPanel, constraints);
    }

    /**
     * Populates this view with data of an existing WinningCondition
     * @param winningCondition the data
     */
    public void setData(WinningCondition winningCondition)
    {
        m_nameField.setText(winningCondition.getName());
        m_rewardField.setText(Float.toString(winningCondition.getReward()));

        CombinationView view;
        GridBagConstraints constraints;
        for(int i = 0; i < winningCondition.getNumberOfCombinations(); i++)
        {
            // Add empty CombinationView
            view = addCombinationView();

            // Get Combination from WinningCondition
            Combination comb = winningCondition.getCombination(i);

            // Set data in CombinationView
            view.setData(comb);
        }
    }

    /**
     * Adds a new CombinationView to this view
     * @return returns the newly added CombinationView
     */
    private CombinationView addCombinationView()
    {
        CombinationView combView = new CombinationView();
        GridBagConstraints constraints;

        constraints = new GridBagConstraints();
        constraints.gridy = m_nextGridPosY;
        m_nextGridPosY++;
        constraints.anchor = GridBagConstraints.WEST;

        m_combViews.add(combView);
        this.add(combView, constraints);

        return combView;
    }

    public String getName()
    {
        return m_nameField.getText();
    }

    public float getReward()
    {
        return Float.parseFloat(m_rewardField.getText());
    }

    /**
     * Returns the number of CombinationView's in this view
     */
    public int getNrOfCombinations()
    {
        return m_combViews.size();
    }

    public WinningCondition getAsWinningCondition()
    {
        WinningCondition winningCondition = new WinningCondition();

        winningCondition.setName(this.getName());
        winningCondition.setReward(this.getReward());
        for(CombinationView combView: m_combViews)
        {
            winningCondition.addCombination(combView.getAsCombination());
            System.out.println("Combination added to new WinningCondition: " + combView.getAsCombination().getName());
        }

        return winningCondition;
    }

    /**
     * A listener for the button that adds a new CombinationView to this view
     */
    private class AddCombinationListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            addCombinationView();
            revalidate();
        }
    }
}
