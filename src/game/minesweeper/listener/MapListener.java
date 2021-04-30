package game.minesweeper.listener;

import game.minesweeper.map.Block;
import game.minesweeper.utils.ClickType;
import game.minesweeper.utils.GameStatus;
import game.minesweeper.utils.Point2D;

public interface MapListener extends Listener {
    void onBlockClicked(Block block);

    void onMapReload();
}
