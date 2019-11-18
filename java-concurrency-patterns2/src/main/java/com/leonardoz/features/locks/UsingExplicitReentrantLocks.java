package com.leonardoz.features.locks;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Alternative mechanism for Intrinsic Locks, but with the same guarantees on
 * serialization and visibility;
 *
 * A Lock interface with multiple implementations with some differences in
 * behavior; it offers cancellation, fairness and others facilities.
 *
 * Fair in constructor:
 *
 * true: Fair Lock, newly requesting threads are queued if the lock is held.
 *
 * false: Unfair lock: if the lock is held, requesting threads can 'jump' the
 * waiting queue.
 * @Auther: viagra
 * @Date: 2019/11/13 15:56
 * @Description:
 */
public class UsingExplicitReentrantLocks {
    // Equivalent to Intrinsic Locks
    private ReentrantLock reentrantLock = new ReentrantLock();
    private boolean state;

    /**
     * Simplest way to use
     */
    public void lockMyHearth() {
        reentrantLock.lock();
        try {
            System.out.println("Changing stated in a serialized way");
            state = !state;
            System.out.println("Changed: " + state);
        } finally {
            reentrantLock.unlock();
        }
    }


    /**
     * Try lock - Timed and polled lock acquisition
     *
     * @throws InterruptedException
     */
    public void lockMyHearthWithTiming() throws InterruptedException {

        // Tries to acquire lock in the specified timeout
        if (!reentrantLock.tryLock(1l,TimeUnit.SECONDS)){
            System.err.println("Failed to acquire the lock - it's already held.");
        }else{
            try {
                System.out.println("Simulating a blocking computation - forcing tryLock() to fail");
                Thread.sleep(3000);
            } finally {
                reentrantLock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        UsingExplicitReentrantLocks self = new UsingExplicitReentrantLocks();

        for (int i = 0; i < 10; i++) {
            executorService.execute(() -> self.lockMyHearth());
        }

        for (int i = 0; i < 40; i++) {
            executorService.execute(() -> {
                try {
                    self.lockMyHearthWithTiming();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        executorService.shutdown();
    }






}
