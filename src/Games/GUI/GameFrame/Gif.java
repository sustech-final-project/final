package Games.GUI.GameFrame;

import javax.swing.*;

public enum Gif {

    Tnt(new ImageIcon("final\\src\\Games\\gif\\tnt_3 5.gif")),//
    IconLose(new ImageIcon("final\\src\\Games\\gif\\You  lose 2.5.gif")),//
    Win(new ImageIcon("final\\src\\Games\\gif\\you win 3.05.gif")),//
    Begin(new ImageIcon("final\\src\\Games\\gif\\开头 3s.gif")),//
    Mistake(new ImageIcon("final\\src\\Games\\gif\\转换 2s.gif")),//
    BackGround(new ImageIcon("final\\src\\Games\\pic\\背景.gif")),//
    DogFall(new ImageIcon("final\\src\\Games\\pic\\平局.gif"));//5s

    private final ImageIcon icon;

    private Gif(ImageIcon icon) {
        this.icon = icon;
    }

    public ImageIcon getIcon() {
        return icon;
    }
}
