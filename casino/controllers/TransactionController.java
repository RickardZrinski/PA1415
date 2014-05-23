package casino.controllers;

import casino.MainFrame;
import casino.events.TransactionEvent;
import casino.events.TransactionListener;
import casino.models.TransactionModel;
import casino.views.DepositView;
import casino.views.WithdrawView;

import java.awt.*;

/**
 * @author  Dino Opijac
 * @since   21/05/2014
 */
public class TransactionController implements TransactionListener {
    private DepositView depositView = new DepositView();
    private WithdrawView withdrawView = new WithdrawView();
    private TransactionModel model = new TransactionModel();

    public TransactionController() {
        this.depositView.subscribe(this);
        this.withdrawView.subscribe(this);

        //MainFrame.getInstance().add(this.depositView, BorderLayout.CENTER);
        MainFrame.getInstance().add(this.withdrawView, BorderLayout.CENTER);
    }

    @Override
    public void depositPerformed(TransactionEvent event) {
        System.out.println("Deposit performed");

        model.deposit(event.getAmount());
        model.makePayment(event.getPayment());
        model.makeTransaction();
        model.endTransaction();
    }

    @Override
    public void withdrawPerformed(TransactionEvent event) {
        System.out.println("Withdraw performed");

        model.withdraw(event.getAmount());
        model.makePayment(event.getPayment());
        model.makeTransaction();
        model.endTransaction();
    }
}
