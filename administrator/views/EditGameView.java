package administrator.views;

import administrator.controllers.EditGameController;
import administrator.views.subviews.WinningConditionView;
import shared.game.WinningCondition;

import javax.swing.*;
import java.awt.*;
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
    private int m_nextGridPosY;

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
    }

    private void addComponents()
    {
        // Configure the overall layout and add components
        GridBagConstraints constraints;
        JPanel cellPanel;

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

        // A label for the winning conditions
        constraints = new GridBagConstraints();
        constraints.gridy = m_nextGridPosY;
        m_nextGridPosY++;
        constraints.anchor = GridBagConstraints.WEST;

        cellPanel = new JPanel();
        cellPanel.add(new JLabel("Winning Conditions"));
        this.add(cellPanel, constraints);
    }

    public void addWinningCondition(WinningCondition winningCondition)
    {
        System.out.println("addWinningCondition");

        WinningConditionView view = new WinningConditionView();
        view.setData(winningCondition);

        // A winning conditions view
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridy = m_nextGridPosY;
        m_nextGridPosY++;
        constraints.anchor = GridBagConstraints.WEST;

        m_winningCondViews.add(view);
        this.add(view, constraints);
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

    public void registerListener(EditGameController controller)
    {

    }
}
