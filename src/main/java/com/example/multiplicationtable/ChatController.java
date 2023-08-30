package com.example.multiplicationtable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class ChatController
{
    @Autowired
    private ChatTableRepository chatTableRepository;

    @GetMapping("/sendMessage/{from}/{message}")
    public String sendMessage(@PathVariable String from, @PathVariable String message) {
        try {
            ChatMessageTable ct = new ChatMessageTable();
            ct.setSentBy(from);
            ct.setChatMessage(message);
            ct.setSentAt(new Date());
            chatTableRepository.save(ct);
            return "redirect:/seeChat";
        } catch (Exception ex) {
            return "error";
        }
    }

    @GetMapping("/seeChat")
    public String seeChat(Model model) {
        try {
            List<ChatMessageTable> cts = chatTableRepository.findAll();

            if (cts != null) {
                model.addAttribute("chatMessages", cts);
                return "chat";
            } else {
                model.addAttribute("message", "No Table Found");
                return "message";
            }
        } catch (Exception ex) {
            model.addAttribute("error", ex.getMessage());
            return "error";
        }
    }

    @GetMapping("/likeMessage/{messageId}")
    public String likeMessage(@PathVariable Long messageId) {
        try {
            Optional<ChatMessageTable> optionalMessage = chatTableRepository.findById(messageId);

            if (optionalMessage.isPresent()) {
                ChatMessageTable message = optionalMessage.get();
                message.incrementLikes();
                chatTableRepository.save(message);
                return "redirect:/seeChat";
            } else {
                return "error";
            }
        } catch (Exception ex) {
            return "error";
        }
    }

    @GetMapping("/dislikeMessage/{messageId}")
    public String dislikeMessage(@PathVariable Long messageId) {
        try {
            Optional<ChatMessageTable> optionalMessage = chatTableRepository.findById(messageId);

            if (optionalMessage.isPresent()) {
                ChatMessageTable message = optionalMessage.get();
                message.incrementDislikes();
                chatTableRepository.save(message);
                return "redirect:/seeChat";
            } else {
                return "error";
            }
        } catch (Exception ex) {
            return "error";
        }
    }



}