package com.example.multiplicationtable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/chat")
public class ChatController {
    @Autowired
    private ChatTableRepository chatTableRepository;

    @GetMapping("/send")
    public String sendMessage() {
        try {
            return "sendMessage";
        } catch (Exception ex) {
            return "error";
        }
    }


    @PostMapping("/save")
    public String saveMessage(@ModelAttribute ChatMessage chatMessage) {
        try {
            ChatMessageTable ct = new ChatMessageTable();
            ct.setSentBy(chatMessage.getFrom());
            ct.setChatMessage(chatMessage.getMessage());
            ct.setSentAt(new Date());
            chatTableRepository.save(ct);
            return "redirect:/chat/see";
        } catch (Exception ex) {
            return "error";
        }
    }

    @GetMapping("/see")
    public String seeChat(ModelMap map) {
        try {
            List<ChatMessageTable> cts = chatTableRepository.findAll();
            System.out.println(cts);

            if (cts != null) {
                map.addAttribute("chatMessages", cts);
                map.put("chatMessages", cts);
                return "chat";
            } else {
                map.put("message", "No Table Found");
                return "message";
            }
        } catch (Exception ex) {
            map.put("error", ex.getMessage());
            return "error";
        }
    }

    @GetMapping("/reply")
    public String replyMessage(@RequestParam Long parentMessageId, ModelMap map) {
        try {
            Optional<ChatMessageTable> optionalParentMessage = chatTableRepository.findById(parentMessageId);

            if (optionalParentMessage.isPresent()) {
                ChatMessageTable parentMessage = optionalParentMessage.get();
                map.addAttribute("parentMessage", parentMessage);
                return "composeReply";
            } else {
                return "error";
            }
        } catch (Exception ex) {
            return "error";
        }
    }

    @PostMapping("/saveReply")
    public String saveReply(@RequestParam Long parentMessageId, @RequestParam String replyMessage) {
        try {
            Optional<ChatMessageTable> optionalParentMessage = chatTableRepository.findById(parentMessageId);

            if (optionalParentMessage.isPresent()) {
                ChatMessageTable parentMessage = optionalParentMessage.get();

                ChatMessageTable reply = new ChatMessageTable();
                reply.setSentBy("User");
                reply.setChatMessage(replyMessage);
                reply.setSentAt(new Date());
                reply.setParentMessageId(parentMessageId);

                chatTableRepository.save(reply);
                return "redirect:/chat/see";
            } else {
                return "error";
            }
        } catch (Exception ex) {
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
                return "redirect:/chat/see";
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
                return "redirect:/chat/see";
            } else {
                return "error";
            }
        } catch (Exception ex) {
            return "error";
        }
    }

}