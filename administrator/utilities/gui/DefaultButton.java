package administrator.utilities.gui;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Created by Rickard Zrinski on 2014-05-18.
 */
public class DefaultButton extends JButton
{
    public DefaultButton(String text)
    {
        super(text);

        configure();
    }

    private void configure()
    {
        this.setFocusPainted(false);
        this.setBackground(Color.WHITE);
        Border border = new LineBorder(Color.BLACK);
        Border margin = new EmptyBorder(5, 15, 5, 15);
        Border compound = new CompoundBorder(border, margin);
        this.setBorder(compound);
        this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }
}
