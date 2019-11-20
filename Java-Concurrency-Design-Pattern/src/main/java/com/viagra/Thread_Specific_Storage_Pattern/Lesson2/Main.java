package com.viagra.Thread_Specific_Storage_Pattern.Lesson2;

/**
 * @Auther: viagra
 * @Date: 2019/11/19 20:27
 * @Description:
 */
public class Main {
    public static void main(String[] args) {
        new ClientThread("Steve Nash").start();
        new ClientThread("Michael Jordan").start();
        new ClientThread("Ronaldo").start();
    }
}
