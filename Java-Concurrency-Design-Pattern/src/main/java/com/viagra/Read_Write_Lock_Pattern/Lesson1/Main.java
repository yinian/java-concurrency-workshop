package com.viagra.Read_Write_Lock_Pattern.Lesson1;

/**
 * @Auther: viagra
 * @Date: 2019/11/19 17:32
 * @Description:
 */
public class Main {
    public static void main(String[] args) {
        /**
         * 这里启动6个读取线程和2个写入线程
         */
        Data data = new Data(10);
        new ReaderThread(data).start();
        new ReaderThread(data).start();
        new ReaderThread(data).start();
        new ReaderThread(data).start();
        new ReaderThread(data).start();
        new ReaderThread(data).start();
        new WriterThread(data, "ABCDEFGHIJKLMNOPQRSTUVWXYZ").start();
        new WriterThread(data, "abcdefghijklmnopqrstuvwxyz").start();
    }
}
