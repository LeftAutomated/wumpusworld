//Bryant Le
//Mrs.Kalathy
//Period 5

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class WumpusApplication extends Application
{
    public static final int PLAYING = 0;
    public static final int DEAD = 1;
    public static final int WON = 2;
    private int status;
    private WumpusPlayer player;
    private WumpusMap map;
    private GraphicsContext gc;

    int x;
    int y;
    Image fogOfWar, caveFloor, arrow, treasure, ladder, wumpus,
    deadWumpus, stench, pit, breeze, up, down, left, right;
    int[][] fog;
    int time;
    boolean cheat;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Wumpus World");
        Group root = new Group();
        Canvas canvas = new Canvas(600, 600);
        gc = canvas.getGraphicsContext2D();

        reset();

        //Images
        fogOfWar = new Image("black.GIF");
        caveFloor = new Image("Floor.gif");
        arrow = new Image("arrow.gif");
        treasure = new Image("gold.gif");
        ladder = new Image("ladder.gif");
        wumpus = new Image("wumpus.png");
        deadWumpus = new Image("deadwumpus.png");
        stench = new Image("stench.gif");
        pit = new Image("pit.gif");
        breeze = new Image("breeze.gif");
        up = new Image("playerUp.png");
        down = new Image("playerDown.png");
        left = new Image("playerLeft.png");
        right = new Image("playerRight.png");

        //Text Test Output
        for(int r = 1; r <= 100; r++) {
            System.out.print(map.toString().charAt(r-1));
            if(r % 10 == 0)
                System.out.println();
        }

        //Key Inputs
        canvas.setOnKeyTyped(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent event){
                String s = event.getCharacter();
                gc.setStroke(Color.WHITE);
                if(status == PLAYING) {
                if(s.equals("w") && y >= 50) {
                    y -= 50;
                    player.setDirection(0);
                    map.getSquare(player.getRowPosition()-1,player.getColPosition()).setVisited(true);
                    player.setRowPosition(player.getRowPosition()-1);
                }
                if(s.equals("d") && x <= 450){
                    x += 50;
                    player.setDirection(1);
                    map.getSquare(player.getRowPosition(),player.getColPosition()+1).setVisited(true);
                    player.setColPosition(player.getColPosition()+1);
                }
                if(s.equals("a") && x >= 100){
                    x -= 50;
                    player.setDirection(3);
                    map.getSquare(player.getRowPosition(),player.getColPosition()-1).setVisited(true);
                    player.setColPosition(player.getColPosition()-1);
                }
                if(s.equals("s") && y <= 400){
                    y += 50;
                    player.setDirection(2);
                    map.getSquare(player.getRowPosition()+1,player.getColPosition()).setVisited(true);
                    player.setRowPosition(player.getRowPosition()+1);
                }
                if(s.equals("i") && player.getRowPosition() > 0){
                    if(map.getSquare(player.getRowPosition()-1,player.getColPosition()).isWumpus()){
                        map.getSquare(player.getRowPosition()-1,player.getColPosition()).setDeadWumpus(true);
                        map.getSquare(player.getRowPosition()-1,player.getColPosition()).setWumpus(false);
                    }
                    player.setArrow(false);
                }
                if(s.equals("k") && player.getRowPosition() < 9){
                    if(map.getSquare(player.getRowPosition()+1,player.getColPosition()).isWumpus()){
                        map.getSquare(player.getRowPosition()+1,player.getColPosition()).setDeadWumpus(true);
                        map.getSquare(player.getRowPosition()+1,player.getColPosition()).setWumpus(false);
                    }
                    player.setArrow(false);
                }
                if(s.equals("j") && player.getColPosition() > 0){
                    if(map.getSquare(player.getRowPosition(),player.getColPosition()-1).isWumpus()){
                        map.getSquare(player.getRowPosition(),player.getColPosition()-1).setDeadWumpus(true);
                        map.getSquare(player.getRowPosition(),player.getColPosition()-1).setWumpus(false);
                    }
                    player.setArrow(false);
                }
                if(s.equals("l") && player.getColPosition() < 9){
                    if(map.getSquare(player.getRowPosition(),player.getColPosition()+1).isWumpus()){
                        map.getSquare(player.getRowPosition(),player.getColPosition()+1).setDeadWumpus(true);
                        map.getSquare(player.getRowPosition(),player.getColPosition()+1).setWumpus(false);
                    }
                    player.setArrow(false);
                }
                if(s.equals("p")){
                    if(map.getSquare(player.getRowPosition(),player.getColPosition()).isGold()) {
                        player.setGold(true);
                        map.getSquare(player.getRowPosition(),player.getColPosition()).setGold(false);
                    }
                }
                if(s.equals("c")){
                    if(map.getSquare(player.getRowPosition(),player.getColPosition()).isLadder() && player.isGold())
                        status = WON;
                }}
                if(s.equals("n")){
                    reset();
                }
                if(s.equals("*")){
                    cheat = !cheat;
                }
                draw(canvas.getGraphicsContext2D());
            }
        });

        draw(gc);
        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));
        canvas.requestFocus();
        primaryStage.show();
    }

    //Base Stats. New Game
    private void reset(){
        status = PLAYING;
        map = new WumpusMap();
        player = new WumpusPlayer();
        fog = new int[10][10];
        time = 0;
        cheat = false;
        for(int r = 0; r < 10; r++)
            for(int c = 0; c < 10; c++)
                fog[r][c] = 1;

        player.setRowPosition(map.getLadderRow());
        player.setColPosition(map.getLadderCol());
        map.getSquare(map.getLadderRow(),map.getLadderCol()).setVisited(true);
        x = map.getLadderCol()*50+50;
        y = map.getLadderRow()*50;
        fog[map.getLadderRow()][map.getLadderCol()] = 0;
    }

    private void draw(GraphicsContext gc) {
        //Standard
        gc.setFill(Color.BLACK);
        gc.fillRect(0,0,800,800);
        gc.setStroke(Color.WHITE);

        //Floor
        for(int r = 0; r < 10; r++) {
            for (int c = 0; c < 10; c++)
                gc.drawImage(caveFloor,r * 50 + 50, c * 50);
        }

        //Stuff on floor
        for(int r = 0; r < 10; r++) {
            for (int c = 0; c < 10; c++){
                if(map.getSquare(r,c).isBreeze())
                    gc.drawImage(breeze,c * 50 + 50, r * 50);
                if(map.getSquare(r,c).isStench())
                    gc.drawImage(stench,c * 50 + 50, r * 50);
                if(map.getSquare(r,c).isPit())
                    gc.drawImage(pit,c * 50 + 50, r * 50);
                else if(map.getSquare(r,c).isGold())
                    gc.drawImage(treasure,c * 50 + 50, r * 50);
                else if(map.getSquare(r,c).isWumpus())
                    gc.drawImage(wumpus,c * 50 + 50, r * 50);
                else if(map.getSquare(r,c).isDeadWumpus())
                    gc.drawImage(deadWumpus,c * 50 + 50, r * 50);
                else if(map.getSquare(r,c).isLadder())
                    gc.drawImage(ladder,c * 50 + 50, r * 50);
            }
        }

        //Cheat
        for(int r = 0; r < 10; r++) {
            for(int c = 0; c < 10; c++)
                if(!map.getSquare(c,r).isVisited() && cheat == false)
                    gc.drawImage(fogOfWar, r*50+50, c*50);
                else if(map.getSquare(c,r).isVisited())
                    fog[r][c] = 0;
        }

        //Player Direction
        if(player.getDirection() == 0)
            gc.drawImage(up,x,y);
        else if(player.getDirection() == 1)
            gc.drawImage(right,x,y);
        else if(player.getDirection() == 2)
            gc.drawImage(down,x,y);
        else if(player.getDirection() == 3)
            gc.drawImage(left,x,y);

        //Messages
        gc.strokeText("Messages",400,520);

        if(map.getSquare(player.getRowPosition(),player.getColPosition()).isBreeze()){
            gc.strokeText("You feel a breeze",400,560);
        }
        if(map.getSquare(player.getRowPosition(),player.getColPosition()).isStench()){
            gc.strokeText("You smell a stench",400,570);
        }
        if(map.getSquare(player.getRowPosition(),player.getColPosition()).isGold()){
            gc.strokeText("You see a glimmer",400,550);
        }
        else if(map.getSquare(player.getRowPosition(),player.getColPosition()).isLadder()){
            gc.strokeText("You bump into a ladder",400,550);
        }
        else if(map.getSquare(player.getRowPosition(),player.getColPosition()).isPit()){
            status = DEAD;
            gc.strokeText("You fell down a pit to your death",400,550);
        }
        else if(map.getSquare(player.getRowPosition(),player.getColPosition()).isWumpus()){
            status = DEAD;
            gc.strokeText("You are eaten by the Wumpus",400,550);
        }

        for(int r = 0; r < 10; r++)
            for(int c = 0; c < 10; c++)
                if(time == 0 && map.getSquare(r,c).isDeadWumpus()) {
                    gc.strokeText("You hear a scream", 400, 580);
                    time = 1;
                }

        //Inventory
        gc.strokeText("Inventory", 100,520);

        if(player.isArrow())
            gc.drawImage(arrow,75,530);
        if(player.isGold())
            gc.drawImage(treasure, 125,530);

        gc.strokeText("Press 'n' to play again",220,530);
        gc.strokeText("Press 'c' to climb ladder",220,550);
        gc.strokeText("Press 'p' to pick up gold",220,570);
        gc.strokeText("Press '*' to turn on cheats",220,590);

        //Win.Dead
        if(status == WON){
            gc.setStroke(Color.GREEN);
            gc.strokeText("You Win!",250,510);
        }
        else if(status == DEAD){
            gc.setStroke(Color.RED);
            gc.strokeText("You Died.",250,510);
        }
    }
}