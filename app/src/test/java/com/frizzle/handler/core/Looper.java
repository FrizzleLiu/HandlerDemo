package com.frizzle.handler.core;


public class Looper {

    static final ThreadLocal<Looper> sThreadLocal = new ThreadLocal<>();
    //与Looper关联的唯一消息队列
    public MessageQueue mQueue;

    private Looper() {
        mQueue = new MessageQueue();
    }

    public static void prepare() {
        //主线程只有唯一一个Looper对象
        if (sThreadLocal.get() != null) {
            throw new RuntimeException("Only one Looper may be created per thread");
        }
        //创建Looper对象,存在ThreadLocal,key是主线程(模拟)
        sThreadLocal.set(new Looper());
    }

    /**
     * 轮询,取消息
     */
    public static void loop() {
        //取Looper
        Looper me = myLooper();
        //取消息队列
        MessageQueue queue = me.mQueue;

        Message resultMessage;
        //轮询,取消息队首的Message对象,并调用target.dispatchMessage(resultMessage) --- target就是Handler对象
        while (true) {
            Message msg = queue.next();
            if (msg != null && msg.target != null) {
                msg.target.dispatchMessage(msg);
            }
        }
    }

    public static Looper myLooper() {
        return sThreadLocal.get();
    }
}
