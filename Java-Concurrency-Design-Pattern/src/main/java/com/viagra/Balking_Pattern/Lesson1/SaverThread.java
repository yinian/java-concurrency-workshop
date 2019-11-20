package com.viagra.Balking_Pattern.Lesson1;

import java.io.IOException;

/**
 * @Auther: viagra
 * @Date: 2019/11/19 15:57
 * @Description:
 */
public class SaverThread extends Thread {
    //用户定期保存数据内容
    private final Data data;
    public SaverThread(String name, Data data){
        super(name);
        this.data = data;
    }

    /**
     * 在run方法中，每隔1s就调用一次Data实例的save方法，如果文件操作发生错误，则会抛出异常，线程终止。
     */
    public void run(){
        try{
            while(true){
                data.save();
                Thread.sleep(1000);
            }
        }catch (IOException e){
            e.printStackTrace();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
