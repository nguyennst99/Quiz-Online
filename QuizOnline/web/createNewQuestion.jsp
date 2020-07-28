<%-- 
    Document   : createNewQuestion
    Created on : May 27, 2020, 2:45:55 PM
    Author     : nguyennst
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create New Question</title>
    </head>
    <body>
        <c:if test="${empty sessionScope}">
            <c:redirect url="login.html"/>
        </c:if>
        <h1>Quiz Online</h1>
        <form action="MainController">
            Context Question: <input type="text" name="txtContent" value="" /><br/>
            <font color="red">
            ${requestScope.QUESTIONERROR.contentError}
            </font><br/>
            Answer A: <input type="text" name="txtAnswerA" value="" /><br/>
            <font color="red">
            ${requestScope.QUESTIONERROR.answerAError}
            </font><br/>
            Answer B: <input type="text" name="txtAnswerB" value="" /><br/>
            <font color="red">
            ${requestScope.QUESTIONERROR.answerBError}
            </font><br/>
            Answer C: <input type="text" name="txtAnswerC" value="" /><br/>
            <font color="red">
            ${requestScope.QUESTIONERROR.answerCError}
            </font><br/>
            Answer D: <input type="text" name="txtAnswerD" value="" /><br/>
            <font color="red">
            ${requestScope.QUESTIONERROR.answerDError}
            </font><br/>
            Answer Correct: <input type="text" name="txtCorrect" value="" /><br/>
            <font color="red">
            ${requestScope.QUESTIONERROR.correctError}
            </font><br/>
            Subject : 
            <select name="cboSubject">
                <c:if test="${not empty sessionScope.LISTSUBJECT}" >
                    <c:forEach var="dto" items="${sessionScope.LISTSUBJECT}" >
                        <option value="${dto.subjectID}">${dto.subName}</option>
                    </c:forEach>
                </c:if>
            </select><br/>
            <input type="submit" value="Create New Question" name="btAction"/>
            <input type="reset" value="Reset" />
        </form>
    </body>
</html>
