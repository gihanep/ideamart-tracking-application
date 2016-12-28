<%--
  Created by IntelliJ IDEA.
  User: tharinda
  Date: 12/28/16
  Time: 12:22 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.ideamart.sample.usermgt.UserDAO" %>
<html>
<head>
    <style type="text/css">
        td, th {
            width: 4rem;
            height: 2rem;
            border: 1px solid #ccc;
            text-align: center;
        }

        th {
            background: lightblue;
            border-color: white;
        }

        body {
            padding: 1rem;
        }
    </style>
    <title>mLocation dashboard</title>
</head>
<body>
<h2>ECHAT</h2><br>
<%
    UserDAO userDAO = new UserDAO();
    int total = userDAO.getTotalUsers();
    int [] array = userDAO.getTotalSubscribers();
    int reg = array[0];
    int unReg = array[1];
    int pending = array[2];

%>
<table>
    <tr>
        <th>Application Name</th>
        <th>Total Users</th>
        <th>Subscribed Users(Net Reg Base)</th>
        <th>UnSubscribed Users</th>
        <th>Pending Users</th>

    </tr>
    <tr>
        <td>mLocation</td>
        <td><%=total%>
        <td><%=reg%></td>
        <td><%=unReg%></td>
        <td><%=pending%></td>
    </tr>

</table>
</body>
</html>
