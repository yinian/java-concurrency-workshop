package com.viagra.Active_Object_Pattern.Lesson2;

import java.util.concurrent.Future;

/**
 * @Auther: viagra
 * @Date: 2019/11/19 20:44
 * @Description:
 */
public interface ActiveObject {
    public abstract Future<String> makeString(int count, char fillchar);

    public abstract void displayString(String string);

    public abstract void shutdown();
}
