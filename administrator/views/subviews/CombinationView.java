package administrator.views.subviews;

import administrator.views.View;
import shared.game.Combination;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Rickard Zrinski on 2014-05-22.
 */
public class CombinationView extends View
{
    private JTextField m_name;
    private JTextField m_quantity;
    private FacesView m_facesView;

    public CombinationView()
    {
        initialize();
        configureView();
        configureComponents();
        addComponents();
    }

    private void initialize()
    {
        m_name = new JTextField();
        m_quantity = new JTextField();
        m_facesView = new FacesView();
    }

    private void configureView()
    {
        this.setLayout(new GridBagLayout());
    }

    private void configureComponents()
    {
        m_name.setColumns(10);
        m_quantity.setColumns(10);
    }

    private void addComponents()
    {
        // Configure the overall layout and add components
        GridBagConstraints constraints;
        JPanel cellPanel;

        // A combination label
        constraints = new GridBagConstraints();
        constraints.gridy = 0;
        constraints.gridx = 0;
        constraints.anchor = GridBagConstraints.WEST;

        cellPanel = new JPanel();
        cellPanel.add(new JLabel("Combination"));
        this.add(cellPanel, constraints);

        // A label for the name text field
        constraints = new GridBagConstraints();
        constraints.gridy = 1;
        constraints.gridx = 0;
        constraints.anchor = GridBagConstraints.WEST;

        cellPanel = new JPanel();
        cellPanel.add(new JLabel("Name"));
        this.add(cellPanel, constraints);

        constraints = new GridBagConstraints();
        constraints.gridy = 1;
        constraints.gridx = 1;
        constraints.anchor = GridBagConstraints.WEST;

        cellPanel = new JPanel();
        cellPanel.add(new JLabel("Quantity"));
        this.add(cellPanel, constraints);

        // The name text field
        constraints = new GridBagConstraints();
        constraints.gridy = 2;
        constraints.gridx = 0;
        constraints.anchor = GridBagConstraints.WEST;

        cellPanel = new JPanel();
        cellPanel.add(m_name);
        this.add(cellPanel, constraints);

        // The quantity text field
        constraints = new GridBagConstraints();
        constraints.gridy = 2;
        constraints.gridx = 1;
        constraints.anchor = GridBagConstraints.WEST;

        cellPanel = new JPanel();
        cellPanel.add(m_quantity);
        this.add(cellPanel, constraints);

        // The FacesView
        constraints = new GridBagConstraints();
        constraints.gridy = 3;
        constraints.gridx = 0;
        constraints.anchor = GridBagConstraints.WEST;

        this.add(m_facesView, constraints);
    }

    public Combination getAsCombination()
    {
        Combination combination = new Combination();

        combination.setName(this.getName());
        combination.setQuantity(this.getQuantity());
        combination.setFaces(m_facesView.getAsFaces());

        return combination;
    }

    public void setData(Combination combination)
    {
        this.setName(combination.getName());
        this.setQuantity(combination.getQuantity());
        m_facesView.setData(combination.getFaces());
    }

    public void setName(String name)
    {
        m_name.setText(name);
    }

    public void setQuantity(int quantity)
    {
        m_quantity.setText(Integer.toString(quantity));
    }

    public String getName()
    {
        return m_name.getText();
    }

    public int getQuantity()
    {
        return Integer.parseInt(m_quantity.getText());
    }
}
