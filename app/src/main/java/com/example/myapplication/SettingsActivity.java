//SAMI FARHAT 40097236 ASSIGNMENT 1 COEN 390

package com.example.myapplication;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {

    protected EditText NameEventA, NameEventB, NameEventC, MaxCount;
    protected Button SaveButton;
    protected int MaxNumberCount;
    protected String NameA;

    protected String NameB, NameC,StringMaxNumberCount;

    protected SharedPreferenceManager SharedPreferenceManagerObject;
    protected ImageButton DottedMenu;
    protected ImageButton BackKey;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        SharedPreferenceManagerObject = new SharedPreferenceManager(getApplicationContext());
        InitialiseEverything();


        // Function for ON click of BackKey
        BackKey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });


        // Function for On Click of DottedMenu

        DottedMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu DottedMenu= new PopupMenu(SettingsActivity.this,view);
                DottedMenu.getMenuInflater().inflate(R.menu.settingpop,DottedMenu.getMenu());
                DottedMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()){

                            case R.id.EditModeButton:
                                NameEventA.setEnabled(true);
                                NameEventB.setEnabled(true);
                                NameEventC.setEnabled(true);
                                MaxCount.setEnabled(true);
                                SaveButton.setVisibility(View.VISIBLE);
                                return true;

                            default:
                                return true;
                        }
                    }
                });
                DottedMenu.show();

            }
        });


        // On click behaviour of the Save Button
        SaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NameA = NameEventA.getText().toString();
                NameB = NameEventB.getText().toString();
                NameC = NameEventC.getText().toString();
                StringMaxNumberCount = MaxCount.getText().toString();


                if(!StringMaxNumberCount.isEmpty()) {

                    MaxNumberCount= Integer.parseInt(StringMaxNumberCount) ;

                }

                else {
                    CallError();
                    return;
                    }

                if (NameA.equals("") || NameB.equals("") || NameC.equals("") || 5>MaxNumberCount||MaxNumberCount>200) {
                    CallError();
                    return;
                }

                SavingVariablesResetingVraiables();
                finish();

            }
        });

    }



    private void CallError(){Toast.makeText(getApplicationContext(),"The input are invalid",Toast.LENGTH_LONG).show();} // Error Call for invalid inputs


 // Initialiser
    protected void InitialiseEverything() {

        NameEventA = findViewById(R.id.EventACustomName);
        NameEventB = findViewById(R.id.EventBCustomName);
        NameEventC = findViewById(R.id.EventCCustomName);
        MaxCount = findViewById(R.id.MaxNumberOfCounts);
        SaveButton = findViewById(R.id.SaveButton);
        DottedMenu =findViewById(R.id.SettingButtonLayout);
        BackKey = findViewById(R.id.BackButton);


        NameEventA.setEnabled(false);
        NameEventB.setEnabled(false);
        NameEventC.setEnabled(false);
        MaxCount.setEnabled(false);
        SaveButton.setVisibility(View.INVISIBLE);

    }



    // Savingg Variables to the Shared Preferences
    private void SavingVariablesResetingVraiables(){
        SharedPreferenceManagerObject.SaveName(getString(R.string.First_Event_Name_Key), NameA);
        SharedPreferenceManagerObject.SaveName(getString(R.string.Second_Event_Name_Key), NameB);
        SharedPreferenceManagerObject.SaveName(getString(R.string.Third_Event_Name_Key), NameC);
        SharedPreferenceManagerObject.SaveCount(getString(R.string.Max_No_Count_Key),MaxNumberCount);
        SharedPreferenceManagerObject.SaveCount(getString(R.string.CounterA),0);
        SharedPreferenceManagerObject.SaveCount(getString(R.string.CounterB),0);
        SharedPreferenceManagerObject.SaveCount(getString(R.string.CounterC),0);
        SharedPreferenceManagerObject.SaveCount(getString(R.string.TotalCounter),0);
        SharedPreferenceManagerObject.SaveName(getString(R.string.ArrayList1),"");
        SharedPreferenceManagerObject.SaveName(getString(R.string.ArrayList2),"");


    }

}




