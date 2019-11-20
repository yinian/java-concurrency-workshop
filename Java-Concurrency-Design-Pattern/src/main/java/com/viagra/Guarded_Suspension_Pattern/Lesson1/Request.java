package com.viagra.Guarded_Suspension_Pattern.Lesson1;

/**
 * @Auther: viagra
 * @Date: 2019/11/19 14:58
 * @Description:
 */
public class Request {
    private final String name;

    public Request(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return " [ Request " + name + " ] ";
    }
}
