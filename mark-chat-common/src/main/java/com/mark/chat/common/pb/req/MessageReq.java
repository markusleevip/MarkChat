package com.mark.chat.common.pb.req;

import com.baidu.bjf.remoting.protobuf.annotation.ProtobufClass;
import lombok.AccessLevel;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

/**
 * @author Mark
 * @date 2023/4/7
 */
@ToString
@ProtobufClass
@FieldDefaults(level = AccessLevel.PUBLIC)
public class MessageReq {
    Long id;
    Integer type;
    String content;
}
