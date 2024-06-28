<%@taglib uri='/WEB-INF/mytags/tmtags.tld' prefix='tm' %>
<tm:Module name='EMPLOYEE' />

<jsp:include page='/MasterPageTopSection.jsp' />
<link rel='stylesheet' type='text/css' href='/styletwo/css/employees.css'>
<script src='/styletwo/js/Employees.js'></script>
<h3>Employees</h3>
<div class='employeeGrid'>
    <table border='1' id='employeesGridTable'>
        <thead>
            <tr>
                <th colspan='6' class='employeeGridHeader'>
                    <a href='/styleone/getEmployeeAddForm'>Add Employee</a>
                </th>
            </tr>
            <tr>
                <th class='employeeGridSNoColumnTitle'>S.No.</th>
                <th class='employeeGridIdColumnTitle'>Id.</th>
                <th class='employeeGridNameColumnTitle'>Name</th>
                <th class='employeeGridDesignationColumnTitle'>Designation</th>
                <th class='employeeGridEditOptionColumnTitle'>Edit</th>
                <th class='employeeGridDeleteOptionColumnTitle'>Delete</th>
            </tr>
        </thead>
        <tbody>
            <tr style='cursor: pointer'>
                <td placeHolderId='serialNumber' style='text-align:right'></td>
                <td placeHolderId='employeeId'></td>
                <td placeHolderId='name'></td>
                <td placeHolderId='designation'></td>
                <td placeHolderId='editOption' style='text-align:center'></td>
                <td placeHolderId='deleteOption' style='text-align:center'></td>
            </tr>
        </tbody>
    </table>
</div>
<div style='height: 19vh;margin-left: 5px;margin-right: 5px;margin-bottom: px;margin-top: 5px;border: 1px solid black;'>
    <label style='background:gray;color: white;padding-left: 5px;padding-right: 5px;'>Deatils</label>
    <table border='0' width='100%'>
        <tr>
            <td>Employee Id : <span id='detailPanel_employeeId' style='margin-right: 30px;'></span></td>
            <td>Name : <span id='detailPanel_name' style='margin-right: 30px;'></span></td>
            <td>Designation : <span id='detailPanel_designation' style='margin-right: 30px;'></span></td>
        </tr>
        <tr>
            <td>Date Of Birth : <span id='detailPanel_dateOfBirth' style='margin-right: 30px;'></span></td>
            <td>Gender : <span id='detailPanel_gender' style='margin-right: 30px;'></span></td>
            <td>Is Indian : <span id='detailPanel_isIndian' style='margin-right: 30px;'></span></td>
        </tr>
        <tr>
            <td>Basic Salary : <span id='detailPanel_basicSalary' style='margin-right: 30px;'></span></td>
            <td>PAN Number : <span id='detailPanel_panNumber' style='margin-right: 30px;'></span></td>
            <td>Aadhaar Card Number : <span id='detailPanel_aadhaarCardNumber' style='margin-right: 30px;'></span>
            </td>
        </tr>
    </table>
</div>
<jsp:include page='/MasterPageBottomSection.jsp' />