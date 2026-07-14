package model;

import model.game.Game;
import model.game.mapElement.Rock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    private Game game;

    @BeforeEach
    void setup() {
        game = new Game();
    }

    @Test
    void testInitialState() {
        assertNotNull(game.map);
        assertNotNull(game.snake);
        assertNotNull(game.apple);

        assertEquals(0, game.point);

        int size = 1 + game.snake.tail.size();
        assertEquals(2, size);
    }

    @Test
    void testSpawnAppleNotOnSnakeOrRock() {
        Point applePos = game.apple.getPosition();

        assertFalse(game.snake.tail.contains(applePos));
        assertFalse(game.snake.getHead().equals(applePos));

        for (Rock r : game.map.getRocks()) {
            assertNotEquals(r.getPosition(), applePos);
        }
    }

    @Test
    void testNextMoveValidInsideMap() {
        game.snake.changeHeading(new Point(0, 1));
        assertTrue(game.isNextMoveValid(game.snake.getHeading()));
    }

    @Test
    void testHitsOwnBody() {
        Point next = new Point(game.snake.getHead().x, game.snake.getHead().y + 1);
        game.snake.tail.add(next);

        game.snake.changeHeading(new Point(0, 1));

        assertTrue(game.hitsOwnBody(game.snake.getHeading()));
    }

    @Test
    void testHitsRock() {
        Point rockPos = new Point(game.snake.getHead().x, game.snake.getHead().y + 1);
        game.map.getRocks().add(new Rock(rockPos));

        game.snake.changeHeading(new Point(0, 1));

        assertTrue(game.hitsRock(game.snake.getHeading()));
    }

    @Test
    void testMoveSnakeAndEatApple() {
        Point target = new Point(game.snake.getHead().x, game.snake.getHead().y + 1);
        game.apple.setPosition(target);

        int oldSize = 1 + game.snake.tail.size();
        int oldPoints = game.point;

        game.snake.changeHeading(new Point(0, 1));
        game.moveSnakeAndCheckApple();

        int newSize = 1 + game.snake.tail.size();

        assertEquals(oldSize + 1, newSize);
        assertEquals(oldPoints + 1, game.point);

        assertNotEquals(target, game.apple.getPosition());
    }
}
