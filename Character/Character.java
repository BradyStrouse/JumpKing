package Character;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.Point;
public class Character extends Rectangle{
    private Color color = new Color(0, 0, 0);

    //Constructors
    public Character(){}
    public Character(int width, int height){
        setWidth(width);
        setHeight(height);
    }
    public Character(int width, int height, int x, int y){
        setWidth(width);
        setHeight(height);
        setLocation(new Point((int)x, (int)y));
    }
    public Character(int width, int height, Color color){
        setWidth(width);
        setHeight(height);
        this.color = color;
    }
    public Character(int x, int y, int width, int height, Color color){
        setWidth(width);
        setHeight(height);
        setLocation(new Point(x,y));
        this.color = color;
    }

    public void setColor(Color color){
        this.color = color;
    }
    public void setHeight(int height){
        setSize(new Dimension((int)getWidth(), height));
    }
    public void setWidth(int width){
        setSize(new Dimension(width, (int)getHeight()));
    }

    public int getintX(){
        return (int) getX();
    }
    public int getintY(){
        return (int) getY();
    }
    public int getintWidth(){
        return (int) getWidth();
    }
    public int getintHeight(){
        return (int) getHeight();
    }
    public Color getColor(){
        return color;
    }
}