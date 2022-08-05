package Character;

import javax.swing.JFrame;
import java.awt.Graphics;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JPanel;

import TimedEvents.TimedEvents;

import java.awt.Color;
import java.awt.Dimension;

public class Character_test extends JFrame {
    private Character chara = new Character(50, 70, 250, 250);
    private final long calculations = 6944;
    private final long paintTime = 6944;
    private static TimedEvents.repaint rPaint;// repaints every clock amount of time
    private static TimedEvents.calculatePhysics cPhysics;// Used to calcualte physics
    private ScheduledExecutorService eService;

    public Character_test() {
        eService = Executors.newSingleThreadScheduledExecutor();
        TimedEvents TE = new TimedEvents();
        rPaint = TE.new repaint(this);
        cPhysics = TE.new calculatePhysics(this);
        createAndMakeGUI();
    }

    private void createAndMakeGUI() {
        Dimension size = new Dimension(500, 500);
        chara.moveTo(250, 250);
        JPanel pane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                System.out.println(chara.getintY());
                drawCharacter(g);
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
        repaint();
    }

    public void calculatePhysics() {
        chara.doGravity();
    }

    public void startGame() {
        // eService.scheduleAtFixedRate(cPhysics, 0, calculations,
        // TimeUnit.MICROSECONDS);
        eService.scheduleAtFixedRate(rPaint, 0, paintTime, TimeUnit.MICROSECONDS);
    }
}
