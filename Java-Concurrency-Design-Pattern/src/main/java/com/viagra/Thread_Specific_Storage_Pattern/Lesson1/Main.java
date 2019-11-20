package com.viagra.Thread_Specific_Storage_Pattern.Lesson1;

/**
 * @Auther: viagra
 * @Date: 2019/11/19 20:22
 * @Description:
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Begin");
        for (int i = 0; i < 10; i++) {
            Log.println("main: i = " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Log.close();
        System.out.println("End");
    }
}
