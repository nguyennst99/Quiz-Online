    <%-- 
    Document   : createNewAccount
    Created on : May 24, 2020, 11:12:30 AM
    Author     : nguyennst
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign Up</title>
    </head>
    <body>
        <h1>Quiz Online</h1>
        <form action="MainController" method="POST">
            Email: <input type="text" name="txtEmail" value="${param.txtEmail}" /><br/>
            <font color="red">
            ${requestScope.ERROR.emailError}
            </font><br/>
            Password: <input type="password" name="txtPassword" value="" /><br/>
            <font color="red">
            ${requestScope.ERROR.passwordError}
            </font><br/>
            Confirm: <input type="password" name="txtConfirm" value="" /><br/>
            <font color="red">
            ${requestScope.ERROR.confirmError}
            </font><br/>
            Name: <input type="text" name="txtName" value="${param.txtName}" /><br/>
            <font color="red">
            ${requestScope.ERROR.nameError}
            </font><br/>
            <input type="submit" value="Sign Up" name="btAction" />
        </form>
    </body>
</html>
