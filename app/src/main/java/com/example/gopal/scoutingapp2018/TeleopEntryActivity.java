package com.example.gopal.scoutingapp2018;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

/**
 * Created by Gopal on 2/6/2018.
 */

public class TeleopEntryActivity extends AppCompatActivity{
    CheckBox cubeOnSwitch;
    CheckBox cubeOnScale;
    Button cubesToVaultDecrement;
    Button cubesToVaultIncrement;
    TextView cubesTOVaultDisplay;
    int cubesToVault;
    CheckBox onPlatform;
    CheckBox climbed;
    Button save;
    String fileName;
    String path;
    String[] data;
    String[] labels;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teleop_entry);

        data = new String[13];
        Intent intent = getIntent();
        String[] autoEntryData = intent.getStringArrayExtra(EXTRA_MESSAGE);
        for(int i = 0; i<8; i++){
            data[i] = autoEntryData[i];
        }

        //field layout contains two letters, the first representing this team's side on the switch, and the second representing its side on the scale
        labels = new String[]{"Event", "Team Number", "Match Number", "Field Layout", "Start Position", "Crossed Auto Line", "Destination", "Cube on Plate", "Cube on Switch", "Cube on Scale", "Cubes to Vault", "On Platform", "Climbed"};

        cubeOnSwitch = (CheckBox)findViewById(R.id.cube_on_switch);
        cubeOnScale = (CheckBox)findViewById(R.id.cube_on_scale);
        cubesToVaultDecrement = (Button)findViewById(R.id.cubes_to_vault_decrement);
        cubesToVaultIncrement = (Button)findViewById(R.id.cubes_to_vault_increment);
        cubesTOVaultDisplay = (TextView)findViewById(R.id.cubes_to_vault_display);
        save = (Button)findViewById(R.id.save);
        cubesToVault = 0;
        cubesTOVaultDisplay.setText("   " + String.valueOf(cubesToVault) + "   ");
        onPlatform =(CheckBox)findViewById(R.id.on_platform);
        climbed = (CheckBox)findViewById(R.id.climbed);

        cubesToVaultDecrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cubesToVault > 0) {
                    cubesToVault--;
                    cubesTOVaultDisplay.setText("   " + String.valueOf(cubesToVault) + "   ");
                }
            }
        });
        cubesToVaultIncrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cubesToVault < 9) {
                    cubesToVault++;
                    cubesTOVaultDisplay.setText("   " + String.valueOf(cubesToVault) + "   ");
                }
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data[8] = String.valueOf(cubeOnSwitch.isChecked());
                data[9] = String.valueOf(cubeOnScale.isChecked());
                data[10] = String.valueOf(cubesToVault);
                data[11] = String.valueOf(onPlatform.isChecked());
                data[12] = String.valueOf(climbed.isChecked());
                saveData(data, labels);
            }
        });
    }
    private void saveData(String[] saveData, String[] saveLabels){
        path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Documents/TeamData2018/";//temporarily without subfolder
        fileName = data[0] + "_" + data[1] + "_" + data[2] + ".json";
        try{
            new File(path).mkdir();
            path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Documents/TeamData2018/" + data[1] + '/';
            new File(path).mkdir();
            File file = new File(path + fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            else{
                return;
            }
            JSONObject obj = new JSONObject();
            for(int i = 0; i < saveData.length; i++){
                obj.put(saveLabels[i],saveData[i]);
            }

            FileWriter fileWriter = new FileWriter(file,true);
            fileWriter.write(obj.toString());
            fileWriter.flush();

            Toast.makeText(getApplicationContext(),"Data saved", Toast.LENGTH_LONG).show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
