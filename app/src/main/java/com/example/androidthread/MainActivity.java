package com.example.androidthread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity{

    private Button btnStart, btnStop;
    private boolean startLoop;
    private TextView tvCounter;
    private int counter = 0;
  //  private Handler handler;
    public static final String TAG ="MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = findViewById(R.id.btn_start_thread);
        btnStop = findViewById(R.id.btn_stop_thread);
        tvCounter = findViewById(R.id.tv_counter);

    //    handler = new Handler(getApplicationContext().getMainLooper());

        Log.i(TAG, "Current thread id: " + Thread.currentThread().getId());

   /*     btnStop.setOnClickListener(this);
        btnStart.setOnClickListener(this);*/

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startLoop = true;

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while(startLoop){
                            Log.i(TAG, "Thread id in loop " + Thread.currentThread().getId());
                            try {
                                Thread.sleep(1000);
                                counter++;

                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            Log.i(TAG, "Counter is now: " + counter);
                            /*handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    tvCounter.setText(Integer.toString(counter));
                                }
                            });*/

                            //alternatively we can do it this way

                            tvCounter.post(new Runnable() {
                                @Override
                                public void run() {
                                    tvCounter.setText(Integer.toString(counter));
                                }
                            });
                        }
                    }
                }).start();

            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startLoop = false;

    //            tvCounter.setText(String.valueOf(counter));
            }

        });
    }

    // this is the alternative way of implementing the OnclickListener
    // if the View.OnClickListener interface is implemented
 /*   @Override
    public void onClick(View view) {

        switch(view.getId()){

            case R.id.btn_start_thread:
                //do whatever
                break;

            case R.id.btn_stop_thread:
                //do whatever
                break;
        }

    }*/

}
