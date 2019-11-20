package com.viagra.Future_Pattern.Lesson1;

/**
 * @Auther: viagra
 * @Date: 2019/11/19 19:49
 * @Description:
 */
public interface Data {
    /**
     * Data接口是表示访问数据的方法接口，FurureData类和RealData类实现了该接口
     * @return
     */
    public abstract String getContent();
}
