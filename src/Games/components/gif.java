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
        ImageIcon imageIconDogFall = new ImageIcon("src\\Games\\pic\\平局.gif");
        if(name.equals("tnt")       ) return imageIconTnt;
        if(name.equals("lose")      ) return imageIconLose;
        if(name.equals("win")       )return imageIconWin;
        if(name.equals("begin")     )    return imageIconBegin;
        if(name.equals("mistake")   )    return imageIconTransMistake;
        if(name.equals("background"))     return imageIconBackGround;
        if(name.equals("DogFall")   )     return imageIconDogFall;
        else return null;
    }
}
