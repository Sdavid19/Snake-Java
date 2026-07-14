package controller;

import model.game.Game;
import model.highscore.HighScore;
import model.highscore.HighScores;
import view.AskNameDialog;
import view.HighScoresDialog;
import view.SnakeView;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class SnakeController {
    private static final int TIMER_DELAY = 250;

    private Game game;
    private SnakeView view;
    private Timer timer;
    private String playerName;
    private HighScores highScores;

    public SnakeController(String playerName) {
        this.playerName = playerName;

        initHighScores();
        initGame();
        initView();
        drawRocks();
        startGameLoop();
    }

    private void initHighScores() {
        try {
            highScores = new HighScores(10);
        } catch (Exception ex) {
            ex.printStackTrace();
            highScores = null;
        }
    }

    private void initGame() {
        game = new Game();
    }

    private void initView() {
        SnakeKeyListener keyListener = new SnakeKeyListener(game.snake);
        view = new SnakeView(15, 17, keyListener, playerName);
    }

    private void startGameLoop() {
        timer = new Timer(TIMER_DELAY, e -> {
            if (game.gameIsOver()) {
                timer.stop();
                manageHighscores();
                return;
            }

            moveSnake();
            setApple();

            view.setScore(game.point);
        });
        timer.start();
    }

    private void drawRocks() {
        for (var r : game.map.getRocks()) {
            Point p = r.getPosition();
            view.setRockCell(p.x, p.y);
        }
    }

    public void restartGame() {
        String newPlayerName = AskNameDialog.askPlayerName();
        this.playerName = newPlayerName;
        timer.stop();
        view.dispose();
        initGame();
        initView();
        drawRocks();
        startGameLoop();
    }

    private void manageHighscores() {
        if (highScores != null) {
            try {
                highScores.putHighScore(playerName, game.point);
                ArrayList<HighScore> top = highScores.getHighScores();
                HighScoresDialog.showDialog(view, playerName, game.point, top, this::restartGame);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }


    private void moveSnake() {
        int previousTailSize = game.snake.tail.size();
        Point oldTail = game.moveSnakeAndCheckApple();
        Point head = game.snake.getHead();

        view.deleteCell(oldTail.x, oldTail.y);
        view.setCell(head.x, head.y);

        if (game.snake.tail.size() > previousTailSize) {
            Point newTail = game.snake.tail.get(game.snake.tail.size() - 1);
            view.setCell(newTail.x, newTail.y);
        }
    }

    private void setApple() {
        Point a = game.apple.getPosition();
        view.setAppleCell(a.x, a.y);
    }
}
