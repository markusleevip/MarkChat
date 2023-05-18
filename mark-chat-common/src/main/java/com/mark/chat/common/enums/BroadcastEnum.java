package com.mark.chat.common.enums;

import lombok.Getter;

/**
 * @author Mark
 * @date 2023/4/20
 */
@Getter
public enum BroadcastEnum {

    NOTICE_MSG(50000,"通知消息"),

    BROADCAST_USER_ONLINE(20002,"用户上线"),

    BROADCAST_USER_OFFLINE(20004,"用户下线"),

    BROADCAST_ONLINE_USERS(20006,"在线用户列表"),

    BROADCAST_CHAT_PRIVATE_MSG(23002,"通知用户收到私信"),

    BROADCAST_CHAT_GROUP_MSG(23004,"通知用户收到群组消息"),
    ;
    private final int code;
    private final String msg;

    BroadcastEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
