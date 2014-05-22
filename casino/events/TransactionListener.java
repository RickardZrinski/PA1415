package casino.events;

/**
 * @author  Dino Opijac
 * @since   22/05/14
 */
public interface TransactionListener {
    public void depositPerformed(TransactionEvent e);
    public void withdrawPerformed(TransactionEvent e);
}
