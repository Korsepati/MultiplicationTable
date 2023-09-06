<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
            <td><c:out value = "${message.chatMessage}"/></td>
            <td>${message.chatMessage}</td>
            <td>${message.sentAt}</td>
            <td>${message.id}</td>
            <td>${message.likes}</td>
            <td>${message.dislikes}</td>
            <td>
                <a href="/chat/likeMessage/${message.id}">Like</a>
                <a href="/chat/dislikeMessage/${message.id}">Dislike</a>
            </td>
        </tr>
    </c:forEach>
</table>
<br>
<a href="/chat/send">Send a Message</a>
</body>
</html>
