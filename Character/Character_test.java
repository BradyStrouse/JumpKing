package Character;

import javax.swing.JFrame;
import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;

public class Character_test extends JFrame {
    private Character chara = new Character(50, 70, 250, 250);

    public Character_test() {
        createAndMakeGUI();
    }

    private void createAndMakeGUI() {
        Dimension size = new Dimension(500, 500);
        chara.moveTo(250, 250);
        JPanel pane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                drawCharacter(g);
            }
        };
        pane.setSize(size);
        pane.setBackground(new Color(255, 255, 255));
        setSize(size);
        add(pane);
        setVisible(true);
    }

    private void drawCharacter(Graphics g) {
        g.fillRect(chara.getintX(), chara.getintY(), chara.getintWidth(), chara.getintHeight());
        g.setColor(chara.getColor());
        // draws the inner rect with color
        g.fillRect((int) chara.inner.getX(), (int) chara.inner.getY(), (int) chara.inner.getWidth(),
                (int) chara.inner.getHeight());
    }
}
