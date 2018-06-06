package com.example.buste.staffinfo;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Locale;

public class Main2Activity extends AppCompatActivity {
String department,railway;
    FirebaseDatabase database;
    DatabaseReference reference;
    ListView listView;
    static ArrayList<String> notes = new ArrayList<>();         //stores all the notes
    static ArrayAdapter arrayAdapter;

    double lat, lng;

    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        context = Main2Activity.this;

        Intent i=getIntent();
        department=i.getStringExtra("department");
        railway=i.getStringExtra("railway");
        Toast.makeText(Main2Activity.this, department, Toast.LENGTH_SHORT).show();
        Log.d("railway",railway);

        listView = findViewById(R.id.listView);
        notes.clear();
        //initialise your adapter
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, notes);


        database = FirebaseDatabase.getInstance();

        reference = database.getReference(railway).child("Department")
                .child(department);

        Query pendingTasks = reference.orderByChild("val").equalTo("0");
        pendingTasks.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot tasksSnapshot) {
                for (DataSnapshot snapshot: tasksSnapshot.getChildren()) {

                    Log.d("#qwerty2", snapshot.child("Description").getValue().toString());
                    //snapshot.getRef().child("Status").setValue("COMPLETED");

                    lat = Double.parseDouble(snapshot.child("Latitude").getValue().toString());
                    lng = Double.parseDouble(snapshot.child("Longitude").getValue().toString());

                    Log.d("#qwerty3", snapshot.child("Latitude").getValue().toString());
                    //arrayAdapter.clear();
                    notes.add(snapshot.child("Problem Type").getValue().toString() + "("+ snapshot.child("Description").getValue().toString() + ")");
                    listView.setAdapter(arrayAdapter);
                }
            }
            @Override
            public void onCancelled(DatabaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });

        //set listview clicklistener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                String uri = String.format(Locale.ENGLISH, "geo:0,0?q=%f,%f(%s)?z=23", lat, lng, "Train");
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                context.startActivity(intent);

            }
        });
    }
}
