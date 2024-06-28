package com.ttj.hr.servlets;

import com.ttj.hr.bl.*;
import com.ttj.hr.beans.*;
import com.ttj.hr.beans.EmployeeBean;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class EmployeesJS extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            PrintWriter pw = response.getWriter();
            response.setContentType("text/javascript");
            // File file=new
            // File("C:\\tomcatr9\\webapps\\styletwo\\WEB-INF\\js\\Employees.js"); Very bad
            // idea
            ServletContext servletContext = getServletContext();
            File file = new File(servletContext.getRealPath("") + File.separator + "WEB-INF" + File.separator + "js" + File.separator + "Employees.js");
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
            while (randomAccessFile.getFilePointer() < randomAccessFile.length()) {
                pw.println(randomAccessFile.readLine());
            }
            randomAccessFile.close();
            List<EmployeeBean> employees = new EmployeeBL().getAll();
            pw.println("var employee;");
            int i = 0;
            // Populating employees in employees array in js
            for (EmployeeBean employeeBean : employees) {
                pw.println("employee = new Employee();");
                pw.println("employee.employeeId = \"" + employeeBean.getEmployeeId() + "\";");
                pw.println("employee.name = \"" + employeeBean.getName() + "\";");
                pw.println("employee.designationCode = " + employeeBean.getDesignationCode() + ";");
                pw.println("employee.designation = \"" + employeeBean.getDesignation() + "\";");
                pw.println("employee.dateOfBirth = \"" + employeeBean.getDateOfBirth() + "\";");
                pw.println("employee.gender = \"" + employeeBean.getGender() + "\";");
                pw.println("employee.isIndian = " + employeeBean.getIsIndian() + ";");
                pw.println("employee.basicSalary = " + employeeBean.getBasicSalary() + ";");
                pw.println("employee.panNumber = \"" + employeeBean.getPANNumber() + "\";");
                pw.println("employee.aadhaarCardNumber = \"" + employeeBean.getAadhaarCardNumber() + "\";");
                pw.println("employees[" + i + "] = employee;");
                i++;
            }
        } catch (Exception exception) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/ErrorPage.jsp");
            try {
                requestDispatcher.forward(request, response);
            } catch (Exception e) {
                // do nothing
            }
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        doGet(request, response);
    }
}
