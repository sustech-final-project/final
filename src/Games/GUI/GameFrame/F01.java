package Games.GUI.GameFrame;

import Games.Map.Map;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

import static Games.GUI.GameFrame.MainLocal.f02;
import static Games.GUI.GameFrame.MainLocal.f03;

public class F01 extends JFrame {

    JLabel haveSave = new JLabel("是否继续上次未完成的游戏");
    JButton buttonYes = new JButton("是");
    JButton buttonNo = new JButton("否");

    public F01(String title){
        super(title);
        setLocationRelativeTo(null);
        Container contentPane = getContentPane();
        Layout layout = new Layout();
        contentPane.setLayout(layout);
        contentPane.add(haveSave);
        contentPane.add(buttonYes);
        contentPane.add(buttonNo);

        buttonYes.addActionListener((l) -> {
            this.dispose();
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    Map map = new Map();
                    F03 f03 = new F03("扫雷",map, new String[3]);
                    f03(f03);
                }
            });
        });

        buttonNo.addActionListener((l) -> {
            this.dispose();
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {

                    f02();
                }
            });
        });
    }

    private class Layout implements LayoutManager {

        @Override
        public void layoutContainer(Container parent) {
            int width = parent.getWidth();
            int height = parent.getHeight();
//            System.out.println(width + " " + height);

            if (haveSave.isVisible()) {
                Dimension size = haveSave.getPreferredSize();
                int x = (width - size.width)/2;
                int y = (height - size.height) / 3;
                haveSave.setBounds(x, y, size.width, size.height);
            }

            if (buttonYes.isVisible()) {
                Dimension size = buttonYes.getPreferredSize();
                int x = (width - size.width) / 3;
                int y = 2 * (height - size.height) / 3;
                buttonYes.setBounds(x, y, size.width, size.height);
            }

            if (buttonNo.isVisible()) {
                Dimension size = buttonNo.getPreferredSize();
                int x =  2 * (width - size.width) / 3;
                int y = 2 * (height - size.height) / 3;
                buttonNo.setBounds(x, y, size.width, size.height);
            }
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