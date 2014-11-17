<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:useBean id="person" class="person.PersonSave" scope="session"/>
<jsp:setProperty name="person" property="*"/> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Confirmation</title>
</head>
<body>
Your Search was:<br/>
First Name: <%= request.getAttribute("firstname_search") %><BR>
Last Name: <%= request.getAttribute("lastname_search") %><BR>
Last Name: <%= request.getAttribute("multipleRecords") %><BR>
The other details for your search:


</body>
</html>
