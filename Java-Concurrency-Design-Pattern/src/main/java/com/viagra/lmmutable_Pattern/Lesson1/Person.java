package com.viagra.lmmutable_Pattern.Lesson1;

/**
 * @Auther: viagra
 * @Date: 2019/11/19 13:31
 * @Description:
 */
//Person声明了final类型，就是表示我们无法创建Person类的子类，虽然这并不是lmmutable模式的必要条件，但却是防止子类修改其字段值的一种措施
public final class Person {
    //字段可见性为private，也就是说，二个字段只能从内部才能访问，这也不是lmmutable模式的必要条件，是防止子类修改其字段值的一种措施
    //另外字段都声明为final，意思是一旦字段被赋值一次，就不会再被赋值，这也不是lmmutable模式的必要条件，只是为了明确编程人员的意图
    private final String name;
    private final String address;

    public Person(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String toString() {
        return "[ Person name = " + name + " , address = " + address + " ]";
    }
}