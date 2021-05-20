package Games.Map;

import java.util.Random;

import Games.Internet.GameServer.Map;
import Games.Map.Data;

public class Robot {
//    private static int pointOfRo1=0;
//    private static int mineOfRo1=0;
//    private static int mistakeOfRo1=0;
////    private static int pointOfRo2=0;
////    private static int mineOfRo2=0;
////    private static int mistakeOfRo2=0;
////    private static int pointOfRo3=0;
////    private static int mineOfRo3=0;
////    private static int mistakeOfRo3=0;
//    private static String nameEz ="容易被击败的玩家";
//    private static String nameMd ="不太容易被击败的玩家";
//    private static String nameHa ="强大的机器人玩家";
//    private static String winner ="";
     Player Hdrobot=new Player("困难AI");
     Player Mdrobot=new Player("中等AI");
     Player Ezrobot=new Player("简单AI");
    Games.Internet.GameServer.Map map =new Games.Internet.GameServer.Map();

    public Games.Internet.GameServer.Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }
    /*public void RobotClick(int r,int c,int button){
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
     */
    private static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
    /**AiClick的返回值1 代表左击 返回值3 代表右击 返回值0代表不存在当前情况 重新使用AiClick进行递归
     *
     */

    public int AiClick(Player Robot){
//        if(Robot==Hdrobot)

        char map[][] = Map.getMap();
        int r=getRandomNumberInRange(0, map.length);
        int c=getRandomNumberInRange(0,map[0].length);
        if(Data.getHasClicked(r,c)==1){
            if(getMap().getMap(r,c-1)=='M'&&c-1!=-1){
                Robot.addScore(2);
                Data.HasClicked(r,c-1);
                return 3;
            }if(getMap().getMap(r+1,c-1)=='M'&&c-1!=-1&&r+1<=map.length){
                Robot.addScore(2);
                Data.HasClicked(r+1,c-1);
                return 3;
            }
            if(getMap().getMap(r+1,c)=='M'&&r+1<=map.length){
                Robot.addScore(2);
                Data.HasClicked(r+1,c);
                return 3;
            }
            if(getMap().getMap(r+1,c+1)=='M'&&r+1<=map.length&&c+1<=map[0].length){
                Robot.addScore(2);
                Data.HasClicked(r+1,c+1);
                return 3;
            }
            if(getMap().getMap(r-1,c-1)=='M'&&c-1!=-1&&r-1!=-1){
                Robot.addScore(2);
                Data.HasClicked(r-1,c-1);
                return 3;
            }
            if(getMap().getMap(r-1,c+1)=='M'&&c-1!=-1&&c+1<=map[0].length){
                Robot.addScore(2);
                Data.HasClicked(r-1,c+1);
                return 3;
            }
            if(getMap().getMap(r-1,c)=='M'&&r-1!=-1){
                Robot.addScore(2);
                Data.HasClicked(r-1,c);
                return 3;
            }
            if(getMap().getMap(r-1,c-1)=='M'&&c-1!=-1&&r-1!=-1){
                Robot.addScore(2);
                Data.HasClicked(r-1,c-1);
                return 3;
            }


            if(getMap().getMap(r,c-1)!='M'&&c-1!=-1){
                Robot.addScore(1);
                Data.HasClicked(r,c-1);
                return 1;
            }if(getMap().getMap(r+1,c-1)!='M'&&c-1!=-1&&r+1<=map.length){
                Robot.addScore(1);
                Data.HasClicked(r+1,c-1);
                return 1;
            }
            if(getMap().getMap(r+1,c)!='M'&&r+1<=map.length){
                Robot.addScore(1);
                Data.HasClicked(r+1,c);
                return 1;
            }
            if(getMap().getMap(r+1,c+1)!='M'&&r+1<=map.length&&c+1<=map[0].length){
                Robot.addScore(1);
                Data.HasClicked(r+1,c+1);
                return 1;
            }
            if(getMap().getMap(r-1,c-1)!='M'&&c-1!=-1&&r-1!=-1){
                Robot.addScore(1);
                Data.HasClicked(r-1,c-1);
                return 1;
            }
            if(getMap().getMap(r-1,c+1)!='M'&&c-1!=-1&&c+1<=map[0].length){
                Robot.addScore(1);
                Data.HasClicked(r-1,c+1);
                return 1;
            }
            if(getMap().getMap(r-1,c)!='M'&&r-1!=-1){
                Robot.addScore(1);
                Data.HasClicked(r-1,c);
                return 1;
            }
            if(getMap().getMap(r-1,c-1)!='M'&&c-1!=-1&&r-1!=-1){
                Robot.addScore(1);
                Data.HasClicked(r-1,c-1);
                return 1;
            }
            else return 0;
        }
        else return 0;

    }



}
