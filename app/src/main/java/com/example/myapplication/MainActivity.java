package com.example.myapplication;


import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {

    protected TextView EditTotalCount;
    protected Button EventAButton,EventBButton,EventCButton,SettingButton,CountTotalButton;
    protected int Total = 0;
    protected int CountA = 0;
    protected int CountB = 0;
    protected int CountC = 0;
    protected int MAXNUMBEROFCOUNTS;
    protected String InputEventsInOrder1 = "";
    protected String InputEventsInOrder2 = "";
    protected String name1,name2, name3;
    protected String CountMessage = "Total Count ";
    SharedPreferenceManager SharedPreferenceManagerObject;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitialiseEverything();

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



    private void InitialiseEverything() {
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
                if (!VerifyCounterSize(Total,SharedPreferenceManagerObject.getInt((getString(R.string.Max_No_Count_Key))))) {
                    CountA++;
                    Total = CountB + CountA + CountC;
                    InputEventsInOrder1 += (SharedPreferenceManagerObject.getName(getString(R.string.First_Event_Name_Key))+",");
                    InputEventsInOrder2 += "1,";
                }

                else {


                    Toast.makeText(getApplicationContext(),"The Limit has been achieved. Please Stop.",Toast.LENGTH_LONG).show();
                    EventAButton.setClickable(false);
                }

                SharedPreferenceManagerObject.SaveName(getString((R.string.ArrayList1)),InputEventsInOrder1);
                SharedPreferenceManagerObject.SaveName(getString((R.string.ArrayList2)),InputEventsInOrder2);
                SharedPreferenceManagerObject.SaveCount(getString(R.string.CounterA),CountA);
                SharedPreferenceManagerObject.SaveCount(getString(R.string.TotalCounter),Total);
                CountMessage = String.format("Total Count: %s", Total);
                EditTotalCount.setText(CountMessage);

            }
        };

        View.OnClickListener buttonIncrementEventCountB = new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (!VerifyCounterSize(Total,SharedPreferenceManagerObject.getInt((getString(R.string.Max_No_Count_Key))))) {
                    CountB++;
                    Total = CountB + CountA + CountC;
                    InputEventsInOrder1+=(SharedPreferenceManagerObject.getName(getString(R.string.Second_Event_Name_Key))+",");
                    InputEventsInOrder2+="2,";
                }

                else {

                    Toast.makeText(getApplicationContext(),"The Limit has been achieved. Please Stop.",Toast.LENGTH_LONG).show();
                    EventBButton.setClickable(false);

                }



                SharedPreferenceManagerObject.SaveName(getString((R.string.ArrayList1)),InputEventsInOrder1);
                SharedPreferenceManagerObject.SaveName(getString((R.string.ArrayList2)),InputEventsInOrder2);
                SharedPreferenceManagerObject.SaveCount(getString(R.string.CounterB),CountB);

                SharedPreferenceManagerObject.SaveCount(getString(R.string.TotalCounter),Total);
                CountMessage = String.format("Total Count: %s", Total);
                EditTotalCount.setText(CountMessage);

            }
        };

        View.OnClickListener buttonIncrementEventCountC = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!VerifyCounterSize(Total,SharedPreferenceManagerObject.getInt((getString(R.string.Max_No_Count_Key))))){
                CountC++;
                Total = CountB + CountA + CountC;
                InputEventsInOrder1+=(SharedPreferenceManagerObject.getName(getString(R.string.Third_Event_Name_Key))+",");
                InputEventsInOrder2+="3,";
                }

                else {

                    Toast.makeText(getApplicationContext(),"The Limit has been achieved. Please Stop.",Toast.LENGTH_LONG).show();
                    EventCButton.setClickable(false);

                }

                SharedPreferenceManagerObject.SaveName(getString((R.string.ArrayList1)),InputEventsInOrder1);
                SharedPreferenceManagerObject.SaveName(getString((R.string.ArrayList2)),InputEventsInOrder2);
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


                GoToDataActivity(CountA);

            }



        };

        SettingButton.setOnClickListener(SettingButtons);
        EventAButton.setOnClickListener(buttonIncrementEventCountA);
        EventBButton.setOnClickListener(buttonIncrementEventCountB);
        EventCButton.setOnClickListener(buttonIncrementEventCountC);
        CountTotalButton.setOnClickListener(ButtonDataActivity);



    }


    private void GoToSettingActivity() {

        Intent intent = new Intent(this, SettingsActivity.class);

        startActivity(intent);

        // this method will allow to go to settings, take hte value we need and come back from setting
    }



    private void GoToDataActivity(int a) {

        if (a==0){

            return;
        }
        else {
        Intent intent = new Intent(this, DataActivity.class);

        startActivity(intent);
        }
        // this method will allow to go to settings, take hte value we need and come back from setting
    }



    private boolean VerifyCounterSize(int Total, int Max){

        if (Total == Max) {

            return true ;
        }

        return false;
    }

}





















