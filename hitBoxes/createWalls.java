package hitBoxes;

import javax.swing.*;

import TimedEvents.TimedEvents.repaint;

import java.awt.event.*;
import java.awt.*;


import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Scanner;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.File;
import java.io.FileWriter;

import javax.imageio.ImageIO;

public class createWalls extends JFrame{
    int sizeMod = 2; //the smaller the bigger
    Dimension size = new Dimension(1000, 750);
    JPanel pane;
    int x1, y1, x2, y2;
    int snapRange = 20; //the range that makes it snap to horizontal/vertical
    int level = 0;
    ArrayList<Hitbox> hbox = new ArrayList<Hitbox>();
    BufferedImage background = getbackground();
    public createWalls(){
        try {
            setHitboxes(level);
        } catch (FileNotFoundException e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
        }
        pane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                g.drawImage(background, 0, 0, (int) size.getWidth(), (int) size.getHeight(), Color.BLACK, null);
                g.setColor(Color.RED);
                g.drawLine(x1, y1, x2, y2);
                Hitbox line;
                char[] id;
                for(int i = 0; i < hbox.size(); i++){
                    line = hbox.get(i);
                    // g.setColor(Color.BLACK);
                    id = Integer.toString(i+1).toCharArray();
                    g.drawChars(id, 0, id.length, 
                                line.getMiddleX(), line.getMiddleY());
                    g.drawLine(line.getintX1(), line.getintY1(), line.getintX2(), line.getintY2());
                }
            }
        };
        pane.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                System.out.println("Pressed " + e.getKeyChar());
                if(e.getKeyChar() == 'z'){
                    try {
                        undo();
                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }
                repaint();
            }

            @Override
            public void keyReleased(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
            }
        });
        pane.addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                //On Dragg:
                x2 = e.getX();
                y2 = e.getY();
                //snaps to points
                for(Hitbox line : hbox){
                    int lineY1 = line.getintY1();
                    int lineX1 = line.getintX1();
                    int lineX2 = line.getintX2();
                    int lineY2 = line.getintY2();
                    if(inRange(x1, snapRange, lineX1)){
                        x1 = lineX1;
                    }
                    if(inRange(x1, snapRange, lineX2)){
                        x1 = x2;
                    }
                    if(inRange(x2, snapRange, lineX2)){
                        x2 = lineX2;
                    }
                    if(inRange(x2, snapRange, lineX1)){
                        x2 = lineX1;
                    }
                    if(inRange(y1, snapRange, lineY1)){
                        y1 = lineY1;
                    }
                    if(inRange(y2, snapRange, lineY1)){
                        y2 = lineY1;
                    }
                    if(inRange(y2, snapRange, lineY2)){
                        y2 = lineY2;
                    }
                    if(inRange(y1, snapRange, lineY2)){
                        y2 = lineY2;
                    }
                    
                }
                //snaps vertical
                if(x1-snapRange <= x2  && x2 <= x1 + snapRange){
                    x2 = x1;
                }
                //snaps horizontal
                if(y1-snapRange <= y2  && y2 <= y1 + snapRange){
                    y2 = y1;
                }
                repaint();
            }
        });
        pane.addMouseListener(new MouseListener(){
            @Override
            public void mousePressed(MouseEvent e) {
                // TODO Auto-generated method stub
                x1 = e.getX();
                y1 = e.getY();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub
                try {
                    addHitbox();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                repaint();
                repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                // TODO Auto-generated method stub
                
            }
        });
        pane.setFocusable(true);
        getContentPane().add(pane);
        setSize(size);
        requestFocusInWindow();
        setVisible(true);
    }

    //returns true if the selected number is within the box range of the other number
    private boolean inRange(int num1, int range, int num2){
        return num1-range <= num2 && num1 + range >= num2;
    }
    private void setHitboxes(int level) throws FileNotFoundException{
        File currentLevel = new File("hitBoxes//level_" + level + ".txt");
        Scanner reader = new Scanner(currentLevel);
        hbox = new ArrayList<Hitbox>();
        if(reader.hasNextLine()) reader.nextLine();
        while(reader.hasNextLine()){
            String currentWall = reader.nextLine();
            String[] strCoords = currentWall.split(",");
            int[] coords = new int[strCoords.length];
            for(int i = 0; i < coords.length; i++){
                coords[i] = Integer.parseInt(strCoords[i]);
            }
            hbox.add(new Hitbox(coords[0], coords[1], coords[2], coords[3]));
        }        
    }
    private void addHitbox() throws IOException{
        hbox.add(new Hitbox(x1, x2, y1, y2));
        try {
            FileWriter myWriter = new FileWriter("\nhitBoxes//level_"+level+".txt", true);
            myWriter.append(Integer.toString(x1) + ',' + Integer.toString(x2) + ',' +
            Integer.toString(y1) + ',' + Integer.toString(y2) + "\n");
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        }
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        
        x1 = 0;
        x2 = 0;
        y1 = 0;
        y2 = 0;
    }
    private void undo() throws IOException{
        if(hbox.size() == 0) return;
        hbox.remove(hbox.size()-1);
        String fileName = "hitBoxes//level_" + level +".txt";
        RandomAccessFile f = new RandomAccessFile(fileName, "rw");
        long length = f.length();
        byte b = 0;
        do {                     
            f.seek(length);
            length -= 1;
            b = f.readByte();
        } while(b != 10 && length > 0);
        f.setLength(length+1);
        f.close();
    }
    private void nextLevel() throws IOException{
        level++;
        File myObj = new File("Level_" + level+ ".txt");
        if (myObj.createNewFile()) {
            System.out.println("File created: " + myObj.getName());
        } else {
            System.out.println("File already exists.");
        }
    }
    private BufferedImage getbackground() {
        try {
            return ImageIO.read(new File("Levels//Level_" + level + ".png"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }
}
