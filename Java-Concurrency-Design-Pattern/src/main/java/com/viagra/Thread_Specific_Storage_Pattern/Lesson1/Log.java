package com.viagra.Thread_Specific_Storage_Pattern.Lesson1;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Auther: viagra
 * @Date: 2019/11/19 20:22
 * @Description:
 */
public class Log {
    /**
     * 是用于在名为log.txt的文件中记录程序运行记录的类。
     * writer字段中保存的是用于写文件的java.io.PrintWriter的实例。
     * println方法是用于将通过参数接收到的字符串写入到文件中的方法。
     * close方法是用于关闭日志文件的方法。
     */
    private static PrintWriter writer = null;

    //初始化writer字段
    static {
        try {
            writer = new PrintWriter(new FileWriter("log.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //写日志
    public static void println(String s) {
        writer.println(s);
    }

    //关闭日志
    public static void close() {
        writer.println("===end of log===");
        writer.close();
    }
}
