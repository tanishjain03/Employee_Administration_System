package com.ttj.hr.servlets;
import com.ttj.hr.dl.*;
import com.ttj.hr.beans.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class ConfirmDeleteDesignation extends HttpServlet {
    public void doGet(HttpServletRequest request,HttpServletResponse response) {
        try {
            try {
                int code=Integer.parseInt(request.getParameter("code"));
                DesignationDAO designationDAO=new DesignationDAO();
                DesignationDTO designation=designationDAO.getByCode(code);
                DesignationBean designationBean=new DesignationBean();
                designationBean.setCode(code);
                designationBean.setTitle(designation.getTitle());
                request.setAttribute("designationBean",designationBean);
                RequestDispatcher requestDispatcher=request.getRequestDispatcher("/ConfirmDeleteDesignation.jsp");
                requestDispatcher.forward(request,response);
            } catch (NumberFormatException numberFormatException) {
                RequestDispatcher requestDispatcher=request.getRequestDispatcher("/Designations.jsp");
                requestDispatcher.forward(request,response);
            } catch (DAOException daoException) {
                RequestDispatcher requestDispatcher=request.getRequestDispatcher("/Designations.jsp");
                requestDispatcher.forward(request,response);
            }
        } catch(Exception e2) {
            RequestDispatcher requestDispatcher=request.getRequestDispatcher("ErrorPage.jsp");
            try {
                requestDispatcher.forward(request,response);
            } catch (Exception exception2) {
                // TODO: handle exception
            }
        }
    }
    public void doPost(HttpServletRequest request,HttpServletResponse response) {
        doGet(request,response);
    }
}