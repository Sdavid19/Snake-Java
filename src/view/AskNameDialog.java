package view;

import javax.swing.*;

public class AskNameDialog {

    public static String askPlayerName() {
        String name = null;

        while (name == null || name.isBlank()) {
            name = JOptionPane.showInputDialog(
                    null,
                    "Player name:"
            );

            if (name == null) {
                System.exit(0);
            }
        }

        return name;
    }
}
