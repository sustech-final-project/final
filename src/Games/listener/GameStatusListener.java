package Games.listener;

import Games.utils.*;

public interface GameStatusListener extends Listener {
    void onGameStatusChanged(GameStatus status);
}
