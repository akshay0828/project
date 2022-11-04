<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Register Account</h1><br>
<form action="/register" method="post">
  Enter USERNAME: <input name="username" type="text"   />  <br/>
  Enter PASSWORD: <input name="password" type="text"   />  <br/>
 	Confirm-PASSWORD: <input name="cnfmPassword" type="text"   />  <br/>
  <input name="submit" value="Register" type="submit">
  <input name="submit" value="Cancel" type="submit">
  
</form>
</body>
</html>