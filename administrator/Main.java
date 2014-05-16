package administrator;

import administrator.views.ListAllGamesView;
import administrator.views.ListAllUsersView;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                GUI frame = new GUI();
                frame.setVisible(true);

                frame.addView(new ListAllGamesView(), "ListAllGamesView");
                frame.showView("ListAllGamesView");

                frame.addView(new ListAllUsersView(), "ListAllUsersView");
                frame.showView("ListAllUsersView");
            }
        });
    }
}
