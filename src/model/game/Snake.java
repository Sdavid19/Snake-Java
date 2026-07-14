package model.game;

import java.awt.*;
import java.util.LinkedList;
import java.util.Random;

public class Snake {

    private Point head;
    public LinkedList<Point> tail;
    private Point heading;

    private static final Point[] HEADINGS = {
            new Point(-1, 0), // fel
            new Point(1, 0),  // le
            new Point(0, -1), // balra
            new Point(0, 1)   // jobbra
    };

    public Point nextHeading = null;

    public Snake(Point head) {
        this.head = head;
        this.tail = new LinkedList<>();
        Random rand = new Random();
        this.heading = HEADINGS[rand.nextInt(HEADINGS.length)];
    }

    public void changeHeading(Point newHeading) {
        this.heading = newHeading;
    }

    public Point getHeading() {
        return this.heading;
    }

    public Point getHead(){
        return this.head;
    }

    public void addBodyPart() {
        if (tail.isEmpty()) {
            tail.add(new Point(head.x, head.y));
        } else {
            Point last = tail.getLast();
            tail.add(new Point(last.x, last.y));
        }
    }

    public Point moveBody() {
        Point oldHead = new Point(head.x, head.y);

        head = new Point(head.x + heading.x, head.y + heading.y);

        tail.addFirst(oldHead);
        return tail.removeLast();
    }


}
