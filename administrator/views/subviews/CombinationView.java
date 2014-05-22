package administrator.views.subviews;

import administrator.views.View;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Rickard Zrinski on 2014-05-22.
 */
public class CombinationView extends View
{
    private JTextField m_name;
    private JTextField m_quantity;

    public CombinationView()
    {
        initialize();
        configure();
        addComponents();
    }

    private void initialize()
    {
        m_name = new JTextField();
        m_quantity = new JTextField();
    }

    private void configure()
    {
        this.setLayout(new GridBagLayout());
    }

    private void addComponents()
    {
        // Configure the overall layout and add components
        GridBagConstraints constraints;
        JPanel cellPanel;

        // The name text field
        constraints = new GridBagConstraints();
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.WEST;

        cellPanel = new JPanel();
        cellPanel.add(m_name);
        this.add(cellPanel, constraints);

        // The quantity text field
        constraints = new GridBagConstraints();
        constraints.gridy = 1;
        constraints.anchor = GridBagConstraints.WEST;

        cellPanel = new JPanel();
        cellPanel.add(m_quantity);
        this.add(cellPanel, constraints);
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
