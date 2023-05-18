package com.mark.chat.common.pb.chat.req;

import com.baidu.bjf.remoting.protobuf.annotation.ProtobufClass;
import lombok.AccessLevel;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

/**
 * 发送群组消息
 * @author Mark
 * @date 2023/4/27
 */
@ToString
@ProtobufClass
@FieldDefaults(level = AccessLevel.PUBLIC)
public class SendGroupMessageReq {
    Long groupId;
    String body;
}
