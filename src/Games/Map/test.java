package Games.Map;

public class test {
    public static void main(String[] args) {
        Map map = new Map(1,1,10,10,10);
        char [][] a = map.getMap();
        for(int i=0;i<10;i++){
            for (int j=0;j<10;j++){
                System.out.print(a[i][j]);
            }
            System.out.println();
        }
        System.out.println("1");
        map.mapReader();
        char [][] b = map.getMap();
        for(int i=0;i<10;i++){
            for (int j=0;j<10;j++){
                System.out.print(b[i][j]);
            }
            System.out.println();
        }
    }
}
