package Games.GUI.GameFrame;

import Games.components.pic;
import Games.listener.GameController;


import javax.swing.*;
import java.awt.*;

import static Games.GUI.GameFrame.MainLocal.f01;
import static Games.GUI.GameFrame.MainLocal.f02;

public class F00 extends JFrame {
    JLabel bg = new JLabel();
    JLabel label = new JLabel("请选择游戏类型");
    JButton native_ = new JButton("");
    JButton net = new JButton("");
    JButton solo = new JButton("");
    GameController gc;//新建一个JLabel存放背景

    public F00(String title, GameController gc){
        super(title);
        this.gc = gc;
        Container contentPane = getContentPane();
        Layout layout = new Layout();
        contentPane.setLayout(layout);
        contentPane.add(label);
        contentPane.add(native_);
        contentPane.add(net);
        contentPane.add(solo);
        contentPane.add(bg);//把背景放在最后面

        ImageIcon DuoRen = pic.getDuoRen();//建立Icon，得到对应的图像
        ImageIcon JuQing = pic.getJuQing();
        ImageIcon LianWang = pic.getLianWang();

        DuoRen.setImage(DuoRen.getImage().getScaledInstance(86, 26, Image.SCALE_DEFAULT));//设置图片的大小，质量
        JuQing.setImage(JuQing.getImage().getScaledInstance(86, 26, Image.SCALE_DEFAULT));
        LianWang.setImage(LianWang.getImage().getScaledInstance(86, 26, Image.SCALE_DEFAULT));
        solo.setIcon(JuQing);//在JLabel里面加入图片
        native_.setIcon(DuoRen);
        net.setIcon(LianWang);
        native_.addActionListener((l) -> {
            this.dispose();
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    if (gc.isSaveExist()) {         ////////////////////////////
                        gc.clear();
                        F03.clear();
                        f01();
                    } else {
                        F03.clear();
                        gc.clear();
                        f02();
                    }
                }
            });
        });

        net.addActionListener(lis -> {
            this.dispose();
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                   // mai();
                    Games.Internet.GameClient.Main.mai();
                }
            });
        });
        solo.addActionListener(lis -> {
            this.dispose();
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    // mai();
                    MainLocal.solo1();
                }
            });
        });

    }

    private class Layout implements LayoutManager {

        @Override
        public void layoutContainer(Container parent) {
            int width = parent.getWidth();
            int height = parent.getHeight();

            if (label.isVisible()) {
                Dimension size = label.getPreferredSize();
                int x = (width - 86)/2;
                int y = (height - 26) / 3;
                label.setBounds(x, y, 86, 26);
            }
            if (bg.isVisible()) {
                bg.setBounds(0, 0, width, height);
                ImageIcon show = pic.getBG1();
                show.setImage(show.getImage().getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING));
                bg.setIcon(show);
            }//这一段代码复制粘贴
            if (native_.isVisible()) {
                Dimension size = native_.getPreferredSize();
                System.out.println(size);
                int x = (width - 3 * 86) / 4;
                int y = 2 * (height - 26) / 3;
                native_.setBounds(x, y, 86, 26);
            }
            if (net.isVisible()) {
                Dimension size = net.getPreferredSize();
                int x = (width - 3 * 86) / 4 * 2 + 86;
                int y = 2 * (height - 26) / 3;
                net.setBounds(x, y, 86, 26);
            }
            if(solo.isVisible()) {
                Dimension size = solo.getPreferredSize();
                int x = (width - 3 * 86) / 4 * 3 + (2 * 86);
                int y = 2 * (height - 26) / 3;
                solo.setBounds(x, y, 86, 26);
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