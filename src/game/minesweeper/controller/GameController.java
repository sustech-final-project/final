package game.minesweeper.controller;

import game.minesweeper.listener.GameStatusListener;
import game.minesweeper.utils.GameStatus;
import game.minesweeper.view.Display;
import game.minesweeper.listener.InputListener;
import game.minesweeper.listener.Listenable;
import game.minesweeper.map.Map;
import game.minesweeper.utils.ClickType;

import java.util.ArrayList;

public class GameController implements InputListener, Listenable<GameStatusListener> {
    private final Map map;
    private final int numMine;
    private GameStatus gameStatus;

    private final ArrayList<GameStatusListener> listenerList = new ArrayList<>();

    public GameController(int numMine) {
        this.numMine = numMine;
        this.gameStatus = GameStatus.PROGRESS;
        map = new Map(9, 9, numMine);
        map.printMap();
        Display display = new Display();
        display.registerListener(this);
        map.registerListener(display);
        registerListener(display);
    }

    @Override
    public void onPlayerClickBoard(int x, int y, ClickType type) {
        if (gameStatus != GameStatus.PROGRESS) return;
        if (type == ClickType.LEFT_CLICK) map.clickBlock(x, y);
        if (type == ClickType.RIGHT_CLICK) map.rightClick(x, y);
        if (map.isLose()) {
            gameStatus = GameStatus.LOSE;
            listenerList.forEach(listener -> listener.onGameStatusChanged(gameStatus));
        }
        if (map.getCount() == numMine) {
            gameStatus = GameStatus.WIN;
            listenerList.forEach(listener -> listener.onGameStatusChanged(gameStatus));
        }
    }

    @Override
    public void onGameRestart() {
        gameStatus = GameStatus.PROGRESS;
        map.init(9, 9, 10);
    }

    @Override
    public void registerListener(GameStatusListener listener) {
        listenerList.add(listener);
    }

    @Override
    public void unregisterListener(GameStatusListener listener) {
        listenerList.remove(listener);
    }
}
