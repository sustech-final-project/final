package Games.GUI.GameFrame;

import Games.Map.Data;
import Games.Map.Player;
import java.io.*;
import java.util.*;

public class SoloData {
    static ArrayList<Player> soloplayer = new ArrayList<>();
    //static ArrayList<String> paiming = new ArrayList<>();
    static Map<Double,String> paiming = new TreeMap<>();

    public static void savepaiming(){
        try {
            String s = "buffer/paiming.txt";
            File file = new File(s);
            PrintStream writer = new PrintStream(new FileOutputStream(file));
            Set<Double> keySet = paiming.keySet();
            for (Double key : keySet) {
                writer.println(key + " " + paiming.get(key));
            }
            }
    catch (IOException e) {
            System.out.println("writer");
        }
    }

    public static void readpaiming(){
        try {
            String s = "buffer/paiming.txt";
            BufferedReader in = new BufferedReader(new FileReader(s));
            String str ="";

            while ((str = in.readLine())!=null){
                String[] arr = str.split("\\s+");
                paiming.put(Double.valueOf(arr[0]),arr[1]);
            }
        } catch (Exception e) {
            System.out.println("reader");
        }
    }

    public static ArrayList<Player> getSoloplayer() {
        return soloplayer;
    }

    public static void setSoloplayer(ArrayList<Player> soloplayer) {
        SoloData.soloplayer = soloplayer;
    }

    public static Map<Double, String> getPaiming() {
        return paiming;
    }

    public static void setPaiming(Map<Double, String> paiming) {
        SoloData.paiming = paiming;
    }
}
