package Games.GUI.GameFrame;

import Games.components.Music;

public class test2 {
    public static void main(String[] args) {
        try {
//            Fgif.Open();
//            Fgif.Tnt();
//            Fgif.Chaqi();
            Music.playMusic("Begin");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
