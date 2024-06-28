package com.ttj.hr.tags;

import java.io.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.*;
import java.util.*;
import java.lang.reflect.*;

public class EntityListTagHandler extends BodyTagSupport {
    private List list;
    private String populatorClass;
    private String populatorMethod;
    private String name;
    private int index;

    public EntityListTagHandler() {
        reset();
    }

    public void setPopulatorClass(java.lang.String populatorClass) {
        this.populatorClass = populatorClass;
    }

    public java.lang.String getPopulatorClass() {
        return this.populatorClass;
    }

    public void setPopulatorMethod(java.lang.String populatorMethod) {
        this.populatorMethod = populatorMethod;
    }

    public java.lang.String getPopulatorMethod() {
        return this.populatorMethod;
    }

    public void setName(java.lang.String name) {
        this.name = name;
    }

    public java.lang.String getName() {
        return this.name;
    }
    
    private void reset() {
        this.list = null;
        this.populatorClass = null;
        this.populatorMethod = null;
        this.name = null;
        this.index = 0;
    }

    public int doStartTag() {
        try {
            if(name==null || name.trim().length()==0) return super.SKIP_BODY;
            Class c=Class.forName(populatorClass);
            Object obj=c.newInstance();
            Class parameters[]=new Class[0];
            Method method = c.getMethod(populatorMethod,parameters);
            list=(List)method.invoke(obj);
            if(list==null || list.size()==0) return super.SKIP_BODY;
            Object bean=list.get(index);
            pageContext.setAttribute(name,bean,PageContext.REQUEST_SCOPE);
            pageContext.setAttribute("serialNumber",index+1,PageContext.REQUEST_SCOPE);
            index++;
            return super.EVAL_BODY_INCLUDE;
        } catch (Throwable t) {
            //some logging act shoud be done later on (in next style) 
            return super.SKIP_BODY;
        }
    }

    public int doAfterBody() {
        if(index==list.size()) return super.SKIP_BODY;
        Object bean=list.get(index);
        pageContext.setAttribute(name,bean,PageContext.REQUEST_SCOPE);
        pageContext.setAttribute("serialNumber",index+1,PageContext.REQUEST_SCOPE);
        index++;
        return super.EVAL_BODY_AGAIN;
    }

    public int doEndTag() {
        reset();
        return super.EVAL_PAGE;
    }
}
