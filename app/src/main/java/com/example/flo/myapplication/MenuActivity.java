package com.example.flo.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MenuActivity extends ActionBarActivity {

    private double latitude = 0;
    private double longitude = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            latitude = extras.getDouble("latitude");
            longitude = extras.getDouble("longitude");
        }
        setContentView(R.layout.activity_menu);
        setupUI();
    }

    private void setupUI() {
        Button gefahrenstelle_absichern_button = (Button) findViewById(R.id.gefahrenstelle_absichern_button);
        Button panne_beheben_button = (Button) findViewById(R.id.panne_beheben_button);
        Button werkstatt_finden_button = (Button) findViewById(R.id.werkstatt_finden_button);
        Button wichtige_nummern_button = (Button) findViewById(R.id.wichtige_nummern_button);
        Button flashlight_button = (Button) findViewById(R.id.taschenlampe_button);
        Button panne_dokumentieren_button = (Button) findViewById(R.id.panne_dokumentieren_button);

        TextView gefahrenstelle_absichern_tv = (TextView) findViewById(R.id.menu_gefahrenstelle_absichern_titel);
        TextView panne_beheben_tv = (TextView) findViewById(R.id.menu_panne_beheben_titel);
        TextView werkstatt_finden_tv = (TextView) findViewById(R.id.menu_werkstatt_finden_titel);
        TextView wichtige_nummern_tv = (TextView) findViewById(R.id.menu_wichtige_nummern_titel);
        TextView flashlight_tv = (TextView) findViewById(R.id.menu_taschenlampe_titel);
        TextView panne_dokumentieren_tv = (TextView) findViewById(R.id.menu_panne_dokumentieren_titel);


        // Set onClicklisteners on Buttons
        gefahrenstelle_absichern_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, GefahrenstelleAbsichern.class);
                startActivity(intent);
            }
        });

        panne_beheben_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, PanneBeheben.class);
                startActivity(intent);
            }
        });

        werkstatt_finden_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, WerkstattFindenActivity.class);
                intent.putExtra("latitude", latitude);
                intent.putExtra("longitude", longitude);
                startActivity(intent);
            }
        });

        wichtige_nummern_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, WichtigeNummern.class);
                startActivity(intent);
            }
        });

        flashlight_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, Taschenlampe.class);
                startActivity(intent);
            }
        });

        panne_dokumentieren_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, PanneDokumentieren.class);
                startActivity(intent);
            }
        });

        // Set onClicklisteners on textviews
        gefahrenstelle_absichern_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, GefahrenstelleAbsichern.class);
                startActivity(intent);
            }
        });

        panne_beheben_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, PanneBeheben.class);
                startActivity(intent);
            }
        });

        werkstatt_finden_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, WerkstattFindenActivity.class);
                intent.putExtra("latitude", latitude);
                intent.putExtra("longitude", longitude);
                startActivity(intent);
            }
        });

        wichtige_nummern_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, WichtigeNummern.class);
                startActivity(intent);
            }
        });

        flashlight_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, Taschenlampe.class);
                startActivity(intent);
            }
        });

        panne_dokumentieren_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, PanneDokumentieren.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //  getMenuInflater().inflate(R.menu.menu_main, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
