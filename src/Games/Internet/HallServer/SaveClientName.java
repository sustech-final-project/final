package Games.Internet.HallServer;

import java.util.ArrayList;

public class SaveClientName {
    public static ArrayList<String> Clientname = new ArrayList<>();
    public SaveClientName(){
    }

    public static ArrayList<String> getClientname() {
        return Clientname;
    }

    public static void setClientname(ArrayList<String> clientname) {
        Clientname = clientname;
    }
}
