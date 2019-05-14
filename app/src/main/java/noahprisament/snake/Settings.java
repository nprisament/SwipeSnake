package noahprisament.snake;

import android.view.View;

/**
 * Created by student on 7/28/2016.
 */
public class Settings {
    private final static int xtraLowDensity = 10;
    private final static int lowDensity = 20;
    private final static int mediumDensity = 30;
    private final static int highDensity = 40;
    public int pixelDensity;

    private final static int xtraLowFPS = 2;
    private final static int lowFPS = 4;
    private final static int mediumFPS = 10;
    private final static int highFPS = 15;
    private final static int xtraHighFPS = 20;
    public int FPS;

    private String targetColor ;
    private String wallColor;

    public Settings(){
        this(3, 3, "Red", "White");
    }

    public Settings(int density, int fps, String target, String wall){
        pixelDensity = density;
        FPS = fps;
        targetColor = target;
        wallColor = wall;
    }

    public void loadSettings(int type) {
        try {
            pixelDensity = GameLoop.tinydb.getInt("settingsDensity");
            FPS = GameLoop.tinydb.getInt("settingsFPS");
            targetColor = GameLoop.tinydb.getString("settingsTarget");
            wallColor = GameLoop.tinydb.getString("settingsWall");
        }catch(Exception e){
            if(type == 1){
                GameLoop.tinydb.putInt("settingsDensity", pixelDensity);
                GameLoop.tinydb.putInt("settingsFPS", FPS);
                GameLoop.tinydb.putString("settingsTarget", targetColor);
                GameLoop.tinydb.putString("settingsWall", wallColor);
            }else{
                EditSettingsActivity.tiny.putInt("settingsDensity", pixelDensity);
                EditSettingsActivity.tiny.putInt("settingsFPS", FPS);
                EditSettingsActivity.tiny.putString("settingsTarget", targetColor);
                EditSettingsActivity.tiny.putString("settingsWall", wallColor);
            }
        }
    }

    public int getDensity(){
        if(pixelDensity == 1){
            return xtraLowDensity;
        }else if(pixelDensity == 2){
            return lowDensity;
        }else if(pixelDensity == 3){
            return mediumDensity;
        }else if(pixelDensity == 4){
            return highDensity;
        }else{
            return mediumDensity;
        }
    }

    public int getDensityNum(){
        return pixelDensity;
    }

    public int getFPS(){
        if(FPS == 1){
            return xtraLowFPS;
        }else if(FPS == 2){
            return lowFPS;
        }else if(FPS == 3){
            return mediumFPS;
        }else if(FPS == 4){
            return highFPS;
        }else if(FPS == 5){
            return xtraHighFPS;
        }else{
            return mediumFPS;
        }
    }

    public int getFPSNum(){
        return FPS;
    }

    public String getTarget(){
        return targetColor;
    }

    public String getWall(){
        return wallColor;
    }
}
