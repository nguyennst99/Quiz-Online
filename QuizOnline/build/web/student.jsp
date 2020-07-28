<%-- 
    Document   : student.jsp
    Created on : May 21, 2020, 1:06:19 PM
    Author     : nguyennst
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Student Page</title>
    </head>
    <body>
        <h1>Quiz Online</h1>
        Welcome, ${sessionScope.NAME}

        <c:if test="${empty sessionScope}">
            <c:redirect url="login.html"/>
        </c:if>
        <form action="MainController">
            <input type="submit" value="Logout" name="btAction" />
        </form>

        <form action="MainController">
            <select name="subIDquiz">
                <c:if test="${not empty sessionScope.LISTSUBJECT}" >
                    <c:forEach var="dto" items="${sessionScope.LISTSUBJECT}" >
                        <option value="${dto.subjectID}">${dto.subName}</option>
                    </c:forEach>
                </c:if>
            </select>
            <input type="hidden" name="txtIndex" value="0" />
            <input type="hidden" name="txtScore" value="0" />

            <input type="submit" value="Start" name="btAction"/>              
            
            <a href="history.jsp">
                <button type="button">View History</button>
            </a>
        </form>
        

        <input type="hidden" name="txtTime" value="${sessionScope.TIME}" id="time"/>


        <div id="countdown">
        </div>
        <form action="MainController">
            <c:if test="${not empty param.subIDquiz}">
                <c:if test="${not empty sessionScope.LISTQUIZ}">

                    <script>
                        var time = document.getElementById("time").value;

                        function countDown() {

                            time = time - 1;
                            document.getElementById("timee").value = time;
                            var min = Math.floor(time / 60);
                            var seconds = time % 60;
                            if (time > 0) {
                                document.getElementById("countdown").innerHTML = min + ":" + seconds;
                            } else {
                                document.getElementById("abc").click();
                            }
                        }
                        setInterval("countDown()", 1000);

                    </script>

                    <c:set var="i" value="${requestScope.DETAIL}"/>  


                    <form action="MainController">   
                        <c:if test="${param.txtIndex < (sessionScope.NUMBERQUESTION - 1) && param.txtIndex > 0}">

                            No. ${param.txtIndex + 1}<br/>
                            Question:  ${i.question_content}<br/>
                            <input type="radio" name="answer" value="A" /> A:  ${i.answerA}<br/>
                            <input type="radio" name="answer" value="B" /> B: ${i.answerB}<br/>
                            <input type="radio" name="answer" value="C" /> C: ${i.answerC}<br/>
                            <input type="radio" name="answer" value="D" /> D:  ${i.answerD}<br/>     
                            <input type="hidden" name="txtQueID" value="${i.id}" />
                            <input type="submit" value="Next" name="btAction" /><br/><br/>
                            <input type="hidden" name="txtIndex" value="${param.txtIndex+1}" />
                            <input type="hidden" name="subIDquiz" value="${sessionScope.SUBID}" />
                            <input type="hidden" name="txtCorrect" value="${i.answer_correct}" />

                            <input type="hidden" name="txtScore" value="${requestScope.SCORE}" />
                            <input type="hidden" name="txtTimee" id="timee" value=""/>
                            <input type="submit" value="Finish" name="btAction" id="abc"/>
                        </c:if>
                    </form>



                    <form action="MainController">   
                        <c:if test="${param.txtIndex == (sessionScope.NUMBERQUESTION - 1)}">
                            No. ${param.txtIndex + 1}<br/>
                            Question:  ${i.question_content}<br/>
                            <input type="radio" name="answer" value="A" /> A:  ${i.answerA}<br/>
                            <input type="radio" name="answer" value="B" /> B: ${i.answerB}<br/>
                            <input type="radio" name="answer" value="C" /> C: ${i.answerC}<br/>
                            <input type="radio" name="answer" value="D" /> D:  ${i.answerD}<br/>   
                            <input type="hidden" name="txtQueID" value="${i.id}" />
                            <input type="submit" value="Finish" name="btAction" id="abc"/>
                            <input type="hidden" name="txtCorrect" value="${i.answer_correct}" />
                            <input type="hidden" name="txtScore" value="${requestScope.SCORE}" />
                            <input type="hidden" name="txtNumQues" value="${requestScope.NUMBERQUESTION}" />   
                            <input type="hidden" name="txtTimee" id="timee" value="" />
                        </c:if>
                    </form>


                    <form action="MainController">
                        <c:if test="${param.txtIndex == 0}">
                            No. ${param.txtIndex + 1}<br/>
                            Question:  ${i.question_content}<br/>
                            <input type="radio" name="answer" value="A" /> A:  ${i.answerA}<br/>
                            <input type="radio" name="answer" value="B" /> B: ${i.answerB}<br/>
                            <input type="radio" name="answer" value="C" /> C: ${i.answerC}<br/>
                            <input type="radio" name="answer" value="D" /> D:  ${i.answerD}<br/>   
                            <input type="hidden" name="txtQueID" value="${i.id}" />
                            <input type="submit" value="Next" name="btAction" /><br/><br/>
                            <input type="submit" value="Finish" name="btAction" id="abc"/>
                            <input type="hidden" name="txtIndex" value="${param.txtIndex+1}" />
                            <input type="hidden" name="subIDquiz" value="${sessionScope.SUBID}" />
                            <input type="hidden" name="txtCorrect" value="${i.answer_correct}" />                            
                            <input type="hidden" name="txtScore" value="0" />
                            <input type="hidden" name="txtTimee" id="timee" value=""/>
                        </c:if>                        
                    </form>

                    <br/>
                    <br/>


                </c:if>

            </c:if>                    
        </form>
    </body>
</html>
