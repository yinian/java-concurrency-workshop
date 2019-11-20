package com.viagra.Guarded_Suspension_Pattern.Lesson2;

import com.viagra.Guarded_Suspension_Pattern.Lesson1.RequestQueue;

/**
 * @Auther: viagra
 * @Date: 2019/11/19 14:58
 * @Description:
 */
public class Main {
    public static void main(String[] args) {
        RequestQueue requestQueue = new RequestQueue();
        new ClientThread(requestQueue, "Alice", 3141592L).start();
        new ServerThread(requestQueue, "Bobby", 6535897L).start();
    }
}
