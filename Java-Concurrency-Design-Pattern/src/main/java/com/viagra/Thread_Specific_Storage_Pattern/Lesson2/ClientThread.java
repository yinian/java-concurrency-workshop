package com.viagra.Thread_Specific_Storage_Pattern.Lesson2;

/**
 * @Auther: viagra
 * @Date: 2019/11/19 20:22
 * @Description:
 */
public class ClientThread extends Thread {
    public ClientThread(String name) {
        super(name);
    }

    public void run() {
        System.out.println(getName() + " Begin");
        for (int i = 0; i < 10; i++) {
            Log.println(getName() + " i = " + i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Log.close();
        System.out.println(getName() + " End");
    }
}
