package Games.listener;

import game.minesweeper.map.Block;

public interface MapListener extends Listener {
    void onBlockClicked(Block block);

    void onMapReload();
}
