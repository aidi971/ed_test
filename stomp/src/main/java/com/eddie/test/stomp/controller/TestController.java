package com.eddie.test.stomp.controller;

import com.eddie.test.stomp.vo.MessageVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

/**
 * @Author Eddie
 * @Date 2022/7/26 17:03
 * @Version 1.0
 * @Description
 */
@RestController
public class TestController {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    private Logger logger = LoggerFactory.getLogger(TestController.class);

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public MessageVo sendMessage(MessageVo messageVo) throws InterruptedException {
        Thread.sleep(1000);
        messageVo.setContent("ed");
        return new MessageVo("Hello, " + HtmlUtils.htmlEscape(messageVo.getContent()) + "!");
    }

    @MessageMapping("/topic/greetings")
    public MessageVo greeting2(MessageVo message) throws Exception {
        Thread.sleep(1000); // simulated delay
        message.setContent("ed");
        logger.info ("Hello, " + HtmlUtils.htmlEscape(message.getContent()) + "!");
        return new MessageVo("Hello, " + HtmlUtils.htmlEscape(message.getContent()) + "!");
    }
    @GetMapping("/hello2")
    public void greeting3(MessageVo message) throws Exception {
        Thread.sleep(1000); // simulated delay
        message.setContent("ed");
        simpMessagingTemplate.convertAndSend ("/topic/greetings",
                new MessageVo("Hello, " + HtmlUtils.htmlEscape(message.getContent()) + "!"));
    }


    @MessageMapping("/sendToUser")
    public void sendToUser(MessageVo message) throws Exception {
        Thread.sleep(1000); // simulated delay
        message.setContent("ed");
//        logger.info("userId:{},msg:{}", message.getUserId(), message.getName());
//		simpMessagingTemplate.convertAndSendToUser (message.getUserId (),"/sendToUser",
//				new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!"));
//		simpMessagingTemplate.convertAndSend ("/user/1/sendToUser",
//				new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!"));
        simpMessagingTemplate.convertAndSend("/topic/user/" + message.getContent() + "/sendToUser",
                new MessageVo("Hello, " + HtmlUtils.htmlEscape(message.getContent()) + "!"));
    }
}
