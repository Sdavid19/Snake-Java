package model.game;

import model.game.mapElement.Rock;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class Map {
    private static final int ROWS = 15;
    private static final int COLS = 17;

    public static final int ROCK_DISTANCE = 5;
    public static final int ROCK_COUNT = 5;

    private ArrayList<Rock> rocks;
    private final Random random = new Random();
    private final ArrayList<Point> emptyFields;

    public Map(){
        emptyFields = new ArrayList<>();
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                emptyFields.add(new Point(i, j));
            }
        }
    }

    public void addToEmptyList(Point p){
        emptyFields.add(p);
    }

   public  void removeFromEmptyList(Point p){
        emptyFields.remove(p);
    }

    public void removeAllFromEmptyList(LinkedList<Point> p){
        emptyFields.removeAll(p);
    }

    public boolean isOutMap(Point p){
        return p.x >= 0 && p.x < ROWS && p.y >= 0 && p.y < COLS;
    }

    public Point getRandom(){
        return emptyFields.get(random.nextInt(emptyFields.size()));
    }

    public boolean emptyFieldsIsEmpty(){
        return emptyFields.isEmpty();
    }


    public void spawnRocks(Point startPoint) {
        rocks = new ArrayList<>();
        ArrayList<Point> available = new ArrayList<>();

        for (Point p : emptyFields) {
            if (Math.abs(p.x - startPoint.x) + Math.abs(p.y - startPoint.y) >= Map.ROCK_DISTANCE) {
                available.add(p);
            }
        }

        for (int i = 0; i < Map.ROCK_COUNT && !available.isEmpty(); i++) {
            Point pos = available.remove(random.nextInt(available.size()));
            rocks.add(new Rock(pos));
            emptyFields.remove(pos);
        }
    }

    public boolean hasRockOnPosition(Point position){
        return rocks.stream().anyMatch(r -> position.equals(r.getPosition()));
    }

    public ArrayList<Rock> getRocks(){
        return rocks;
    }

}