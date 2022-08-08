package hitBoxes;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
public class Hitboxes extends Line2D.Double{

    private boolean horizontal, vertical, diagonal;
    Point2D p1;
    Point2D p2;
    public Hitboxes(int x1, int x2, int y1, int y2) {
        p1 = new Point2D.Double(x1, y1);
        p2 = new Point2D.Double(x2, y2);
        super.setLine(p1,p2);
        horizontal = x1 == x2;
        vertical = y1 == y2;
        diagonal = !(vertical && horizontal);
    }

    public Hitboxes(int x1, int x2, int y1, int y2, int height) {
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
    public int getintX1() {
        return (int) super.getX1();
    }
    public int getintX2(){
        return (int) super.getX2();
    }
    public int getintY1(){
        return (int) super.getY1();
    }
    public int getintY2(){
        return (int) super.getY2();
    }
}
