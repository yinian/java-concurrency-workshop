package com.viagra.Producer_Consumer_Pattern.Lesson1;

import java.util.Random;

/**
 * @Auther: viagra
 * @Date: 2019/11/19 17:18
 * @Description:
 */
public class MakerThread extends Thread {
    private final Random random;
    private final Table table;
    private static int id = 0; //蛋糕的流水号

    public MakerThread(String name, Table table, int seed) {
        super(name);
        this.table = table;
        this.random = new Random(seed);
    }

    public void run() {
        try {
            while (true) {
                Thread.sleep(random.nextInt(1000));
                String cake = "[ Cake No. " + nextId() + " by " + getName() + "]";
                table.put(cake);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static synchronized int nextId() {
        return id++;
    }
}
