package casino.controllers;

import casino.MainFrame;
import casino.views.DepositView;

import java.awt.*;

/**
 * @author  Dino Opijac
 * @since   21/05/2014
 */
public class TransactionController {
    private DepositView view = new DepositView();

    public TransactionController() {
        this.view.subscribe(this);

        MainFrame.getInstance().add(this.view, BorderLayout.CENTER);
    }
}
