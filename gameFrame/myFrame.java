package gameFrame;

import Character.Character;
import hitBoxes.Hitboxes;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.Graphics;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import TimedEvents.TimedEvents;

import java.awt.Color;
import java.awt.Dimension;

public class myFrame extends JFrame {
    // The main controlled character
    private Character chara = new Character(50, 70, 250, 250);
    int level = 0;
    BufferedImage background = getbackground();
    /*
     * The number for calculations is VERY important,
     * it determins how often everything will be calcualted
     * Currently it runs at 144.1 times per second
     * once every about 6944 microseconds for precisions sake
     */
    private final long calculations = 4000;
    private final long paintTime = 6944;//miliseconds

    /*
     * The two main events that are seperate from eachother
     * 
     * rPaint = Repainting
     * cPhysics = calculate physcis
     */
    private static TimedEvents.repaint rPaint;
    private static TimedEvents.calculatePhysics cPhysics;
    private ScheduledExecutorService eService;

    // main panel that paints the game on it
    private JPanel pane;

    private Hitboxes[] hbox = new Hitboxes[2];

    public myFrame() {
        eService = Executors.newSingleThreadScheduledExecutor();
        TimedEvents TE = new TimedEvents();
        rPaint = TE.new repaint(this);
        cPhysics = TE.new calculatePhysics(this);
        hbox[0] = new Hitboxes(0, 1200, 700, 700);
        hbox[1] = new Hitboxes(900, 900, 0, 900);
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
                for (Hitboxes hit : hbox) {
                    System.out.println(chara);
                    g.drawLine(hit.getintX1(), hit.getintY1(), hit.getintX2(), hit.getintY2());
                    if(chara.getNextFrame().intersectsLine(hit)){
                        chara.interact(hit);
                    }
                }
            }
        };
        pane.setSize(size);
        pane.setBackground(new Color(255, 255, 255));
        setSize(size);
        add(pane);
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
        // if the vels are negative then they will move down
        chara.moveUp(chara.getintY_vel());
        chara.moveRight(chara.getintX_vel());
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
        eService.scheduleAtFixedRate(cPhysics, 0, calculations,
                TimeUnit.MICROSECONDS);
        eService.scheduleAtFixedRate(rPaint, 0, paintTime, TimeUnit.MICROSECONDS);
    }
}
