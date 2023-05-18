package com.mark.chat.common.pb.chat.res;

import com.baidu.bjf.remoting.protobuf.annotation.ProtobufClass;
import lombok.AccessLevel;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

/**
 * 接收私信消息
 * @author Mark
 * @date 2023/4/27
 */
@ToString
@ProtobufClass
@FieldDefaults(level = AccessLevel.PUBLIC)
public class ReceivePrivateMessageRes {

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
