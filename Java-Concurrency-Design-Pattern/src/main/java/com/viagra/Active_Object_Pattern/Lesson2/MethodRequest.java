package com.viagra.Active_Object_Pattern.Lesson2;

import com.viagra.Active_Object_Pattern.Lesson1.FutureResult;
import com.viagra.Active_Object_Pattern.Lesson1.Servant;

/**
 * @Auther: viagra
 * @Date: 2019/11/19 20:37
 * @Description:
 */
abstract class MethodRequest<T> {
    protected final Servant servant;
    protected final FutureResult<T> future;

    protected MethodRequest(Servant servant, FutureResult<T> future) {
        this.servant = servant;
        this.future = future;
    }

    public abstract void execute();
}
