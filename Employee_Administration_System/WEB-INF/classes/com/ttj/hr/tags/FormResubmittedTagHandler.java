package com.ttj.hr.tags;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.*;
import javax.servlet.http.*;

public class FormResubmittedTagHandler extends TagSupport {
    public FormResubmittedTagHandler() {
        reset();
    }
    private void reset() {
    }
    public int doStartTag() {
        HttpServletRequest request=(HttpServletRequest)pageContext.getRequest();
        String formID=request.getParameter("formID");
        if(formID==null) {
            return super.EVAL_BODY_INCLUDE;
        }
        String formIDINSession=(String)pageContext.getAttribute(formID,PageContext.SESSION_SCOPE);
        if(formIDINSession==null) {
            return super.EVAL_BODY_INCLUDE;
        }
        pageContext.removeAttribute(formID,PageContext.SESSION_SCOPE);
        if(formIDINSession.equals(formID)) {
            return super.SKIP_BODY;
        }
        else {
            return super.EVAL_BODY_INCLUDE;
        }
    }
    public int doEndTag() {
        reset();
        return super.EVAL_PAGE;
    }
}
