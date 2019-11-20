package com.viagra.Single_Threaded_Execution_Pattern.Not_Haved;

/**
 * @Auther: viagra
 * @Date: 2019/11/19 11:11
 * @Description:
 */
public class Gate {
    protected int conuter = 0;
    protected String name = "Nobody";
    protected String address = "Nowhere";

    /**
     * 非安全线程
     * @param name
     * @param address
     */
    public void pass(String name, String address) {
        this.conuter++;
        this.name = name;
        this.address = address;
        check();
    }

    /**
     * toString返回
     * @return
     */
    public String toString() {
        return "No." + conuter + ": " + name + "," + address;
    }

    /**
     * 检查name和adress的首字母是否项目，不相同则为异常数据
     */
    private void check(){
        if(name.charAt(0) != address.charAt(0)){
            System.out.println("******* 不一致 ********:"+toString());
        }
    }
}
