package com.viagra.Read_Write_Lock_Pattern.Lesson1;

/**
 * @Auther: viagra
 * @Date: 2019/11/19 17:32
 * @Description:
 */
public class Data {
    /**
     * 是可以执行读取read和写入write操作的类
     * buffer对象实际读取的对象是char的数组
     * lock字段保存的是该模式的主角ReadWriteLock的实例
     */
    private final char[] buffer;
    private ReadWriteLock lock = new ReadWriteLock();

    /**
     * 构造函数会根据参数传入的长度来分配一个char数组,并初始化buffer字段,同时以*填满buffer,为初始值.
     *
     * @param size
     */
    public Data(int size) {
        this.buffer = new char[size];
        for (int i = 0; i < buffer.length; i++) {
            buffer[i] = '*';
        }
    }

    /**
     * read方法执行读取操作,实际的读取是通过doRead方法方法执行的,而doRead方法夹在lock.readLock和lock.readUnLock之间
     * read.readLock表示获取用户读取的锁
     * 而read.readUnLock表示释放用户读取的锁,finally操作表示不管遇到啥情况read.readLock都会被调用.
     *
     * @return
     * @throws InterruptedException
     */
    public char[] read() throws InterruptedException {
        lock.readLock();
        try {
            return doRead();
        } finally {
            lock.readUnLock();
        }
    }

    public void write(char c) throws InterruptedException {
        lock.writeLock();
        try {
            doWrite(c);
        } finally {
            lock.writeUnLock();
        }
    }

    /**
     * doRead方法用户执行实际的读取操作,该方法会创建一个新的char数组newbuf来复制buffer字段的内容,并返回newbuf
     *
     * @return
     */
    private char[] doRead() {
        char[] newBuf = new char[buffer.length];
        for (int i = 0; i < buffer.length; i++) {
            newBuf[i] = buffer[i];
        }
        slowly();
        return newBuf;
    }

    /**
     * doWrite方法用于执行实际的写入操作,该方法会以参数传入字符c来填满buffer字段
     *
     * @param c
     */
    private void doWrite(char c) {
        for (int i = 0; i < buffer.length; i++) {
            buffer[i] = c;
            slowly();
        }
    }

    /**
     * 用户辅助模拟消耗的操作
     */
    private void slowly() {
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
