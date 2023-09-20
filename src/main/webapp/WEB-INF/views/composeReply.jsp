<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Compose Reply</title>
</head>
<body>
<h1>Compose Reply</h1>

<form method="POST" action="/chat/saveReply">
    <input type="hidden" name="parentMessageId" value="${parentMessage.id}" />
    <textarea name="replyMessage" rows="4" cols="50" placeholder="Compose your reply"></textarea>
    <button type="submit">Send Reply</button>
</form>
</body>
</html>
