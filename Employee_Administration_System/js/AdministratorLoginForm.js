function validateAdministrationForm(frm) {
    var username=frm.username.value.trim();
    var usernameErrorSection=document.getElementById('usernameErrorSection');
    usernameErrorSection.innerHTML='';
    if(username.length==0) {
        usernameErrorSection.innerHTML='required';
        frm.username.focus();
        return false;
    }
    var password=frm.password.value.trim();
    var passwordErrorSection=document.getElementById('passwordErrorSection');
    passwordErrorSection.innerHTML='';
    if(password.length==0) {
        passwordErrorSection.innerHTML='required';
        frm.password.focus();
        return false;
    }
    return true;
}