// package Character;

// import javax.swing.JFrame;
// import java.awt.Graphics;
// import java.util.concurrent.Executors;
// import java.util.concurrent.ScheduledExecutorService;
// import java.util.concurrent.TimeUnit;

// import javax.swing.JPanel;

// import TimedEvents.TimedEvents;

// import java.awt.Color;
// import java.awt.Dimension;

// public class Character_test extends JFrame {
// // The main controlled character
// private Character chara = new Character(50, 70, 250, 250);

// /*
// * The number for calculations is VERY important,
// * it determins how often everything will be calcualted
// * Currently it runs at 144.1 times per second
// * once every about 6944 microseconds for precisions sake
// */
// private final long calculations = 6944;
// private final long paintTime = 6944;

// /*
// * The two main events that are seperate from eachother
// *
// * rPaint = Repainting
// * cPhysics = physcis
// */
// private static TimedEvents.repaint rPaint;
// private static TimedEvents.calculatePhysics cPhysics;
// private ScheduledExecutorService eService;

// // main panel that paints the game on it
// private JPanel pane;

// public Character_test() {
// eService = Executors.newSingleThreadScheduledExecutor();
// TimedEvents TE = new TimedEvents();
// rPaint = TE.new repaint(this);
// cPhysics = TE.new calculatePhysics(this);
// createAndMakeGUI();
// }

// private void createAndMakeGUI() {
// Dimension size = new Dimension(800, 800);
// chara.moveTo((int) size.getWidth() / 2, (int) size.getHeight() / 2);
// pane = new JPanel() {
// @Override
// protected void paintComponent(Graphics g) {
// drawCharacter(g);
// }
// };
// pane.setSize(size);
// pane.setBackground(new Color(255, 255, 255));
// setSize(size);
// add(pane);
// setVisible(true);
// startGame();
// }

// private void drawCharacter(Graphics g) {
// g.fillRect(chara.getintX(), chara.getintY(), chara.getintWidth(),
// chara.getintHeight());
// g.setColor(chara.getColor());
// // draws the inner rect with color
// g.fillRect((int) chara.inner.getX(), (int) chara.inner.getY(), (int)
// chara.inner.getWidth(),
// (int) chara.inner.getHeight());
// }

// public void repaint() {
// super.repaint();
// }

// public void calculatePhysics() {
// chara.doGravity();
// // if the vels are negative then they will move down
// chara.moveUp(chara.getintY_vel());
// chara.moveRight(chara.getintX_vel());
// }

// public void startGame() {
// eService.scheduleAtFixedRate(cPhysics, 0, calculations,
// TimeUnit.MICROSECONDS);
// eService.scheduleAtFixedRate(rPaint, 0, paintTime, TimeUnit.MICROSECONDS);
// }
// }
