package Games.GUI.GameFrame;

import Games.Map.Data;
import Games.Map.Map;
import Games.components.pic;
import Games.listener.GameController;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

import static Games.GUI.GameFrame.MainLocal.f02;
import static Games.GUI.GameFrame.MainLocal.f03;

public class F01 extends JFrame {
    GameController gc;
    JLabel bg = new JLabel();
    JLabel haveSave = new JLabel("是否从存档开始游戏");
    JButton buttonYes = new JButton();
    JButton buttonNo = new JButton();

    public F01(String title, GameController gc){
        super(title);
        this.gc = gc;
        setLocationRelativeTo(null);
        Container contentPane = getContentPane();
        Layout layout = new Layout();
        contentPane.setLayout(layout);
        contentPane.add(haveSave);
        contentPane.add(buttonYes);
        contentPane.add(buttonNo);
        getContentPane().add(bg);

        ImageIcon No= pic.getNo();
        ImageIcon Yes=pic.getYes();
        No.setImage(No.getImage().getScaledInstance(46,24,Image.SCALE_DEFAULT));
        Yes.setImage(Yes.getImage().getScaledInstance(46,24,Image.SCALE_DEFAULT));

        buttonYes.setIcon(Yes);

        buttonNo.setIcon(No);

        buttonYes.addActionListener((l) -> {
            this.dispose();
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    boolean a = false;
                    while(!a){
                        try{
                            gc.choseSave();
                            a = true;
                        }catch (Exception e){
                            int ant = JOptionPane.showConfirmDialog(null, "数据不合法", "数据不合法", JOptionPane.YES_NO_OPTION);
//            if (a == 1) {
//                System.exit(0); // 关闭
//            }
//            else {
//                System.exit(0);
//            }
                        }
                    }
                    //Data.resetorder();
                    f03();
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
            if (bg.isVisible()) {
                bg.setBounds(0, 0, width, height);
                ImageIcon show = pic.getBG1();
                show.setImage(show.getImage().getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING));
                bg.setIcon(show);
            }
            if (buttonYes.isVisible()) {
                Dimension size = buttonYes.getPreferredSize();

                int x = (width - size.width) / 3;
                int y = 2 * (height - size.height) / 3;
                buttonYes.setBounds(x, y,46, 24);
            }

            if (buttonNo.isVisible()) {
                Dimension size = buttonNo.getPreferredSize();
                int x =  2 * (width - size.width) / 3;
                int y = 2 * (height - size.height) / 3;
                buttonNo.setBounds(x, y, 46, 24);
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