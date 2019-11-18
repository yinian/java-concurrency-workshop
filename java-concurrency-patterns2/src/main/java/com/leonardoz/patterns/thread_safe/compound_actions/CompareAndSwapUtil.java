package com.leonardoz.patterns.thread_safe.compound_actions;
/**
 *
 * Pattern: Atomic Compound Actions
 *
 * Example: Simple Atomic Compound Actions using Compare-and-swap idiom
 *
 * @Auther: viagra
 * @Date: 2019/11/14 15:09
 * @Description:
 */
public class CompareAndSwapUtil {

    private int value;

    public synchronized int getValue() {
        return value;
    }

    public synchronized int compareAndSwap(int expected, int newValue) {
        int old = value;
        if (old == expected) {
            value = newValue;
        }
        return old;
    }

}
