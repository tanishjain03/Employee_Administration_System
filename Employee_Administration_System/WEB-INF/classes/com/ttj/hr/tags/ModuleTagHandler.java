package com.ttj.hr.tags;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.*;

public class ModuleTagHandler extends TagSupport {
    private String name;
    public ModuleTagHandler() {
        reset();
    }
    public void setName(String name) {
        this.name=name;
    }
    public String getName() {
        return this.name;
    }
    private void reset() {
        this.name="";
    }
    public int doStartTag() {
        pageContext.setAttribute("HOME",0,PageContext.REQUEST_SCOPE);
        pageContext.setAttribute("DESIGNATION",1,PageContext.REQUEST_SCOPE);
        pageContext.setAttribute("EMPLOYEE",2,PageContext.REQUEST_SCOPE);
        if(name.equalsIgnoreCase("DESIGNATION")) {
            pageContext.setAttribute("module",1,PageContext.REQUEST_SCOPE);
        }
        else if(name.equalsIgnoreCase("EMPLOYEE")) {
            pageContext.setAttribute("module",2,PageContext.REQUEST_SCOPE);
        }
        else if(name.equalsIgnoreCase("HOME")) {
            pageContext.setAttribute("module",0,PageContext.REQUEST_SCOPE);
        }
        else {
            pageContext.setAttribute("module",-1,PageContext.REQUEST_SCOPE);
        }
        return super.SKIP_BODY;
    }
    public int doEndTag() {
        reset();
        return super.EVAL_PAGE;
    }
}
