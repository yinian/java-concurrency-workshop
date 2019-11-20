package com.viagra.Balking_Pattern.Lesson1;

import java.io.IOException;
import java.util.Random;

/**
 * @Auther: viagra
 * @Date: 2019/11/19 15:58
 * @Description:
 */
public class ChangerThread extends Thread {
    //用户修改数据内容，并执行保存处理
    private final Data data;
    private final Random random = new Random();
    public ChangerThread(String name, Data data){
        super(name);
        this.data = data;
    }

    /**
     * 启动的线程以“No.0”,“No.1”,“No.2”,字符串作为参数，循环调用change方法来修改数据，没修改完一个数据，线程都会“执行点别的操作”，然后调用save方法保存数据。这里用来表示“将刚才修改的数据反映到文件中”
     * 注意Data类的doSave方法每次都是重新创建文件，由于在重新创建后，文件内容将全部消失，所以该程序并不能直接用作应用程序的自动保存功能，除非备份。
     */
    public void run(){
        try{
            for(int i=0;true;i++){
                data.change("No." + i); //修改数据
                Thread.sleep(random.nextInt(1000)); //执行其他操作
                data.save();
            }
        }catch (IOException e){
            e.printStackTrace();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
