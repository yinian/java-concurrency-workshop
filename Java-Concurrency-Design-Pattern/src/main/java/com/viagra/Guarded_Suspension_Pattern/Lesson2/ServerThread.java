package com.viagra.Guarded_Suspension_Pattern.Lesson2;

import com.viagra.Guarded_Suspension_Pattern.Lesson1.Request;
import com.viagra.Guarded_Suspension_Pattern.Lesson1.RequestQueue;

import java.util.Random;

/**
 * @Auther: viagra
 * @Date: 2019/11/19 14:58
 * @Description:
 */
public class ServerThread extends Thread {
    private final Random random;
    private final RequestQueue requestQueue;

    public ServerThread(RequestQueue requestQueue, String name, long seed) {
        super(name);
        this.requestQueue = requestQueue;
        this.random = new Random(seed);
    }

    public void run() {
        for (int i = 0; i < 10000; i++) {
            Request request = requestQueue.getRequest();
            System.out.println(Thread.currentThread().getName() + " handle: " + request);
            try {
                Thread.sleep(random.nextInt(1000));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
