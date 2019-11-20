package com.viagra.Active_Object_Pattern.Lesson2;

/**
 * @Auther: viagra
 * @Date: 2019/11/19 20:44
 * @Description:
 */
public class ActiveObjectFactory {
    public static ActiveObject createActiveObject() {
        return new AcitveObjectImpl();
    }
}
