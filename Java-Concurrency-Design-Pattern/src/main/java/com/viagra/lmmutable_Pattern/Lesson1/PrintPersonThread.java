package com.viagra.lmmutable_Pattern.Lesson1;

/**
 * @Auther: viagra
 * @Date: 2019/11/19 13:38
 * @Description:
 */
public class PrintPersonThread extends Thread {
    private Person person;

    public PrintPersonThread(Person p) {
        this.person = p;
    }

    public void run() {
        while (true) {
            System.out.println(Thread.currentThread().getName() + " prints " + person);
        }
    }
}
