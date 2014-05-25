package casino.events;

/**
 * @author  Dino Opijac
 * @since   25/05/14.
 */
public interface TransactionResponse {
    public void transactionSuccessful();
    public void transactionUnsuccessful();
}
