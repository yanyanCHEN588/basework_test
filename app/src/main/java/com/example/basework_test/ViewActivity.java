package com.example.basework_test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ViewActivity extends AppCompatActivity {

    //golbal variable
    TextView tvResult;
//    Button btAdd;
    int counter = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        //Setting Listener
        tvResult = findViewById(R.id.tv_result);

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
        tvResult.setText(String.valueOf(++counter));
    }

    public void OnClickZero(View view){
        counter=0;
        tvResult.setText("0");
    }

}