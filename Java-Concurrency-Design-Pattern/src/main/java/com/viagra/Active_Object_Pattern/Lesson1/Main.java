package com.viagra.Active_Object_Pattern.Lesson1;

/**
 * @Auther: viagra
 * @Date: 2019/11/19 20:35
 * @Description:
 */
public class Main {
    public static void main(String[] args) {
        ActiveObject activeObject = ActiveObjectFactory.createActiveObject();
        new MakerClientThread("Steve Nash", activeObject).start();
        new MakerClientThread("Michael Jordan", activeObject).start();
        new DisplayClientThread("Ronaldo", activeObject).start();
    }
}
