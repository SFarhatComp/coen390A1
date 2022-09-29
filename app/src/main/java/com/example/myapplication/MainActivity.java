package com.example.myapplication;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    protected TextView EditTotalCount;
    protected Button EventAButton,EventBButton,EventCButton,SettingButton,CountTotalButton;
    protected int Total = 0;
    protected int CountA = 0;
    protected int CountB = 0;
    protected int CountC = 0;
    protected int MAXNUMBEROFCOUNTS;
    protected ArrayList<String> inputs= new ArrayList<String>();
    protected String name1,name2, name3;
    protected String CountMessage = "Total Count ";
    SharedPreferenceManager SharedPreferenceManagerObject;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupUI();

    }


    @Override
    protected void onResume() {
        super.onResume();
        CountA=SharedPreferenceManagerObject.getInt(getString(R.string.CounterA));
        CountB=SharedPreferenceManagerObject.getInt(getString(R.string.CounterB));
        CountC=SharedPreferenceManagerObject.getInt(getString(R.string.CounterC));
        Total=SharedPreferenceManagerObject.getInt(getString(R.string.TotalCounter));
        EventAButton.setClickable(true);
        EventBButton.setClickable(true);
        EventCButton.setClickable(true);
        name1=SharedPreferenceManagerObject.getName(getString(R.string.First_Event_Name_Key));
        name2=SharedPreferenceManagerObject.getName(getString(R.string.Second_Event_Name_Key));
        name3=SharedPreferenceManagerObject.getName(getString(R.string.Third_Event_Name_Key));
        MAXNUMBEROFCOUNTS=SharedPreferenceManagerObject.getInt(getString(R.string.Max_No_Count_Key));
        EditTotalCount.setText(CountMessage);

        if (name1==null){
            GoToSettingActivity();

            // i am only verifying for the first value, because logically at create time, if name1 is empty, all the other values would be null as well
        }

        else {
            EventAButton.setText(name1);
            EventBButton.setText(name2);
            EventCButton.setText(name3);

        }

    }



    private void setupUI() {
        EditTotalCount = findViewById(R.id.TotalCountText);
        EventAButton = findViewById(R.id.ButtonEventA);
        EventBButton = findViewById(R.id.ButtonEventB);
        EventCButton = findViewById(R.id.ButtonEventC);
        SettingButton = findViewById(R.id.SettingButton);
        CountTotalButton = findViewById(R.id.TotalCountButton);
        EditTotalCount.setText("Total count: ");
        SharedPreferenceManagerObject = new SharedPreferenceManager(getApplicationContext());

        View.OnClickListener buttonIncrementEventCountA = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CountA++;
                inputs.add("Event A");
                Total = CountB + CountA + CountC;

                if(VerifyCounterSize(Total,SharedPreferenceManagerObject.getInt(getString(R.string.Max_No_Count_Key)))){
                    Toast.makeText(getApplicationContext(),"The Limit has been achieved. Please Stop.",Toast.LENGTH_LONG).show();
                    EventAButton.setClickable(false);
                    Total--;
                    CountA--;
                }
                SharedPreferenceManagerObject.SaveCount(getString(R.string.CounterA),CountA);
                SharedPreferenceManagerObject.SaveCount(getString(R.string.TotalCounter),Total);
                CountMessage = String.format("Total Count: %s", Total);
                EditTotalCount.setText(CountMessage);

            }
        };

        View.OnClickListener buttonIncrementEventCountB = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CountB++;
                inputs.add("Event B");
                Total = CountB + CountA + CountC;


                if(VerifyCounterSize(Total,SharedPreferenceManagerObject.getInt(getString(R.string.Max_No_Count_Key)))){


                    Toast.makeText(getApplicationContext(),"The Limit has been achieved. Please Stop.",Toast.LENGTH_LONG).show();
                    EventBButton.setClickable(false);
                    Total--;
                    CountB--;
                }


                SharedPreferenceManagerObject.SaveCount(getString(R.string.CounterB),CountB);

                SharedPreferenceManagerObject.SaveCount(getString(R.string.TotalCounter),Total);
                CountMessage = String.format("Total Count: %s", Total);
                EditTotalCount.setText(CountMessage);

            }
        };

        View.OnClickListener buttonIncrementEventCountC = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CountC++;
                inputs.add("Event C");
                Total = CountB + CountA + CountC;
                if(VerifyCounterSize(Total,SharedPreferenceManagerObject.getInt(getString(R.string.Max_No_Count_Key)))){
                    Toast.makeText(getApplicationContext(),"The Limit has been achieved. Please Stop.",Toast.LENGTH_LONG).show();
                    EventCButton.setClickable(false);
                    Total--;
                    CountC--;
                }
                SharedPreferenceManagerObject.SaveCount(getString(R.string.CounterC),CountC);
                SharedPreferenceManagerObject.SaveCount(getString(R.string.TotalCounter),Total);
                CountMessage = String.format("Total Count: %s", Total);
                EditTotalCount.setText(CountMessage);

            }
        };










        View.OnClickListener SettingButtons = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                GoToSettingActivity();

            }
        };


        View.OnClickListener ButtonDataActivity = new View.OnClickListener(){
            @Override
            public void onClick(View view){


                GoToDataActivity();

            }



        };

        SettingButton.setOnClickListener(SettingButtons);
        EventAButton.setOnClickListener(buttonIncrementEventCountA);
        EventBButton.setOnClickListener(buttonIncrementEventCountB);
        EventCButton.setOnClickListener(buttonIncrementEventCountC);
        CountTotalButton.setOnClickListener(ButtonDataActivity);



    }

    ;
    private void GoToSettingActivity() {

        Intent intent = new Intent(this, SettingsActivity.class);

        startActivity(intent);

        // this method will allow to go to settings, take hte value we need and come back from setting
    }

    ;

    private void GoToDataActivity() {

        Intent intent = new Intent(this, DataActivity.class);

        startActivity(intent);

        // this method will allow to go to settings, take hte value we need and come back from setting
    }

    ;

    private boolean VerifyCounterSize(int Total, int Max){

        if (Total > Max) {

            return true ;
        }

        return false;
    }

}





















