package Games.Map;

public class testwriter {
    public static void main(String[] args) {
        Map map = new Map(1, 1, 10, 10, 10);
        map.mapWriter("mine.txt");
    }
}
