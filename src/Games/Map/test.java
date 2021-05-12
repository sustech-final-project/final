package Games.Map;

public class test {
    public static void main(String[] args) {
        Map map = Save.MapReader();
        Data.setHasClicked(10,10);
        Data.setPoint1(1);
        Data.setPoint2(2);
        map.mapWriter("mine4.txt");
        //Save.Save("save.txt");
        //Save.Reader();
        //map.mapWriter("mine3.txt");


    }
}
