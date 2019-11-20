package com.viagra.Active_Object_Pattern.Lesson1;

/**
 * @Auther: viagra
 * @Date: 2019/11/19 20:35
 * @Description:
 */
public interface ActiveObject {
    public abstract Result<String> makeString(int count, char fillchar);

    public abstract void displayString(String string);
}
