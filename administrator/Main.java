package administrator;

import administrator.controllers.ListAllGamesController;
import administrator.controllers.ListAllUsersController;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                GUI frame = new GUI();
                frame.setVisible(true);

               //ListAllGamesController controller = new ListAllGamesController(frame);
                ListAllUsersController controller = new ListAllUsersController(frame);
            }
        });
    }
}
