package model.game;

import model.game.mapElement.Apple;

import java.awt.*;
import java.util.Random;

public class Game {

    public Map map;

    public Snake snake;
    public Apple apple;

    public int point;

    private final Random random = new Random();

    public Game() {
        map = new Map();
        snake = new Snake(new Point(8, 9));
        point = 0;

        snake.addBodyPart();

        updateEmptyFields();

        map.spawnRocks(snake.getHead());

        apple = new Apple();
        spawnApple();
    }

    private void updateEmptyFields() {
        map.removeFromEmptyList(snake.getHead());
        map.removeAllFromEmptyList(snake.tail);
    }

    public boolean isNextMoveValid(Point nextHeading) {
        Point dir = (nextHeading != null) ? nextHeading : snake.getHeading();
        int nextX = snake.getHead().x + dir.x;
        int nextY = snake.getHead().y + dir.y;
        return map.isOutMap(new Point(nextX, nextY));
    }

    public boolean hitsOwnBody(Point nextHeading) {
        Point dir = (nextHeading != null) ? nextHeading : snake.getHeading();
        Point nextHead = new Point(snake.getHead().x + dir.x, snake.getHead().y + dir.y);
        return snake.tail.contains(nextHead);
    }

    public boolean hitsRock(Point nextHeading) {
        Point dir = (nextHeading != null) ? nextHeading : snake.getHeading();
        Point nextHead = new Point(snake.getHead().x + dir.x, snake.getHead().y + dir.y);
        return map.hasRockOnPosition(nextHead);
    }

    public Point moveSnakeAndCheckApple() {
        if (snake.nextHeading != null) {
            snake.changeHeading(snake.nextHeading);
            snake.nextHeading = null;
        }

        Point oldTail = snake.moveBody();
        Point head = snake.getHead();

        map.removeFromEmptyList(head);
        map.addToEmptyList(oldTail);

        if (head.equals(apple.getPosition())) {
            snake.addBodyPart();
            map.removeFromEmptyList(apple.getPosition());
            spawnApple();
            point += 1;
        }

        return oldTail;
    }

    public void spawnApple() {
        if (map.emptyFieldsIsEmpty()) return;
        Point newPos = map.getRandom();
        apple.setPosition(newPos);
    }


    public boolean gameIsOver() {
        return !isNextMoveValid(snake.nextHeading) ||
                hitsOwnBody(snake.nextHeading) ||
                hitsRock(snake.nextHeading);
    }
}