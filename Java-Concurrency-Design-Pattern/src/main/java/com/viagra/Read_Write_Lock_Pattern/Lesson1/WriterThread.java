package com.viagra.Read_Write_Lock_Pattern.Lesson1;

import java.util.Random;

/**
 * @Auther: viagra
 * @Date: 2019/11/19 17:32
 * @Description:
 */
public class WriterThread extends Thread {
    /**
     * 表示是对Data实例写入的操作的线程,filler是一个字符串,程序会逐个读取filler中的字符,并write到Data的实例中.
     * 每写一次,线程就会在0~3000毫秒内随即sleep一段时间,此外nextChar方法用户获取下一次应该写如的字符.
     */
    private final Data data;
    private static final Random random = new Random();
    private final String filler;
    private int index = 0;

    public WriterThread(Data data, String filler) {
        this.data = data;
        this.filler = filler;
    }

    public void run() {
        try {
            while (true) {
                char c = nextChar();
                //System.out.println(Thread.currentThread().getName() + " wwwwwwwwwrite : " + c);
                data.write(c);
                Thread.sleep(random.nextInt(3000));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private char nextChar() {
        char c = filler.charAt(index);
        index++;
        if (index >= filler.length()) {
            index = 0;
        }
        return c;
    }
}
