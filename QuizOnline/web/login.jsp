<%-- 
    Document   : login
    Created on : May 20, 2020, 1:28:46 PM
    Author     : nguyennst
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <h1>Quiz Online</h1>
        <h2>Login</h2>
        <form action="MainController" method="POST">
            Email: <input type="text" name="txtEmail" value="${param.txtEmail}" /><br/>
            <font color="red">
            ${requestScope.LOGINERROR.emailError}
            </font><br/>
            Password: <input type="password" name="txtPassword" value="" /><br/>
            <font color="red">
            ${requestScope.LOGINERROR.passwordError}
            </font><br/>
            <font color="red">${requestScope.INVALID}</font><br/>
            <input type="submit" value="Login" name="btAction" />
            <input type="reset" value="Reset" />
            
        </form>
        <a href="createNewAccount.html">Sign Up</a>
    </body>
</html>
