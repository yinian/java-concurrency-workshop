package com.leonardoz.patterns.thread_safe.shared_state;

import com.leonardoz.patterns.thread_safe.GuardedBy;
import com.leonardoz.patterns.thread_safe.ThreadSafe;

/**
 * @Auther: viagra
 * @Date: 2019/11/14 16:17
 * @Description:
 */
@ThreadSafe
public class ProtectedSharedState {

    @GuardedBy("this")
    private Object state;

    @GuardedBy("this")
    private Object state2;

    public synchronized Object getState() {
        return state;
    }

    public synchronized void setState(Object state) {
        this.state = state;
    }

    public synchronized Object getState2() {
        return state2;
    }

    public synchronized void setState2(Object state2) {
        this.state2 = state2;
    }

}
