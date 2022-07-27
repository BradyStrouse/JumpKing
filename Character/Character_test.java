package Character;

import javax.swing.JFrame;
import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
public class Character_test extends JFrame{
    public Character_test(){
        createAndMakeGUI();
    }
    private void createAndMakeGUI(){
        Dimension size = new Dimension(500, 500);
        Character chara = new Character(50, 50, 250, 250);
        JPanel pane = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                g.setColor(chara.getColor());
                System.out.println(chara.getintWidth());
                g.fillRect(chara.getintX(), chara.getintY(), chara.getintWidth(), chara.getintHeight());
            }
        };
        pane.setSize(size);
        pane.setBackground(new Color(255,255,255));
        setSize(size);
        add(pane);
        setVisible(true);
    }
}
