package com.example.flo.myapplication;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class PanneBehebenFragmentBauteilTab extends Fragment {


    private PanneDBHelper db;
    private PanneAdapter adapter;
    private ArrayList<Panne> pannen;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_layout_bauteil, container, false);
       // TextView tv = (TextView) v.findViewById(R.id.text);
       // tv.setText("Bauteil");
        final ListView bauteilListView = (ListView)v.findViewById(R.id.panne_beheben_fragment_bauteil_listview);

        db = new PanneDBHelper(getActivity());

        // falls zu viele testelemente in der db sind
        //   db.deleteAllContacts();

        pannen = db.getAllPannen();

        String[] bauteilArray = new String[pannen.size()];

        int index = 0;

        for (Panne panne : pannen) {

            bauteilArray[index] = panne.getBauteil();
            index++;
        }

        ArrayAdapter<String> bauteilAdapter = new ArrayAdapter<String>(this.getActivity(), R.layout.custom_listitem, bauteilArray);

        bauteilListView.setAdapter(bauteilAdapter);

        bauteilListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getActivity() , PanneBehebenAnleitung.class);
                int clickedItemIndex = (int) bauteilListView.getAdapter().getItemId(position);

                String panneName = pannen.get(position).getName();
                String panneSchritte = pannen.get(position).getSchritte();
                String panneSchritteBilder = pannen.get(position).getBilder();

                int symptomId = pannen.get(position).getId();

                i.putExtra("NAME", panneName);
                i.putExtra("SCHRITTE", panneSchritte);
                i.putExtra("BILDER", panneSchritteBilder);


                startActivity(i);
            }
        });
        return v;
    }
}
