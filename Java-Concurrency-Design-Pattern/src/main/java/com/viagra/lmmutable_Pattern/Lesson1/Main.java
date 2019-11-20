package com.viagra.lmmutable_Pattern.Lesson1;

/**
 * @Auther: viagra
 * @Date: 2019/11/19 13:38
 * @Description:
 */
public class Main {
    public static void main(String[] args) {
        Person p = new Person("Steve Nash", "San Francisco");
        new PrintPersonThread(p).start();
        new PrintPersonThread(p).start();
        new PrintPersonThread(p).start();
    }
}
