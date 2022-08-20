package Character;

import hitBoxes.Hitboxes;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.Point;

import java.lang.Math;

public class Character extends Rectangle {
    private Color color = new Color(100, 0, 200);
    public Rectangle inner; // the inner color of the character
    private int xsmaller = 10, ysmaller = 10; // how much smaller the inner rect is going to be from the original rect
    boolean onGround = false;
    private double gravity = .02;
    private double x_vel = 1;
    private double y_vel = -4.5;
    private boolean charging = false;
    private double chargeAmount;
    
    public String toString(){
        return " x_vel: " + x_vel +
        " y_vel: " + y_vel +
        " x: " + getX() +
        " y: " + getY() + "\n";
    }

    // Constructors
    
    /*
     * used to generate characters in the future or past, hence why theres no inner
     */
    public Character(Character chara){
        setWidth(chara.getintWidth());
        setHeight(chara.getintHeight());
        setx_vel(chara.getx_vel());
        sety_vel(chara.gety_vel());
        inner = null;
    }
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
    public void interact(Hitboxes hit){
        if(hit.getHorizontal()) {
            onGround = true;
            x_vel = 0;
            y_vel = 0;
            moveTo(getintX(), hit.getintY1()-height);
        }
        else if(hit.getVertical()){
            moveLeft(1);
            x_vel = (x_vel*-.6);
        }
    }
    // creates the inner rectangle that has color
    private void declareInner() {
        inner = new Rectangle(getintWidth() - xsmaller, getintHeight() - ysmaller);
        Point pon = getLocation();
        inner.setLocation(new Point((int) pon.getX() - xsmaller, (int) pon.getY() - ysmaller));
    }

    // inner color
    public void setColor(Color color) {
        this.color = color;
    }

    public void chargeJump(){
        charging = true;
    }
    public void stepLeft(){
        if(!onGround) return;
        x_vel = 3;
    }
    public void stepRight(){
        if(!onGround) return;
        x_vel = -3;
    }
    /*
    * MOVEMENT FOR DOUBLE
    */
    public void moveUp(double howMuch) {
        moveTo(getX(), getY() + howMuch);
    }
    
    public void moveDown(double howMuch) {
        moveTo(getX(), getY() - howMuch);
    }
    
    public void moveLeft(double howMuch) {
        moveTo(getX() - howMuch, getY());
    }
    
    public void moveRight(double howMuch) {
        moveTo(getX() + howMuch, getY());
    }
    
    /*
    * MOVEMENT FOR INTEGERS
    */
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
    
    public void doGravity() {
        if(charging && chargeAmount < 4){
            chargeAmount += .05;
        }
        if (y_vel > 20)
        return;
        if (onGround)
        return;
        // adding makes it move down (ik its weird)
        y_vel += gravity;
    }
    
    public void moveTo(int x, int y) {
        Point newLocation = new Point(x, y);
        super.setLocation(newLocation);
        if(inner == null) return;
        inner.setLocation(new Point((int) newLocation.getX() + xsmaller / 2, (int) newLocation.getY() + ysmaller / 2));
    }
    public void moveTo(double x, double y) {
        Point newLocation = new Point();
        newLocation.setLocation(x, y);
        super.setLocation(newLocation);
        if(inner == null) return;
        inner.setLocation(new Point((int) newLocation.getX() + xsmaller / 2, (int) newLocation.getY() + ysmaller / 2));
    }

    public Character getNextFrame(){
        Character nextFrame = new Character(this);
        nextFrame.moveTo(this.getintX()+this.getintX_vel()
        ,this.getintY()+this.getintX_vel());       
        return nextFrame;
    }
    
    public void setHeight(int height) {
        setSize(new Dimension(getintWidth(), height));
    }

    public void setWidth(int width) {
        setSize(new Dimension(width, getintHeight()));
    }
    
    public void setx_vel(double newVel) {
        x_vel = newVel;
    }

    public void sety_vel(double newVel) {
        y_vel = newVel;
    }

    public int getintX() {
        // math.round twice bc it needs to go from double to long to int
        return Math.round(Math.round(getX()));
        // return (int) getX();
    }
    
    public int getintY() {
        return Math.round(Math.round(getY()));
        // return (int)super.getY();
    }

    public int getintX_vel() {
        // math.round twice bc it needs to go from double to long to int
        return Math.round(Math.round(x_vel));
    }

    public int getintY_vel() {
        // math.round twice bc it needs to go from double to long to int
        return Math.round(Math.round(y_vel));
    }

    public double getx_vel() {
        return x_vel;
    }

    public double gety_vel() {
        return y_vel;
    }


    public int getintWidth() {
        return Math.round(Math.round(getWidth()));
    }

    public int getintHeight() {
        return Math.round(Math.round(getHeight()));
    }

    public Color getColor() {
        return color;
    }
}