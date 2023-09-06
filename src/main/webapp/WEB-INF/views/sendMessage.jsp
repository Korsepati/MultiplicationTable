<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Message</title>
</head>
<body>
<h2>Send Message</h2>
<form action="/chat/save" method="post">
From:<input type="text" name="from"/><br/><br/>
Message:<input type="text" name="message"/><br/><br/>
<input type="submit" value="Send"/>
</form>
</body>
</html>
