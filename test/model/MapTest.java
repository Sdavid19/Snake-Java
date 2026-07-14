package model;

import model.game.Map;
import model.game.mapElement.Rock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MapTest {

    private Map map;

    @BeforeEach
    void setup() {
        map = new Map();
    }

    @Test
    void testEmptyFieldsNotEmptyAfterInit() {
        assertFalse(map.emptyFieldsIsEmpty());
    }

    @Test
    void testAddToEmptyList() {
        Point p = new Point(1, 1);
        map.addToEmptyList(p);
        assertTrue(map.getRandom() != null);
    }

    @Test
    void testRemoveFromEmptyList() {
        Point p = new Point(1, 1);
        map.removeFromEmptyList(p);
        // ha még egyszer eltávolítjuk, nem dob hibát
        map.removeFromEmptyList(p);
        assertTrue(true);
    }

    @Test
    void testIsOutMap() {
        assertTrue(map.isOutMap(new Point(0, 0)));
        assertTrue(map.isOutMap(new Point(14, 16)));

        assertFalse(map.isOutMap(new Point(-1, 0)));
        assertFalse(map.isOutMap(new Point(0, 17)));
        assertFalse(map.isOutMap(new Point(15, 0)));
    }

    @Test
    void testGetRandomReturnsEmptyField() {
        Point p = map.getRandom();
        assertNotNull(p);
        assertTrue(map.isOutMap(p));
    }

    @Test
    void testSpawnRocksCreatesCorrectAmount() {
        Point start = new Point(0, 0);
        map.spawnRocks(start);

        ArrayList<Rock> rocks = map.getRocks();
        assertEquals(Map.ROCK_COUNT, rocks.size());
    }

    @Test
    void testSpawnRocksRespectsDistance() {
        Point start = new Point(5, 5);
        map.spawnRocks(start);

        for (Rock r : map.getRocks()) {
            int manhattan = Math.abs(r.getPosition().x - start.x)
                    + Math.abs(r.getPosition().y - start.y);

            assertTrue(manhattan >= Map.ROCK_DISTANCE);
        }
    }

    @Test
    void testHasRockOnPosition() {
        Point start = new Point(0, 0);
        map.spawnRocks(start);

        Rock r = map.getRocks().get(0);

        assertTrue(map.hasRockOnPosition(r.getPosition()));
    }
}
