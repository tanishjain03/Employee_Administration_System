<%@taglib uri='/WEB-INF/mytags/tmtags.tld' prefix='tm' %>
<tm:Session>
    <jsp:forward page='/LoginForm.jsp' />
</tm:Session>
<!DOCTYPE HTML>
<html lang='en'>

<head>
    <meta charset='UTF-8'>
    <title>HR Application</title>
    <link rel='stylesheet' type='text/css' href='/styletwo/css/styles.css'>
</head>

<body>
    <!-- Main conatiner starts here -->
    <div class='main-container'>
        <!-- Header starts here -->
        <div class='header'>
            <a href=/styletwo/index.html><img src='/styletwo/images/logo.png' class='logo'></a>
            <div class='brand-name'>TTJ</div>
            <div class='user'><img src='/styletwo/images/user.png' width='20px' height='20px'>&nbsp;${username}&nbsp;&nbsp;
            <a href='/styletwo/logout'>Logout</a></div>
        </div> <!-- Header ends here -->
        <!-- content section starts here -->
        <div class='content'>
            <!-- left panel starts here -->
            <div class='content-left-panel'>
                <tm:If condition='${module==DESIGNATION}' >
                    <b>Designations</b><br>
                </tm:If>
                <tm:If condition='${module!=DESIGNATION}' >
                    <a href='/styletwo/Designations.jsp'>Designations</a><br>
                </tm:If>
                <tm:If condition='${module==EMPLOYEE}' >
                    <b>Employees</b><br>
                </tm:If>
                <tm:If condition='${module!=EMPLOYEE}' >
                    <a href='/styletwo/Employees.jsp'>Employees</a><br><br>
                </tm:If>
                <tm:If condition='${module!=HOME}'>
                    <a href='/styletwo/index.jsp'>Home</a>
                </tm:If>
            </div> <!-- left panel ends here -->
            <div class='content-right-panel'> <!--right panel starts here-->
