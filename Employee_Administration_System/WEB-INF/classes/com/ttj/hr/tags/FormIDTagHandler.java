package com.ttj.hr.tags;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.*;
import java.io.*;
import java.util.*;

public class FormIDTagHandler extends TagSupport {
    public FormIDTagHandler() {
        reset();
    }
    private void reset() {
    }
    public int doStartTag() {
        String formID=UUID.randomUUID().toString();
        pageContext.setAttribute(formID,formID,PageContext.SESSION_SCOPE);
        JspWriter jw=pageContext.getOut();
        try {
            jw.print("<input type='hidden' id='formID' name='formID' value='"+formID+"'>");
        } catch (IOException e) {
            // do nothing
        }
        return super.SKIP_BODY;
    }
    public int doEndTag() {
        reset();
        return super.EVAL_PAGE;
    }
}
