package com.akter.reminderappdemo;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText timeEditText,timeEditText2;
    Button reminderBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timeEditText    = findViewById(R.id.editText);
        timeEditText2   = findViewById(R.id.editText2);
        reminderBtn     = findViewById(R.id.button);


        reminderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int time = Integer.parseInt(timeEditText.getText().toString());
                int time2 = Integer.parseInt(timeEditText2.getText().toString());
                setReminder(time);
                setReminder(time2);
            }
        });


    }

    private void setReminder(int time) {
        Intent intent = new Intent(MainActivity.this,MyBroadCastReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this,time,intent,0);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP,System.currentTimeMillis()+(time*1000),pendingIntent);
        Toast.makeText(this, "reminder set at :   "+time, Toast.LENGTH_SHORT).show();


    }
}