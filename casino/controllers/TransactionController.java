package casino.controllers;

import casino.MainFrame;
import casino.events.TransactionEvent;
import casino.events.TransactionListener;
import casino.views.DepositView;

import java.awt.*;

/**
 * @author  Dino Opijac
 * @since   21/05/2014
 */
public class TransactionController implements TransactionListener {
    private DepositView depositView = new DepositView();

    public TransactionController() {
        this.depositView.subscribe(this);

        MainFrame.getInstance().add(this.depositView, BorderLayout.CENTER);
    }

    @Override
    public void depositPerformed(TransactionEvent e) {
        System.out.println(String.format("Deposit performed: %s", e.getPayment()));
    }

    @Override
    public void withdrawPerformed(TransactionEvent e) {
        System.out.println(String.format("Withdraw performed: %s", e.getPayment()));
    }
}
