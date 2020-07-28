<%-- 
    Document   : finish
    Created on : May 31, 2020, 8:41:54 PM
    Author     : nguyennst
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Result Page</title>
    </head>
    <body>
        <h1>Quiz Online</h1>
        <h3>Submit successfully</h3>
        <h2>

            <font color="green">
            Number Of Correct: ${requestScope.NUMBERCORRECT}<br/>
            SCORE: ${requestScope.SCOREEE}
            </font>
            
            <br/>
        
            <a href="student.jsp">HOME</a>
        </h2>
            
            
    </body>
</html>
