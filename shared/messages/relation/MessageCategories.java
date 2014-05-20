package shared.messages.relation;

import utilities.sql.annotations.PrimaryKey;

/**
 * @author  Dino Opijac
 * @since   20/05/2014
 */
public class MessageCategories {
    @PrimaryKey private int mid;
    private int cid;
}
