package Games.Internet.GameServer;

import Games.Map.Data;

public class Robot {
    private static int pointOfRo1=0;
    private static int mineOfRo1=0;
    private static int mistakeOfRo1=0;
//    private static int pointOfRo2=0;
//    private static int mineOfRo2=0;
//    private static int mistakeOfRo2=0;
//    private static int pointOfRo3=0;
//    private static int mineOfRo3=0;
//    private static int mistakeOfRo3=0;
    private static String nameEz ="容易被击败的玩家";
    private static String nameMd ="不太容易被击败的玩家";
    private static String nameHa ="强大的机器人玩家";
    private static String winner ="";
    Map map =new Map();
    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }
    public void RobotClick(int r,int c,int button){
        if(button==1){
            if(map.getMap(r,c)=='M'){
            Data.setPointOfRo1(Data.getPointOfRo1()-1);
            Data.setMineOfRo1(Data.getMineOfRo1());
            Data.HasClicked(r,c);
            }
            if(map.getMap(r,c)!='M'){
                Data.setPointOfRo1(Data.getPointOfRo1()+1);
                Data.HasClicked(r,c);
            }
        }
        if(button==3){
            if(map.getMap(r,c)=='M'){
                Data.setPointOfRo1(Data.getPointOfRo1()+2);
                Data.HasClicked(r,c);
            }
            if(map.getMap(r,c)!='M'){
                Data.setMistakeOfRo1(Data.getMistakeOfRo1()+1);
                Data.HasClicked(r,c);
            }
        }
    }
    public String getLevelOfRobot(String name){
        if(name==Data.getNameEz())return "简单模式";
        if(name==Data.getNameMd())return "中等模式";
        if(name==Data.getNameHa())return "困难模式";
        else return"没有此难度模式";

    }

    public String getWinner(){
        if(Data.getPoint1()==Data.getPointOfRo1()&&Data.getMistake1()<Data.getMistakeOfRo1())
            return "YOU WIN!";
        if(Data.getPoint1()==Data.getPointOfRo1()&&Data.getMistake1()>Data.getMistakeOfRo1())
            return "YOU LOSE!";
        if(Math.max(Data.getPoint1(),Data.getPointOfRo1())==Data.getPointOfRo1()){
            return "YOU LOSE!";
        } if(Math.max(Data.getPoint1(),Data.getPointOfRo1())==Data.getPoint1()){
            return "YOU WIN!";
        }
        else return "WIN WIN RUN!";
    }

    public int[] getMistakes(){
        int[] getMistakes=new int[2];
        getMistakes[0]=Data.getMistake1();
        getMistakes[1]=Data.getMistakeOfRo1();
        return getMistakes;
    }
public int[] getScores(){
        int[] getScores=new int[2];
        getScores[0]=Data.getPoint1();
        getScores[1]=Data.getPointOfRo1();
        return getScores;
    }



}
