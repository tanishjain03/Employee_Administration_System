package com.ttj.hr.servlets;

import com.ttj.hr.dl.*;
import com.ttj.hr.beans.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class EditDesignation extends HttpServlet {
    public void doGet(HttpServletRequest request,HttpServletResponse response) {
        try {
            int code=Integer.parseInt(request.getParameter("code"));
            DesignationDAO designationDAO=new DesignationDAO();
            try {
                DesignationDTO designation=designationDAO.getByCode(code);
                DesignationBean designationBean=new DesignationBean();
                designationBean.setCode(code);
                designationBean.setTitle(designation.getTitle());
                request.setAttribute("designationBean",designationBean);
                RequestDispatcher requestDispatcher=request.getRequestDispatcher("/DesignationEditForm.jsp");
                requestDispatcher.forward(request,response);
            } catch (DAOException daoException) {
                RequestDispatcher requestDispatcher=request.getRequestDispatcher("/Designations.jsp");
                try {
                    requestDispatcher.forward(request,response);
                } catch(Exception e1) {
                    //do nothing
                }
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
        doGet(request, response);
    }
}
