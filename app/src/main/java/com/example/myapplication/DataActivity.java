package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Arrays;


public class DataActivity extends AppCompatActivity {

    TextView EventA,EventB,EventC,TotalCount;
    protected SharedPreferenceManager SharedPreferenceManagerObject;
    ListView VerticalList;
    ArrayList <String> ListOfData = new ArrayList<String>();
    ArrayList <String> ListOfData2 = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    ArrayAdapter<String> adapter2;
    ImageButton BackKey;
    ImageButton DottedMenu;
    String TempEvent;
    String TempCounter;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        SharedPreferenceManagerObject = new SharedPreferenceManager(getApplicationContext());


        InitialiseEverything();  // this will initialise everything


        EventA.setText(" Event A : " + SharedPreferenceManagerObject.getInt(getString(R.string.CounterA))+" events");
        EventB.setText(" Event B : " + SharedPreferenceManagerObject.getInt(getString(R.string.CounterB))+" events");
        EventC.setText(" Event C : " + SharedPreferenceManagerObject.getInt(getString(R.string.CounterC))+" events");
        TotalCount.setText("Total Count : " + SharedPreferenceManagerObject.getInt(getString(R.string.TotalCounter)));

        //These 4 lines will Set the Text For my Buttons,


        TempEvent= SharedPreferenceManagerObject.getName(getString(R.string.ArrayList1));
        TempCounter=SharedPreferenceManagerObject.getName(getString(R.string.ArrayList2));
        ListOfData= unpackingList(TempEvent);
        ListOfData2=unpackingList(TempCounter);



        // These 4 lines will fetch the string that represent my ARRAY in order to Put them in the LIstView and will unpack my string and transform it into an array;

       adapter = new ArrayAdapter(DataActivity.this, android.R.layout.simple_list_item_1,ListOfData);
       adapter2 = new ArrayAdapter(DataActivity.this, android.R.layout.simple_list_item_1,ListOfData2);

       // creation of adapters


        VerticalList.setAdapter(adapter);   // Show my Vertical list using adapters


        BackKey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    // What does by Back button do,

        DottedMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu= new PopupMenu(DataActivity.this,view);
                popupMenu.getMenuInflater().inflate(R.menu.popupmenu2,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()){

                            case R.id.ToggleEventModeButton:

                                EventA.setText(" Counter 1 : " + SharedPreferenceManagerObject.getInt(getString(R.string.CounterA))+" events");
                                EventB.setText(" Counter 2 : " + SharedPreferenceManagerObject.getInt(getString(R.string.CounterB))+" events");
                                EventC.setText(" Counter 3 : " + SharedPreferenceManagerObject.getInt(getString(R.string.CounterC))+" events");
                                VerticalList.setAdapter(adapter2);
                                return true;
                            default:
                                return true;
                        }
                    };
                });
                popupMenu.show();

            }
        }); // What does the dotted menu do,

    }

    //  Function Created to unpack the string that was passed through shared preferences and transform it into a list


    private ArrayList<String> unpackingList(String a){
        return  new ArrayList<String>(Arrays.asList(a.split(",")));

    };

    // Initialise Everything using
    private void InitialiseEverything(){
        VerticalList=findViewById(R.id.ListViewOfData);
        EventA=findViewById(R.id.EventAViewer);
        EventB=findViewById(R.id.EventBViewer);
        EventC=findViewById(R.id.Event3Viewer);
        TotalCount=findViewById(R.id.TotalEventCounter);
        BackKey=findViewById(R.id.BackButton);
        DottedMenu=findViewById(R.id.SettingButtonLayout);};
}