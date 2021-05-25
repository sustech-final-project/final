package Games.Map;

import Games.GUI.GameFrame.layout.AfYLayout;

import javax.swing.*;

public class test2 extends JFrame {
    public static void main(String[] args) throws InterruptedException {
        JPanel timerPanel = new SoloTimer(new JPanel()).getPanel1();
        JPanel left = new JPanel(new AfYLayout());
        left.add(timerPanel);
        Thread.sleep(2000);
        System.out.println(SoloTimer.gettime());

    }
}
