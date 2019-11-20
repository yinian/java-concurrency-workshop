package com.viagra.Single_Threaded_Execution_Pattern.Haved.Lesson1;

/**
 * @Auther: viagra
 * @Date: 2019/11/19 11:18
 * @Description:
 */
public class Gate {
    private int conuter = 0;
    private String name = "Nobody";
    private String address = "Nowhere";

    /**
     * 加上synchronized
     *
     * @param name
     * @param address
     */
    public synchronized void pass(String name, String address) {
        this.conuter++;
        this.name = name;
        this.address = address;
        check();
    }

    /**
     * 加上synchronized
     * toString返回
     *
     * @return
     */
    public synchronized String toString() {
        return "No." + conuter + ": " + name + "," + address;
    }

    /**
     * 检查name和adress的首字母是否项目，不相同则为异常数据
     */
    private void check() {
        if (name.charAt(0) != address.charAt(0)) {
            System.out.println("******* 不一致 ********:" + toString());
        }
    }


}
