package game.minesweeper.listener;

import game.minesweeper.utils.ClickType;

public interface InputListener extends Listener{
    void onPlayerClickBoard(int x, int y, ClickType type);

    void onGameRestart();
}
