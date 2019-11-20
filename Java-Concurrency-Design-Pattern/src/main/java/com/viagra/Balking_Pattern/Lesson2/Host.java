package com.viagra.Balking_Pattern.Lesson2;

import java.util.concurrent.TimeoutException;

/**
 * @Auther: viagra
 * @Date: 2019/11/19 16:04
 * @Description:
 */
public class Host {
    private final long timeout;
    private boolean ready = false;

    public Host(long timeout) {
        this.timeout = timeout;
    }

    //修改状态
    public synchronized void setExecutable(boolean on) {
        ready = on;
        notifyAll();
    }

    //检查状态之后再执行
    public synchronized void execute() throws InterruptedException, TimeoutException {
        long start = System.currentTimeMillis();
        while (!ready) {
            long now = System.currentTimeMillis();//当前时间
            long rest = timeout - (now - start); //剩余的等待时间
            if (rest <= 0) {
                throw new TimeoutException("now - start = " + (now - start) + ", timeout = " + timeout);
            }
        }
        doExecute();
    }

    //实际的处理
    public void doExecute() {
        System.out.println(Thread.currentThread().getName() + " calls doExecute");
    }
}
