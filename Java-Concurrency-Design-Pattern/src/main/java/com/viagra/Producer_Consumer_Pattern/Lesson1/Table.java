package com.viagra.Producer_Consumer_Pattern.Lesson1;

/**
 * @Auther: viagra
 * @Date: 2019/11/19 17:18
 * @Description:
 */
public class Table {
    private final String[] buffer;
    private int tail;//下次put的位置
    private int head;//下次take的位置
    private int count;//buffer中的蛋糕数

    public Table(int count) {
        this.buffer = new String[count];
        this.head = 0;
        this.tail = 0;
        this.count = 0;
    }

    /**
     * put方法使用了Guarded Suspension模式,守护条件为while条件表达式的逻辑非运算!count >= buffer.length,也就是说当桌上放置的蛋糕小于最大
     * 放置量,把它作为放置蛋糕的put方法守护条件.
     * 通过上面的处理,蛋糕已经放置导桌上了,由于桌子的状态发生了变化,所以要执行notifyAll,唤醒所有正在wait的线程.
     *
     * @param cake
     * @throws InterruptedException
     */
    //放置蛋糕
    public synchronized void put(String cake) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " puts " + cake);
        while (count >= buffer.length) {
            wait();
        }
        buffer[tail] = cake;
        tail = (tail + 1) % buffer.length;
        count++;
        notifyAll();
    }

    /**
     * take方法使用了Guarded Suspension模式,守护条件为!count <= 0.
     *
     * @return
     * @throws InterruptedException
     */
    //拿取蛋糕
    public synchronized String take() throws InterruptedException {
        while (count <= 0) {
            wait();
        }
        String cake = buffer[head];
        head = (head + 1) % buffer.length;
        count--;
        notifyAll();
        System.out.println(Thread.currentThread().getName() + " takes " + cake);
        return cake;
    }
}
