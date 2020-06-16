package com.frizzle.handler.core;


public class Handler {
    private Looper mLooper;
    private MessageQueue mQueue;

    public Handler() {
        mLooper = Looper.myLooper();
        if (mLooper == null) {
            throw new RuntimeException(
                    "Can't create handler inside thread " + Thread.currentThread()
                            + " that has not called Looper.prepare()");
        }
        mQueue = mLooper.mQueue;
    }

    /**
     * @param msg
     * 处理消息
     * 开放Api,用于处理和回调
     */
    public void handleMessage(Message msg) {
    }

    /**
     * @param message
     * 发送消息
     */
    public void sendMessage(Message message) {
        equeueMessage(message);
    }

    /**
     * @param message
     * 将消息放入消息队列
     */
    private void equeueMessage(Message message) {
        //将message.target赋值为当前Handler
        message.target=this;
        //将消息放入消息队列
        mQueue.equeueMessage(message);
    }

    public void dispatchMessage(Message msg) {
        handleMessage(msg);
    }
}
