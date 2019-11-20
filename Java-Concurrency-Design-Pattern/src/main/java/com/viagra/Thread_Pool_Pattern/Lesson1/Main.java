package com.viagra.Thread_Pool_Pattern.Lesson1;

/**
 * @Auther: viagra
 * @Date: 2019/11/19 19:09
 * @Description:
 */
public class Main {
    /**
     * 会创建一个雇佣了五个工人线程的Channel实例，并将其共享给三个ClientThread实例
     * @param args
     */
    public static void main(String[] args) {
        Channel channel = new Channel(5); //工人线程的个数
        channel.startWorkers();
        new ClientThread("Steve Nash", channel).start();
        new ClientThread("Michael Jordan", channel).start();
        new ClientThread("Ronaldo", channel).start();
    }
}
