package com.example.basework_test;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class ViewActivity extends AppCompatActivity {

    //golbal variable
    TextView tvResult;
//    Button btAdd;
//    Double counter = 0.0;

//    TextView timerText;
    Button stopStartButton;

    Timer timer;
    TimerTask timerTask;
    Double time = 0.0;

    boolean timerStarted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        //Setting Listener
        tvResult = findViewById(R.id.tv_result);
//        timerText = findViewById(R.id.timerText);
        stopStartButton =findViewById(R.id.bt_start);

        timer = new Timer();

        long time =CurrentTimeMillisClock.getInstance().now();
        String timeString = Long.toString(time);
        Log.i("test","class_timeStamp:"+timeString);

//        //原始版systme，內建的，只是效率稍微慢些，但是不用創CurrentTimeMillisClock類別
//        long totalMilliSeconds = System.currentTimeMillis();
//        //ms
//        String info_t1m = Long.toString(totalMilliSeconds);
//        Log.i("test","System:"+info_t1m);

    }

    public void OnClickADD(View view){
        tvResult.setText(Double.toString(++time));
    }

    public void OnClickZero(View view){
        time=0.0;
        tvResult.setText("0");
    }

    public void resetTapped(View view)
    {
        AlertDialog.Builder resetAlert = new AlertDialog.Builder(this);
        resetAlert.setTitle("Reset Timer");
        resetAlert.setMessage("Are you sure you want to reset the timer?");
        resetAlert.setPositiveButton("Reset", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                if(timerTask != null)
                {
                    timerTask.cancel();
                    setButtonUI("START", R.color.green);
                    time = 0.0;
                    timerStarted = false;
//                    timerText.setText(formatTime(0,0,0));
                    tvResult.setText("0.0");

                }
            }
        });

        resetAlert.setNeutralButton("Cancel", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                //do nothing
            }
        });

        resetAlert.show();

    }
    public void startStopTapped(View view)
    {
        if(timerStarted == false)
        {
            timerStarted = true;
            setButtonUI("STOP", R.color.red);

            startTimer();
        }
        else
        {
            timerStarted = false;
            setButtonUI("START", R.color.green);

            timerTask.cancel();
        }
    }

    private void startTimer()
    {
        timerTask = new TimerTask()
        {
            @Override
            public void run()
            {
                runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        time++;
//                        timerText.setText(getTimerText());
                        String timeString = Double.toString(time);
                        tvResult.setText(timeString);
                        Log.i("test","time:"+timeString);
                    }
                });
            }

        };
        timer.scheduleAtFixedRate(timerTask, 0 ,1000);
    }

    private void setButtonUI(String start, int color)
    {
        stopStartButton.setText(start);
        stopStartButton.setTextColor(ContextCompat.getColor(this, color));
    }

}