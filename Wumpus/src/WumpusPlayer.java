//Bryant Le
//Mrs.Kalathy
//Period 5

public class WumpusPlayer {

    public static final int NORTH = 0;
    public static final int EAST = 1;
    public static final int SOUTH = 2;
    public static final int WEST = 3;

    private int direction, colPosition, rowPosition;
    private boolean arrow, gold;

    public WumpusPlayer()
    {
        direction = NORTH;
        gold = false;
        arrow = true;
    }

    //Mutators
    public void setArrow(boolean arrow) { this.arrow = arrow; }

    public void setDirection(int direction) { this.direction = direction; }

    public void setGold(boolean gold) {
        this.gold = gold;
    }

    public void setColPosition(int colPosition) {
        this.colPosition = colPosition;
    }

    public void setRowPosition(int rowPosition) {
        this.rowPosition = rowPosition;
    }

    //Accessors
    public boolean isGold() {
        return gold;
    }

    public boolean isArrow() {
        return arrow;
    }

    public int getColPosition() {
        return colPosition;
    }

    public int getDirection() {
        return direction;
    }

    public int getRowPosition() {
        return rowPosition;
    }

}
