package com.example.flo.myapplication;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class PanneBehebenAnleitung extends ActionBarActivity {

    private int currentStep;
    private int maxSteps;
    private TextView step;
    private TextView toDo;
    private ArrayList<String> pannenAnleitung;
    private ArrayList<String> pannnenAnleitungBilder;
    private ImageView helpImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panne_beheben_anleitung);

        Intent intent = getIntent();

        String name = intent.getStringExtra("NAME");
        Log.w("pannebeheben anleitung ", name);
        setTitle(name);

        String pannenAnleitungString = intent.getStringExtra("SCHRITTE");
        Log.w("pannebeheben anleitung ", pannenAnleitungString);

        String pannenAnleitungPicturesString = intent.getStringExtra("BILDER");
        Log.w("pannebeheben anleitung ", pannenAnleitungPicturesString);


        pannenAnleitung = createPannenStepsFromDBString(pannenAnleitungString);
        pannnenAnleitungBilder = createPannenStepsPicturesFromDBString(pannenAnleitungPicturesString);

        for (int i = 0; i < pannenAnleitung.size(); i++) {
            Log.e("%%%%%steps: " , pannenAnleitung.get(i));

        }

        for (int i = 0; i < pannnenAnleitungBilder.size(); i++) {
            Log.e("%%%%%bilder: " , pannnenAnleitungBilder.get(i));

            String idString = pannnenAnleitungBilder.get(i);
            int id = getResources().getIdentifier(idString, "drawable", getPackageName());
            Log.e("%%%%%bilderINT: " , String.valueOf(id));


        }

          maxSteps = pannenAnleitung.size();
          currentStep = 1;

        Button nextButton = (Button)findViewById(R.id.panne_beheben_nextStepButton);
        Button previousButton = (Button)findViewById(R.id.panne_beheben_previousStepButton);

         helpImage = (ImageView)findViewById(R.id.panneBeheben_anleitung_bild);
         step = (TextView)findViewById(R.id.panne_beheben_stepTextview);
         toDo = (TextView)findViewById(R.id.panneBeheben_anleitung_text);

        step.setText("Schritt: " + currentStep + " / " + maxSteps);
        toDo.setText(pannenAnleitung.get(0));

        String idString = pannnenAnleitungBilder.get(0);
        int id = getResources().getIdentifier(idString, "drawable", getPackageName());
        Drawable new_image= getResources().getDrawable(id);
        helpImage.setImageDrawable(new_image);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (currentStep < maxSteps) {

                    currentStep++;
                    step.setText("Schritt: " + currentStep + " / " + maxSteps);
                    step.setText("Schritt: " + currentStep + " / " + maxSteps);
                    toDo.setText(pannenAnleitung.get(currentStep-1));

                    String idString = pannnenAnleitungBilder.get(currentStep-1);
                    int id = getResources().getIdentifier(idString, "drawable", getPackageName());
                    Drawable new_image= getResources().getDrawable(id);
                    helpImage.setImageDrawable(new_image);

                }
            }
        });

        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(currentStep > 1){

                    currentStep--;
                    step.setText("Schritt: " + currentStep + " / " + maxSteps);
                    step.setText("Schritt: " + currentStep + " / " + maxSteps);
                    toDo.setText(pannenAnleitung.get(currentStep-1));

                    String idString = pannnenAnleitungBilder.get(currentStep-1);
                    int id = getResources().getIdentifier(idString, "drawable", getPackageName());
                    Drawable new_image= getResources().getDrawable(id);
                    helpImage.setImageDrawable(new_image);

                }
            }
        });

    }

    private ArrayList<String> createPannenStepsPicturesFromDBString(String stepsPicturesFromDB) {

        ArrayList<String> stepsToDoPictures = new ArrayList<String>();
        int firstWordPart = 0;
        int secondWordPart = 1;

        for (int index = 0; index < stepsPicturesFromDB.length(); index++) {

            if (index == (stepsPicturesFromDB.length() - 1) && stepsPicturesFromDB.charAt(index) != '|') {
                String bla = stepsPicturesFromDB.substring(firstWordPart, secondWordPart);
                if (bla.length() != 0) {
                    stepsToDoPictures.add(bla);
                }
            }

            if (stepsPicturesFromDB.charAt(index) != '|') {
                secondWordPart++;
            } else {
                String bla = stepsPicturesFromDB.substring(firstWordPart, secondWordPart - 1);
                if (bla.length() != 0) {
                    stepsToDoPictures.add(bla);
                }
                firstWordPart = secondWordPart;
                secondWordPart++;
            }
        }

        return stepsToDoPictures;
    }

    private ArrayList<String> createPannenStepsFromDBString(String stepsFromDB) {

        ArrayList<String> stepsToDo = new ArrayList<String>();
        int firstWordPart = 0;
        int secondWordPart = 1;

        for (int index = 0; index < stepsFromDB.length(); index++) {

            if (index == (stepsFromDB.length() - 1) && stepsFromDB.charAt(index) != '$') {
                String bla = stepsFromDB.substring(firstWordPart, secondWordPart);
                if (bla.length() != 0) {
                    stepsToDo.add(bla);
                }
            }

            if (stepsFromDB.charAt(index) != '$') {
                secondWordPart++;
            } else {
                String bla = stepsFromDB.substring(firstWordPart, secondWordPart - 1);
                if (bla.length() != 0) {
                    stepsToDo.add(bla);
                }
                firstWordPart = secondWordPart;
                secondWordPart++;
            }
        }

        return stepsToDo;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       // getMenuInflater().inflate(R.menu.menu_panne_beheben_anleitung, menu);
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
