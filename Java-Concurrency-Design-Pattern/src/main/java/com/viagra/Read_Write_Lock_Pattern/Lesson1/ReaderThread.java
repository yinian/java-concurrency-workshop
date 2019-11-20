package com.viagra.Read_Write_Lock_Pattern.Lesson1;

/**
 * @Auther: viagra
 * @Date: 2019/11/19 17:32
 * @Description:
 */
public class ReaderThread extends Thread {
    /**
     * 表示的执行读取操作的线程,该类会循环调用data.read方法,并显示读取到的char数组.
     */
    private final Data data;

    public ReaderThread(Data data) {
        this.data = data;
    }

    public void run() {
        try {
            while (true) {
                char[] buffer = data.read();
                System.out.println(Thread.currentThread().getName() + " reads " + String.valueOf(buffer));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
