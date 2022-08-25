package hitBoxes;

import javax.swing.*;


import java.awt.event.*;
import java.awt.*;


import java.awt.image.BufferedImage;

import java.util.ArrayList;
import java.io.IOException;
import java.io.File;
import javax.imageio.ImageIO;

public class createWalls extends JFrame{
    Dimension size = new Dimension(1200, 800);
    JPanel pane;
    int x1, y1, x2, y2;
    int snapRange = 20; //the range that makes it snap to horizontal/vertical
    int level = 0;
    ArrayList<Hitbox> hbox = new ArrayList<Hitbox>();
    BufferedImage background = getbackground(0);
    public createWalls(){
        pane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                g.drawImage(background, 0, 0, (int) size.getWidth(), (int) size.getHeight(), Color.BLACK, null);
                g.setColor(Color.RED);
                g.drawLine(x1, y1, x2, y2);
                for(Hitbox line : hbox){
                    g.drawLine(line.getintX1(), line.getintY1(), line.getintX2(), line.getintY2());
                }
            }
        };
        pane.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                System.out.println("Pressed " + e.getKeyChar());
                if(e.getKeyChar() == 'z'){
                    hbox.remove(hbox.size()-1);
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
                //snaps vertical
                if(x1-snapRange <= x2  && x2 <= x1 + snapRange){
                    x2 = x1;
                }
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
                x2 = e.getX();
                y2 = e.getY();
                addHitbox();
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
    private void addHitbox(){
        hbox.add(new Hitbox(x1, x2, y1, y2));
        x1 = 0;
        x2 = 0;
        y1 = 0;
        y2 = 0;
    }
    private BufferedImage getbackground(int level) {
        try {
            return ImageIO.read(new File("Levels//Level_" + level + ".png"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }
}
