package administrator.views.subviews;

import administrator.views.View;
import shared.game.Combination;
import shared.game.WinningCondition;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Rickard Zrinski on 2014-05-22.
 */
public class WinningConditionView extends View
{
    private JTextField m_nameField;
    private JTextField m_rewardField;
    private ArrayList<CombinationView> m_combViews;
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
    }

    private void addComponents()
    {
        // Configure the overall layout and add components
        GridBagConstraints constraints;
        JPanel cellPanel;

        // A label for the name text field
        constraints = new GridBagConstraints();
        constraints.gridy = m_nextGridPosY;
        m_nextGridPosY++;
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
        m_nextGridPosY++;
        constraints.gridx = m_nextGridPosY;
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
    }

    public void setData(WinningCondition winningCondition)
    {
        m_nameField.setText(winningCondition.getName());
        m_rewardField.setText(Float.toString(winningCondition.getReward()));

        CombinationView view;
        GridBagConstraints constraints;
        for(int i = 0; i < winningCondition.getNumberOfCombinations(); i++)
        {
            view = new CombinationView();
            Combination comb = winningCondition.getCombination(i);
            view.setName(comb.getName());
            view.setQuantity(comb.getQuantity());

            // A combination view
            constraints = new GridBagConstraints();
            constraints.gridy = m_nextGridPosY;
            m_nextGridPosY++;
            constraints.anchor = GridBagConstraints.WEST;

            m_combViews.add(view);
            this.add(view, constraints);
        }
    }

    public int getNrOfCombinations()
    {
        return m_combViews.size();
    }
}
