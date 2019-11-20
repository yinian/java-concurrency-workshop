package com.viagra.Future_Pattern.Lesson1;

/**
 * @Auther: viagra
 * @Date: 2019/11/19 19:49
 * @Description:
 */
public class Main {
    public static void main(String[] args) {
        /**
         * Main类会调用request方法三次，接着它会接收三个Data（data1、data2、data3）作为返回值
         * 这三个返回值实际上都是FutureData的实例，无需花费时间即可获取它们，类似蛋糕的提货单
         * 然后为了表示Main类去执行了其他操作，我们让其sleep2s，接下来，分别调用之前接收到的返回值data1、data2、data3的getContent方法来获取真正希望获取的结果。
         */
        System.out.println("Main Begin");
        Host host = new Host();
        Data data1 = host.request(10, 'A');
        Data data2 = host.request(20, 'B');
        Data data3 = host.request(30, 'C');
        System.out.println("Main otherJob Begin");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("data1 = " + data1.getContent());
        System.out.println("data2 = " + data2.getContent());
        System.out.println("data3 = " + data3.getContent());
        System.out.println("Main End");
    }
}
