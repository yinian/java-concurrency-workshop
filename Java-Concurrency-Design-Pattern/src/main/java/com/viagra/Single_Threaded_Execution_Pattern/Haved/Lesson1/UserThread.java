package com.viagra.Single_Threaded_Execution_Pattern.Haved.Lesson1;


/**
 * @Auther: viagra
 * @Date: 2019/11/19 11:13
 * @Description:
 */
public class UserThread extends Thread {
    private final Gate gate;
    private final String myname;
    private final String myaddress;

    public UserThread(Gate gate, String myname, String myaddress) {
        super();
        this.gate = gate;
        this.myname = myname;
        this.myaddress = myaddress;
    }

    public void run() {
        System.out.println(myname + " 开始:");
        while (true) {
            gate.pass(myname, myaddress);
        }
    }
}
