package com.viagra.lmmutable_Pattern.Lesson3;

import java.util.List;

/**
 * @Auther: viagra
 * @Date: 2019/11/19 13:53
 * @Description:
 */
public class ReaderThread extends Thread {
    private final List<Integer> list;

    public ReaderThread(List<Integer> list) {
        super();
        this.list = list;
    }

    public void run() {
        while (true) {
            synchronized (list) {
                for (int n : list) {
                    System.out.println(n);
                }
            }
        }
    }
}
