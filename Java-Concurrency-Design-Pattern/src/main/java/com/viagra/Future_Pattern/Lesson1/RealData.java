package com.viagra.Future_Pattern.Lesson1;

/**
 * @Auther: viagra
 * @Date: 2019/11/19 19:49
 * @Description:
 */
public class RealData implements Data {
    /**
     * 是一个需要花费很长时间才能创建实例的类。
     * getContent方法的处理仅仅是返回content字段的内容
     */
    private final String content;

    public RealData(int count, char c) {
        System.out.println("    making RealData ( " + count + " , " + c + " ) Begin");
        char[] buffer = new char[count];
        for (int i = 0; i < count; i++) {
            buffer[i] = c;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("    making RealData ( " + count + " , " + c + " ) End");
        this.content = new String(buffer);
    }

    public String getContent() {
        return content;
    }
}
