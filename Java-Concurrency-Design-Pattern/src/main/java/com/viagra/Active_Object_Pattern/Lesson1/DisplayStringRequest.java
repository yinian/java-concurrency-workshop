package com.viagra.Active_Object_Pattern.Lesson1;

/**
 * @Auther: viagra
 * @Date: 2019/11/19 20:38
 * @Description:
 */
public class DisplayStringRequest extends MethodRequest<Object> {
    private final String string;

    public DisplayStringRequest(Servant servant, String string) {
        super(servant, null);
        this.string = string;
    }

    public void execute() {
        servant.displayString(string);
    }

}
