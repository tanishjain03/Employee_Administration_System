<%@taglib uri='/WEB-INF/mytags/tmtags.tld' prefix='tm' %>
<tm:Session>
    <jsp:forward page='/LoginForm.jsp' />
</tm:Session>
<tm:Module name='DESIGNATION' />
<tm:FormResubmitted>
    <jsp:forward page='/notifyFormResubmission' />
</tm:FormResubmitted>
<jsp:useBean id='designationBean' scope='request' class='com.ttj.hr.beans.DesignationBean' />
<jsp:setProperty name='designationBean' property='*' />
<jsp:forward page='/addDesignation' />