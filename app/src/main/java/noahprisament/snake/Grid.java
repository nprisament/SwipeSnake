package noahprisament.snake;

import android.graphics.Canvas;

import java.util.Random;

/**
 * Created by student on 7/25/2016.
 */
public class Grid {
    public int amountOfRoadBlocks = 2;
    public int x;
    public int y;
    private int[][] grid;

    public Grid(){
        this(0,0);
    }

    public Grid(int newX, int newY) {
        x = newX;
        y = newY;
        grid = new int[x][y];
        amountOfRoadBlocks = 2;
        newTarget(true);
        for (int i = 0; i < x; i++){
            for (int i2 = 0; i2 < y; i2++){
                if(i == 0 | i == (x - 1) | i2 == 0 | i2 == (y - 1)){
                    grid[i][i2] = 4;
                }
            }
        }
    }

    public void setPointOn(int xPoint, int yPoint) {
        grid[xPoint][yPoint] = 1;
    }

    public void setPointWall(int xPoint, int yPoint) {
        grid[xPoint][yPoint] = 2;
    }

    public void setPointTarget(int xPoint, int yPoint) {
        grid[xPoint][yPoint] = 3;
    }

    public void setPointOff(int xPoint, int yPoint) {
        grid[xPoint][yPoint] = 0;
    }

    public void setGrid(int[][] setGrid){
        grid = setGrid;
    }

    public int[][] getGrid(){
        return grid;
    }

    public int getPoint(int xGet, int yGet){
        return grid[xGet][yGet];
    }

    public boolean testPoint(int xPoint, int yPoint) {
        try {
            if(grid[xPoint][yPoint] == 1 | grid[xPoint][yPoint] == 2 | grid[xPoint][yPoint] == 4) {
                return true;
            }else if(grid[xPoint][yPoint] == 3){
                this.newTarget(false);
                this.newRoadBlocks();
                return false;
            }else if(grid[xPoint][yPoint] == 0){
                return false;
            }
        }catch(IndexOutOfBoundsException e){
            // MAKE GAME OVER
            return true;
        }
        return true;
    }

    public boolean emptyTestPoint(int xPoint, int yPoint) {
        try {
            if(grid[xPoint][yPoint] == 1 | grid[xPoint][yPoint] == 2 | grid[xPoint][yPoint] == 4) {
                return true;
            }else if(grid[xPoint][yPoint] == 3){
                return false;
            }else if(grid[xPoint][yPoint] == 0){
                return false;
            }
        }catch(IndexOutOfBoundsException e){
            return true;
        }
        return true;
    }

    public void newTarget(boolean first){
        if(first){
            boolean loop = true;
            while(loop){
                int xRand = new Random().nextInt((int)x/2);
                int yRand = new Random().nextInt(((int)y/2)-1);
                if(!this.emptyTestPoint(xRand, yRand)){
                    this.setPointTarget(xRand, yRand);
                    loop = false;
                }
            }
        }else{
            boolean loop = true;
            while(loop){
                int xRand = new Random().nextInt(x + 1);
                int yRand = new Random().nextInt(y + 1);
                if(!this.emptyTestPoint(xRand, yRand)){
                    this.setPointTarget(xRand, yRand);
                    loop = false;
                }
            }
            GameLoop.snake.changeSize();
        }
        GameLoop.currentScore++;
    }

    public void newRoadBlocks(){
        int counter = 1;
        boolean loop = true;
        while(loop){
            int xRand = new Random().nextInt(x + 1);
            int yRand = new Random().nextInt(y + 1);
            if( !this.emptyTestPoint(xRand, yRand)
                    & !this.emptyTestPoint(xRand + 1, yRand)
                    & !this.emptyTestPoint(xRand, yRand + 1)
                    & !this.emptyTestPoint(xRand + 1, yRand + 1)){
                this.setPointWall(xRand, yRand);
                this.setPointWall(xRand + 1, yRand);
                this.setPointWall(xRand, yRand + 1);
                this.setPointWall(xRand + 1, yRand + 1);
                if(counter >= amountOfRoadBlocks){
                    loop = false;
                }else{
                    counter++;
                }
            }
        }
        if(amountOfRoadBlocks < 8){
          amountOfRoadBlocks += 2;
        }
    }
}