package com.viagra.Producer_Consumer_Pattern.Lesson1;

import java.util.Random;

/**
 * @Auther: viagra
 * @Date: 2019/11/19 17:18
 * @Description:
 */
public class EaterThread extends Thread {
    private final Random random;
    private final Table table;

    public EaterThread(String name, Table table, long seed) {
        super(name);
        this.table = table;
        this.random = new Random(seed);
    }

    public void run() {
        try {
            while (true) {
                String cake = table.take();
                Thread.sleep(random.nextInt(1000));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
