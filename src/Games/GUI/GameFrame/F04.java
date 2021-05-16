package Games.GUI.GameFrame;

import Games.GUI.GameFrame.layout.AfYLayout;
import Games.Map.Map;
import Games.listener.GameController;

import javax.swing.*;
import java.awt.*;

import static Games.GUI.GameFrame.MainLocal.f02;

public class F04 extends JFrame {
    GameController gc;
    JButton play;
    JLabel label;
    public F04(String title, GameController gc){
        super(title);
        this.gc = gc;
        setLocationRelativeTo(null);
        Container contentPane = getContentPane();
        contentPane.setLayout(new Layout());
        label = new JLabel("Congratulation, " + gc.whoWin() + ", wins!");
        play = new JButton("Play Again.");
        play.addActionListener(e -> {
            javax.swing.SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    dispose();
                    f02();
                }
            });
        });
        contentPane.add(label);
        contentPane.add(play);

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