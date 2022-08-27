package Character;

import hitBoxes.Hitbox;
import hitBoxes.Hitbox;

import java.awt.Color;
import java.awt.Rectangle;
import java.io.FileNotFoundException;
import java.awt.Dimension;
import java.awt.Point;

import java.lang.Math;

import gameFrame.myFrame;

public class Character extends Rectangle {

    myFrame frame;

    private Color color = new Color(100, 0, 200);
    public Rectangle inner; // the inner color of the character

    private boolean canControl = true;

    private int xsmaller = 10, ysmaller = 10; // how much smaller the inner rect is going to be from the original rect

    boolean onGround = false;

    private double gravity = .015;
    private double x_vel = 0;
    private double y_vel = -4.5;

    private boolean charging = false;
    private double chargeAmount;

    int count = 0;

    private double groundSpeed = 1;
    private double airSpeed = 1.5;

    private int moving = 0; /*0 = not moving
                              *1 = moving left
                              *2 = moving right
                              *this is used for the jumping
                              */
    public String toString(){
        return " x_vel: " + x_vel +
        " y_vel: " + y_vel +
        " x: " + getX() +
        " y: " + getY() + "\n";
    }

    // Constructors
    
    /*
     * this first one is used to generate characters that 
     * arent meant to be used for GUI
     */
    public Character(Character chara){
        setWidth(chara.getintWidth());
        setHeight(chara.getintHeight());
        setx_vel(chara.getx_vel());
        sety_vel(chara.gety_vel());
        inner = null;
    }

    /*
     * Different constructors
     */
    public Character(int width, int height) {
        setWidth(width);
        setHeight(height);
        declareVariables();
    }

    public Character(int width, int height, int x, int y, myFrame frame) {
        setWidth(width);
        setHeight(height);
        setLocation(new Point((int) x, (int) y));
        declareVariables();
        this.frame = frame;
    }

    public Character(int width, int height, Color color) {
        setWidth(width);
        setHeight(height);
        this.color = color;
        declareVariables();
    }

    public Character(int x, int y, int width, int height, Color color) {
        setWidth(width);
        setHeight(height);
        setLocation(new Point(x, y));
        this.color = color;
        declareVariables();
    }


    public void interact(Hitbox hit){
        if(hit.getVertical()){
            if(x_vel > 0){
                moveLeft(1);
            }
            else if(x_vel < 0){
                moveRight(1);
            }
            if(onGround){
                x_vel = 0;
            }
            x_vel = (x_vel*-.6);
        }
        else if(hit.getHorizontal() && y_vel > 0) {
            onGround = true;
            y_vel = 0;
            if(moving == 1){
                x_vel = -groundSpeed;
            }
            else if(moving == 2){
                x_vel = groundSpeed;
            }
            else{
                x_vel = 0;
            }
            moveTo(getintX(), hit.getintY1()-height-1);
        }
    }
    // creates the inner rectangle that has color
    private void declareVariables() {
        inner = new Rectangle(getintWidth() - xsmaller, getintHeight() - ysmaller);
        Point pon = getLocation();
        inner.setLocation(new Point((int) pon.getX() - xsmaller, (int) pon.getY() - ysmaller));
    }

    // inner color
    public void setColor(Color color) {
        this.color = color;
    }

    public void startCharging(){
        if (charging || !onGround) return;
        x_vel = 0;
        crouch();
    }

    public void crouch(){
        this.setSize(new Dimension(getintWidth(), getintHeight()/2));
        inner.setSize(new Dimension(getintWidth()-10, getintHeight()/2+7));
        charging = true;
        moveDown(width/2-20);
    }

    public void stand(){
        this.setSize(new Dimension(getintWidth(), getintHeight()*2));
    }
    public void stopCharging(){
        if(!onGround) return;
        if(!charging) return;
        charging = false;
        onGround = false;
        stand();
        jump();
    }
    public void jump(){
        declareVariables(); //puts the inner square in the correct place
        if(moving == 1) x_vel = -airSpeed;
        if(moving == 2) x_vel = airSpeed;
        y_vel = chargeAmount;
        moveUp(width/2);
        chargeAmount = 0;
        //TODO: delete later
        try {
            frame.setHitboxes();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void stepLeft(){
        moving = 1;
        if(charging) return;
        if(!canControl) return;
        x_vel = -groundSpeed;
    }
    public void stepRight(){
        moving = 2;
        if(charging) return;
        if(!canControl) return;
        x_vel = groundSpeed;
    }
    public void enableControls(){
        canControl = true;
    }
    public void disableControls(){
        canControl = false;
    }
    /*
    * MOVEMENT FOR DOUBLE
    */
    public void moveUp(double howMuch) {
        moveTo(getX(), getY() + howMuch);
    }
    
    public void moveDown(double howMuch) {
        moveTo(getX(), getY() + howMuch);
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
        moveTo(getintX(), getintY() - howMuch);
    } 
    
    public void moveDown(int howMuch) {
        moveTo(getintX(), getintY() + howMuch);
    }
    
    public void moveLeft(int howMuch) {
        moveTo(getintX() - howMuch, getintY());
    }

    public void moveRight(int howMuch) {
        moveTo(getintX() + howMuch, getintY());
    }
    
    public void doGravity() {
        if(charging && chargeAmount > -3.65){
            chargeAmount -= .03;
        }
        if(charging)
            return;
        if (y_vel > 20)
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
        ,this.getintY()+this.getintY_vel());       
        return nextFrame;
    }
    
    public void stop(){
        moving = 0;
        if(!onGround) return;
        x_vel = 0;
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
        /*  math.round twice bc it needs to go as follows
         *  double -> long -> int
         */
        return Math.round(Math.round(x_vel));
    }

    public int getintY_vel() {
        /*  math.round twice bc it needs to go as follows
         *  double -> long -> int
         */
        return Math.round(Math.round(y_vel));
    }

    public double getx_vel() {
        return x_vel;
    }

    public double gety_vel() {
        return y_vel;
    }

    public int getMoving(){
        return moving;
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