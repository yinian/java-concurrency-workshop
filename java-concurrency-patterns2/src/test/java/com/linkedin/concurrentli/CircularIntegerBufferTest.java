package com.linkedin.concurrentli;
import org.junit.Test;

import static org.junit.Assert.*;
/**
 * @Auther: viagra
 * @Date: 2019/11/15 10:03
 * @Description:
 */
public class CircularIntegerBufferTest {

    @Test
    public void basic() {
        UnsafeCircularIntegerBuffer buff = new UnsafeCircularIntegerBuffer(10, 42);
        buff.set(9, 10);
        assertEquals(42, buff.getAndAdd(8, -42));
        assertTrue(buff.compareAndSet(0, 42, 10));

        assertEquals(0, buff.advanceIfEqual(10));
        assertEquals(-1, buff.advanceIfEqual(10));

        assertTrue(buff.compareAndAdvance(1));
        assertEquals(buff.getFirstElementIndex(), 2);
        assertEquals(42, buff.get(10));
        assertEquals(0, buff.get(8));
        assertEquals(10, buff.get(9));
    }
}
