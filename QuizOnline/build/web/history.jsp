<%-- 
    Document   : history
    Created on : Jun 2, 2020, 8:57:42 PM
    Author     : nguyennst
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>HISTORY</title>
    </head>
    <body>
        <h1>QUIZ ONLINE <br/>
            <font color="green">View History</font>
        </h1>
        <form action="MainController">
            <select name="cboSubjectt">
                <c:if test="${not empty sessionScope.LISTSUBJECT}" >
                    <c:forEach var="dto" items="${sessionScope.LISTSUBJECT}" >
                        <option value="${dto.subjectID}">${dto.subName}</option>
                    </c:forEach>
                </c:if>
            </select>
            <input type="hidden" name="txtPageee" value="1" />
                <input type="submit" value="History" name="btAction"/>
        </form>
        <c:if test="${not empty param.cboSubjectt}">
            <c:if test="${not empty requestScope.LISTQUY}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Email</th>
                            <th>Sub ID</th>
                            <th>Score</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="dto" items="${requestScope.LISTQUY}" varStatus="counter">
                            <tr>
                                <td>${counter.count}</td>
                                <td>${dto.email}</td>
                                <td>${dto.subID}</td>
                                <td>${dto.score}</td>
                            </tr>
                        </c:forEach>
                    </tbody>

                </table>
                <c:if test="${requestScope.TRANG > 1}">
                    <c:forEach begin="1" end="${requestScope.TRANG}" var="i">
                        <c:if test="${(param.txtPageee) == i}">
                            <font color="red">
                            <a href="MainController?btAction=History&txtPageee=${i}&cboSubjectt=${param.cboSubjectt}">${i}</a>
                            </font>
                        </c:if>
                        <c:if test="${(param.txtPageee) != i}">
                            <font color="green">
                            <a href="MainController?btAction=History&txtPageee=${i}&cboSubjectt=${param.cboSubjectt}">${i}</a>
                            </font>
                        </c:if>
                    </c:forEach>
                </c:if>
            </c:if>
            <c:if test="${empty requestScope.LISTQUY}">
                <h2>
                    <font color="red">
                    No record is matched!!!!
                    </font>
                </h2>
            </c:if>
        </c:if>
    </body>
</html>
