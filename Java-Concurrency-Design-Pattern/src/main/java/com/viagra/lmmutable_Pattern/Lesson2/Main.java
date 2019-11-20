package com.viagra.lmmutable_Pattern.Lesson2;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: viagra
 * @Date: 2019/11/19 13:53
 * @Description:
 */
public class Main {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>();
        new WriterThread(list).start();
        System.out.println("==================");
        new ReaderThread(list).start();
    }
}
