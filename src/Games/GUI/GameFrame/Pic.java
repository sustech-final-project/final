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
    BLANK(new ImageIcon("src\\Games\\image\\正常方块.jpg")),

    NUM10(new ImageIcon("src\\Games\\image\\blank.jpg")),
    NUM11(new ImageIcon("src\\Games\\image\\转换\\1.jpg")),
    NUM12(new ImageIcon("src\\Games\\image\\转换\\2.jpg")),
    NUM13(new ImageIcon("src\\Games\\image\\转换\\3.jpg")),
    NUM14(new ImageIcon("src\\Games\\image\\转换\\4.jpg")),
    NUM15(new ImageIcon("src\\Games\\image\\转换\\5.jpg")),
    NUM16(new ImageIcon("src\\Games\\image\\转换\\6.jpg")),
    NUM17(new ImageIcon("src\\Games\\image\\转换\\7.jpg")),
    MINE1(new ImageIcon("src\\Games\\image\\mine.jpg")),
    FLAG1(new ImageIcon("src\\Games\\image\\flag.jpg"));




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
                case "0": return NUM10.getIcon();
                case "1": return NUM11.getIcon();
                case "2": return NUM12.getIcon();
                case "3": return NUM13.getIcon();
                case "4": return NUM14.getIcon();
                case "5": return NUM15.getIcon();
                case "6": return NUM16.getIcon();
                case "7": return NUM17.getIcon();
                case "M": return MINE1.getIcon();
                case "F": return FLAG1.getIcon();
                default: return null;
            }
        }

    }
}
