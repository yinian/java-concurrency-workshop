package com.viagra.Active_Object_Pattern.Lesson2;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @Auther: viagra
 * @Date: 2019/11/19 20:44
 * @Description:
 */
//ActiveObject接口的实现类
//ActiveObject接口的实现类
public class ActiveObjectImpl implements ActiveObject {
    /**
     * AcitveObjectImpl类实现了ActiveObject接口的类，它可以进行很多工作
     * ExecutorService对象的内部保存着一个线程安全的队列。
     * shutdown方法是用户关于关闭service字段中保存的ExecutorService对象的方法，这样以来，ExecutorService对象就不会再接收新的请求了。
     * makeString方法会创建MakeStringRequest类的实例，并submit给ExecutorService对象。
     * displayString方法会创建DisplayStringRequest类的实例，并在ExecutorService中execute。
     */
    private final ExecutorService service = Executors.newSingleThreadExecutor();

    //终止服务
    public void shutdown() {
        service.shutdown();
    }

    //有返回值的调用
    public Future<String> makeString(final int count, final char fillchar) {
        //请求
        class MakeStringRequest implements Callable<String> {
            public String call() {
                char[] buffer = new char[count];
                for (int i = 0; i < count; i++) {
                    buffer[i] = fillchar;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
//                        e.printStackTrace();
                    }
                }
                return new String(buffer);
            }
        }
        //发出请求
        return service.submit(new MakeStringRequest());
    }

    //没有返回值的调用
    public void displayString(final String string) {
        //请求
        class DisplayStringRequest implements Runnable {
            public void run() {
                try {
                    System.out.println("displayString: " + string);
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName() + ":" + e);
                }
            }
        }
        //发出请求
        service.execute(new DisplayStringRequest());
    }
}
