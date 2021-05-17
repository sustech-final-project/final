package Games.components;

import Games.Internet.GameServer.Map;

public class CheckMap extends Map {
    /**
     * 作者：戴郭轶
     * 日期：2021.5.4
     * @param a   棋盘的行数
     * @param b   棋盘的列数
     * @param num 雷的个数
     *return说明：
     *            1：应当输出雷数过多，请重新定义雷数
     *            2：地图过小
     *            3：为雷数过密，需要重新生成地图
     *            4：继续下一步
     */
    public int CheckMap(int a,int b,int num){
        char[][] map = getMap();
        if(a*b*0.7<num) return 1;  //应当输出雷数过多，请重新定义雷数
        else if(a<3||b<3) return 2; //地图过小
        else {
            for (int i=1; i<a-1;i++){
                for (int j=1; j<b-1;j++){
                    if (map[i-1][j-1]=='M'&&map[i][j-1]=='M'&&map[i+1][j-1]=='M'&&map[i-1][j]=='M'&&map[i+1][j]=='M'&&map[i+1][j+1]=='M'&&map[i-1][j+1]=='M'&&map[i][j+1]=='M'){
                        return 3; //为雷数过密，需要重新生成地图
                    }
                }
            }
        }
        return 4;
    }

}
