package com.ttj.hr.bl;

import com.ttj.hr.dl.*;
import com.ttj.hr.beans.*;
import java.util.*;
import java.text.*;

public class EmployeeBL {
    public List<EmployeeBean> getAll() {
        List<EmployeeBean> employees=new LinkedList<>();
        try {
            EmployeeDAO employeeDAO=new EmployeeDAO();
            List<EmployeeDTO> dlEmployees=employeeDAO.getAll();
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
            EmployeeBean employeeBean;
            for(EmployeeDTO dlEmployee : dlEmployees) {
                employeeBean=new EmployeeBean();
                employeeBean.setEmployeeId(dlEmployee.getEmployeeId());
                employeeBean.setName(dlEmployee.getName());
                employeeBean.setDesignationCode(dlEmployee.getDesignationCode());
                employeeBean.setDesignation(dlEmployee.getDesignation());
                employeeBean.setDateOfBirth(simpleDateFormat.format(dlEmployee.getDateOfBirth()));
                employeeBean.setGender(dlEmployee.getGender());
                employeeBean.setBasicSalary(dlEmployee.getBasicSalary().toPlainString());
                employeeBean.setIsIndian(dlEmployee.getIsIndian());
                employeeBean.setAadhaarCardNumber(dlEmployee.getAadhaarCardNumber());
                employeeBean.setPANNumber(dlEmployee.getPANNumber());
                employees.add(employeeBean);
            }
        } catch(DAOException daoException) {
            System.out.println(daoException.getMessage()); //to be changed after
        }
        return employees;
    }
}
