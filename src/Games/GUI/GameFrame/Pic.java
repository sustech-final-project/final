package Games.GUI.GameFrame;

import Games.Map.Data;

import javax.swing.*;

public enum Pic {
    NUM0(new ImageIcon("src\\Games\\image\\0.jpg")),
    NUM1(new ImageIcon("src\\Games\\image\\1.jpg")),
    NUM2(new ImageIcon("src\\Games\\image\\2.jpg")),
    NUM3(new ImageIcon("src\\Games\\image\\3.png")),
    NUM4(new ImageIcon("src\\Games\\image\\4.jpg")),
    NUM5(new ImageIcon("src\\Games\\image\\5.jpg")),
    NUM6(new ImageIcon("src\\Games\\image\\6.jpg")),
    NUM7(new ImageIcon("src\\Games\\image\\7.jpg")),
    MINE(new ImageIcon("src\\Games\\image\\tnt.jpg")),
    FLAG(new ImageIcon("src\\Games\\image\\方块插旗.jpg")),
    BLANK(new ImageIcon("src\\Games\\image\\正常方块.jpg"));

    private final ImageIcon icon;

    private Pic(ImageIcon icon) {
        this.icon = icon;
    }

    public ImageIcon getIcon() {
        return icon;
    }

    public static ImageIcon getIcon(String Char) {
        if (Data.zhuti==1){
            switch (Char) {
                case "0": return NUM0.getIcon();
                case "1": return NUM1.getIcon();
                case "2": return NUM2.getIcon();
                case "3": return NUM3.getIcon();
                case "4": return NUM4.getIcon();
                case "5": return NUM5.getIcon();
                case "6": return NUM6.getIcon();
                case "7": return NUM7.getIcon();
                case "M": return MINE.getIcon();
                case "F": return FLAG.getIcon();
                default: return null;
            }
        }
        else {
            switch (Char) {
                case "0": return NUM0.getIcon();
                case "1": return NUM1.getIcon();
                case "2": return NUM2.getIcon();
                case "3": return NUM3.getIcon();
                case "4": return NUM4.getIcon();
                case "5": return NUM5.getIcon();
                case "6": return NUM6.getIcon();
                case "7": return NUM7.getIcon();
                case "M": return MINE.getIcon();
                case "F": return FLAG.getIcon();
                default: return null;
            }
        }

    }
}
