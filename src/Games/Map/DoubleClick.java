package Games.Map;

/**
 * 作者：戴郭轶
 * 日期：2021.5.19
 * 本程序用于判断用户双击点的各格子是否能够全开，全开则为true，否则为false
 */
public class DoubleClick extends Map implements pve{
    public  boolean DoubleClick (int row,int column){
        if (getMap(row,column)=='0') return true;
        if (getMap(row,column)=='M') return false;
        int total = getMap(row,column) - '0';
        int count = 0;
        if (row>0&&row<getRow()&&column>0&&column<getColumn()) count = top(row,column)+topleft(row,column)+topright(row,column)+left(row,column)+right(row,column)+bottom(row,column)+bottomleft(row,column)+bottomright(row,column);
        if (row==0&&column>0&&column<getColumn()) count = left(row,column)+right(row,column)+bottom(row,column)+bottomleft(row,column)+bottomright(row,column);
        if (row==getRow()&&column>0&&column<getColumn()) count = top(row,column)+topleft(row,column)+topright(row,column)+left(row,column)+right(row,column);
        if (row==0&&column==0) count =right(row,column)+bottom(row,column)+bottomright(row,column);
        if (row==getRow()&&column==0) count = top(row,column)+topright(row,column)+right(row,column);
        if (row==0&&column==getColumn()) count = left(row,column)+bottom(row,column)+bottomleft(row,column);
        if (row>0&&row<getRow()&&column==0) count = top(row,column)+topright(row,column)+right(row,column)+bottom(row,column)+bottomright(row,column);
        if (row>0&&row<getRow()&&column==getColumn()) count = top(row,column)+topleft(row,column)+left(row,column)+bottom(row,column)+bottomleft(row,column);
        if (row==getRow()&&column==getColumn()) count = top(row,column)+topleft(row,column)+left(row,column);
        if (count==total) return true;
        return false;
    }

    /**
     * 作者：戴郭轶
     * 日期：2021.5.21
     * 用于pve的实现
     * @param row
     * @param column
     * @return
     */
    public boolean robotcheck(int row,int column){
        if (getMap(row,column)=='0') return true;
        if (getMap(row,column)=='M') return false;
        int total = getMap(row,column) - '0';
        int count = 0;
        if (row>0&&row<getRow()&&column>0&&column<getColumn()) count = top(row,column)+topleft(row,column)+topright(row,column)+left(row,column)+right(row,column)+bottom(row,column)+bottomleft(row,column)+bottomright(row,column);
        if (row==0&&column>0&&column<getColumn()) count = left(row,column)+right(row,column)+bottom(row,column)+bottomleft(row,column)+bottomright(row,column);
        if (row==getRow()&&column>0&&column<getColumn()) count = top(row,column)+topleft(row,column)+topright(row,column)+left(row,column)+right(row,column);
        if (row==0&&column==0) count =right(row,column)+bottom(row,column)+bottomright(row,column);
        if (row==getRow()&&column==0) count = top(row,column)+topright(row,column)+right(row,column);
        if (row==0&&column==getColumn()) count = left(row,column)+bottom(row,column)+bottomleft(row,column);
        if (row>0&&row<getRow()&&column==0) count = top(row,column)+topright(row,column)+right(row,column)+bottom(row,column)+bottomright(row,column);
        if (row>0&&row<getRow()&&column==getColumn()) count = top(row,column)+topleft(row,column)+left(row,column)+bottom(row,column)+bottomleft(row,column);
        if (row==getRow()&&column==getColumn()) count = top(row,column)+topleft(row,column)+left(row,column);
        return count == total - 1;
    }


    public int top(int row,int column){
        if (getMap(row-1,column)=='M'&&Data.getHasClicked(row-1,column)==1) return 1;
        return 0;
    }
    public int topleft(int row,int column){
        if (getMap(row-1,column-1)=='M'&&Data.getHasClicked(row-1,column-1)==1) return 1;
        return 0;
    }
    public int topright(int row,int column){
        if (getMap(row-1,column+1)=='M'&&Data.getHasClicked(row-1,column+1)==1) return 1;
        return 0;
    }
    public int left(int row,int column){
        if (getMap(row,column-1)=='M'&&Data.getHasClicked(row,column-1)==1) return 1;
        return 0;
    }
    public int right(int row,int column){
        if (getMap(row,column+1)=='M'&&Data.getHasClicked(row,column+1)==1) return 1;
        return 0;
    }
    public int bottom(int row,int column){
        if (getMap(row+1,column)=='M'&&Data.getHasClicked(row+1,column)==1) return 1;
        return 0;
    }
    public int bottomleft(int row,int column){
        if (getMap(row+1,column-1)=='M'&&Data.getHasClicked(row+1,column-1)==1) return 1;
        return 0;
    }
    public int bottomright(int row,int column){
        if (getMap(row+1,column+1)=='M'&&Data.getHasClicked(row+1,column+1)==1) return 1;
        return 0;
    }
}
