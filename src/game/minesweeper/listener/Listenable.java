package game.minesweeper.listener;

import java.util.ArrayList;

public interface Listenable<T extends Listener> {

    void registerListener(T listener);

    void unregisterListener(T listener);
}