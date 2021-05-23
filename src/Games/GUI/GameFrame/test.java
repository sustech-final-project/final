package Games.GUI.GameFrame;

import Games.listener.GameController;

import javax.swing.*;

public class test {
    static GameController gc = new Games.Map.GameController();
    public static void main(String[] args) {
    F02 f02 = new F02("游戏设置", gc);
    f02.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    f02.setBounds(600, 400, 1017, 559);
    f02.setVisible(true);
}
}
