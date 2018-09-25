<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registration</title>
</head>
<body style="background-color: #324677;
	         color:#f3ff99; 
 	    	 font-weight: bold;">
<h1><center>Sing in</center></h1>
 <form action="registration" method="post">
 <table
 		cellpadding = "8" style="border: 1px solid black;
 	    border-radius: 16px; 
 	    border-color: #f3ff99;
 	    border-width: 3px;
 	    padding: 20px;" 
 	    align = center>
 	  
 	 <tr>
	 	<td><center>Full Name</center></td>
	 </tr>
	 
	 </tr>
	 	<td><center><input type="text" name = fullname></center></td>
	 </tr>
	 
 	 <tr>
	 	<td><center>Email</center></td>
	 </tr>
	 
	  </tr>
	 	<td><center><input type="text" name = email></center></td>
	 </tr>
	 
 	<tr>
	 	<td><center>Username</center></td>
	 </tr>
	 
	 </tr>
	 	<td><center><input type="text" name = username></center></td>
	 </tr>
	 
	 <tr>
	 	<td><center>Password</center></td>
	 </tr>
	 
	 <tr>
	 	<td><center><input type="password" name = password></center></td>
	 </tr>
	 
	 <tr>
	 	<td><center><input type="submit" value = "Registration"></center></td>
	 </tr>
 	
 </table>
 </form>
</body>
</html>