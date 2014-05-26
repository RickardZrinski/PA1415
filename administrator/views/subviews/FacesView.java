package administrator.views.subviews;

import administrator.utilities.gui.DefaultButton;
import administrator.views.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Rickard Zrinski on 2014-05-26.
 */
public class FacesView extends View
{
    private DefaultButton m_addFaceBtn;
    private ArrayList<JTextField> m_faces;
    private JPanel m_facesPanel;

    public FacesView()
    {
        initialize();
        configureView();
        configureComponents();
        addComponents();
    }

    private void initialize()
    {
        m_addFaceBtn = new DefaultButton("Add face");
        m_faces = new ArrayList<>();
        m_facesPanel = new JPanel();
    }

    private void configureView()
    {
        this.setLayout(new GridBagLayout());
    }

    private void configureComponents()
    {
        m_facesPanel.setLayout(new FlowLayout());

        m_addFaceBtn.addActionListener(new AddButtonFaceListener());
    }

    private void addComponents()
    {
        // Configure the overall layout and add components
        GridBagConstraints constraints;
        JPanel cellPanel;

        // A button for adding new face fields
        constraints = new GridBagConstraints();
        constraints.gridy = 0;
        constraints.gridx = 0;
        constraints.anchor = GridBagConstraints.WEST;

        cellPanel = new JPanel();
        cellPanel.add(m_addFaceBtn);
        this.add(cellPanel, constraints);

        // A label for the faces
        constraints = new GridBagConstraints();
        constraints.gridy = 1;
        constraints.gridx = 0;
        constraints.anchor = GridBagConstraints.WEST;

        cellPanel = new JPanel();
        cellPanel.add(new JLabel("Faces"));
        this.add(cellPanel, constraints);

        // Add faces panel
        constraints = new GridBagConstraints();
        constraints.gridy = 2;
        constraints.gridx = 0;
        constraints.anchor = GridBagConstraints.WEST;

        cellPanel.add(m_facesPanel);
        this.add(m_facesPanel, constraints);
    }

    public void setData(ArrayList<String> faces)
    {
        for(String face: faces)
        {
            JTextField field = this.addFace();
            field.setText(face);
        }
    }

    public JTextField addFace()
    {
        JTextField field = new JTextField();
        field.setColumns(2);

        if(m_faces.size() < 6)
        {
            m_faces.add(field);
            m_facesPanel.add(field);
            revalidate();
        }

        return field;
    }

    public ArrayList<String> getAsFaces()
    {
        ArrayList<String> faces = new ArrayList<>();

        for(JTextField faceField: m_faces)
        {
            String faceFieldValue = faceField.getText();

            if(faceFieldValue != "")
            {
                faces.add(faceFieldValue);
            }
        }

        return faces;
    }

    private class AddButtonFaceListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            addFace();
        }
    }
}
