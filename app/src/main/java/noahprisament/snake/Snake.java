package noahprisament.snake;

import java.util.ArrayList;

/**
 * Created by student on 7/25/2016.
 */
public class Snake {
    private ArrayList<int[]> snake = new ArrayList<int[]>(0);
    private Grid grid;
    public int[] gridSize = {10, 10};
    public int[] startingPoint;

    private int length = 5;
    private int newSizeMultiplier = 3;
    private int currentDirection = 3;

    public Snake(){
        this(36, 61);
    }

    public Snake(int xSize, int ySize){
        gridSize[0] = xSize;
        gridSize[1] = ySize;
        int[] temp = {0, 0};
        if((xSize % 2) == 0){
            temp[0] = (xSize / 2);
            if((ySize % 2) == 0){
                temp[1] = (ySize / 2);
            }else{
                temp[1] = ((ySize - 1) /2);
            }
        }else{
            temp[0] = (xSize / 2);
            if((ySize % 2) == 0){
                temp[1] = (ySize / 2);
            }else{
                temp[1] = ((ySize - 1) /2);
            }
        }
        snake.add(temp);
        grid = new Grid(xSize, ySize);
    }

//    public Snake(ArrayList<int[]> setSnake, int setLength,
//                 int setCurrentDirection, int setNewSizeMultiplier){
//        snake = setSnake;
//        length = setLength;
//        currentDirection = setCurrentDirection;
//        newSizeMultiplier = setNewSizeMultiplier;
//    }

    public void saveSnake(String loadNum){
        GameLoop.tinydb.putInt("gridSize0" + loadNum, this.gridSize[0]);
        GameLoop.tinydb.putInt("gridSize1" + loadNum, this.gridSize[1]);
        GameLoop.tinydb.putInt("startingPoint0" + loadNum, this.startingPoint[0]);
        GameLoop.tinydb.putInt("startingPoint1" + loadNum, this.startingPoint[1]);
        GameLoop.tinydb.putInt("length" + loadNum, this.length);
        GameLoop.tinydb.putInt("newSizeMultiplier" + loadNum, this.newSizeMultiplier);
        GameLoop.tinydb.putInt("currentDirection" + loadNum, this.currentDirection);
        GameLoop.tinydb.putInt("roadBlocks" + loadNum, this.grid.amountOfRoadBlocks);
        GameLoop.tinydb.putInt("gridX" + loadNum, this.grid.x);
        GameLoop.tinydb.putInt("gridY" + loadNum, this.grid.y);

        int[][] tempGrid = this.getGrid().getGrid();
        for(int x = 0; x < this.getGrid().x; x++){
            for (int y = 0; y < this.getGrid().y; y++) {
                GameLoop.tinydb.putInt("grid" + x + y + loadNum, tempGrid[x][y]);
            }
        }

        for(int x = 0; x < this.snake.size(); x++){
            GameLoop.tinydb.putInt("snake" + x + "0" + loadNum, this.snake.get(x)[0]);
            GameLoop.tinydb.putInt("snake" + x + "1" + loadNum, this.snake.get(x)[1]);
        }
        GameLoop.tinydb.putInt("snakeSize" + loadNum, this.snake.size());
    }

    public void loadSnake(String loadNum){
        this.gridSize[0] = GameLoop.tinydb.getInt("gridSize0" + loadNum);
        this.gridSize[1] = GameLoop.tinydb.getInt("gridSize1" + loadNum);
        this.startingPoint[0] = GameLoop.tinydb.getInt("startingPoint0" + loadNum);
        this.startingPoint[1] = GameLoop.tinydb.getInt("startingPoint1" + loadNum);
        this.length = GameLoop.tinydb.getInt("length" + loadNum);
        this.newSizeMultiplier = GameLoop.tinydb.getInt("newSizeMultiplier" + loadNum);
        this.currentDirection = GameLoop.tinydb.getInt("currentDirection" + loadNum);
        this.grid.amountOfRoadBlocks = GameLoop.tinydb.getInt("roadBlocks" + loadNum);
        this.grid.x = GameLoop.tinydb.getInt("gridX" + loadNum);
        this.grid.y = GameLoop.tinydb.getInt("gridY" + loadNum);

        int[][] tempGrid = new int[this.getGrid().x][this.getGrid().y];
        for(int x = 0; x < this.getGrid().x; x++){
            for (int y = 0; y < this.getGrid().y; y++) {
                tempGrid[x][y] = GameLoop.tinydb.getInt("grid" + x + y + loadNum);
            }
        }
        this.grid.setGrid(tempGrid);

        snake = new ArrayList<int[]>(0);
        int[] temp = {0, 0};
        for(int x = 0; x < GameLoop.tinydb.getInt("snakeSize" + loadNum); x++){
            temp[0] = GameLoop.tinydb.getInt("snake" + x + "0" + loadNum);
            temp[1] = GameLoop.tinydb.getInt("snake" + x + "1" + loadNum);
            this.snake.add(temp);
        }
    }

    public void changeSize(){
        length += newSizeMultiplier;
        this.snake.ensureCapacity(length);
        newSizeMultiplier += 3;
    }

    public boolean addNew(int direction){
        int[] temp = {0, 0};
        if(direction != 0){
            currentDirection = direction;
        }
        switch (currentDirection){
            case 1:
                temp[0] = this.snake.get(this.snake.size()-1)[0]-1;
                temp[1] = this.snake.get(this.snake.size()-1)[1];
                break;
            case 2:
                temp[0] = this.snake.get(this.snake.size()-1)[0];
                temp[1] = this.snake.get(this.snake.size()-1)[1]-1;
                break;
            case 3:
                temp[0] = this.snake.get(this.snake.size()-1)[0]+1;
                temp[1] = this.snake.get(this.snake.size()-1)[1];
                break;
            case 4:
                temp[0] = this.snake.get(this.snake.size()-1)[0];
                temp[1] = this.snake.get(this.snake.size()-1)[1]+1;
                break;
        }
        if(this.snake.size() == length){
            this.snake.set(length - 1, temp);
        }else{
            this.snake.add(temp);
        }
        if(!grid.testPoint(this.snake.get(this.snake.size()-1)[0], this.snake.get(this.snake.size()-1)[1])){
            grid.setPointOn(this.snake.get(this.snake.size()-1)[0], this.snake.get(this.snake.size()-1)[1]);
            return true;
        }else{
            return false;
        }
    }

    public void eraseLast(){
        grid.setPointOff(this.snake.get(0)[0], this.snake.get(0)[1]);
        this.snake.remove(0);
    }

    public ArrayList<int[]> getSnake(){
        return this.snake;
    }

    public Grid getGrid(){
        return this.grid;
    }

    public int getLength(){
        return length;
    }

//    public int getLength(){
//        return this.length;
//    }
}
