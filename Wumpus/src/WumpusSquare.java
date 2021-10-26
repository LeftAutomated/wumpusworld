//Bryant Le
//Mrs.Kalathy
//Period 5

public class WumpusSquare {

    private boolean gold, ladder, pit,
    breeze, wumpus, deadWumpus, stench, visited;

    public WumpusSquare(){
        gold = ladder = pit = breeze = wumpus
        = deadWumpus = stench = visited = false;
    }

    //Mutators
    public void setGold(boolean gold) {
        this.gold = gold;
    }

    public void setBreeze(boolean breeze) {
        this.breeze = breeze;
    }

    public void setDeadWumpus(boolean deadWumpus) {
        this.deadWumpus = deadWumpus;
    }

    public void setLadder(boolean ladder) {
        this.ladder = ladder;
    }

    public void setPit(boolean pit) {
        this.pit = pit;
    }

    public void setStench(boolean stench) {
        this.stench = stench;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public void setWumpus(boolean wumpus) {
        this.wumpus = wumpus;
    }

    //Accessors
    public boolean isGold() {
        return gold;
    }

    public boolean isLadder() {
        return ladder;
    }

    public boolean isPit() {
        return pit;
    }

    public boolean isBreeze() {
        return breeze;
    }

    public boolean isWumpus() {
        return wumpus;
    }

    public boolean isDeadWumpus() {
        return deadWumpus;
    }

    public boolean isStench() {
        return stench;
    }

    public boolean isVisited() {
        return visited;
    }

    public String toString(){
        if(isDeadWumpus())
            return "D";
        else if(isLadder())
            return "L";
        else if(isPit())
            return "P";
        else if(isGold())
            return "G";
        else if(isWumpus())
            return "w";
        else
            return "*";
    }
}
