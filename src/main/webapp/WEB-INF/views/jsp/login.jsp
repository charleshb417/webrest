<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<head>
	<link href="<c:url value="./resources/static/css/login.css" />" rel="stylesheet">
	<link href="<c:url value="./webjars/bootstrap/3.3.5/css/bootstrap.css" />" rel="stylesheet">
</head>
<body>
	<div id="loginContainer">
		<div class="sectionTitle">
			<h2>Inside Baltimore</h2>
		</div>
	
		<c:if test="${not empty param.error && param.error == 'true'}" >
		   <div class="errorMsg">
		   	Username and password combination does not match our records.
		   </div>
		</c:if>
		
		<div>
			<form name='f' action="/webrest/login" method='POST'>
			   <table>
			      <tr>
			         <td><input type='text' name='username' placeholder='Username' value=''></td>
			      </tr>
			      <tr>
			         <td><input type='password' name='password' placeholder='Password' /></td>
			      </tr>
			      <tr>
			         <td><input name="submit" class="btn btn-primary btn-sm" type="submit" value="Submit" /></td>
			      </tr>
			   </table>
			   <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			</form>
		</div>
		<a href="/webrest/signup">Signup</a>
  </div>
</body>
</html>