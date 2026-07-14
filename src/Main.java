import controller.SnakeController;
import view.AskNameDialog;

public class Main {
    public static void main(String[] args) {
        String playerName = AskNameDialog.askPlayerName();
        new SnakeController(playerName);
    }
}