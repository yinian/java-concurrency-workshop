package com.leonardoz.features.threads;
/**
 * Threads
 *
 * Java support for OS-threads.
 *
 * Thread is a basic unit that can run in parallel through CPU cores.
 *
 * A thread can 'see' others threads memory.
 *
 * @Auther: viagra
 * @Date: 2019/11/13 19:25
 * @Description:
 */
public class UsingThreads {

    public static void main(String[] args) throws InterruptedException {
        // Creating
        Thread created = new Thread();
        created.start();
        // .run() runs on main thread

        // Assigning a task for running on a thread - we pass a Runnable instance
        Thread threadWithTask = new Thread(() -> System.out.println("Inside thread" + Thread.currentThread().getName()));
        threadWithTask.start();

        // Interrupting a thread
        Runnable interrupatblyTask = () -> {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("Im not interrupted " + Thread.currentThread().getName());
            }
        };
        Thread interruptable = new Thread(interrupatblyTask);
        interruptable.start();
        Thread.sleep(3000);
        interruptable.interrupt();

    }
}
