package com.eddie.test.stomp.vo;

import org.apache.commons.lang3.ObjectUtils;

import java.util.Date;

/**
 * @Author Eddie
 * @Date 2022/7/26 17:15
 * @Version 1.0
 * @Description
 */
public class MessageVo {
    private Date deliveryTime;
    private String content;
    private String key;
    private String type;

    public Date getDeliveryTime() {
        return deliveryTime;
    }

    public MessageVo setDeliveryTime(Date deliveryTime) {
        if (ObjectUtils.isEmpty(deliveryTime)){
            deliveryTime = new Date();
        }
        this.deliveryTime = deliveryTime;
        return this;
    }

    public String getContent() {
        return content;
    }

    public MessageVo setContent(String content) {
        this.content = content;
        return this;
    }

    public String getKey() {
        return key;
    }

    public MessageVo setKey(String key) {
        this.key = key;
        return this;
    }

    public String getType() {
        return type;
    }

    public MessageVo setType(String type) {
        this.type = type;
        return this;
    }

    public MessageVo(String content) {
        this.content = content;
    }
}
