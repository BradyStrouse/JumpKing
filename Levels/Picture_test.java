package Levels;

import java.awt.BorderLayout;
import javax.swing.*;
import java.awt.Dimension;

public class Picture_test {
    public Picture_test(int level_num) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new pictureFrame("..\\Square\\Levels\\Level_" + level_num + ".png").setVisible(true);
            }
        });
    }
}

class pictureFrame extends JFrame {
    public pictureFrame(String filePath) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(getWidth(), getHeight()));
        JScrollPane scrollPane = new JScrollPane(new JLabel(new ImageIcon(filePath)));
        // scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        // scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        setUndecorated(true);
        add(scrollPane, BorderLayout.CENTER);
        pack();
    }
}
