package com.ttj.hr.tags;
import javax.servlet.jsp.tagext.*;
public class IfTagHandler extends TagSupport {
    private boolean condition;
    public IfTagHandler() {
        reset();
    }
    public void setCondition(boolean condtion) {
        this.condition=condtion;
    }
    public boolean getCondition() {
        return this.condition;
    }
    private void reset() {
        this.condition=false;
    }
    public int doStartTag() {
        if(condition==true) return super.EVAL_BODY_INCLUDE;
        return super.SKIP_BODY;
    }
    public int doEndTag() {
        reset();
        return super.EVAL_PAGE;
    }
}
