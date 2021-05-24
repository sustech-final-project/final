package Games.GUI.GameFrame;

import Games.Map.Map;
import Games.listener.GameController;


import javax.swing.*;
import java.awt.*;

import static Games.GUI.GameFrame.Fgif.Open;

public class MainLocal {
    private static final GameController gc = new Games.Map.GameController();

    public static void begin() {
        try {
            Open();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                f00();
            }
        });
    }

    public static void exception() {
        JFrame frame = new JFrame("Error");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setBounds(600, 400, 289, 153);
        frame.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel l = new JLabel("请输入数字!");
        frame.add(l);
        frame.setVisible(true);
    }

    public static void f00() {
        F00 f00 = new F00("欢迎", gc);
        f00.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f00.setSize(289, 153);
        f00.setLocationRelativeTo(null);
        f00.setVisible(true);
    }

    public static void f01() {
        F01 f01 = new F01("是否继续上次游戏", gc);
        f01.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f01.setBounds(600, 400, 289, 153);
        f01.setVisible(true);
    }

    public static void f02() {
        F02 f02 = new F02("游戏设置", gc);
        f02.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f02.setSize(470, 170);
        f02.setLocationRelativeTo(null);
        f02.setVisible(true);
    }

    public static void f03() {
        F03 f03 = new F03("扫雷", gc);
        f03.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f03.setSize(gc.getMap()[0].length * (int) (43.0 / 0.7), gc.getMap().length * 42 + 60);
        f03.setLocationRelativeTo(null);
        f03.setVisible(true);
    }

    public static void f04() {
        F04 f04 = new F04("Congratulation", gc);
        f04.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f04.setBounds(600, 400, 289, 153);
        f04.setLocationRelativeTo(null);
        f04.setVisible(true);
    }

//    public static void f05() {
//        F05 f05 = new F05("");
//        f05.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        f05.setBounds(600, 400, 289, 153);
//        f05.setVisible(true);
//    }

//    public static void f06() {
//        F06 f06 = new F06("");
//        f06.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        f06.setBounds(600, 400, 289, 153);
//        f06.setVisible(true);
//    }
//
//    public static void f07() {
//        F07 f07 = new F07("");
//        f07.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        f07.setBounds(600, 400, 289, 153);
//        f07.setVisible(true);
//    }
//
//    public static void f08() {
//        F08 f08 = new F08("");
//        f08.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        f08.setBounds(600, 400, 289, 153);
//        f08.setVisible(true);
//    }
//
//    public static void f02() {
//        F09 f02 = new F09("");
//        f09.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        f09.setBounds(600, 400, 289, 153);
//        f09.setVisible(true);
//    }
//
//    public static void f10() {
//        F10 f10 = new F10("");
//        f10.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        f10.setBounds(600, 400, 289, 153);
//        f10.setVisible(true);
//    }
}
