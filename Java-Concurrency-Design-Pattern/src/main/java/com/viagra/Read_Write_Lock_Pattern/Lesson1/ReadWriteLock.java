package com.viagra.Read_Write_Lock_Pattern.Lesson1;

/**
 * @Auther: viagra
 * @Date: 2019/11/19 17:32
 * @Description:
 */
public class ReadWriteLock {
    /**
     * 有冲突
     * 当线程想要获取读取锁时:
     * 1.如果有线程正在执行写入,则等待,
     * 2.如果有线程正在执行读取,则无需等待
     * 当线程想要获取写入锁时:
     * 3.如果有线程正在执行写入,则等待
     * 4.如果有线程正在执行读取,则等待
     */
    private int readingReaders = 0; //实际正在读取的线程个数
    private int waitingWriters = 0; //正在等待写入线程的个数
    private int writingWriters = 0; //实际正在写入线程的个数
    private boolean preferWriter = true; //若写入优先,则为true

    /**
     * 读取
     * 如果写入线程大于0或者写入有true且等待的线程大于0,则等待
     *
     * @throws InterruptedException
     */
    public synchronized void readLock() throws InterruptedException {
        while (writingWriters > 0 || (preferWriter && waitingWriters > 0)) {
            wait();
        }
        readingReaders++; //实际正在读取的线程个数加1
    }

    /**
     * 释放读取,读取-1,notifyAll
     */
    public synchronized void readUnLock() {
        readingReaders--; //实际正在读取的线程个数减1
        preferWriter = true;
        notifyAll();
    }

    /**
     * 写入
     * 如果读取线程大于0或者写入线程大于0,则等待
     *
     * @throws InterruptedException
     */
    public synchronized void writeLock() throws InterruptedException {
        waitingWriters++; //正在等待写入的线程数加1
        try {
            while (readingReaders > 0 || writingWriters > 0) {
                wait();
            }
        } finally {
            waitingWriters--; //正在等待写入的线程数减1
        }
        writingWriters++; //实际正在写入的线程加1
    }

    /**
     * 写入释放 -1 ,notifyAll
     */
    public synchronized void writeUnLock() {
        writingWriters--; //正在等待写入的线程数减1
        preferWriter = false;
        notifyAll();
    }
}
