package com.example.gopal.scoutingapp2018;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class TeamEntryActivity extends AppCompatActivity {
    Button startRecording;
    String[] data;
    EditText matchNum;
    EditText teamNum;
    Spinner event;
    ArrayAdapter<CharSequence> eventAdapter;
    Spinner alliance;
    ArrayAdapter<CharSequence> allianceAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_entry);

        data = new String[3];

        matchNum = (EditText)findViewById(R.id.match_number);
        teamNum = (EditText)findViewById(R.id.team_number);
        event = (Spinner)findViewById(R.id.event);
        eventAdapter = ArrayAdapter.createFromResource(this,R.array.event_ops, R.layout.spinner_layout);
        eventAdapter.setDropDownViewResource(R.layout.spinner_layout);
        event.setAdapter(eventAdapter);
        alliance = (Spinner)findViewById(R.id.alliance);
        allianceAdapter = ArrayAdapter.createFromResource(this,R.array.alliance_ops,R.layout.spinner_layout);
        allianceAdapter.setDropDownViewResource(R.layout.spinner_layout);
        alliance.setAdapter(allianceAdapter);
        startRecording = (Button)findViewById(R.id.start_record);
        startRecording.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data[0] = event.getSelectedItem().toString();
                data[1] = teamNum.getText().toString();
                data[2] = matchNum.getText().toString();

                Intent autoIntent = new Intent(getApplicationContext(), AutoEntryActivity.class);
                autoIntent.putExtra(EXTRA_MESSAGE, data);
                autoIntent.putExtra("Alliance",alliance.getSelectedItem().toString());
                startActivity(autoIntent);
            }
        });
    }


}
