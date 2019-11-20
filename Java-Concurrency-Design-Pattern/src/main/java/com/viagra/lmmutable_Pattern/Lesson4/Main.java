package com.viagra.lmmutable_Pattern.Lesson4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Auther: viagra
 * @Date: 2019/11/19 13:53
 * @Description:
 */
public class Main {
    public static void main(String[] args) {
        List<Integer> list = new CopyOnWriteArrayList<Integer>();
        new WriterThread(list).start();
        new ReaderThread(list).start();
    }
}
