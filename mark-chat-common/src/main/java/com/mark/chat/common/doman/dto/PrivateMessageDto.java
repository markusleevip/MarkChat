package com.mark.chat.common.doman.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: Mark
 * @Date: 2023/5/24
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PrivateMessageDto implements Serializable {
    /**
     * 消息ID
     */
    String id;

    /**
     * 聊天类型
     */
    Integer chatType;

    /**
     * 消息类型
     */
    Integer msgType;

    /**
     * 接收人ID
     */
    Long toId;

    /**
     * 发送人ID
     */
    Long senderId;

    /**
     *  发送人名称
     */
    String senderName;

    /**
     * 消息内容
     */
    String body;

    /**
     * 发送时间
     */
    Long sendTime;
}
