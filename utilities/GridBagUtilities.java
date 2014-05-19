package utilities;

import javax.swing.*;
import java.awt.*;

/**
 * @author  Dino Opijac
 * @since   19/05/2014
 */
public class GridBagUtilities {
    /**
     * Creates a cell with less code
     * @param parent        the parent component
     * @param component     the component to place
     * @param point         the point (x, y)
     * @param w             the width
     * @param fill          the fill method
     * @param anchor        the anchor type
     * @return              the completed constraint
     */
    public static GridBagConstraints makeCell(JComponent parent, JComponent component, Point point, int w, int fill, int anchor) {
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.fill = fill;
        constraints.gridwidth = w;
        constraints.gridx = point.x;
        constraints.gridy = point.y;
        constraints.anchor = anchor;

        parent.add(component, constraints);

        return constraints;
    }

    /**
     * The same as the first makeCell, but without the anchor
     * @param parent        the parent component
     * @param component     the component to place
     * @param point         the point (x, y)
     * @param w             the width
     * @param fill          the fill method
     * @return              the completed constraint
     */
    public static GridBagConstraints makeCell(JComponent parent, JComponent component, Point point, int w, int fill) {
        return GridBagUtilities.makeCell(parent, component, point, w, fill, GridBagConstraints.CENTER);
    }

    /**
     * The same as the first makeCell, but without the anchor and fill
     * @param parent        the parent component
     * @param component     the component to place
     * @param point         the point (x, y)
     * @param w             the width
     * @return              the completed constraint
     */
    public static GridBagConstraints makeCell(JComponent parent, JComponent component, Point point, int w) {
        return GridBagUtilities.makeCell(parent, component, point, w, GridBagConstraints.NONE, GridBagConstraints.CENTER);
    }
}
