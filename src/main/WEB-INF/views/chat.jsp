<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Chat Messages</title>
</head>
<body>
<h2>Chat Messages</h2>
<table border="1">
    <tr>
        <th>Sender</th>
        <th>Message</th>
        <th>Sent At</th>
        <th>ID</th>
        <th>Likes</th>
        <th>Dislikes</th>
        <th>Actions</th>
    </tr>
    <c:forEach items="${chatMessages}" var="message">
        <tr>
            <td>${message.sentBy}</td>
            <td>${message.chatMessage}</td>
            <td>${message.sentAt}</td>
            <td>${message.id}</td>
            <td>${message.likes}</td>
            <td>${message.dislikes}</td>
            <td>
                <a href="<c:url value='/likeMessage/${message.id}'/>">Like</a>
                <a href="<c:url value='/dislikeMessage/${message.id}'/>">Dislike</a>
            </td>
        </tr>
    </c:forEach>
</table>
<br>
<a href="<c:url value='/sendMessage'/>">Send a Message</a>
</body>
</html>
