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
</head>
<body>
<h2>Trackz</h2><br>
<%
    UserDAO userDAO = new UserDAO();
    int total = userDAO.getTotalUsers();
    int pending = userDAO.getPendingUsers();
    int active = total - pending;
%>
<table>
    <tr>
        <th>Application Name</th>
        <th>Total Users</th>
        <th>Active Users</th>
        <th>Pending Users</th>

    </tr>
    <tr>
        <td>Trackz</td>
        <td><%=total%>
        <td><%=active%>
        <td><%=pending%>
    </tr>

</table>
</body>
</html>
