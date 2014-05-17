package administrator;

import administrator.controllers.MainMenuController;
import administrator.models.GamesModel;
import administrator.models.UsersModel;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                GUI frame = new GUI();
                frame.setVisible(true);

                MainMenuController controller = new MainMenuController(frame, new UsersModel(), new GamesModel());
            }
        });
    }
}
