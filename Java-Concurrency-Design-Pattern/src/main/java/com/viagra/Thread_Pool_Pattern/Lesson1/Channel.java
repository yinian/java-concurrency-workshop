package com.viagra.Thread_Pool_Pattern.Lesson1;

/**
 * @Auther: viagra
 * @Date: 2019/11/19 19:09
 * @Description:
 */
public class Channel {
    /**
     * 是负责传递工作请求以及保存工人线程的类
     * 为了传递工作请求，我们Channel类中定义了requestQueue字段，该字段扮演保存请求队列的角色，putRequest方法用于将请求加入到队列中，takeRequest方法则用于取出后队列中的请求。
     * Channel类中定义了一个用于保存工人线程的threadPool字段。threadPool是WorkerThread的数组，Channel类的构造函数会初始化threadPool字段并创建WorkerThread的实例
     */
    private static final int MAX_REQUEST = 100;
    private final Request[] requestQueue;
    private int tail; //下次putRequest的位置
    private int head; //下次takeRequest的位置
    private int count; //Request的数量

    private final WorkerThread[] threadPool;

    public Channel(int threads) {
        this.requestQueue = new Request[MAX_REQUEST];
        this.tail = 0;
        this.head = 0;
        this.count = 0;

        threadPool = new WorkerThread[threads];
        for (int i = 0; i < threadPool.length; i++) {
            threadPool[i] = new WorkerThread("Worker-" + i, this);
        }
    }

    public void startWorkers() {
        for (int i = 0; i < threadPool.length; i++) {
            threadPool[i].start();
        }
    }

    public synchronized void putRequest(Request request) {
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

    public synchronized Request takeRequest() {
        while (count <= 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Request request = requestQueue[head];
        head = (head + 1) % requestQueue.length;
        count--;
        notifyAll();
        return request;
    }

}
