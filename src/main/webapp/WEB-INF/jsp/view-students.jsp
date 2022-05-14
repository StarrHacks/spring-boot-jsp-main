<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
    <head>
        <title>View Students</title>
        <link href="<c:url value="/css/common.css"/>" rel="stylesheet" type="text/css">
    </head>
    <body>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Age</th>
                    <th>edit</th>
					<th>delete</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${students}" var="student">
                    <tr>
                        <td>${student.id}</td>
                        <td>${student.name}</td>
                        <td>${student.age}</td>
                        <td><form:form action= "updateStudent/${student.id}" method = "get">
                            <input type="submit" value="update"/>
                        </form:form></td>
                        <td><form:form action= "deleteStudent/${student.id}" method = "get">
                            <input type="submit" value="delete"/>
                        </form:form></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>