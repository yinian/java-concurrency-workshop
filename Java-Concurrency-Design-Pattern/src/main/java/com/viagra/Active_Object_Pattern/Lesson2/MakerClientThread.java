package com.viagra.Active_Object_Pattern.Lesson2;


import java.util.concurrent.Future;

/**
 * @Auther: viagra
 * @Date: 2019/11/19 20:44
 * @Description:
 */
public class MakerClientThread extends Thread {
    /**
     * 是调用ActiveObject对象的makeString方法的线程
     */
    private final ActiveObject activeObject;
    private final char fillchar;

    public MakerClientThread(String name, ActiveObject activeObject) {
        super(name);
        this.activeObject = activeObject;
        this.fillchar = name.charAt(0);
    }

    public void run() {
        try {
            for (int i = 0; true; i++) {
                //有返回值的调用
                Future<String> future = activeObject.makeString(i, fillchar);
                Thread.sleep(10);
                String value = future.get();
                System.out.println(Thread.currentThread().getName() + ": value = " + value);
            }
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + ":" + e);
        } catch (Exception e) {
            System.out.println(Thread.currentThread().getName() + ":" + e);
        }
    }
}
