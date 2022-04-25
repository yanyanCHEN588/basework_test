package com.example.basework_test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
        tvResult =(TextView)findViewById(R.id.tv_result);

    }

    public void OnClickADD(View view){
        tvResult.setText(String.valueOf(++counter));
    }

    public void OnClickZero(View view){
        counter=0;
        tvResult.setText("0");
    }

}