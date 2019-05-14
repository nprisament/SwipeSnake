package noahprisament.snake;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Transition;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONObject;
import org.json.*;

import static android.support.v4.app.ActivityCompat.startActivityForResult;

/**
 * Created by student on 7/28/2016.
 */
public class EditSettingsActivity extends AppCompatActivity {
    SharedPreferences mPrefs;
    public static TinyDB tiny;
    private String targetColor ;
    private String wallColor;
    public int pixelDensity;
    public int FPS;
    Settings setting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        View v = this.findViewById(android.R.id.content);
        tiny = new TinyDB(v.getContext());
        this.setting = new Settings();
        this.setting.loadSettings(2);
        FPS = 0;
        pixelDensity = 0;

        Button button1 = (Button)findViewById(R.id.backFromSettingsButton);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), HomeActivity.class);
                Transition transition;
                startActivityForResult(intent, 0);
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            }
        });

        Button r1xs = (Button)findViewById(R.id.radioButton1);
        r1xs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pixelDensity = 1;
            }
        });

        Button r1s = (Button)findViewById(R.id.radioButton2);
        r1s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pixelDensity = 2;
            }
        });

        Button r1m = (Button)findViewById(R.id.radioButton3);
        r1m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pixelDensity = 3;
            }
        });

        Button r1l = (Button)findViewById(R.id.radioButton4);
        r1l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pixelDensity = 4;
            }
        });

        Button r2xs = (Button)findViewById(R.id.radioButton5);
        r2xs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FPS = 1;
            }
        });

        Button r2s = (Button)findViewById(R.id.radioButton6);
        r2s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FPS = 2;
            }
        });

        Button r2m = (Button)findViewById(R.id.radioButton7);
        r2m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FPS = 3;
            }
        });

        Button r2h = (Button)findViewById(R.id.radioButton8);
        r2h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FPS = 4;
            }
        });

        Button r2xh = (Button)findViewById(R.id.radioButton9);
        r2xh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FPS = 5;
            }
        });

        Button button2 = (Button)findViewById(R.id.saveButton);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText targetField = (EditText)findViewById(R.id.editText2);
                EditText wallField = (EditText)findViewById(R.id.editText);
                if(pixelDensity != 0){
                    tiny.putInt("settingsDensity", pixelDensity);
                }else{
                    tiny.putInt("settingsDensity", setting.getDensityNum());
                }
                if(FPS != 0){
                    tiny.putInt("settingsFPS", FPS);
                }else{
                    tiny.putInt("settingsFPS", setting.getFPSNum());
                }
                if(!targetField.equals(null)){
                    tiny.putString("settingsTarget", targetField.getText().toString());
                }else{
                    tiny.putString("settingsTarget", setting.getTarget());
                }
                if(!wallField.equals(null)){
                    tiny.putString("settingsWall", wallField.getText().toString());
                }else{
                    tiny.putString("settingsWall", setting.getWall());
                }
            }
        });
    }
}
