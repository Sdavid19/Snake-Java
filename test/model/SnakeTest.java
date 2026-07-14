package model;

import model.game.Snake;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class SnakeTest {

    private Snake snake;

    @BeforeEach
    void setup() {
        snake = new Snake(new Point(5, 5));
    }

    @Test
    void testConstructorInitialState() {
        assertEquals(new Point(5, 5), snake.getHead());
        assertTrue(snake.tail.isEmpty());
        assertNotNull(snake.getHeading());
    }

    @Test
    void testChangeHeading() {
        Point newHeading = new Point(0, 1);
        snake.changeHeading(newHeading);
        assertEquals(newHeading, snake.getHeading());
    }

    @Test
    void testAddBodyPartWhenTailEmpty() {
        snake.addBodyPart();
        assertEquals(1, snake.tail.size());
        assertEquals(new Point(5, 5), snake.tail.getFirst());
    }

    @Test
    void testAddBodyPartWhenTailNotEmpty() {
        snake.addBodyPart();
        snake.addBodyPart();
        assertEquals(2, snake.tail.size());
        assertEquals(snake.tail.get(0), snake.tail.get(1));
    }

    @Test
    void testMoveBody() {
        snake.changeHeading(new Point(0, 1));
        snake.addBodyPart();
        Point oldHead = new Point(snake.getHead().x, snake.getHead().y);
        Point oldTailEnd = snake.tail.getLast();
        Point removed = snake.moveBody();
        assertEquals(new Point(oldHead.x, oldHead.y + 1), snake.getHead());
        assertEquals(oldHead, snake.tail.getFirst());
        assertEquals(oldTailEnd, removed);
    }

    @Test
    void testMoveBodyWithMultipleTail() {
        snake.addBodyPart();
        snake.addBodyPart();
        snake.addBodyPart();
        int oldSize = snake.tail.size();
        Point oldHead = new Point(snake.getHead());
        Point lastBeforeMove = snake.tail.getLast();
        snake.changeHeading(new Point(1, 0));
        Point removed = snake.moveBody();
        assertEquals(new Point(oldHead.x + 1, oldHead.y), snake.getHead());
        assertEquals(oldHead, snake.tail.getFirst());
        assertEquals(oldSize, snake.tail.size());
        assertEquals(lastBeforeMove, removed);
    }
}
