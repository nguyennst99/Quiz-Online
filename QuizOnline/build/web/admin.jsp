<%-- 
    Document   : admin
    Created on : May 21, 2020, 1:06:28 PM
    Author     : nguyennst
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
    </head>
    <body>
        <h1>Quiz Online</h1>
        Welcome, ${sessionScope.NAME}<br/>
        <c:if test="${empty sessionScope}">
            <c:redirect url="login.html"/>
        </c:if>
        <form action="MainController">
            <input type="submit" value="Logout" name="btAction" />
        </form>

        <a href="createNewQuestion.jsp">Create New Question</a>

        <form action="MainController">
            <select name="cboSubject">
                <c:if test="${not empty sessionScope.LISTSUBJECT}" >
                    <c:forEach var="dto" items="${sessionScope.LISTSUBJECT}" >
                        <option value="${dto.subjectID}">${dto.subName}</option>
                    </c:forEach>
                </c:if>
            </select>
            <select name="cboStatus">
                <option value="2">Active</option>
                <option value="3">deActive</option>
            </select>
            Search Value: <input type="text" name="txtSearch" value="${param.txtSearch}" />
            <input type="submit" value="Search" name="btAction" />
            <input type="hidden" name="txtPage" value="1" />
        </form>
        <br/>
        <c:if test="${not empty param.txtSearch}">
            <c:if test="${not empty requestScope.LISTQUESTION}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Question Content</th>
                            <th>Answer A</th>
                            <th>Answer B</th>
                            <th>Answer C</th>
                            <th>Answer D</th>
                            <th>Answer Correct</th>                      
                            <th>Subject</th>                      
                            <th>Create Date</th>                      
                            <th>Update</th>                      
                            <th>Delete</th>                      
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="dto" items="${requestScope.LISTQUESTION}" varStatus="counter">
                        <form action="MainController">

                            <tr>
                                <td>${counter.count}</td>
                            <input type="hidden" name="txtQuestionID" value="${dto.id}" />
                            <td>
                                <input type="text" name="question_content" value="${dto.question_content}" />
                            </td>
                            <td>
                                <input type="text" name="txtAnsA" value="${dto.answerA}" size="10px" />
                            </td>
                            <td>
                                <input type="text" name="txtAnsB" value="${dto.answerB}" size="10px"/>
                            </td>
                            <td>
                                <input type="text" name="txtAnsC" value="${dto.answerC}" size="10px" />
                            </td>
                            <td>
                                <input type="text" name="txtAnsD" value="${dto.answerD}" size="10px"/>
                            </td>
                            <td>
                                <input type="text" name="txtAnsCorrect" value="${dto.answer_correct}" size="15px"/>
                            </td>      

                            <td>
                                <select name="cboSubjectUpdate">

                                    <c:if test="${not empty sessionScope.LISTSUBJECT}" >                                    
                                        <c:forEach var="dtoo" items="${sessionScope.LISTSUBJECT}" >    
                                            <c:if test="${(dto.subID) == (dtoo.subjectID) }">
                                                <option value="${dtoo.subjectID}" selected="selected">${dtoo.subName}</option> 
                                            </c:if>
                                            <c:if test="${(dto.subID) != (dtoo.subjectID) }">
                                                <option value="${dtoo.subjectID}">${dtoo.subName}</option> 
                                            </c:if>                
                                        </c:forEach>
                                    </c:if>
                                </select>
                            </td>                      
                            <td>${dto.createDate}</td>                      
                            <td>
                                <input type="submit" value="Update" name="btAction" />
                                <input type="hidden" name="txtLastSearch" value="${param.txtSearch}" />
                                <input type="hidden" name="txtLastPage" value="${param.txtPage}" />
                                <input type="hidden" name="txtLastSub" value="${param.cboSubject}" />
                                <input type="hidden" name="txtLastStatus" value="${param.cboStatus}" />
                            </td>   
                            <td>
                                <c:if test="${param.cboStatus == 2}">
                                <input type="submit" value="Delete" name="btAction"/>
                                </c:if>
                            </td>
                            </tr>
                        </form>             
                    </c:forEach>
                </tbody>    
            </table>

            <font color="red">
            ${requestScope.UPDATEERROR.contentError}
            </font><br/>
            <font color="red">
            ${requestScope.UPDATEERROR.answerAError}
            </font><br/>
            <font color="red">
            ${requestScope.UPDATEERROR.answerBError}
            </font><br/>
            <font color="red">
            ${requestScope.UPDATEERROR.answerCError}
            </font><br/>
            <font color="red">
            ${requestScope.UPDATEERROR.answerDError}
            </font><br/>
            <font color="red">
            ${requestScope.UPDATEERROR.correctError}
            </font><br/>

            <c:if test="${requestScope.PAGENUM > 1}">
                <c:forEach begin="1" end="${requestScope.PAGENUM}" var="i">
                    <c:if test="${(param.txtPage) == i}">
                        <font color="red">
                        <a href="MainController?btAction=Search&txtSearch=${param.txtSearch}&txtPage=${i}&cboSubject=${param.cboSubject}&cboStatus=${param.cboStatus}">${i}</a>
                        </font>
                    </c:if>
                    <c:if test="${(param.txtPage) != i}">
                        <font color="green">
                        <a href="MainController?btAction=Search&txtSearch=${param.txtSearch}&txtPage=${i}&cboSubject=${param.cboSubject}&cboStatus=${param.cboStatus}">${i}</a>
                        </font>
                    </c:if>
                </c:forEach>
            </c:if>
        </c:if>
        <c:if test="${empty requestScope.LISTQUESTION}">
            <h2>
                <font color="red">
                No record is matched!!!!
                </font>
            </h2>
        </c:if>
    </c:if>


</body>
</html>
