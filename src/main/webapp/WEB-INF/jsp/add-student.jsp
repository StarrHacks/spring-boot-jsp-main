<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Add Student</title>
    </head>
    <body>
        <c:if test="${addStudentSuccess}">
            <div>Successfully added Student with ID: ${savedStudent.id}</div>
        </c:if>

        <c:url var="add_student_url" value="/student/addStudent"/>
        <form:form action="${add_student_url}" method="post" modelAttribute="student">
            <form:label path="name">Name: </form:label> <form:input type="text" path="name"/>
            <form:label path="name">Age: </form:label> <form:input type="text" path="age"/>
            <input type="submit" value="submit"/>
        </form:form>
    </body>
</html>