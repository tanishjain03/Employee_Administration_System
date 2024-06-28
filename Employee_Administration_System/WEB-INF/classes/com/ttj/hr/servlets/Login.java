package com.ttj.hr.servlets;
import com.ttj.hr.dl.*;
import com.ttj.hr.beans.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Login extends HttpServlet  {
    public void doPost(HttpServletRequest request,HttpServletResponse response) {
        try {
            AdministratorBean administratorBean;
            administratorBean=(AdministratorBean)request.getSession().getAttribute("administratorBean");
            if(administratorBean==null) {
                RequestDispatcher requestDispatcher=request.getRequestDispatcher("/LoginForm.jsp");
                requestDispatcher.forward(request,response);
            }
            String username=administratorBean.getUsername();
            String password=administratorBean.getPassword();
            AdministrattorDAO administrattorDAO=new AdministrattorDAO();
            try {
                AdministratorDTO administrator=administrattorDAO.getByUsername(username);
                String pwd=administrator.getPassword();
                if(pwd.equals(password)==false) {
                    ErrorBean errorBean=new ErrorBean();
                    errorBean.setError("Invalid credentials");
                    request.setAttribute("errorBean",errorBean);
                    RequestDispatcher requestDispatcher=request.getRequestDispatcher("/LoginForm.jsp");
                    requestDispatcher.forward(request,response);
                    return;
                }
                HttpSession session=request.getSession(true);
                session.setAttribute("username",username);
                RequestDispatcher requestDispatcher;
                requestDispatcher=request.getRequestDispatcher("/index.jsp");
                requestDispatcher.forward(request,response);
            } catch (DAOException daoException) {
                ErrorBean errorBean=new ErrorBean();
                errorBean.setError(daoException.getMessage());
                request.setAttribute("errorBean",errorBean);
                RequestDispatcher requestDispatcher=request.getRequestDispatcher("/LoginForm.jsp");
                requestDispatcher.forward(request,response);
            }
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
}
