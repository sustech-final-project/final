package Games.GUI.GameFrame;

import Games.components.Music;

import javax.swing.*;

public class test1 {
    public static void main(String[] args) throws InterruptedException {
        Tnt tnt = new Tnt();
        tnt.setVisible(true);
        ImageIcon tnt1 = new ImageIcon("src\\Games\\gif\\tnt_3 5.gif");
        JLabel label = new JLabel(tnt1);

        tnt.setSize(2000,1000);
        //tnt.pack();
        tnt.setLocationRelativeTo(null);
        tnt.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tnt.setVisible(true);
        Thread.sleep(3700);
        Music.stopMusic();
        tnt.dispose();
    }
}
