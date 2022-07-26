package com.eddie.test.stomp.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.eddie.test.stomp.service.WebsocketService;
import org.apache.logging.log4j.message.SimpleMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

/**
 * @Author Eddie
 * @Date 2022/7/26 16:29
 * @Version 1.0
 * @Description
 */
@Service
public class WebSocketServiceImpl implements WebsocketService {
    private static final Logger logger = LoggerFactory.getLogger(WebSocketServiceImpl.class);

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    public void sendMsg(String path,String message){
        simpMessagingTemplate.convertAndSend(path,message);
    }
}
