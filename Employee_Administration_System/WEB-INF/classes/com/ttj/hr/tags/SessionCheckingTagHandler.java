package com.ttj.hr.tags;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.*;
import javax.servlet.http.*;
//it's also GuardTagHandler
public class SessionCheckingTagHandler extends TagSupport {
    public SessionCheckingTagHandler() {
        reset();
    }
    private void reset() {
    }
    public int doStartTag() {
        HttpServletRequest request=(HttpServletRequest)pageContext.getRequest();
        HttpSession session=request.getSession(false);
        if(session==null) {
            return super.EVAL_BODY_INCLUDE;
        }
        String uname=(String)session.getAttribute("username");
        if(uname==null || uname.length()==0) {
            return super.EVAL_BODY_INCLUDE;
        }
        return super.SKIP_BODY;
    }
    public int doEndTag() {
        reset();
        return EVAL_PAGE;
    }
}
