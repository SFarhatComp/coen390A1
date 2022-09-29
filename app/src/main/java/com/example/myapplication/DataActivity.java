package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;

public class DataActivity extends AppCompatActivity {



    TextView EventA,EventB,EventC,TotalCount;
    protected SharedPreferenceManager SharedPreferenceManagerObject;
   /* ListView VerticalList;
    List ListOfData = new ArrayList();
    ArrayAdapter <String>adapter;*/
    ImageButton BackKey;
    ImageButton DottedMenu;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
       /* VerticalList=findViewById(R.id.ListViewOfData);


        adapter = new ArrayAdapter(DataActivity.this, android.R.layout.simple_list_item_1,ListOfData);
        VerticalList.setAdapter(adapter);
*/


        SharedPreferenceManagerObject = new SharedPreferenceManager(getApplicationContext());

        EventA=findViewById(R.id.EventAViewer);
        EventB=findViewById(R.id.EventBViewer);
        EventC=findViewById(R.id.Event3Viewer);
        TotalCount=findViewById(R.id.TotalEventCounter);
        BackKey=findViewById(R.id.BackButton);
        DottedMenu=findViewById(R.id.SettingButtonLayout);






        EventA.setText(" Event A : " + SharedPreferenceManagerObject.getInt(getString(R.string.CounterA))+" events");
        EventB.setText(" Event B : " + SharedPreferenceManagerObject.getInt(getString(R.string.CounterB))+" events");
        EventC.setText(" Event C : " + SharedPreferenceManagerObject.getInt(getString(R.string.CounterC))+" events");
        TotalCount.setText("Total Count : " + SharedPreferenceManagerObject.getInt(getString(R.string.TotalCounter)));




        BackKey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


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

                                return true;




                            default:
                                return true;
                        }
                    };
                });



                popupMenu.show();

            }
        });

    }

}