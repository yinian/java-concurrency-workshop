package com.viagra.Balking_Pattern.Lesson2;

import java.util.concurrent.TimeoutException;

/**
 * @Auther: viagra
 * @Date: 2019/11/19 16:05
 * @Description:
 */
public class Main {
    public static void main(String[] args) {
        Host host = new Host(10000);
        try {
            System.out.println("execute BEGIN");
            host.execute();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
