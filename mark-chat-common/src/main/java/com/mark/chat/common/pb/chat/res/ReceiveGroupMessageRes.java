package com.mark.chat.common.pb.chat.res;

import com.baidu.bjf.remoting.protobuf.annotation.ProtobufClass;
import lombok.AccessLevel;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

/**
 * @author Mark
 * @date 2023/4/27
 */
@ToString
@ProtobufClass
@FieldDefaults(level = AccessLevel.PUBLIC)
public class ReceiveGroupMessageRes {

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
     * 群组ID
     */
    Long groupId;

    /**
     *  发送人ID
     */
    Long senderId;

    /**
     *  发送人名称
     */
    String senderName;
    String body;
    /**
     * 发送时间
     */
    Long sendTime;
}
