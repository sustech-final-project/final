package Games.components;

import javax.swing.*;

public class gif {
    public ImageIcon gifGetter(String name){
        ImageIcon imageIconTnt = new ImageIcon("src\\Games\\gif\\tnt_3 5.gif");
        ImageIcon imageIconLose = new ImageIcon("src\\Games\\gif\\You  lose 2.5.gif");
        ImageIcon imageIconWin = new ImageIcon("src\\Games\\gif\\you win 3.05.gif");
        ImageIcon imageIconBegin = new ImageIcon("src\\Games\\gif\\开头 3s.gif");
        ImageIcon imageIconTransMistake = new ImageIcon("src\\Games\\gif\\转换 2s.gif");
        ImageIcon imageIconBackGround = new ImageIcon("src\\Games\\pic\\背景.gif");
        if(name=="tnt")  return imageIconTnt;
        if(name=="lose") return imageIconLose;
        if(name=="win") return imageIconWin;
        if(name=="begin") return imageIconBegin;
        if(name=="mistake") return imageIconTransMistake;
        if(name=="background") return imageIconBackGround;
        else return null;
    }


}
