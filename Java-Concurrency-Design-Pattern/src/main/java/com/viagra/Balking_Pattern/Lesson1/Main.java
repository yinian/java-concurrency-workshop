package com.viagra.Balking_Pattern.Lesson1;

/**
 * @Auther: viagra
 * @Date: 2019/11/19 15:58
 * @Description:
 */
public class Main {
    public static void main(String[] args) {
        Data data = new Data("data.txt","(empty)");
        new ChangerThread("ChangerThread",data).start();
        new SaverThread("SaverThread", data).start();
    }
}
