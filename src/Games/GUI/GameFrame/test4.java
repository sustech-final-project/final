package Games.GUI.GameFrame;

import java.util.Map;
import java.util.TreeMap;

public class test4 {
    public static void main(String[] args) {
//        Map<Double,String> paiming = new TreeMap<>();
//        paiming.put(30.0,"戴郭轶");
//        paiming.put(67.0,"杨清淞");
//        paiming.put(63.0,"杨基");
//        SoloData.setPaiming(paiming);
        SoloData.readpaiming();
        SoloData.savepaiming();
        new paiming();
    }
}
