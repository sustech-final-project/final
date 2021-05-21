package Games.Map;

public interface pve {
    public default boolean DoubleClick(int row, int column) {
        return false;
    }
}
