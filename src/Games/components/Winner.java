package Games.components;

import Games.Map.Data;
import Games.Map.Player;

import java.util.ArrayList;

/**5.17 杨基 暂定一个winner
 * point 1 2 3 4 对应 String name 1 2 3 4
 *暂时将Winner定为String name n 对应 point最高的n
 *
 * 5.20在新增player类后重写
 */
public class Winner {
    private static ArrayList<String> winner;



    public static void setWinner() {

//        if(Math.max(Data.getPoint1(),Math.max(Data.getPoint2(),Math.max(Data.getPoint3(),Data.getPoint4())))==Data.getPoint1())
//            setWinner =""+Data.getName1();
//        if(Math.max(Data.getPoint1(),Math.max(Data.getPoint2(),Math.max(Data.getPoint3(),Data.getPoint4())))==Data.getPoint2())
//            setWinner =""+Data.getName2();
//        if(Math.max(Data.getPoint1(),Math.max(Data.getPoint2(),Math.max(Data.getPoint3(),Data.getPoint4())))==Data.getPoint3())
//            setWinner =""+Data.getName3();
//        if(Math.max(Data.getPoint1(),Math.max(Data.getPoint2(),Math.max(Data.getPoint3(),Data.getPoint4())))==Data.getPoint4())
//            setWinner =""+Data.getName4();
//        if((Data.getPoint1()==Data.getPoint2()||Data.getPoint1()==Data.getPoint3()||Data.getPoint1()==Data.getPoint4()||Data.getPoint2()==Data.getPoint3()||Data.getPoint2()==Data.getPoint4()||Data.getPoint3()==Data.getPoint4())
//                && Math.min(Data.getMistake1(),Math.min(Data.getMistake2(),Math.min(Data.getMistake3(),Data.getMistake4())))==Data.getMistake1())
//            setWinner =""+Data.getName1();
//        if((Data.getPoint1()==Data.getPoint2()||Data.getPoint1()==Data.getPoint3()||Data.getPoint1()==Data.getPoint4()||Data.getPoint2()==Data.getPoint3()||Data.getPoint2()==Data.getPoint4()||Data.getPoint3()==Data.getPoint4())
//                && Math.min(Data.getMistake1(),Math.min(Data.getMistake2(),Math.min(Data.getMistake3(),Data.getMistake4())))==Data.getMistake2())
//            setWinner =""+Data.getName2();
//        if((Data.getPoint1()==Data.getPoint2()||Data.getPoint1()==Data.getPoint3()||Data.getPoint1()==Data.getPoint4()||Data.getPoint2()==Data.getPoint3()||Data.getPoint2()==Data.getPoint4()||Data.getPoint3()==Data.getPoint4())
//                && Math.min(Data.getMistake1(),Math.min(Data.getMistake2(),Math.min(Data.getMistake3(),Data.getMistake4())))==Data.getMistake3())
//            setWinner =""+Data.getName3();
//        if((Data.getPoint1()==Data.getPoint2()||Data.getPoint1()==Data.getPoint3()||Data.getPoint1()==Data.getPoint4()||Data.getPoint2()==Data.getPoint3()||Data.getPoint2()==Data.getPoint4()||Data.getPoint3()==Data.getPoint4())
//                && Math.min(Data.getMistake1(),Math.min(Data.getMistake2(),Math.min(Data.getMistake3(),Data.getMistake4())))==Data.getMistake4())
//            setWinner =""+Data.getName4();
//        else setWinner ="There's no winner!!";
for(int i=0;i<Data.getPlayers().size();i++){
    int sc=0;
    if(Data.getScore()[i]>=sc){
            sc=Data.getScore()[i];
    }
    for (int j=0;j<Data.getPlayers().size();j++){
        Player player = Data.getPlayers().get(i);
        if(player.getScore()==sc){
        winner.add(player.getName());
        }
    }


    }

}

    public static ArrayList<String> getWinner() {
        return winner;
    }
}
