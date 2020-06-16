package com.frizzle.handler;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.LoginFilter;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG="HANDLER_TEST";
    private TextView mTextView;

    //第一种方式创建handler
    Handler handler1 = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            //跳转另一个Activity
            startActivity(new Intent(MainActivity.this,SecondActivity.class));
            super.handleMessage(msg);
        }
    };

//    //第二种方式创建handler
//    Handler handler2 = new Handler(new Handler.Callback() {
//        @Override
//        public boolean handleMessage(Message msg) {
//            mTextView.setText(msg.obj.toString());
//            return false;
//        }
//    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = findViewById(R.id.tv);
        leakTest();
    }

    //内存泄露测试,开启一个线程,休眠5s后handler1发送消息
    private void leakTest() {
        new Thread(new Runnable(){
            @Override
            public void run() {
//                SystemClock.sleep(1000);
                mTextView.setText("子线程更新文本内容");
//                Toast.makeText(MainActivity.this,"子线程弹吐司",Toast.LENGTH_SHORT).show();
            }
        }).start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG,"onDestroy");
        if (handler1!=null) {
            handler1.removeCallbacksAndMessages(123);
            handler1=null;
        }
    }
}
