package com.linkedin.concurrentli;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.assertEquals;

/**
 * @Auther: viagra
 * @Date: 2019/11/15 09:16
 * @Description:
 */
public class AutoResetEventTest {


    @Test
    public void test() throws InterruptedException {

        AtomicInteger incrementor = new AtomicInteger();
        ResettableEvent are = new ResettableEvent(true);
        ResettableEvent done = new ResettableEvent(false);

        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 10; i++) {

            threadPool.submit(Interrupted.unchecked(()->{
                are.getAndReset();
                incrementor.incrementAndGet();
                done.set();
            }));

        }

        done.getAndReset();
        Thread.sleep(100); // give a little time to other threads to increment, if there's indeed a bug
        assertEquals(1, incrementor.get());
        threadPool.shutdownNow();
    }

}
