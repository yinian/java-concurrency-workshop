package com.viagra.Two_Phase_Termination_Pattern.Lesson1;

/**
 * @Auther: viagra
 * @Date: 2019/11/19 20:06
 * @Description:
 */
public class Main {
    /**
     * 启动CountupThread的线程，大约10s后终止该线程
     * Thread类的join方法是用户等待线程终止的方法，在指定的线程终止前，join方法不会返回。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("main: Begin");
        try {
            //启动线程
            CountupThread t = new CountupThread();
            t.start();

            //稍微间隔一段时间
            Thread.sleep(10000);

            //线程的终止请求
            System.out.println("main: shutdownRequest");
            t.shutdownRequest();

            System.out.println("main: join");

            //等待线程终止
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main: End");
    }
}
