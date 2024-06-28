package com.ttj.hr.servlets;
import javax.servlet.*;
import javax.servlet.http.*;

import com.ttj.hr.beans.*;

public class Logout extends HttpServlet  {
    public void doPsot(HttpServletRequest request,HttpServletResponse response) {
        try {
            HttpSession session=request.getSession(false);
            RequestDispatcher requestDispatcher=request.getRequestDispatcher("/LoginForm.jsp");
            if(session==null) {
                requestDispatcher.forward(request,response);
            }
            //session.setAttribute("username",null);
            //session.removeAttribute("username");
            session.invalidate();
            requestDispatcher.forward(request,response);
        } catch (Exception exception) {
            System.out.println(exception);
            RequestDispatcher requestDispatcher=request.getRequestDispatcher("ErrorPage.jsp");
            try {
                requestDispatcher.forward(request,response);
            } catch (Exception exception2) {
                // TODO: handle exception
            }
        }
    }
    public void doGet(HttpServletRequest request,HttpServletResponse response) {
        doPsot(request, response);
    }
}