package com.viagra.Thread_Per_Message_Pattern.Lesson1;

/**
 * @Auther: viagra
 * @Date: 2019/11/19 18:43
 * @Description:
 */
public class Main {
    /**
     * 先创建一个Host类的实例，然后调用Host的request方法。
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Main -- BEGIN ");
        Host host = new Host();
        host.request(10, 'A');
        host.request(20, 'B');
        host.request(30, 'C');
        System.out.println("Main -- END ");
    }
}
