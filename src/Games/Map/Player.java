package Games.Map;

import Games.GUI.GameFrame.Pic;
import Games.Internet.GameServer.Map;

import javax.swing.*;
import java.util.Arrays;
import java.util.Random;

public class Player {
    String name;
    String characteristic;
    int score;
    int mistake;
    int level = 0;// 简单 1、 普通 2、 困难 3
    ImageIcon icon;

    public ImageIcon getIcon() {
        return icon;
    }

    public int getLevel() {
        return level;
    }

    public Player(String name, String characteristic){
        this.name=name;
        this.characteristic = characteristic;
        this.score=0;
        this.mistake=0;
        if (characteristic.length() == 3)icon = Pic.getPlayerIcon(Integer.parseInt(characteristic.substring(2)));
    }

    public Player(String name, String characteristic, int level) {
        this.name = name;
        this.characteristic = characteristic;
        this.level = level;
        this.score=0;
        this.mistake=0;
        if (characteristic.length() == 3)icon = Pic.getPlayerIcon(Integer.parseInt(characteristic.substring(2)));

        System.out.println("level" + level);
    }

    public Player(String name, String characteristic, int score, int mistake) {
        this.name = name;
        this.characteristic = characteristic;
        this.score = score;
        this.mistake = mistake;
        if (characteristic.length() == 3)icon = Pic.getPlayerIcon(Integer.parseInt(characteristic.substring(2)));
    }

    public String getCharacteristic() {
        return characteristic;
    }

    public void setCharacteristic(String characteristic) {
        this.characteristic = characteristic;
    }

    public String getName() {
        return name;
    }
    public int getScore() {
        return score;
    }
    public int getMistake() {
        return mistake;
    }
    public void addScore(int num){
        this.score+=num;
    }
    public void loseScore(int num){
        this.score-=num;
    }
    public void addMistake(){
        mistake++;
    }

    public String toString(){
        String str = name + " " + score + " " + mistake;
        return str;
    }

    public String AiClick(char[][] map) {
        Random random = new Random();
        if (level == 1) {
            int r = random.nextInt(map.length);
            int c = random.nextInt(map[0].length);
            int t = random.nextInt(2);
            if (t == 1) t = 3;
            if (t == 0) t = 1;
            if(Data.getHasClicked(r, c) == 1){
                System.out.println("has clicked");
                return AiClick(map);
            }
            else {
                System.out.println("Can Return");
                return r + " " + c + " " + t;
            }
        } else if (level == 2){
            return new pvetest().pvemiddle();
        } else if (level == 3){
            int r = random.nextInt(map.length);
            int c = random.nextInt(map[0].length);
            int t;
            if (map[r][c] == 'M') t = 3;
            else t = 1;
            if (Data.isAllClicked()) return "0 0 0";
            if(Data.getHasClicked(r, c) == 1) return AiClick(map);
            else return r + " " + c + " " + t;
        }
        System.out.println("no entry");
        return null;
    }

}

