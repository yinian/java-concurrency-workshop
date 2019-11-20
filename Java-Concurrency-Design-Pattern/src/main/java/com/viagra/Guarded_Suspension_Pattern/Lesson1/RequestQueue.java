package com.viagra.Guarded_Suspension_Pattern.Lesson1;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Auther: viagra
 * @Date: 2019/11/19 14:58
 * @Description:
 */
public class RequestQueue {
    private final Queue<Request> queue = new LinkedList<Request>();

    /**
     * 首先，我们来思考一下getRequest方法应该执行的目标处理是什么？目的是“从quene中取出一个Request实例”，也就是执行下面这条语句
     * queue.remove()
     * 但是，为了安全地执行这条语句，必须满足如下条件：
     * quene.peek()!=null  //守护条件
     *
     * 仔细查看那getRequest中的while语句的条件表达式，你会发现while语句的条件表达式是守护条件的逻辑非运算，这条while语句会保证在remove方法被调用时，守护条件一定是成立的
     * 当守护条件的逻辑非运算满足时，也就是说守护条件不成立时，绝对不会继续执行while之后的语句。
     *
     * 不等待的情况和等待的情况
     * 当线程执行到while语句时，需要考虑守护条件成立与补成立这二种情况
     * 1.当守护条件成立时(queue.peek()!=null)，线程不会进入while内，而是执行while的下一条语句，调用remove()方法，这时不会执行到wait，所以线程也不会等待。
     * 2.当守护条件不成立时(queue.peek()==null)，线程会进入while语句内，执行wait，开始等待。
     *
     * 执行wait
     * 开始等待，那到底等待什么？
     * “当然是等到notify/notifyAll”，当守护条件成立时(quene.peek()!=null)，正在wait的线程希望被notify/notifyAll，因为只有在这时，while后面的语句才能够执行。
     *
     * @return
     */
    public synchronized Request getRequest() {
        while (queue.peek() == null) {
            try {
                wait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return queue.remove();
    }

    /**
     * 处理执行offer方法 ，向queue的末尾添加一个请求（request），这时，queue中至少存在一个可供取出的元素，因此，下面的表达式为真。
     * queue.peek()!=null
     * 在上面的getRequest中，正在wait的线程等待的是什么呢？正是这个条件，即守护条件的成立，那么，这里就来执行nofityAll吧。
     * @param request
     */
    public synchronized void putRequest(Request request) {
        queue.offer(request);
        notifyAll();
    }
    /**
     * synchronized保护的是queue字段（LinkedList实例），getRequest方法中的二个处理就必须确保同时“只能由一个线程执行”。
     * 1.判断queue字段中是否存在可供取出的元素
     * 2.从queue字段中取出一个元素
     */

    /**
     * wait与锁
     * 假设线程要执行某个实例的wait方法，这时，线程必须获取该实例的锁，上面的synchronized方法中，wait方法被调用时，获取的就是this的锁
     * 线程执行this的wait方法后，进入this的等待队列，并释放持有的this锁。
     * notify、notifyAll或interrupt会让线程退出等待队列，但是实际地继续执行处理之前，还必须再获取this的锁。
     */
}
