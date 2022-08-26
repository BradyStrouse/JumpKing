package gameFrame;


import javax.swing.JFrame;
import javax.swing.JPanel;
import Character.Character;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.Graphics;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


import TimedEvents.TimedEvents;
import hitBoxes.Hitbox;

import java.awt.Color;
import java.awt.Dimension;

public class myFrame extends JFrame {
    // The main controlled character
    private Character chara = new Character(50, 70, 250, 250,this);
    int level = 0;
    BufferedImage background = getbackground();

    /*
     * how often it will do certain things
     */
    private final long calculations = 3;//miliseconds
    private final long paintTime = 16;//miliseconds

    /*
     * The two main events that are seperate from eachother
     * 
     * rPaint = Repainting
     * cPhysics = calculate physcis
     */
    private static TimedEvents.repaint rPaint;
    private static TimedEvents.calculatePhysics cPhysics;
    //used to schedule tasks
    private ScheduledExecutorService eService;

    // main panel that paints the game on it
    private JPanel pane;

    private ArrayList<Hitbox> hbox = new ArrayList<Hitbox>();

    public myFrame() {
        try {
            setHitboxes(0);
        } 
        catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        eService = Executors.newSingleThreadScheduledExecutor();
        TimedEvents TE = new TimedEvents();
        rPaint = TE.new repaint(this);
        cPhysics = TE.new calculatePhysics(this);
        createAndMakeGUI();
    }
    
    private void createAndMakeGUI() {
        Dimension size = new Dimension(1200, 800);
        chara.moveTo((int) size.getWidth() / 2, (int) size.getHeight() / 2);
        pane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                g.drawImage(background, 0, 0, (int) size.getWidth(), (int) size.getHeight(), Color.BLACK, null);
                drawCharacter(g);
                g.setColor(Color.RED);
                for (Hitbox hit : hbox) {
                    g.drawLine(hit.getintX1(), hit.getintY1(), hit.getintX2(), hit.getintY2());
                }
            }
        };
        new Keybindings(chara, pane);
        pane.setSize(size);
        pane.setBackground(new Color(255, 255, 255));
        setSize(size);
        setResizable(false);
        add(pane);
        this.isAlwaysOnTop();
        setVisible(true);
        startGame();
    }

    private void drawCharacter(Graphics g) {
        g.fillRect(chara.getintX(), chara.getintY(), chara.getintWidth(), chara.getintHeight());
        g.setColor(chara.getColor());
        // draws the inner rect with color
        g.fillRect((int) chara.inner.getX(), (int) chara.inner.getY(), (int) chara.inner.getWidth(),
                (int) chara.inner.getHeight());
        }

    public void repaint() {
        pane.repaint();
        Toolkit.getDefaultToolkit().sync();
    }

    public void calculatePhysics() {
        chara.doGravity();     
        // if the vels are negative then the character will move the other direction
        chara.moveUp(chara.gety_vel());
        chara.moveRight(chara.getx_vel());
        // System.out.println(chara);
        for(Hitbox hit:hbox){
            if(chara.getNextFrame().intersectsLine(hit)){
                chara.interact(hit);
            }
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

    public void startGame() {
        TimeUnit unit = TimeUnit.MILLISECONDS;
        eService.scheduleAtFixedRate(cPhysics, 500, calculations, unit);
        eService.scheduleAtFixedRate(rPaint, 0, paintTime, unit);
    }
    private void setHitboxes(int level) throws FileNotFoundException{
        File currentLevel = new File("hitBoxes//level_" + level + ".txt");
        Scanner reader = new Scanner(currentLevel);
        hbox = new ArrayList<Hitbox>();
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
    public void setHitboxes()throws FileNotFoundException{
        File currentLevel = new File("hitBoxes//level_" + level + ".txt");
        Scanner reader = new Scanner(currentLevel);
        hbox = new ArrayList<Hitbox>();
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
}
