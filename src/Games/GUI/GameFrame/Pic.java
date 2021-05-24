package Games.GUI.GameFrame;

import javax.swing.*;

public enum Pic {
    NUM0(new ImageIcon("final\\src\\Games\\image\\空白.png")),
    NUM1(new ImageIcon("final\\src\\Games\\image\\1.jpg")),
    NUM2(new ImageIcon("final\\src\\Games\\image\\2.jpg")),
    NUM3(new ImageIcon("final\\src\\Games\\image\\3.jpg")),
    NUM4(new ImageIcon("final\\src\\Games\\image\\4.jpg")),
    NUM5(new ImageIcon("final\\src\\Games\\image\\5.jpg")),
    NUM6(new ImageIcon("final\\src\\Games\\image\\6.jpg")),
    NUM7(new ImageIcon("final\\src\\Games\\image\\7.jpg")),
    MINE(new ImageIcon("final\\src\\Games\\image\\mine.jpg")),
    FLAG(new ImageIcon("final\\src\\Games\\image\\方块插旗.jpg")),
    BLANK(new ImageIcon("final\\final\\src\\Games\\image\\正常方块.jpg"));

    private final ImageIcon icon;

    private Pic(ImageIcon icon) {
        this.icon = icon;
    }

    public ImageIcon getIcon() {
        return icon;
    }

    public static ImageIcon getIcon(String Char) {
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
