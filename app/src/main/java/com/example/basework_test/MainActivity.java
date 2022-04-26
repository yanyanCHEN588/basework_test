package com.example.basework_test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //宣告有哪些物件
    TextView tvS1,tvS2,tvD1,tvD2,tvD3,tvResult;
    Spinner spinnerS1,spinnerS2;
    Button btStart,btPause,btDEview,bt_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //選擇結果init>顯示
        tvS1 = (TextView)findViewById(R.id.tvS1);
        tvS2 = (TextView)findViewById(R.id.tvS2);
        //偵測結果init>顯示
        tvD1 = (TextView)findViewById(R.id.tvD1);
        tvD2 = (TextView)findViewById(R.id.tvD2);
        tvD3 = (TextView)findViewById(R.id.tvD3);
        //計算結果顯示
        tvResult = (TextView)findViewById(R.id.tvResult);
        //選單init
        spinnerS1 = (Spinner)findViewById(R.id.spinnerS1);
        spinnerS2 = (Spinner)findViewById(R.id.spinnerS2);
        //按鈕init
        btStart = (Button)findViewById(R.id.btStart);
        btPause = (Button)findViewById(R.id.btPause);

        //按下開始按鈕後的事件
        btStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSpinnerShow(3);
            }
        });

        //spinner1 監聽事件
        spinnerS1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            //如果馬上選擇選單我要做什麼
                getSpinnerShow(1);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            //沒有被選擇我什麼也不做:))
            }
        });


    }


    //這裡在onCreate外
    protected void getSpinnerShow(int satus){
        // 從Value/string 取得字串資源
        String[] Select1=getResources().getStringArray(R.array.Select1);
        String[] Select2=getResources().getStringArray(R.array.Select2);
        int index1 = spinnerS1.getSelectedItemPosition(); //被選取項目的位置
        int index2 = spinnerS2.getSelectedItemPosition(); //被選取項目的位置
        switch (satus){

            case 1:
                tvS1.setText("First:"+Select1[index1]);           //在textView顯示選擇結果
            break;
            case 2:
                tvS2.setText("Second:"+Select2[index2]);
            break;
            case 3:
                tvS1.setText("First:"+Select1[index1]);           //在textView顯示選擇結果
                tvS2.setText("Second:"+Select2[index2]);
            break;
            default:
                Log.d("TEST","getSpinnerShow staus 不對 非整數");
        }
    }

    //換到第二頁，要看計算過程
    public void OnOpenDEVIEW(View view){
        //設定"換頁"物件，且換到哪頁
        Intent intent = new Intent(MainActivity.this,ViewActivity.class);
        //啟動換頁
        startActivity(intent);

    }

    //換到time，要看時間碼表
    public void OnOpenTime(View view){
        Log.i("test","testbuttom");
        //設定"換頁"物件，且換到哪頁
        Intent intent = new Intent(MainActivity.this,timeCount.class);
        //啟動換頁
        startActivity(intent);

    }

}