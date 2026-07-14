package controller;

import model.game.Snake;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class SnakeKeyListener implements KeyListener {

    private final Snake snake;

    public SnakeKeyListener(Snake snake) {
        this.snake = snake;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        Point heading = snake.getHeading();

        switch (e.getKeyCode()) {
            case KeyEvent.VK_W, KeyEvent.VK_UP -> {
                if (heading.x != 1) snake.nextHeading = new Point(-1, 0);
            }
            case KeyEvent.VK_S, KeyEvent.VK_DOWN -> {
                if (heading.x != -1) snake.nextHeading = new Point(1, 0);
            }
            case KeyEvent.VK_A, KeyEvent.VK_LEFT -> {
                if (heading.y != 1) snake.nextHeading = new Point(0, -1);
            }
            case KeyEvent.VK_D, KeyEvent.VK_RIGHT -> {
                if (heading.y != -1) snake.nextHeading = new Point(0, 1);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
}