package com.viagra.Thread_Per_Message_Pattern.Lesson1;

/**
 * @Auther: viagra
 * @Date: 2019/11/19 18:43
 * @Description:
 */
public class Helper {
    /**
     * 提供了一个用户按指定次数显示字符的handle方法，另外为了延缓显示速度，在slowly方法中使用了Thread.sleep方法。
     *
     * @param count
     * @param c
     */
    public void handle(int count, char c) {
        System.out.println(" handle (" + count + ", " + c + ") BEGIN");
        for (int i = 0; i < count; i++) {
            slowly();
            System.out.println(c);
        }
        System.out.println("~~~~~~~~~~~");
        System.out.println(" handle (" + count + ", " + c + ") END");
    }

    public void slowly() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
