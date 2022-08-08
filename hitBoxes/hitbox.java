package hitBoxes;

import java.awt.Rectangle;
import java.awt.geom.Line2D;
public class Hitbox extends Line2D.Double {

    private int x1, x2, y1, y2;
    private boolean horizontal, vertical, diagonal;

    public Hitbox(int x1, int x2, int y1, int y2) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        horizontal = x1 == x2;
        vertical = y1 == y2;
        diagonal = !(vertical && horizontal);
    }

    public Hitbox(int x1, int x2, int y1, int y2, int height) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        horizontal = x1 == x2;
        vertical = y1 == y2;
        diagonal = !(vertical && horizontal);

    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

    public boolean getVertical(){
        return vertical;
    }
    public boolean getHorizontal(){
        return horizontal;
    }
    public boolean getDiagonal(){
        return diagonal;
    }
    public double getX1() {
        return x1;
    }

    public int getX2() {
        return x2;
    }

    public int getY1() {
        return y1;
    }

    public int getY2() {
        return y2;
    }
}
