package noahprisament.snake;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Transition;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends AppCompatActivity {
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;
    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_MAX_OFF_PATH = 250;
    GameView game;

    OnSwipeListener listener = new OnSwipeListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        game = new GameView(this);
        setContentView(game);
        game.thread.running = true;
        game.thread.pause = false;
        new GameStateCheck().execute();
        new GamePauseCheck().execute();
    }

    @Override
    protected void onStop() {
        super.onStop();
        game.thread.running = false;
        game.thread.pause = false;
        game = null;
    }

    @Override
    protected void onStart() {
        super.onStart();  // Always call the superclass method first
        game = new GameView(this);
        setContentView(game);
        game.thread.running = true;
        game.thread.pause = false;
        new GameStateCheck().execute();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        return super.onTouchEvent(event);
    }

    public void gameOver(){
        Intent i = new Intent(GameActivity.this, GameOver.class);
        startActivity(i);
    }

    public void pause(){
        startActivity(new Intent(GameActivity.this, Pause.class));
    }

    private class GameStateCheck extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            boolean checkRun = true;
            while(checkRun) {
                if (!game.thread.running) {
                    checkRun = false;
                    gameOver();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {}

        @Override
        protected void onPreExecute() {}

        @Override
        protected void onProgressUpdate(Void... values) {}
    }

    private class GamePauseCheck extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            boolean checkRun = true;
            while(checkRun) {
                if (game.thread.pause) {
                    checkRun = false;
                    pause();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {}

        @Override
        protected void onPreExecute() {}

        @Override
        protected void onProgressUpdate(Void... values) {}
    }
}
