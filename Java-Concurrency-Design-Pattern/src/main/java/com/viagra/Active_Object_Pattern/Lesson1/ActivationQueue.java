package com.viagra.Active_Object_Pattern.Lesson1;

/**
 * @Auther: viagra
 * @Date: 2019/11/19 20:37
 * @Description:
 */
public class ActivationQueue {
    private static final int MAC_METHOD_REQUEST = 100;
    private final MethodRequest[] requestQueue;
    private int tail; //下次putRqueset的位置
    private int head; //下次takeRequest的位置
    private int count; //MethodRequest的数量

    public ActivationQueue() {
        this.requestQueue = new MethodRequest[MAC_METHOD_REQUEST];
        this.head = 0;
        this.tail = 0;
        this.count = 0;
    }

    public synchronized void putRequest(MethodRequest request) {
        while (count >= requestQueue.length) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        requestQueue[tail] = request;
        tail = (tail + 1) % requestQueue.length;
        count++;
        notifyAll();
    }

    public synchronized MethodRequest takeRequest() {
        while (count <= 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        MethodRequest request = requestQueue[head];
        head = (head + 1) % requestQueue.length;
        count--;
        notifyAll();
        return request;
    }

}
