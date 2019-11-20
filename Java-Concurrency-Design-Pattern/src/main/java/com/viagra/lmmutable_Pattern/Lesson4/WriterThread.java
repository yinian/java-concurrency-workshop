package com.viagra.lmmutable_Pattern.Lesson4;

import java.util.List;

/**
 * @Auther: viagra
 * @Date: 2019/11/19 13:53
 * @Description:
 */
public class WriterThread extends Thread {
    private final List<Integer> list;

    public WriterThread(List<Integer> list) {
        super("WriterThread");
        this.list = list;
    }

    public void run() {
        for (int i = 0; true; i++) {
            list.add(i);
            list.remove(0);
        }
    }
}
