package casino.controllers;

import casino.MainFrame;
import casino.events.TransactionEvent;
import casino.events.TransactionListener;
import casino.models.TransactionModel;
import casino.views.DepositView;
import casino.views.WithdrawView;
import shared.AuthenticationSession;

import java.awt.*;

/**
 * @author  Dino Opijac
 * @since   21/05/2014
 */
public class TransactionController implements TransactionListener {
    private DepositView depositView;
    private WithdrawView withdrawView;
    private TransactionModel model;

    public TransactionController() {}

    public void depositAction() {
        this.model = new TransactionModel();
        this.depositView = new DepositView();
        this.model.subscribe(this.depositView);
        this.depositView.subscribe(this);

        MainFrame.getInstance().add(this.depositView);
    }

    public void withdrawAction() {
        this.model = new TransactionModel();
        this.withdrawView = new WithdrawView();
        this.model.subscribe(this.withdrawView);
        this.withdrawView.subscribe(this);

        MainFrame.getInstance().add(this.withdrawView);
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

        // Check if its possible to withdraw any money from this account
        try {
            if (AuthenticationSession.getInstance().getUser().getAccount().isWithdrawable(event.getAmount())) {
                model.withdraw(event.getAmount());
                model.makePayment(event.getPayment());
                model.makeTransaction();
                model.endTransaction();
            } else {
                System.out.println("Not possible.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
