package com.example.buste.staffinfo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //ProgressBar progressBar;
    Spinner spinner5,spinner2,spinner3,spinner4;
    Button button;
    int pos2,pos3,pos4,pos5;
    String values;
    String[] items4;
    String[] items5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //create a list of items for the spinner.
        spinner2 = findViewById(R.id.sp2);
        spinner3 = findViewById(R.id.sp3);
        spinner4= findViewById(R.id.sp4);
        spinner5 = findViewById(R.id.sp5);
        button=findViewById(R.id.button);
        final String[] items2 = new String[]{"Select State", "Karnataka", "Rajathan"};
        items4 = new String[]{"Select Railway Station", "Udaipur City",
                "KSR(Majestic)","Baiyappanahalli","Ranapratapnagar"};
        final String[] items3 = new String[]{"Select District", "Udaipur","Bangalore"};
        items5 = new String[]{"Department", "SeniorCitizen", "Women Safety"};
        //create an adapter to describe how the items are displayed
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, items2);

        //set the spinners adapter to the previously created one.
        spinner2.setAdapter(adapter2);
        //create an adapter to describe how the items are displayed
        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, items3);

        //set the spinners adapter to the previously created one.
        spinner3.setAdapter(adapter3);
        //create an adapter to describe how the items are displayed
        ArrayAdapter<String> adapter4 = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, items4);

        //set the spinners adapter to the previously created one.
        spinner4.setAdapter(adapter4);
        //create an adapter to describe how the items are displayed
        ArrayAdapter<String> adapter5 = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, items5);

        //set the spinners adapter to the previously created one.
        spinner5.setAdapter(adapter5);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                pos2 = i;
                values = items2[i];
                Log.i("#spinner", values + " selected");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

                Toast.makeText(MainActivity.this, "Please select a state", Toast.LENGTH_SHORT).show();
            }
        });
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                pos3 = i;
                values = items3[i];
                Log.i("#spinner", values + " selected");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

                Toast.makeText(MainActivity.this, "Please select a District", Toast.LENGTH_SHORT).show();
            }
        });
        spinner4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                pos4 = i;
                values = items4[i];
                Log.i("#spinner", values + " selected");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

                Toast.makeText(MainActivity.this, "Please select a railway station", Toast.LENGTH_SHORT).show();
            }
        });
        spinner5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                pos5 = i;
                values = items5[i];
                Log.i("#spinner", values + " selected");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

                Toast.makeText(MainActivity.this, "Please select a department", Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void onClickNext(View view)
    {
        Intent x=new Intent(getApplicationContext(),Main2Activity.class);
        x.putExtra("railway",items4[pos4]);
        x.putExtra("department",items5[pos5]);
        startActivity(x);
    }

}
