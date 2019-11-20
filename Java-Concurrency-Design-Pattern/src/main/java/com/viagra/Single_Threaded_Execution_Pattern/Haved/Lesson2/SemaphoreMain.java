package com.viagra.Single_Threaded_Execution_Pattern.Haved.Lesson2;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * Single Threaded Execution模式用户确保某个区域“只能由一个线程”执行，
 * 下面通过这种模式扩展，以确保某个区域“最多只能由N个线程”执行，就要用计数信号量来控制线程量。
 * @Auther: viagra
 * @Date: 2019/11/19 11:30
 * @Description:
 */
//使用Semaphore类的示例程序
public class SemaphoreMain {
    public static void main(String[] args) {
        //设置3个资源
        BoundedResource boundedResource = new BoundedResource(3);
        //10个线程调用资源
        for (int i = 0; i < 10; i++) {
            new UserThread(boundedResource).start();
        }
    }

}

//日志
class Log {
    public static void println(String s) {
        System.out.println(Thread.currentThread().getName() + "==>:" + s);
    }
}

//资源个数有限
class BoundedResource {
    private final Semaphore semaphore;
    private final int permits;
    private final static Random random = new Random(314159);

    public BoundedResource(int permits) {
        this.semaphore = new Semaphore(permits);
        this.permits = permits;
    }

    //使用资源
    public void use() throws InterruptedException {
        semaphore.acquire();
        try {
            doUse();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }

    //实际使用资源
    protected void doUse() throws InterruptedException {
        Log.println("BEGIN: used = " + (permits - semaphore.availablePermits()));
        Thread.sleep(random.nextInt(500));
        Log.println("END: used = " + (permits - semaphore.availablePermits()));
    }
}

class UserThread extends Thread {
    private final static Random random = new Random(26535);
    private final BoundedResource boundedResource;

    public UserThread(BoundedResource boundedResource) {
        this.boundedResource = boundedResource;
    }

    public void run() {
        try {
            while (true) {
                boundedResource.use();
                Thread.sleep(random.nextInt(3000));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

