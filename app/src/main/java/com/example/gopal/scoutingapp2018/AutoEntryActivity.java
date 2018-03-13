package com.example.gopal.scoutingapp2018;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.ViewFlipper;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

/**
 * Created by Gopal on 2/3/2018.
 */

public class AutoEntryActivity extends AppCompatActivity {
    String[] data;
    //ViewFlipper fieldImages;
    //int currentFieldLayout;
    Spinner startPos;
    ArrayAdapter<CharSequence> startPosAdapter;
    CheckBox crossedALine;
    Spinner destination;
    ArrayAdapter<CharSequence> destinationAdapter;
    CheckBox cubeOnPlate;
    Button toTeleop;
    String alliance;

    CheckBox checkLL;
    CheckBox checkLR;
    CheckBox checkRL;
    CheckBox checkRR;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_entry);

        data = new String[8];
        Intent intent = getIntent();
        String[] teamEntryData = intent.getStringArrayExtra(EXTRA_MESSAGE);
        for(int i = 0; i<3; i++){
            data[i] = teamEntryData[i];
        }
        alliance = intent.getStringExtra("Alliance");

        //former ViewFlipper code
        /*
        fieldImages = (ViewFlipper) findViewById(R.id.field_images);
        currentFieldLayout = 0;
        if(alliance.equals("Red")){
            fieldImages.findViewById(R.id.imageLL).setBackgroundResource(R.drawable.redll);
            fieldImages.findViewById(R.id.imageLR).setBackgroundResource(R.drawable.redlr);
            fieldImages.findViewById(R.id.imageRL).setBackgroundResource(R.drawable.redrl);
            fieldImages.findViewById(R.id.imageRR).setBackgroundResource(R.drawable.redrr);
        }else{
            fieldImages.findViewById(R.id.imageLL).setBackgroundResource(R.drawable.bluell);
            fieldImages.findViewById(R.id.imageLR).setBackgroundResource(R.drawable.bluelr);
            fieldImages.findViewById(R.id.imageRL).setBackgroundResource(R.drawable.bluerl);
            fieldImages.findViewById(R.id.imageRR).setBackgroundResource(R.drawable.bluerr);
        }
        fieldImages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fieldImages.showNext();
                if(currentFieldLayout == 3){
                    currentFieldLayout = 0;
                }
                else{
                    currentFieldLayout++;
                }
            }
        });
        */
        //new four image + four checkbox code
        checkLL = (CheckBox)findViewById(R.id.checkLL);
        checkLR = (CheckBox)findViewById(R.id.checkLR);
        checkRL = (CheckBox)findViewById(R.id.checkRL);
        checkRR = (CheckBox)findViewById(R.id.checkRR);
        if(alliance.equals("Red")){
            findViewById(R.id.imageLL).setBackgroundResource(R.drawable.redll);
            findViewById(R.id.imageLR).setBackgroundResource(R.drawable.redlr);
            findViewById(R.id.imageRL).setBackgroundResource(R.drawable.redrl);
            findViewById(R.id.imageRR).setBackgroundResource(R.drawable.redrr);
        }else{
            findViewById(R.id.imageLL).setBackgroundResource(R.drawable.bluell);
            findViewById(R.id.imageLR).setBackgroundResource(R.drawable.bluelr);
            findViewById(R.id.imageRL).setBackgroundResource(R.drawable.bluerl);
            findViewById(R.id.imageRR).setBackgroundResource(R.drawable.bluerr);
        }
        startPos = (Spinner)findViewById(R.id.start_pos);
        startPosAdapter = ArrayAdapter.createFromResource(this,R.array.start_pos_ops, R.layout.spinner_layout);
        startPosAdapter.setDropDownViewResource(R.layout.spinner_layout);
        startPos.setAdapter(startPosAdapter);

        crossedALine = (CheckBox)findViewById(R.id.crossedALine);

        destination = (Spinner)findViewById(R.id.destination);
        destinationAdapter = ArrayAdapter.createFromResource(this,R.array.destination_ops, R.layout.spinner_layout);
        destinationAdapter.setDropDownViewResource(R.layout.spinner_layout);
        destination.setAdapter(destinationAdapter);

        cubeOnPlate = (CheckBox)findViewById(R.id.cube_on_plate);

        toTeleop = (Button)findViewById(R.id.toTeleop);

        toTeleop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkLL.isChecked()){
                    data[3] = "LL";
                }else if(checkLR.isChecked()){
                    data[3] = "LR";
                }else if(checkRL.isChecked()){
                    data[3] = "RL";
                }else{
                    data[3] = "RR";
                }
                /*
                switch(currentFieldLayout){
                    case 0:
                        data[3] = "LL";
                        break;
                    case 1:
                        data[3] = "LR";
                        break;
                    case 2:
                        data[3] = "RL";
                        break;
                    case 3:
                        data[3] = "RR";
                        break;
                    default:
                        data[3] = "LL";
                        break;
                }
                */
                data[4] = startPos.getSelectedItem().toString();
                data[5] = String.valueOf(crossedALine.isChecked());
                data[6] = destination.getSelectedItem().toString();
                data[7] = String.valueOf(cubeOnPlate.isChecked());

                Intent teleopIntent = new Intent(getApplicationContext(), TeleopEntryActivity.class);
                teleopIntent.putExtra(EXTRA_MESSAGE, data);
                startActivity(teleopIntent);
            }
        });
    }
}
