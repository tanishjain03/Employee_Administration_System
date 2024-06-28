package com.ttj.hr.servlets;

import com.ttj.hr.dl.*;
import com.ttj.hr.beans.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class NotifyFormResubmission extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            MessageBean messageBean = new MessageBean();
            messageBean.setHeading("Notification");
            messageBean.setMessage("Forms are not to be resubmitted");
            messageBean.setGenerateButtons(true);
            messageBean.setGenerateTwoButtons(false);
            messageBean.setButtonOneText("OK");
            messageBean.setButtonOneAction("Designations.jsp");
            request.setAttribute("messageBean", messageBean);
            RequestDispatcher requestDispatcher;
            requestDispatcher = request.getRequestDispatcher("/Notification.jsp");
            requestDispatcher.forward(request, response);
        } catch (Exception exception) {
            System.out.println(exception);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("ErrorPage.jsp");
            try {
                requestDispatcher.forward(request, response);
            } catch (Exception exception2) {
                // do nothing
            }
        }
    }
}
