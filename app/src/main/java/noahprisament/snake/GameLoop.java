package noahprisament.snake;

/**
 * Created by student on 7/26/2016.
 */
import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;
import java.util.Timer;
import java.util.TimerTask;

import static android.support.v4.app.ActivityCompat.startActivityForResult;

public class GameLoop extends Thread {
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;
    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_MAX_OFF_PATH = 250;
    static final long FPS = 10;

    public static Snake snake = new Snake();
    public static Settings settings;
    public static int currentDirection = 2;
    public static int currentScore = 0;
    public static boolean running;
    public static boolean pause;

    public static TinyDB tinydb;
    public Canvas canvas = new Canvas();
    private SurfaceHolder surfaceHolder;
    private GameView gameView;

    public void setRunning(boolean running) {
        this.running = running;
    }

    public GameLoop(GameView gameView) {
        pause = false;
        this.gameView = gameView;
        tinydb = new TinyDB(gameView.context);
        GameLoop.settings = new Settings();
        GameLoop.settings.loadSettings(1);
        gameView.setOnTouchListener(new OnSwipeTouchListener() {
            public boolean onSwipeTop() {
                currentDirection = 2;
                return true;
            }

            public boolean onSwipeRight() {
                currentDirection = 3;
                return true;
            }

            public boolean onSwipeLeft() {
                currentDirection = 1;
                return true;
            }
            public boolean onSwipeBottom() {
                currentDirection = 4;
                return true;
            }

            public void onClick(View arg0) {
                //GameActivity.pause();
            }
        });
    }

    public GameLoop(SurfaceHolder surfaceHolder, GameView gameView) {
        super();
        this.surfaceHolder = surfaceHolder;
        this.gameView = gameView;
        tinydb = new TinyDB(gameView.context);
        GameLoop.settings = new Settings();
        GameLoop.settings.loadSettings(1);
        gameView.setOnTouchListener(new OnSwipeTouchListener() {
            public boolean onSwipeTop() {
                currentDirection = 2;
                return true;
            }

            public boolean onSwipeRight() {
                currentDirection = 3;
                return true;
            }

            public boolean onSwipeLeft() {
                currentDirection = 1;
                return true;
            }
            public boolean onSwipeBottom() {
                currentDirection = 4;
                return true;
            }
        });
    }

    public void move(int direction){
        if(!(this.snake.getSnake().size() < this.snake.getLength())){
            this.snake.eraseLast();
        }
        if(this.snake.addNew(direction)){}else{
            this.gameOver();
        }
    }

    public void gameOver(){
        this.setRunning(false);
    }
    @Override
    @SuppressLint("WrongCall")
    public void run() {
        long ticksPS = 1000 / GameLoop.settings.getFPS();
        long startTime;
        long sleepTime;
        while (running) {
            if(pause){
                continue;
            }
            Canvas canvas = null;
            startTime = System.currentTimeMillis();
            try {
                canvas = gameView.getHolder().lockCanvas();
                synchronized (gameView.getHolder()) {
                    this.move(currentDirection);
                    gameView.onDraw(canvas);
                }
            } finally {
                if (canvas != null) {
                    gameView.getHolder().unlockCanvasAndPost(canvas);
                }
            }
            sleepTime = ticksPS-(System.currentTimeMillis() - startTime);
            try {
                if (sleepTime > 0)
                    sleep(sleepTime);
                else
                    sleep(10);
            } catch (Exception e) {}
        }
    }
}