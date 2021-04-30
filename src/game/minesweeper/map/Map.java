package game.minesweeper.map;

import game.minesweeper.listener.Listenable;
import game.minesweeper.listener.MapListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Map implements Listenable<MapListener> {
    private int x;
    private int y;
    private int numMine;
    private final Block[][] map;
    private final ArrayList<MapListener> listenerList = new ArrayList<>();
    private int count;
    private boolean isLose = false;

    public Map(int x, int y, int numMine) {
        map = new Block[x][y];
        init(x, y, numMine);
    }

    public void init(int x, int y, int numMine) {
        isLose = false;
        this.x = x;
        this.y = y;
        this.numMine = numMine;
        count = x * y;

        //Initialize map
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                map[i][j] = new Block(i, j);
            }
        }
        //Initialize mines
        for (int i = 0; i < numMine; i++) {
            map[i % x][i / x].state = -1;
        }
        //Shuffle
        Random random = new Random();
        for (int i = x * y - 1; i > 0; i--) {
            int tmp = map[i % x][i / x].getState();
            int rand = random.nextInt(i + 1);
            map[i % x][i / y].state = map[rand % x][rand / y].state;
            map[rand % x][rand / x].state = tmp;
        }
        //Add numbers
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (map[i][j].getState() == -1) {
                    for (int k = -1; k <= 1; k++) {
                        for (int l = -1; l <= 1; l++) {
                            if (check(i + k, j + l) && map[i + k][j + l].state != -1) {
                                map[i + k][j + l].state++;
                            }
                        }
                    }
                }
            }
        }
        listenerList.forEach(MapListener::onMapReload);
    }

    public Block getBlock(int x, int y) {
        return map[x][y];
    }

    public void clickBlock(Block block) {
        if (isLose || block.hasFlag() || block.hasClicked()) return;
        block.click();
        listenerList.forEach(listener -> listener.onBlockClicked(block));
        if (block.isMine()) {
            isLose = true;
            return;
        }
        count--;
        int x = block.getLocation().x;
        int y = block.getLocation().y;
        if (block.getState() == 0) {
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if (check(x + i, y + j)) {
                        clickBlock(x + i, y + j);
                    }
                }
            }
        }
    }

    public void clickBlock(int x, int y) {
        clickBlock(getBlock(x, y));
    }

    public void rightClick(int x, int y) {
        Block block = getBlock(x, y);
        if (!block.hasClicked()) {
            block.flag();
        } else {
            int count = 0;
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if (!(i==0 && j==0) && check(x + i, y + j)) {
                        Block tmp = getBlock(x + i, y + j);
                        if (tmp.hasFlag()) {
                            count++;
                        }
                    }
                }
            }
            if (count == block.getState()) {
                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        if (check(x + i, y + j)) {
                            clickBlock(x + i, y + j);
                        }
                    }
                }
            }
        }
        listenerList.forEach(listener -> listener.onBlockClicked(block));
    }

    public boolean check(int x, int y) {
        return x >= 0 && x < this.x && y >= 0 && y < this.y;
    }

    public int getCount() {
        return count;
    }

    public boolean isLose() {
        return isLose;
    }

    public void printMap() {
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                int tmp = map[i][j].getState();
                System.out.printf(((tmp < 0) ? "" : " ") + "%d ", tmp);
            }
            System.out.print("\n");
        }
    }

    @Override
    public String toString() {
        return "Map{" +
                "x=" + x +
                ", y=" + y +
                ", numMine=" + numMine +
                ", map=" + Arrays.toString(map) +
                '}';
    }

    @Override
    public void registerListener(MapListener listener) {
        listenerList.add(listener);
    }

    @Override
    public void unregisterListener(MapListener listener) {
        listenerList.remove(listener);
    }
}
