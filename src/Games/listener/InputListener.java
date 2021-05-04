package Games.listener;

import Games.utils.*;

public interface InputListener extends Listener {
    void onPlayerClickBoard(int x, int y, ClickType type);

    void onGameRestart();
}
