package Games.Map;

public class test extends Save{
    public static void main(String[] args) {
        Map map = new Map(1,1,10,10,10);
        Data.setHasClicked(10,10);
        Data.setPoint1(1);
        Data.setPoint2(2);
        map.mapWriter("mine2.txt");
        //Save.Save("save.txt");
        Save.Reader();
        map.mapWriter("mine3.txt");


    }
}
