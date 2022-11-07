package ru.nsu.ccfit.lopatkin.snakes.game.cell;

import java.io.Serializable;

public record Point(int x, int y) implements Serializable {

    @Override
    public boolean equals(Object p) {
        if (this == p) {
            return true;
        }
        if (!(p instanceof Point)) {
            return false;
        }
        Point tmp = (Point) p;
        return x == tmp.x && y == tmp.y;
    }

    public int getY() {
        return this.y;
    }

    public int getX() {
        return this.x;
    }
}
