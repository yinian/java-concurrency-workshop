package com.viagra.Active_Object_Pattern.Lesson2;

/**
 * @Auther: viagra
 * @Date: 2019/11/19 20:44
 * @Description:
 */
public class DisplayClientThread extends Thread {
    /**
     * 与MakerClientThread类一样，也是表示调用ActiveObject对象的线程的类。
     */
    private final ActiveObject activeObject;

    public DisplayClientThread(String name, ActiveObject activeObject) {
        super(name);
        this.activeObject = activeObject;
    }

    public void run() {
        try {
            for (int i = 0; true; i++) {
                //没有返回值的调用
                String string = Thread.currentThread().getName() + " " + i;
                activeObject.displayString(string);
                Thread.sleep(200);
            }
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + ":" + e);
        } catch (Exception e) {
            System.out.println(Thread.currentThread().getName() + ":" + e);
        }
    }
}
