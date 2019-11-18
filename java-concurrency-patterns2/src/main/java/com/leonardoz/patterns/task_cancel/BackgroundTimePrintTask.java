package com.leonardoz.patterns.task_cancel;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Pattern: Thread Task Cancel
 *
 * Example: Canceling a Background Timer Print Task.
 *
 * @Auther: viagra
 * @Date: 2019/11/14 13:23
 * @Description:
 */
public class BackgroundTimePrintTask {

    private Thread thread;
    private Runnable task = () -> {
        while (!Thread.currentThread().isInterrupted()) {
            Date date = new Date(System.currentTimeMillis());
            System.out.println(new SimpleDateFormat().format(date));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // no need to interrupt() if you don't have anything throwing InterruptedException
                thread.interrupt();
            }
        }
    };

    public void run() {
        thread = new Thread(task);
        thread.start();
    }

    public void cancel() {
        if (thread != null) {
            thread.interrupt();
        }
    }

    public static void main(String[] args) {
        BackgroundTimePrintTask self = new BackgroundTimePrintTask();
        self.run();
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        self.cancel();
    }
}
