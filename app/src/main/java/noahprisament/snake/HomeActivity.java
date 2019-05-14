package noahprisament.snake;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Transition;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import static android.support.v4.app.ActivityCompat.startActivityForResult;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button button = (Button)findViewById(R.id.gameButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), GameActivity.class);
                Transition transition;
                startActivityForResult(intent, 0);
                overridePendingTransition(R.transition.slide_in_right, R.transition.slide_out_left);
            }
        });

        Button button2 = (Button)findViewById(R.id.loadButton);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), LoadActivity.class);
                Transition transition;
                startActivityForResult(intent, 0);
                overridePendingTransition(R.transition.slide_in_right, R.transition.slide_out_left);
            }
        });

        Button button3 = (Button)findViewById(R.id.settingsButton);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), EditSettingsActivity.class);
                Transition transition;
                startActivityForResult(intent, 0);
                overridePendingTransition(R.transition.slide_in_right, R.transition.slide_out_left);
            }
        });

        Button button4 = (Button)findViewById(R.id.helpButton);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), HelpActivity.class);
                Transition transition;
                startActivityForResult(intent, 0);
                overridePendingTransition(R.transition.slide_in_right, R.transition.slide_out_left);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            View v = this.findViewById(android.R.id.content);
            Intent intent = new Intent(v.getContext(), EditSettingsActivity.class);
            Transition transition;
            startActivityForResult(intent, 0);
            overridePendingTransition(R.transition.slide_in_right, R.transition.slide_out_left);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
