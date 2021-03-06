package Games.GUI.GameFrame;

import Games.GUI.GameFrame.layout.AfYLayout;
import Games.Map.Map;
import Games.Map.Player;
import Games.components.pic;
import Games.listener.GameController;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static Games.GUI.GameFrame.MainLocal.f00;
import static Games.GUI.GameFrame.MainLocal.f02;

public class F04 extends JFrame {
    GameController gc;
    JLabel bg = new JLabel();
    JButton play;
    JLabel label;
    public F04(String title, GameController gc){
        super(title);
        this.gc = gc;
        setLocationRelativeTo(null);
        Container contentPane = getContentPane();
        contentPane.setLayout(new Layout());
        System.out.println(gc.whoWin());
        if (!gc.whoWin().contains("平局")){
            label = new JLabel("Congratulation, " + gc.whoWin().toString() + ", wins!");
        }
        else label = new JLabel("We have a tied match!");
        play = new JButton("Play Again.");
        play.addActionListener(e -> {
            javax.swing.SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    dispose();
                    gc.clear();
                    F03.clear();
                    f00();
                }
            });
        });
        contentPane.add(label);
        contentPane.add(play);
        contentPane.add(bg);

//        buttonYes.addActionListener((l) -> {
//            this.dispose();
//            javax.swing.SwingUtilities.invokeLater(new Runnable() {
//                @Override
//                public void run() {
////                    f04(); 游戏开始
//                }
//            });
//        });

//        buttonNo.addActionListener((l) -> {
//            this.dispose();
//            javax.swing.SwingUtilities.invokeLater(new Runnable() {
//                @Override
//                public void run() {
//
//                    f02();
//                }
//            });
//        });
    }

    private class Layout implements LayoutManager {

        @Override
        public void layoutContainer(Container parent) {
            int width = parent.getWidth();
            int height = parent.getHeight();
            if (label.isVisible()){
                Dimension size = label.getPreferredSize();
                label.setBounds((width - size.width) / 2, (height - size.height) / 3, size.width, size.height);
            }
            if (bg.isVisible()) {
                bg.setBounds(0, 0, width, height);
                ImageIcon show = pic.getBG1();
                show.setImage(show.getImage().getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING));
                bg.setIcon(show);
            }
            if (play.isVisible()){
                Dimension size = play.getPreferredSize();
                play.setBounds((width - size.width) / 2, 2 * (height - size.height) / 3, size.width, size.height);
            }
//            System.out.println(width + " " + height);

//            if (haveSave.isVisible()) {
//                Dimension size = haveSave.getPreferredSize();
//                int x = (width - size.width)/2;
//                int y = (height - size.height) / 3;
//                haveSave.setBounds(x, y, size.width, size.height);
//            }


        }

        @Override
        public void addLayoutComponent(String name, Component comp) {

        }

        @Override
        public void removeLayoutComponent(Component comp) {

        }

        @Override
        public Dimension preferredLayoutSize(Container parent) {
            return null;
        }

        @Override
        public Dimension minimumLayoutSize(Container parent) {
            return null;
        }
    }

}