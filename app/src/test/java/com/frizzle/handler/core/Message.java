package com.frizzle.handler.core;

public class Message {
    //消息内容
    public Object obj;
    //Handler对象
    public Handler target;
    //标识
    public int what;


    public Message() {
    }

    public Message(Object obj) {
        this.obj = obj;
    }

}
