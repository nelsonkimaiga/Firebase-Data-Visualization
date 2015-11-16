package com.example.kimaiga.datavisualization;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PeopleData extends AppCompatActivity {
    private List<CardDataHolder> data_set;// = new ArrayList<>();
    private RecyclerView rv;
    JDataObject object;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        Firebase.setAndroidContext(this);

        rv = (RecyclerView)findViewById(R.id.rv);
        LinearLayoutManager llm = new LinearLayoutManager(this);

        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);

        getData("https://hackfest.firebaseio.com/hackfest");
        initializeAdapter();
    }

    private void initializeAdapter(){
        RVAAdapter adapter = new RVAAdapter(data_set);
        rv.setAdapter(adapter);
    }

    public void getData(String url){
        data_set = new ArrayList<>();
        Firebase ref = new Firebase(url);
        ref.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {

                for (DataSnapshot data : snapshot.getChildren()) {
                    object = data.getValue(JDataObject.class);

                    data_set.add(new CardDataHolder(
                            object.getId(),
                            object.getCompany_name(),
                            object.getCountry(),
                            object.getCredit_card(),
                            object.getEmail(),
                            object.getFirst_name(),
                            object.getLast_name()
                    ));

                }

            }


            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });

    }

}
