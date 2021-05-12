package Games.Internet.GameServer;

import java.util.*;


public class MapSaver {
    public static String createRandomStr(){
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < 32; i++) {
            int number = random.nextInt(62);
            stringBuffer.append(str.charAt(number));
        }
        return stringBuffer.toString();
    }

    public static void MapSaver() {
        Data.setFilename("ServerMap/"+createRandomStr()+".txt");
        Map map = new Map(1,1,10,10,10);
        Save.Save(Data.getFilename());
    }

//    public static void main(String[] args) {
//        Map serverMap = new Map(1,1,10,10,10);
//        Data.setHasClicked(10,10);
//        Data.setPoint1(1);
//        Data.setPoint2(2);
//        MapSaver();
//    }
}
