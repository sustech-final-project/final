package Games.Map;

import Games.GUI.GameFrame.layout.AfYLayout;

import javax.swing.*;
import java.awt.*;

public class test2 extends JFrame {
    public static void main(String[] args) throws InterruptedException {
        Frame f = new Frame("dsaf");
        JPanel timerPanel = new SoloTimer(new JPanel()).getPanel1();
        //JPanel left = new JPanel(new AfYLayout());
        //left.add(timerPanel);
        f.add(timerPanel);
        f.setSize(new Dimension(100, 50));
        f.setLocationRelativeTo(null);
        f.setVisible(true);
        Thread.sleep(2001);
        System.out.println(SoloTimer.gettime());

    }
}
