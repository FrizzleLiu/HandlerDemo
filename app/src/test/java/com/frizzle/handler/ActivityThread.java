package com.frizzle.handler;

import com.frizzle.handler.core.Handler;
import com.frizzle.handler.core.Looper;
import com.frizzle.handler.core.Message;

import org.junit.Test;

import java.util.logging.StreamHandler;

public class ActivityThread {
    /**
     * 模拟ActivityThread的main()方法
     */
    @Test
    public void main(){
        //创建全局唯一的主线程Looper对象,以及对应的MessageQueue对象
        Looper.prepare();
        //模拟Activity中创建Handler对象
        //消费消息,回调方法(接口方法),这里没写接口,使用系统Handler建议使用接口的方式
        final Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                System.out.println(msg.obj.toString());
            }
        };

        //子线程发送消息

        new Thread(new Runnable() {
            @Override
            public void run() {
                Message message = new Message();
                message.obj="Hellow Handler~";
                handler.sendMessage(message);
            }
        }).start();


        //轮询取出消息
        Looper.loop();
    }
}
