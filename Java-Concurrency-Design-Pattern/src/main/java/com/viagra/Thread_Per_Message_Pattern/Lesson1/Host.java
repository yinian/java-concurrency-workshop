package com.viagra.Thread_Per_Message_Pattern.Lesson1;

/**
 * @Auther: viagra
 * @Date: 2019/11/19 18:43
 * @Description:
 */
public class Host {
    /**
     * Host类的request方法新启动了一个线程，实际操作将由该线程执行。
     * 使用java的匿名内部类来创建Thread的子类实例，并使用该实例来启动线程。
     */
    private final Helper helper = new Helper();

    public void request(final int count, final char c) {
        System.out.println(" request( " + count + ", " + c + ") BEGIN");
        /**
         * 匿名内部类的run方法使用了request方法的参数count和c，
         * 当匿名内部类用到方法的参数或局部变量时，这些变量就必须声明为final，如果count和c不声明为final，程序会出现编译错误。
         */
        new Thread() {
            public void run() {
                helper.handle(count, c);
            }
        }.start();
        System.out.println(" request( " + count + ", " + c + ") END");
    }
}
