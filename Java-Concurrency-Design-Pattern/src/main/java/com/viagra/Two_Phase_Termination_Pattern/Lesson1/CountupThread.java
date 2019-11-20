package com.viagra.Two_Phase_Termination_Pattern.Lesson1;

/**
 * @Auther: viagra
 * @Date: 2019/11/19 20:06
 * @Description:
 */
public class CountupThread extends Thread {
    /**
     * counter字段表示当前的计数器
     * shutdownRequested字段是表示是否已经发出终止请求的标志，该字段的值用于判断线程是否进入“终止处理中”状态
     * shutdownRequest方法是表示线程终止请求的方法，当要终止CountupThread的线程时，程序会调用该方法
     * 注意，shutdownRequest还调用了interrupt方法，这是为了确保程序在sleep和wait时也会被终止
     */

    //计数值
    private long counter = 0;

    //发出终止请求后变为ture
    private volatile boolean shutdownRequested = false;

    //终止请求
    public void shutdownRequest() {
        shutdownRequested = true;
        interrupt();
    }

    //检查是否发出了终止请求
    public boolean isShutdownRequested() {
        return shutdownRequested;
    }

    //线程体
    public final void run() {
        try {
            while (!isShutdownRequested()) {
                doWork();
            }
        } catch (InterruptedException e) {
        } finally {
            doShutdown();
        }
    }

    //操作
    private void doWork() throws InterruptedException {
        counter++;
        System.out.println("doWork: counter = " + counter);
        Thread.sleep(500);
    }

    //终止处理
    private void doShutdown() {
        System.out.println("doShutdown: counter = " + counter);
    }

}
