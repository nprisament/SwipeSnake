package noahprisament.snake;

/**
 * Created by student on 7/26/2016.
 */
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView {
    private final BitmapFactory bitmapFactory = new BitmapFactory();
    private Bitmap blackMap;
    private Bitmap redMap;
    private Bitmap whiteMap;
    private Bitmap grayMap;
    private Bitmap greenMap;
    private Bitmap blueMap;
    private Bitmap yellowMap;
    private Canvas canvas = new Canvas();

    public GameLoop thread;
    private SurfaceView surface;
    private SurfaceHolder holder;
    Context context;

    public GameView(Context context) {
        super(context);
        this.context = context;
        thread = new GameLoop(this);
        holder = getHolder();
        holder.addCallback(new SurfaceHolder.Callback() {

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                boolean retry = true;
                thread.setRunning(false);
                while (retry) {
                    try {
                        thread.join();
                        retry = false;
                    } catch (InterruptedException e) {
                    }
                }
            }

            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                thread.setRunning(true);
                thread.start();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format,
                                       int width, int height) {
            }
        });
        surface = new SurfaceView(context);
        blackMap = this.bitmapFactory.decodeResource(this.surface.getResources(), R.drawable.black10);
        redMap = this.bitmapFactory.decodeResource(this.surface.getResources(), R.drawable.red10);
        whiteMap = this.bitmapFactory.decodeResource(this.surface.getResources(), R.drawable.white10);
        grayMap = this.bitmapFactory.decodeResource(this.surface.getResources(), R.drawable.gray10);
        greenMap = this.bitmapFactory.decodeResource(this.surface.getResources(), R.drawable.green10);
        blueMap = this.bitmapFactory.decodeResource(this.surface.getResources(), R.drawable.blue10);
        yellowMap = this.bitmapFactory.decodeResource(this.surface.getResources(), R.drawable.yellow10);
        setFocusable(true);
    }

    public int[] getPixelPlace(int[] newPlace){
        int[] pixelPlace = {0, 0};
        pixelPlace[0] = newPlace[0] * (this.thread.canvas.getWidth() / this.thread.snake.gridSize[0]);
        pixelPlace[1] = newPlace[1] * (this.thread.canvas.getHeight() / this.thread.snake.gridSize[1]);
        return pixelPlace;
    }

    public void draw(int xSpot, int ySpot, int color){
        switch (color){
            case 1:
                canvas.drawBitmap(blackMap, xSpot, ySpot, null);
                break;
            case 2:
                if(GameLoop.settings.getTarget().equals("Black")){
                    canvas.drawBitmap(blackMap, xSpot, ySpot, null);
                }else if(GameLoop.settings.getTarget().equals("Red")){
                    canvas.drawBitmap(redMap, xSpot, ySpot, null);
                }else if(GameLoop.settings.getTarget().equals("Gray")){
                    canvas.drawBitmap(grayMap, xSpot, ySpot, null);
                }else if(GameLoop.settings.getTarget().equals("Green")){
                    canvas.drawBitmap(greenMap, xSpot, ySpot, null);
                }else if(GameLoop.settings.getTarget().equals("Blue")){
                    canvas.drawBitmap(blueMap, xSpot, ySpot, null);
                }else if(GameLoop.settings.getTarget().equals("Yellow")){
                    canvas.drawBitmap(yellowMap, xSpot, ySpot, null);
                }else if(GameLoop.settings.getTarget().equals("Default")){
                    canvas.drawBitmap(whiteMap, xSpot, ySpot, null);
                }else{
                    canvas.drawBitmap(whiteMap, xSpot, ySpot, null);
                }
                break;
            case 3:
                if(GameLoop.settings.getWall().equals("Black")){
                    canvas.drawBitmap(blackMap, xSpot, ySpot, null);
                }else if(GameLoop.settings.getWall().equals("White")){
                    canvas.drawBitmap(whiteMap, xSpot, ySpot, null);
                }else if(GameLoop.settings.getWall().equals("Gray")){
                    canvas.drawBitmap(grayMap, xSpot, ySpot, null);
                }else if(GameLoop.settings.getWall().equals("Green")){
                    canvas.drawBitmap(greenMap, xSpot, ySpot, null);
                }else if(GameLoop.settings.getWall().equals("Blue")){
                    canvas.drawBitmap(blueMap, xSpot, ySpot, null);
                }else if(GameLoop.settings.getWall().equals("Yellow")){
                    canvas.drawBitmap(yellowMap, xSpot, ySpot, null);
                }else if(GameLoop.settings.getTarget().equals("Default")){
                    canvas.drawBitmap(redMap, xSpot, ySpot, null);
                }else{
                    canvas.drawBitmap(redMap, xSpot, ySpot, null);
                }
                break;
            case 4:
                canvas.drawBitmap(grayMap, xSpot, ySpot, null);
                break;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }

    @Override
    protected void onDraw(Canvas newCanvas) {
        canvas = newCanvas;
        canvas.drawColor(Color.GRAY);
        int[][] tempGrid = this.thread.snake.getGrid().getGrid();
        for(int x = 0; x < this.thread.snake.getGrid().x; x++){
            for (int y = 0; y < this.thread.snake.getGrid().y; y++) {
                this.draw((x*GameLoop.settings.getDensity()), ((y*GameLoop.settings.getDensity())+10), tempGrid[x][y]);
            }
        }
    }
}

