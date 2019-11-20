package com.viagra.Future_Pattern.Lesson1;

/**
 * @Auther: viagra
 * @Date: 2019/11/19 19:49
 * @Description:
 */
public class FutureData implements Data {
    /**
     * readData字段是用户保存稍后创建完毕的RealData的实例的字段。
     * ready字段是表示是否已经为readData赋值的字段，如果为true，表示已经为realData赋值。
     * setRealData方法是用户将RealData的实例赋值给realData字段的方法
     * setRealData方法会被Host类的request方法创建新的线程调用
     * getContent是用户获取实际数据的方法。
     */
    private RealData realData = null;
    private boolean ready = false;

    public synchronized void setRealData(RealData realData) {
        if (ready) {
            return;
        }
        this.realData = realData;
        this.ready = true;
        notifyAll();
    }

    public synchronized String getContent() {
        while (!ready) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return realData.getContent();
    }
}
