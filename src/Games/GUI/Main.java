package Games.GUI;

import Games.GUI.GameFrame.MainLocal;
import Games.components.Music;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        MainLocal.begin();
        Thread.sleep(1000);
        Music.playCircleMusic("src\\音效\\bgm.wav");
    }
}