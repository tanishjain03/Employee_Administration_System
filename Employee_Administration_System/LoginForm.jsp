<jsp:useBean id='administratorBean' scope='session' class='com.ttj.hr.beans.AdministratorBean' />
<jsp:useBean id='errorBean' scope='request' class='com.ttj.hr.beans.ErrorBean' />
<!DOCTYPE HTML>
<html lang='en'>

<head>
    <meta charset='UTF-8'>
    <title>HR Application</title>
    <link rel='stylesheet' type='text/css' href='/styletwo/css/styles.css'>
    <script src='/styletwo/js/AdministratorLoginForm.js'></script>
</head>

<body>
    <!-- Main conatiner starts here -->
    <div class='main-container'>
        <!-- Header starts here -->
        <div class='header'>
            <img src='/styletwo/images/logo.png' class='logo'>
            <div class='brand-name'>TTJ</div>
        </div> <!-- Header ends here -->
        <!-- content section starts here -->
        <div class='content'>
            <div class='administrator-form'>
                <form method='post' action='/styletwo/Login.jsp' onsubmit='return validateAdministrationForm(this)'>
                    <h2>Administrator</h2>
                    <table>
                        <tr>
                            <td>Username</td>
                            <td><input type='text' id='username' name='username' size='36'
                                    value='${administratorBean.username}'></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td><span id='usernameErrorSection' class='error'></span></td>
                        </tr>
                        <tr>
                            <td>Password</td>
                            <td><input type='password' id='password' name='password' size='36'></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td><span id='passwordErrorSection' class='error'>${errorBean.error}</span></td>
                        </tr>
                        <tr>
                            <td colspan='2' align='center'><button type='submit'>Login</button></td>
                        </tr>
                    </table>
                </form>
            </div>
        </div> <!-- content section ends here -->
        <!-- footer starts here -->
        <div class='footer'>
            &copy; TTJ 2024
        </div>
        <!-- footer ends here -->
    </div> <!-- Main container ends here -->
</body>

</html>