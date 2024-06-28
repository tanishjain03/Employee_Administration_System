package com.ttj.hr.servlets;
import com.ttj.hr.dl.*;
import com.ttj.hr.beans.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class DeleteDesignation extends HttpServlet {
    public void doGet(HttpServletRequest request,HttpServletResponse response) {
        try {
            DesignationBean designationBean=(DesignationBean)request.getAttribute("designationBean");
            DesignationDAO designationDAO=new DesignationDAO();
            try {
                int code=designationBean.getCode();
                designationDAO.deleteByCode(code);
                MessageBean messageBean=new MessageBean();
                messageBean.setHeading("Designation (Delete Module)");
                messageBean.setMessage("Designation deleted");
                messageBean.setGenerateButtons(true);
                messageBean.setGenerateTwoButtons(false);
                messageBean.setButtonOneText("OK");
                messageBean.setButtonOneAction("Designations.jsp");
                request.setAttribute("messageBean",messageBean);
                RequestDispatcher requestDispatcher=request.getRequestDispatcher("/Notification.jsp");
                requestDispatcher.forward(request,response);
            } catch (DAOException daoException) {
                ErrorBean errorBean=new ErrorBean();
                errorBean.setError(daoException.getMessage());
                request.setAttribute("errorBean",errorBean);
                RequestDispatcher requestDispatcher=request.getRequestDispatcher("/Designations.jsp");
                requestDispatcher.forward(request,response);
            }
        } catch(Exception e) {
            RequestDispatcher requestDispatcher=request.getRequestDispatcher("ErrorPage.jsp");
            try {
                requestDispatcher.forward(request,response);
            } catch (Exception exception2) {
                // TODO: handle exception
            }
        }
    }
    public void doPost(HttpServletRequest request,HttpServletResponse response) {
        doGet(request, response);
    }
}
