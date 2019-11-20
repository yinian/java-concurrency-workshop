package com.viagra.Future_Pattern.Lesson1;

/**
 * @Auther: viagra
 * @Date: 2019/11/19 19:49
 * @Description:
 */
public class Host {
    /**
     * 首先会创建FutureData的实例，接着启动一个新的线程并在新线程中创建RealData的实例。
     * 执行request的线程会做一下三件事情：
     * 1.创建FutureData的实例
     * 2.启动一个新线程，用户创建RealData的实例
     * 3.将FutureData的实例作为返回值返回给调用者
     * @param count
     * @param c
     * @return
     */
    public Data request(final int count, final char c) {
        System.out.println("    request( " + count + " , " + c + " ), Begin");
        //（1）创建FutureData的实例
        final FutureData future = new FutureData();

        //（2）启动一个新线程，用户创建RealData的实例
        new Thread(){
            public void run(){
                RealData realData = new RealData(count ,c);
                future.setRealData(realData);
            }
        }.start();
        System.out.println("    request( " + count + " , " + c + " ) End");

        //（3）返回FutureData的实例
        return future;
    }
}
