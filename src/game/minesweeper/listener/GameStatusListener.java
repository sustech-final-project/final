package game.minesweeper.listener;

import game.minesweeper.utils.GameStatus;

public interface GameStatusListener extends Listener{
    void onGameStatusChanged(GameStatus status);
}
