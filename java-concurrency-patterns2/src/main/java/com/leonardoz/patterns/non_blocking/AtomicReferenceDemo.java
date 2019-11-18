package com.leonardoz.patterns.non_blocking;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @Auther: viagra
 * @Date: 2019/11/14 10:01
 * @Description:
 */
public class AtomicReferenceDemo {

    static AtomicReference<Person> p = new AtomicReference<Person>(new Person(20));

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 3; i++) {
                    p.set(new Person(p.get().age + 10));
                    System.out.println("Atomic Check by first thread: " + Thread.currentThread().getName() + " is " + p.get().age);
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                Person per = p.get();
                for (int i = 1; i <= 3; i++) {
                    System.err.println(p.get().equals(per) + "_" + per.age + "_" + p.get().age);
                    p.compareAndSet(per, new Person(p.get().age + 10));
                    System.out.println("Atomic Check by second thread : " + Thread.currentThread().getName() + " is " + p.get().age);
                }
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("Final value: " + p.get().age);
    }


}
class Person {
    int age;

    public Person(int i) {
        age = i;
    }
}