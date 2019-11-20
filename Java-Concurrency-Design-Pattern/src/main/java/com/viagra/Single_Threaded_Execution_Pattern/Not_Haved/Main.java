package com.viagra.Single_Threaded_Execution_Pattern.Not_Haved;

/**
 * @Auther: viagra
 * @Date: 2019/11/19 11:14
 * @Description:
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("测试Single Threaded Execution-Gate，hit CTRL+C to exit.");
        Gate gate = new Gate();
        new UserThread(gate, "Steve Nash", "San Francisco").start();
        new UserThread(gate, "Michael Jordan", "Miami").start();
        new UserThread(gate, "Ronaldo", "Rome").start();
    }
}
