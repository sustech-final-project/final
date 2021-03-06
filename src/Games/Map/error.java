package Games.Map;

import Games.listener.GameController;

import javax.swing.*;
import java.awt.*;

import static Games.GUI.GameFrame.MainLocal.f01;
import static Games.GUI.GameFrame.MainLocal.f02;

public class error extends JFrame {
    JLabel label = new JLabel("请选择游戏类型");
    JButton native_ = new JButton("多人游戏");
    JButton net = new JButton("联网游戏");
    JButton solo = new JButton("剧情模式");
    //GameController gc;

    public error(String title){
        super(title);
       // this.gc = gc;
        Container contentPane = getContentPane();
        Layout layout = new Layout();
        contentPane.setLayout(layout);
        contentPane.add(label);
        contentPane.add(native_);
        contentPane.add(net);
        contentPane.add(solo);

//        native_.addActionListener((l) -> {
//            this.dispose();
//            SwingUtilities.invokeLater(new Runnable() {
////                @Override
////                public void run() {
////                    if (gc.isSaveExist()) {         ////////////////////////////
////                        f01();
////                    } else {
////                        f02();
////                    }
////                }
//            });
//        });

//        net.addActionListener(lis -> {
//            this.dispose();
//            SwingUtilities.invokeLater(new Runnable() {
//                @Override
//                public void run() {
//                   // mai();
//                    Games.Internet.GameClient.Main.mai();
//                }
//            });
//        });

    }

    private class Layout implements LayoutManager {

        @Override
        public void layoutContainer(Container parent) {
            int width = parent.getWidth();
            int height = parent.getHeight();

            if (label.isVisible()) {
                Dimension size = label.getPreferredSize();
                int x = (width - size.width)/2;
                int y = (height - size.height) / 3;
                label.setBounds(x, y, size.width, size.height);
            }
            if (native_.isVisible()) {
                Dimension size = native_.getPreferredSize();
                int x = (width - 3 * size.width) / 4;
                int y = 2 * (height - size.height) / 3;
                native_.setBounds(x, y, size.width, size.height);
            }
            if (net.isVisible()) {
                Dimension size = net.getPreferredSize();
                int x = (width - 3 * size.width) / 4 * 2 + size.width;
                int y = 2 * (height - size.height) / 3;
                net.setBounds(x, y, size.width, size.height);
            }
            if(solo.isVisible()) {
                Dimension size = solo.getPreferredSize();
                int x = (width - 3 * size.width) / 4 * 3 + (2 * size.width);
                int y = 2 * (height - size.height) / 3;
                solo.setBounds(x, y, size.width, size.height);
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