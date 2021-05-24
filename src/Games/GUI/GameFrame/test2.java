package Games.GUI.GameFrame;

public class test2 {
    public static void main(String[] args) {
        try {
            Fgif.Open();
            Fgif.Tnt();
            Fgif.Chaqi();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
