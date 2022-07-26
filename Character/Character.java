package Character;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Dimension;
public class Character{
    private double xcor = 0;
    private double ycor = 0;
    private Rectangle rect;
    private Color color = new Color(0, 0, 0);
    public Character(int width, int height){
        rect = new Rectangle(width, height);
    }
    public Character(double xcor, double ycor){
        this.xcor = xcor;
        this.ycor = ycor;
    }
    public Character(double xcor, double ycor, int width, int height){
        this.xcor = xcor;
        this.ycor = ycor;
        rect = new Rectangle(width, height);
    }



    private void setColor(Color color){
        this.color = color;
    }

    private void setx(double xcor){
        this.xcor = xcor;
    }
    private void sety(double ycor){
        this.ycor = ycor;
    }
    private void setHeight(int height){
        rect.setSize(new Dimension((int)rect.getWidth(), height));
    }
    private void setWidth(int width){
        rect.setSize(new Dimension(width, (int)rect.getHeight()));
    }


    
    private double getx(){
        return xcor;
    }
    private double gety(){
        return ycor;
    }
    private int getHeight(){
        return height;
    }
    private int getWidth(){
        return width;
    }
}