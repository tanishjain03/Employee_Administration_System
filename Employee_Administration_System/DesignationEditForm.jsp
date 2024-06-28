<%@taglib uri='/WEB-INF/mytags/tmtags.tld' prefix='tm' %>
<tm:Module name='DESIGNATION' />
<jsp:useBean id='designationBean' scope='request' class='com.ttj.hr.beans.DesignationBean' />
<jsp:useBean id='errorBean' scope='request' class='com.ttj.hr.beans.ErrorBean' />
<script src='/styletwo/js/DesignationAddForm.js'></script>
<jsp:include page='/MasterPageTopSection.jsp' />
<h2>Designation (Edit module)</h2>
<span class='error'>
    <jsp:getProperty name='errorBean' property='error' />
</span>
<form method='post' action='/styletwo/UpdateDesignation.jsp' onsubmit='return validateForm(this)'>
    Designation
    <input type='hidden' id='code' name='code' value='${designationBean.code}'>
    <input type='text' id='title' name='title' maxlength='35' size='36' value='${designationBean.title}'>
    <span id='titleErrorSection' class='error'></span><br>
    <button type='submit'>Update</button>
    <button type='button' onclick='cancelUpdate()'>Cancel</button>
</form>
<form id='cancelUpdateForm' action='/styletwo/Designations.jsp'>
</form>
<jsp:include page='/MasterPageBottomSection.jsp' />