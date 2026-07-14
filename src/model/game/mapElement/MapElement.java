package model.game.mapElement;

import java.awt.*;

public class MapElement {
    protected Point position;

    public MapElement() {
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = new Point(position.x, position.y);
    }
}
