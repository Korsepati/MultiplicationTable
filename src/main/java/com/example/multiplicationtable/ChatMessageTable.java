package com.example.multiplicationtable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Entity
public class ChatMessageTable {

    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    String sentBy;
    String chatMessage;
    Date sentAt;

    public Long getParentMessageId() {
        return parentMessageId;
    }

    public void setParentMessageId(Long parentMessageId) {
        this.parentMessageId = parentMessageId;
    }

    private Long parentMessageId;

    private int likes = 0;

    private int dislikes = 0;

    public String getSentBy() {
        return sentBy;
    }

    public void setSentBy(String from) {
        this.sentBy = from;
    }

    public String getChatMessage() {
        return chatMessage;
    }

    public void setChatMessage(String message) {
        this.chatMessage = message;
    }

    public Date getSentAt() {
        return sentAt;
    }

    public void setSentAt(Date sentAt) {
        this.sentAt = sentAt;
    }

    public void incrementLikes() {
        this.likes++;
    }

    public void incrementDislikes() {
        this.dislikes++;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "ChatMessageTable{" +
                "id=" + id +
                ", sentBy='" + sentBy + '\'' +
                ", chatMessage='" + chatMessage + '\'' +
                ", sentAt=" + sentAt +
                ", likes=" + likes +
                ", dislikes=" + dislikes +
                '}';
    }
}
