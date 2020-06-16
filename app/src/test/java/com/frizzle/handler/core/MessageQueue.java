package com.frizzle.handler.core;

import java.lang.reflect.Array;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class MessageQueue {

    //阻塞的消息队列,数量是50
    BlockingQueue<Message> blockingQueue= new ArrayBlockingQueue<>(50);

    /**
     * @param message
     * 将message对象放入阻塞的消息队列
     */
    public void equeueMessage(Message message) {
        try {
            blockingQueue.put(message);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return 从消息队列中取消息
     */
    public Message next() {
        try {
            return blockingQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
