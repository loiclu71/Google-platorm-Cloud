<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Messages" %>
<%@ page import="java.util.List"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>Type your message and save it into the google SQL database!</h1>
		<form action="/hello" method="post">
			<p>
				<label>Full name: <input type="text" name="username" /></label>
			</p>
			
			<p>
				<label>Text: <input type="text" name="usermessage" /></label>
			</p>
			
			<p>
				<input type="submit" value = "Save" />
			</p>
		</form>
		
		<h1>Available messages</h1>
		
		<%
		List<Messages> messages = (List<Messages>)request.getAttribute("listofmessages");
		for(Messages msg : messages) {
		%>
		
		<p>
		    
			<strong> Full name: </strong> <%= msg.getName() %> <br />
			<strong> Message: </strong> <%= msg.getTextmessage() %> <br />
			
		</p>
		
		<%
		}
		%>
		
</body>
</html>