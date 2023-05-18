package com.mark.chat.common.pb.chat.res;

import com.baidu.bjf.remoting.protobuf.annotation.ProtobufClass;
import lombok.AccessLevel;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

/**
 * 响应发送消息
 * @author Mark
 * @date 2023/4/27
 */
@ToString
@ProtobufClass
@FieldDefaults(level = AccessLevel.PUBLIC)
public class SendMessageRes {
    /**
     * 消息状态 1 发送成功 2 对方不是你的好友 3 发送失败
     */
    Integer state;
}
