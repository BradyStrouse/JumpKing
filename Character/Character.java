package Character;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.Point;

public class Character extends Rectangle {
    private Color color = new Color(100, 0, 200);
    public Rectangle inner; // the inner color of the character
    private int xsmaller = 10, ysmaller = 10; // how much smaller the inner rect is going to be from the original rect
    // Constructors

    public Character(int width, int height) {
        setWidth(width);
        setHeight(height);
        declareInner();
    }

    public Character(int width, int height, int x, int y) {
        setWidth(width);
        setHeight(height);
        setLocation(new Point((int) x, (int) y));
        declareInner();
    }

    public Character(int width, int height, Color color) {
        setWidth(width);
        setHeight(height);
        this.color = color;
        declareInner();
    }

    public Character(int x, int y, int width, int height, Color color) {
        setWidth(width);
        setHeight(height);
        setLocation(new Point(x, y));
        this.color = color;
        declareInner();
    }

    // creates the inner rectangle that has color
    private void declareInner() {
        inner = new Rectangle(getintWidth() - xsmaller, getintHeight() - ysmaller);
        Point pon = getLocation();
        inner.setLocation(new Point((int) pon.getX() - xsmaller, (int) pon.getY() - ysmaller));
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void moveUp(int howMuch) {
        moveTo(getintX(), getintY() + howMuch);
    }

    public void moveDown(int howMuch) {
        moveTo(getintX(), getintY() - howMuch);
    }

    public void moveLeft(int howMuch) {
        moveTo(getintX() - howMuch, getintY());
    }

    public void moveRight(int howMuch) {
        moveTo(getintX() + howMuch, getintY());
    }

    // moves the center of the character to the point given
    public void moveTo(int x, int y) {
        Point newLocation = new Point(x - (int) (getWidth() / 2), y - (int) (getHeight() / 2));
        super.setLocation(newLocation);
        inner.setLocation(new Point((int) newLocation.getX() + xsmaller / 2, (int) newLocation.getY() + ysmaller / 2));
    }

    public void setHeight(int height) {
        setSize(new Dimension((int) getWidth(), height));
    }

    public void setWidth(int width) {
        setSize(new Dimension(width, (int) getHeight()));
    }

    public int getintX() {
        return (int) getX();
    }

    public int getintY() {
        return (int) getY();
    }

    public int getintWidth() {
        return (int) getWidth();
    }

    public int getintHeight() {
        return (int) getHeight();
    }

    public Color getColor() {
        return color;
    }
}