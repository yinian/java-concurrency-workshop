package com.viagra.Single_Threaded_Execution_Pattern.Haved.Lesson3;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Auther: viagra
 * @Date: 2019/11/19 12:26
 * @Description:
 */
public class Gate {
    private int conuter = 0;
    private String name = "Nobody";
    private String address = "Nowhere";
    private final ReentrantLock mutex = new ReentrantLock();;

    /**
     * 加上Mutex
     * @param name
     * @param address
     */
    public void pass(String name, String address){
        mutex.lock();
        try{
            this.conuter++;
            this.name = name;
            this.address = address;
            check();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            mutex.unlock();
        }
    }


    /**
     * 加上Mutex
     * toString返回
     * @return
     */
    public String toString() {
        String s = null;
        mutex.lock();
        try{
            s = "No." + conuter + ": " + name + "," + address;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            mutex.unlock();
        }
        return s;
    }

    /**
     * 检查name和adress的首字母是否项目，不相同则为异常数据
     */
    private void check(){
        if(name.charAt(0) != address.charAt(0)){
            System.out.println("******* 不一致 ********:"+toString());
        }
    }
}
