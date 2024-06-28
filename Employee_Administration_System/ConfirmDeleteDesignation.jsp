<%@taglib uri='/WEB-INF/mytags/tmtags.tld' prefix='tm' %>
<tm:Module name='DESIGNATION' />
<jsp:useBean id='designationBean' scope='request' class='com.ttj.hr.beans.DesignationBean' />

<script src='/styletwo/js/DesignationAddForm.js'></script>
<jsp:include page='/MasterPageTopSection.jsp' />
<h2>Designation (Delete module)</h2>
<form method='post' action='/styletwo/DeleteDesignation.jsp' onsubmit='return validateForm(this)'>
    <input type='hidden' id='code' name='code' value='${designationBean.code}'>
    Designation : <b>${designationBean.title}</b><br><br>
    Are you sure you want to delete this designation?
    <button type='submit'>Yes</button>
    <button type='button' onclick='cancelDeletion()'>No</button>
</form>
<form id='cancelDeletionForm' action='/styletwo/Designations.jsp'>
</form>
<jsp:include page='/MasterPageBottomSection.jsp' />