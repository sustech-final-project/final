package Games.GUI.GameFrame;

import Games.Map.Map;

import javax.swing.*;
import java.awt.*;

import static Games.GUI.GameFrame.MainLocal.f02;

public class F04 extends JFrame {
    char[][] map;
    public F04(String title, Map map){
        super(title);
        setLocationRelativeTo(null);
        Container contentPane = getContentPane();
        Layout layout = new Layout();
        contentPane.setLayout(layout);
        this.map = map.geTMap().clone();

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