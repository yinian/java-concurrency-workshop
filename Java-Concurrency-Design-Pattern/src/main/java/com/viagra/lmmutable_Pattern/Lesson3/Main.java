package com.viagra.lmmutable_Pattern.Lesson3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Auther: viagra
 * @Date: 2019/11/19 13:53
 * @Description:
 */
public class Main {
    public static void main(String[] args) {
        final List<Integer> list = Collections.synchronizedList(new ArrayList<Integer>());
        new WriterThread(list).start();
        new ReaderThread(list).start();
    }
}
