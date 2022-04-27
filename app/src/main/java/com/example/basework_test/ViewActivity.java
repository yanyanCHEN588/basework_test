package com.example.basework_test;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.DialogInterface;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import android.view.View.OnClickListener; //for implements OnClickListener
public class ViewActivity extends AppCompatActivity implements OnClickListener {

    //golbal variable
    TextView tvResult;
//    Button btAdd;
//    Double counter = 0.0;

//    TextView timerText;
    Button stopStartButton,btGood,btKeep,btCenter;

    Timer timer;
    TimerTask timerTask;
    Double time = 0.0;

    boolean timerStarted = false;
    SoundPool soundPool;
    HashMap<Integer, Integer> soundMap=new HashMap<Integer, Integer>(); //不用宣布大小，利用put動態增加

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        //Setting Listener
        tvResult = findViewById(R.id.tv_result);
//        timerText = findViewById(R.id.timerText);
        stopStartButton =findViewById(R.id.bt_start);

        btGood = findViewById(R.id.bt_good);
        btKeep = findViewById(R.id.bt_keep);
        btCenter = findViewById(R.id.bt_center);
        //implements OnClickListener
        btGood.setOnClickListener(this);
        btKeep.setOnClickListener(this);
        btCenter.setOnClickListener(this);

        //設置音校屬性
        AudioAttributes attr = new AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_ASSISTANCE_NAVIGATION_GUIDANCE) //設置音效使用場景
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC).build(); // 設置音樂類型
        //soundPool Setting
        soundPool = new SoundPool.Builder().setAudioAttributes(attr) // 將屬給予音效池
                .setMaxStreams(20) // 設置最多可以容納個音效數量，我先估計20個拉
                .build(); //

        // load 方法加載至指定音樂文件，並返回所加載的音樂ID然後給hashmap Int
        // 此處用hashmap來管理音樂文件 load 來自於raw文件
        soundMap.put(1, soundPool.load(this, R.raw.good, 1));
        soundMap.put(2, soundPool.load(this, R.raw.keep, 1));
        soundMap.put(3, soundPool.load(this, R.raw.objincenter, 1));


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
                        voicePlay(voiceStatus(time));
                        tvResult.setText(timeString); //依照time撥放聲音的狀態
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

    //implements OnClickListener
    @Override
    public void onClick(View view) {
        // 判斷哪個按鈕被點擊而啟動對應聲音
        if (view.getId() == R.id.bt_good) {
            soundPool.play(soundMap.get(1), 1, 1, 0, 0, 1);
        } else if (view.getId() == R.id.bt_keep) {
            soundPool.play(soundMap.get(2), 1, 1, 0, 0, 1);
        } else if (view.getId() == R.id.bt_center) {
            soundPool.play(soundMap.get(3), 1, 1, 0, 0,1.5f); //調快聲音為1.5倍
        }
    }

    private Integer voiceStatus(Double time)
    {
        Integer status=0;
//        int rounded = (int) Math.round(time); //cuz 0.0 == 0

        if ((time % 8)==0){
            status=1;
        } else if ((time % 20)==0){
            status=2;
        }else if ((time % 30)==0){
            status=3;
        }else{
            status=0;
        }

        return status;
    }

    private void voicePlay(int status){
        if (status == 1) {
            soundPool.play(soundMap.get(1), 1, 1, 0, 0, 1);
        } else if (status == 2) {
            soundPool.play(soundMap.get(2), 1, 1, 0, 0, 1);
        } else if (status == 3) {
            soundPool.play(soundMap.get(3), 1, 1, 0, 0,1.5f); //調快聲音為1.5倍
        }
    }

}