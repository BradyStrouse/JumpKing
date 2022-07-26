package Character;

import javax.swing.JFrame;
import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.Dimension;
public class Character_test extends JFrame{
    public Character_test(){
        createAndMakeGUI();
    }
    private void createAndMakeGUI(){
        Dimension size = new Dimension(500, 500);
        Character rect = new Character();
        JPanel pane = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                g.drawRect(rect.getintX(), rect.getintY(), rect.getintWidth(), rect.getintHeight());
            }
        };
        pane.setSize(size);
        setSize(size);
        add(pane);
        setVisible(true);
    }
}
