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
     * @param weightx       the weight (a value between 0.0-1.0)
     * @param width         the width
     * @param fill          the fill method
     * @param anchor        the anchor type
     * @return              the completed constraint
     */
    public static GridBagConstraints makeCell(JComponent parent, JComponent component, Point point, double weightx, int width, int fill, int anchor) {
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.fill = fill;
        constraints.gridwidth = width;
        constraints.weightx = weightx;
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
     * @param weightx       the weight (a value between 0.0-1.0)
     * @param width         the width
     * @param fill          the fill method
     * @return              the completed constraint
     */
    public static GridBagConstraints makeCell(JComponent parent, JComponent component, Point point, double weightx, int width, int fill) {
        return GridBagUtilities.makeCell(parent, component, point, weightx, width, fill, GridBagConstraints.CENTER);
    }

    /**
     * The same as the first makeCell, but without the anchor and fill
     * @param parent        the parent component
     * @param component     the component to place
     * @param point         the point (x, y)
     * @param weightx       the weight (a value between 0.0-1.0)
     * @param width         the width
     * @return              the completed constraint
     */
    public static GridBagConstraints makeCell(JComponent parent, JComponent component, Point point, double weightx, int width) {
        return GridBagUtilities.makeCell(parent, component, point, weightx, width, GridBagConstraints.NONE, GridBagConstraints.CENTER);
    }

    /**
     * The same as the first makeCell, but without the width, anchor and fill
     * @param parent        the parent component
     * @param component     the component to place
     * @param point         the point (x, y)
     * @param weightx       the weight (a value between 0.0-1.0)
     * @return              the completed constraint
     */
    public static GridBagConstraints makeCell(JComponent parent, JComponent component, Point point, double weightx) {
        return GridBagUtilities.makeCell(parent, component, point, weightx, 1, GridBagConstraints.NONE, GridBagConstraints.CENTER);
    }
}
