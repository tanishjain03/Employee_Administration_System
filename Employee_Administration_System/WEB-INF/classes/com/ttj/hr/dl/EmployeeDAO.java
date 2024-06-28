package com.ttj.hr.dl;
import java.sql.*;
import java.util.*;
import java.math.*;

public class EmployeeDAO {
    public List<EmployeeDTO> getAll() throws DAOException {
        List<EmployeeDTO> employees;
        employees=new LinkedList<>();
        try {
            Connection connection=DAOConnection.getConnection();
            Statement statement;
            statement=connection.createStatement();
            ResultSet resultSet;
            resultSet=statement.executeQuery("select employee.id,employee.name,employee.designation_code,designation.title,employee.date_of_birth,employee.gender,employee.is_indian,employee.basic_salary,employee.pan_number,employee.aadhaar_card_number from employee inner join designation on employee.designation_code=designation.code order by employee.name");
            EmployeeDTO employeeDTO;
            int id;
            String name;
            int designationCode;
            String title;
            java.sql.Date dateOfBirth;
            String gender;
            boolean isIndian;
            BigDecimal basicSalary;
            String panNumber;
            String aadhaarCardNumber;
            while(resultSet.next()) {
                id=resultSet.getInt("id");
                name=resultSet.getString("name").trim();
                designationCode=resultSet.getInt("designation_code");
                title=resultSet.getString("title").trim();
                dateOfBirth=resultSet.getDate("date_of_birth");
                gender=resultSet.getString("gender");
                isIndian=resultSet.getBoolean("is_indian");
                basicSalary=resultSet.getBigDecimal("basic_salary");
                panNumber=resultSet.getString("pan_number").trim();
                aadhaarCardNumber=resultSet.getString("aadhaar_card_number").trim();
                employeeDTO=new EmployeeDTO();
                employeeDTO.setEmployeeId("A"+id);
                employeeDTO.setName(name);
                employeeDTO.setDesignationCode(designationCode);
                employeeDTO.setDesignation(title);
                employeeDTO.setDateOfBirth(dateOfBirth);
                employeeDTO.setGender(gender);
                employeeDTO.setIsIndian(isIndian);
                employeeDTO.setBasicSalary(basicSalary);
                employeeDTO.setPANNumber(panNumber);
                employeeDTO.setAadhaarCardNumber(aadhaarCardNumber);
                employees.add(employeeDTO);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            throw new DAOException(e.getMessage());
        }
        return employees;
    }
    public void add(EmployeeDTO employee) throws DAOException {
        try {
            String panNumber=employee.getPANNumber();
            String aadhaarCardNumber=employee.getAadhaarCardNumber();
            Connection connection=DAOConnection.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("select id from employee where pan_number=?");
            preparedStatement.setString(1, panNumber);;
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()) {
                resultSet.close();
                preparedStatement.close();
                connection.close();
                throw new DAOException("PAN number : "+panNumber+" exists.");
            }
            resultSet.close();;
            preparedStatement.close();
            preparedStatement=connection.prepareStatement("select id from employee where aadhaar_card_number=?");
            preparedStatement.setString(1, aadhaarCardNumber);
            resultSet=preparedStatement.executeQuery();
            if(resultSet.next()) {
                resultSet.close();
                preparedStatement.close();
                connection.close();
                throw new DAOException("Aadhaar card number : "+aadhaarCardNumber+" exists.");
            }
            resultSet.close();
            preparedStatement.close();
            preparedStatement=connection.prepareStatement("insert into employee (name,designation_code,date_of_birth,gender,is_indian,basic_salary,pan_number,aadhaar_card_number) values (?,?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setInt(2, employee.getDesignationCode());
            java.util.Date dateOfBirth=employee.getDateOfBirth();
            java.sql.Date sqlDateOfBirth=new java.sql.Date(dateOfBirth.getYear(), dateOfBirth.getMonth(), dateOfBirth.getDate());
            preparedStatement.setDate(3, sqlDateOfBirth);
            preparedStatement.setString(4, employee.getGender());
            preparedStatement.setBoolean(5, employee.getIsIndian());
            preparedStatement.setBigDecimal(6, employee.getBasicSalary());
            preparedStatement.setString(7, panNumber);
            preparedStatement.setString(8, aadhaarCardNumber);
            preparedStatement.executeUpdate();
            resultSet=preparedStatement.getGeneratedKeys();
            resultSet.next();
            int id=resultSet.getInt(1);
            employee.setEmployeeId("A"+id);
            resultSet.close();;
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        }
    }
    public boolean panNumberExists(String panNumber) throws DAOException {
        boolean exists=false;
        try {
            Connection connection=DAOConnection.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("select gender from employee where pan_number=?");
            preparedStatement.setString(1, panNumber);
            ResultSet resultSet=preparedStatement.executeQuery();
            exists=resultSet.next();
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            throw new DAOException(e.getMessage());
        }
        return exists;
    }
    public boolean aadhaarCardNumberExists(String aadhaarCardNumber) throws DAOException {
        boolean exists=false;
        try {
            Connection connection=DAOConnection.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("select gender from employee where aadhaar_card_number=?");
            preparedStatement.setString(1, aadhaarCardNumber);
            ResultSet resultSet=preparedStatement.executeQuery();
            exists=resultSet.next();
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            throw new DAOException(e.getMessage());
        }
        return exists;
    }
    public void deleteByEmployeeId(String employeeId) throws DAOException {
        int actualEmployeeId=0;
        try {
            actualEmployeeId=Integer.parseInt(employeeId.substring(1));
        } catch (Exception e) {
            throw new DAOException("Invalid employee id : "+employeeId);
        }
        try {
            Connection connection=DAOConnection.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("select gender from employee where id=?");
            preparedStatement.setInt(1,actualEmployeeId);
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()==false) {
                resultSet.close();
                preparedStatement.close();
                connection.close();
                throw new DAOException("Invalid employee id : "+employeeId);
            }
            resultSet.close();
            preparedStatement.close();
            preparedStatement=connection.prepareStatement("delete from employee where id=?");
            preparedStatement.setInt(1, actualEmployeeId);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            throw new DAOException(e.getMessage());
        }
    }
    public EmployeeDTO getByEmployeeId(String employeeId) throws DAOException {
        EmployeeDTO employeeDTO=null;
        int actualEmployeeId=0;
        try {
            actualEmployeeId=Integer.parseInt(employeeId.substring(1));
        } catch (Exception e) {
            throw new DAOException("Invalid employee id : "+employeeId);
        }
        try {
            Connection connection=DAOConnection.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("select employee.id,employee.name,employee.designation_code,designation.title,employee.date_of_birth,employee.gender,employee.is_indian,employee.basic_salary,employee.pan_number,employee.aadhaar_card_number from employee inner join designation on employee.designation_code=designation.code and id=?");
            preparedStatement.setInt(1,actualEmployeeId);
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()==false) {
                resultSet.close();
                preparedStatement.close();
                connection.close();
                throw new DAOException("Invalid employee id : "+employeeId);
            }
            employeeDTO=new EmployeeDTO();
            int id;
            String name;
            int designationCode;
            String title;
            java.sql.Date dateOfBirth;
            String gender;
            boolean isIndian;
            BigDecimal basicSalary;
            String panNumber;
            String aadhaarCardNumber;
            id=resultSet.getInt("id");
            name=resultSet.getString("name");
            designationCode=resultSet.getInt("designation_code");
            title=resultSet.getString("title");
            dateOfBirth=resultSet.getDate("date_of_birth");
            gender=resultSet.getString("gender");
            isIndian=resultSet.getBoolean("is_indian");
            basicSalary=resultSet.getBigDecimal("basic_salary");
            panNumber=resultSet.getString("pan_number").trim();
            employeeDTO=new EmployeeDTO();
            employeeDTO.setEmployeeId("A"+id);
            aadhaarCardNumber=resultSet.getString("aadhaar_card_number").trim();
            employeeDTO=new EmployeeDTO();
            employeeDTO.setEmployeeId("A"+id);
            employeeDTO.setName(name);
            employeeDTO.setDesignationCode(designationCode);
            employeeDTO.setDesignation(title);
            employeeDTO.setDateOfBirth(dateOfBirth);
            employeeDTO.setGender(gender);
            employeeDTO.setIsIndian(isIndian);
            employeeDTO.setBasicSalary(basicSalary);
            employeeDTO.setPANNumber(panNumber);
            employeeDTO.setAadhaarCardNumber(aadhaarCardNumber);
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            throw new DAOException(e.getMessage());
        }
        return employeeDTO;
    }
    public void update(EmployeeDTO employee) throws DAOException {
        try {
            String employeeId=employee.getEmployeeId();
            int actualEmployeeId=0;
            try {
                actualEmployeeId=Integer.parseInt(employeeId.substring(1));
            } catch (NumberFormatException nfe) {
                throw new DAOException("Inavlid employee id : "+employeeId);
            }
            Connection connection=DAOConnection.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("select gender from employee where id=?");
            preparedStatement.setInt(1, actualEmployeeId);
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()==false) {
                resultSet.close();
                preparedStatement.close();
                connection.close();
                throw new DAOException("Inavlid employee id : "+employeeId);
            }
            resultSet.close();
            preparedStatement.close();
            String panNumber=employee.getPANNumber();
            String aadhaarCardNumber=employee.getAadhaarCardNumber();
            preparedStatement=connection.prepareStatement("select id from employee where pan_number=? and id<>?");
            preparedStatement.setString(1, panNumber);;
            preparedStatement.setInt(2, actualEmployeeId);
            resultSet=preparedStatement.executeQuery();
            if(resultSet.next()) {
                resultSet.close();
                preparedStatement.close();
                connection.close();
                throw new DAOException("PAN number : "+panNumber+" exists.");
            }
            resultSet.close();
            preparedStatement.close();
            preparedStatement=connection.prepareStatement("select id from employee where aadhaar_card_number=? and id<>?");
            preparedStatement.setString(1, aadhaarCardNumber);
            preparedStatement.setInt(2, actualEmployeeId);
            resultSet=preparedStatement.executeQuery();
            if(resultSet.next()) {
                resultSet.close();
                preparedStatement.close();
                connection.close();
                throw new DAOException("Aadhaar card number : "+aadhaarCardNumber+" exists.");
            }
            resultSet.close();
            preparedStatement.close();
            preparedStatement=connection.prepareStatement("update employee set name=?,designation_code=?,date_of_birth=?,gender=?,is_indian=?,basic_salary=?,pan_number=?,aadhaar_card_number=? where id=?",Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setInt(2, employee.getDesignationCode());
            java.util.Date dateOfBirth=employee.getDateOfBirth();
            java.sql.Date sqlDateOfBirth=new java.sql.Date(dateOfBirth.getYear(), dateOfBirth.getMonth(), dateOfBirth.getDate());
            preparedStatement.setDate(3, sqlDateOfBirth);
            preparedStatement.setString(4, employee.getGender());
            preparedStatement.setBoolean(5, employee.getIsIndian());
            preparedStatement.setBigDecimal(6, employee.getBasicSalary());
            preparedStatement.setString(7, panNumber);
            preparedStatement.setString(8, aadhaarCardNumber);
            preparedStatement.setInt(9, actualEmployeeId);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        }
    }
    public boolean employeeIdExists(String employeeId) throws DAOException {
        boolean exists=false;
        int actualEmployeeId=0;
        try {
            actualEmployeeId=Integer.parseInt(employeeId.substring(1));
        } catch (Exception e) {
            return false;
        }
        try {
            Connection connection=DAOConnection.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("select gender from employee where id=?");
            preparedStatement.setInt(1,actualEmployeeId);
            ResultSet resultSet=preparedStatement.executeQuery();
            exists=resultSet.next();
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            throw new DAOException(e.getMessage());
        }
        return exists;
    }
    public EmployeeDTO getByPANNumber(String panNumber) throws DAOException {
        EmployeeDTO employeeDTO=null;
        try {
            Connection connection=DAOConnection.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("select employee.id,employee.name,employee.designation_code,designation.title,employee.date_of_birth,employee.gender,employee.is_indian,employee.basic_salary,employee.pan_number,employee.aadhaar_card_number from employee inner join designation on employee.designation_code=designation.code and pan_number=?");
            preparedStatement.setString(1,panNumber);
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()==false) {
                resultSet.close();
                preparedStatement.close();
                connection.close();
                throw new DAOException("Invalid PAN Number : "+panNumber);
            }
            employeeDTO=new EmployeeDTO();
            int id;
            String name;
            int designationCode;
            String title;
            java.sql.Date dateOfBirth;
            String gender;
            boolean isIndian;
            BigDecimal basicSalary;
            String aadhaarCardNumber;
            id=resultSet.getInt("id");
            name=resultSet.getString("name");
            designationCode=resultSet.getInt("designation_code");
            title=resultSet.getString("title");
            dateOfBirth=resultSet.getDate("date_of_birth");
            gender=resultSet.getString("gender");
            isIndian=resultSet.getBoolean("is_indian");
            basicSalary=resultSet.getBigDecimal("basic_salary");
            panNumber=resultSet.getString("pan_number").trim();
            employeeDTO=new EmployeeDTO();
            employeeDTO.setEmployeeId("A"+id);
            aadhaarCardNumber=resultSet.getString("aadhaar_card_number").trim();
            employeeDTO=new EmployeeDTO();
            employeeDTO.setEmployeeId("A"+id);
            employeeDTO.setName(name);
            employeeDTO.setDesignationCode(designationCode);
            employeeDTO.setDesignation(title);
            employeeDTO.setDateOfBirth(dateOfBirth);
            employeeDTO.setGender(gender);
            employeeDTO.setIsIndian(isIndian);
            employeeDTO.setBasicSalary(basicSalary);
            employeeDTO.setPANNumber(panNumber);
            employeeDTO.setAadhaarCardNumber(aadhaarCardNumber);
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            throw new DAOException(e.getMessage());
        }
        return employeeDTO;
    }
    public EmployeeDTO getByAadhaarCardNumber(String aadhaarCardNumber) throws DAOException {
        EmployeeDTO employeeDTO=null;
        try {
            Connection connection=DAOConnection.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("select employee.id,employee.name,employee.designation_code,designation.title,employee.date_of_birth,employee.gender,employee.is_indian,employee.basic_salary,employee.pan_number,employee.aadhaar_card_number from employee inner join designation on employee.designation_code=designation.code and aadhaar_card_number=?");
            preparedStatement.setString(1,aadhaarCardNumber);
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()==false) {
                resultSet.close();
                preparedStatement.close();
                connection.close();
                throw new DAOException("Invalid Aadhaar card number : "+aadhaarCardNumber);
            }
            employeeDTO=new EmployeeDTO();
            int id;
            String name;
            int designationCode;
            String title;
            java.sql.Date dateOfBirth;
            String gender;
            boolean isIndian;
            BigDecimal basicSalary;
            String panNumber;
            id=resultSet.getInt("id");
            name=resultSet.getString("name");
            designationCode=resultSet.getInt("designation_code");
            title=resultSet.getString("title");
            dateOfBirth=resultSet.getDate("date_of_birth");
            gender=resultSet.getString("gender");
            isIndian=resultSet.getBoolean("is_indian");
            basicSalary=resultSet.getBigDecimal("basic_salary");
            panNumber=resultSet.getString("pan_number").trim();
            employeeDTO=new EmployeeDTO();
            employeeDTO.setEmployeeId("A"+id);
            aadhaarCardNumber=resultSet.getString("aadhaar_card_number").trim();
            employeeDTO=new EmployeeDTO();
            employeeDTO.setEmployeeId("A"+id);
            employeeDTO.setName(name);
            employeeDTO.setDesignationCode(designationCode);
            employeeDTO.setDesignation(title);
            employeeDTO.setDateOfBirth(dateOfBirth);
            employeeDTO.setGender(gender);
            employeeDTO.setIsIndian(isIndian);
            employeeDTO.setBasicSalary(basicSalary);
            employeeDTO.setPANNumber(panNumber);
            employeeDTO.setAadhaarCardNumber(aadhaarCardNumber);
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            throw new DAOException(e.getMessage());
        }
        return employeeDTO;
    }
}
