package Games.Internet.HallServer;

import java.util.ArrayList;
import java.util.Map;

public class SaveClientName {
    public static ArrayList<String> Clientname = new ArrayList<>();
    public Map<Integer, Integer> schedule;
    public SaveClientName(){
    }

    public static ArrayList<String> getClientname() {
        return Clientname;
    }

    public static void setClientname(ArrayList<String> clientname) {
        Clientname = clientname;
    }
}
