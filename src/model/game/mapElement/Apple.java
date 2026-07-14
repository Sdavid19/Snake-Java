package model.game.mapElement;

import java.awt.*;

public class Apple extends MapElement {

    private Point position;

    public Apple() {
    }

    public void setPosition(Point position) {
        this.position = new Point(position.x, position.y);
    }

    public Point getPosition() {
        return new Point(position.x, position.y);
    }
}
