//Bryant Le
//Mrs.Kalathy
//Period 5

public class WumpusMap {
    public static final int NUM_ROWS = 10;
    public static final int NUM_COLUMNS = 10;
    public static final int NUM_PITS = 10;

    private WumpusSquare[][] grid;
    private int ladderC;
    private int ladderR;

    public WumpusMap() {
        createMap();
    }

    public void createMap(){
        //Base Map
        grid = new WumpusSquare[NUM_ROWS][NUM_COLUMNS];
        for(int r = 0; r < 10; r++)
            for(int c = 0; c < 10; c++)
                grid[r][c] = new WumpusSquare();

        //Adding stuff to grid
        int ranR;
        int ranC;
        for(int i = 0; i < NUM_PITS; i++)
        {
            ranR = (int) Math.floor(Math.random() * 10);
            ranC = (int) Math.floor(Math.random() * 10);
            while(!grid[ranR][ranC].toString().equals("*"))
            {
                ranR = (int) Math.floor(Math.random() * 10);
                ranC = (int) Math.floor(Math.random() * 10);
            }
            grid[ranR][ranC].setPit(true);
        }

        //Gold
        ranR = (int) Math.floor(Math.random() * 10);
        ranC = (int) Math.floor(Math.random() * 10);
        while(!grid[ranR][ranC].toString().equals("*"))
        {
            ranR = (int) Math.floor(Math.random() * 10);
            ranC = (int) Math.floor(Math.random() * 10);
        }
        grid[ranR][ranC].setGold(true);

        //Wumpus
        ranR = (int) Math.floor(Math.random() * 10);
        ranC = (int) Math.floor(Math.random() * 10);
        while(!grid[ranR][ranC].toString().equals("*"))
        {
            ranR = (int) Math.floor(Math.random() * 10);
            ranC = (int) Math.floor(Math.random() * 10);
        }
        grid[ranR][ranC].setWumpus(true);

        //Ladder
        ranR = (int) Math.floor(Math.random() * 10);
        ranC = (int) Math.floor(Math.random() * 10);
        while(!grid[ranR][ranC].toString().equals("*"))
        {
            ranR = (int) Math.floor(Math.random() * 10);
            ranC = (int) Math.floor(Math.random() * 10);
        }
        grid[ranR][ranC].setLadder(true);
        ladderC = ranC;
        ladderR = ranR;

        //Adding Breezes and Stenches
        for(int r = 0; r < 10; r++){
            for(int c = 0; c < 10; c++){
                if(grid[r][c].toString().equals("P"))
                {
                    setSurround(r,c,"P");
                }
                else if(grid[r][c].toString().equals("w")){
                    setSurround(r,c,"w");
                }
            }
        }

    }

    private void setSurround(int row, int col, String obj)
    {
        if(row > 0){
            if(obj.equals("P") && !grid[row-1][col].isPit() && !grid[row-1][col].isWumpus() && !grid[row-1][col].isLadder() && !grid[row-1][col].isGold())
                grid[row-1][col].setBreeze(true);
            else if(obj.equals("w") && !grid[row-1][col].isPit() && !grid[row-1][col].isWumpus() && !grid[row-1][col].isLadder())
                grid[row-1][col].setStench(true);
        }
        if(col > 0){
            if(obj.equals("P") && !grid[row][col-1].isPit() && !grid[row][col-1].isWumpus() && !grid[row][col-1].isLadder() && !grid[row][col-1].isGold())
                grid[row][col-1].setBreeze(true);
            else if(obj.equals("w") && !grid[row][col-1].isPit() && !grid[row][col-1].isWumpus() && !grid[row][col-1].isLadder())
                grid[row][col-1].setStench(true);
        }
        if(col < 9 ){
            if(obj.equals("P") && !grid[row][col+1].isPit() && !grid[row][col+1].isWumpus() && !grid[row][col+1].isLadder() && !grid[row][col+1].isGold())
                grid[row][col+1].setBreeze(true);
            else if(obj.equals("w") && !grid[row][col+1].isPit() && !grid[row][col+1].isWumpus() && !grid[row][col+1].isLadder())
                grid[row][col+1].setStench(true);
        }
        if(row < 9){
            if(obj.equals("P") && !grid[row+1][col].isPit() && !grid[row+1][col].isWumpus() && !grid[row+1][col].isLadder() && !grid[row+1][col].isGold())
                grid[row+1][col].setBreeze(true);
            else if(obj.equals("w") && !grid[row+1][col].isPit() && !grid[row+1][col].isWumpus() && !grid[row+1][col].isLadder())
                grid[row+1][col].setStench(true);
        }
    }
    //Ladder Accessors
    public int getLadderCol(){
        return ladderC;
    }

    public int getLadderRow(){
        return ladderR;
    }

    //Returns specific square
    public WumpusSquare getSquare(int row, int col){
        if(col >= 0 && col <= 10 && row >= 0 && row <= 10)
            return grid[row][col];
        return null;
    }

    public String toString() {
        String s = "";
        for (int r = 0; r < 10; r++)
            for (int c = 0; c < 10; c++)
                s += grid[r][c].toString();
        return s;
    }
}
