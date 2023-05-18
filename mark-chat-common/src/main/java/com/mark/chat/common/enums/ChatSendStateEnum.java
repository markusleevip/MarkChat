package com.mark.chat.common.enums;

import lombok.Getter;

/**
 * 发送聊天消息状态
 * @author Mark
 * @date 2023/4/27
 */
@Getter
public enum ChatSendStateEnum {

    // 消息状态 1 发送成功 2  对方不是你的好友 3 发送失败,内容不合法  success

    SUCCESS(1,"发送成功"),
    NOT_FRIEND (2,"对方不是你的好友"),
    FAIL_NOT_PASS(3,"发送失败,内容不通过"),
    ;

    private int code;
    private String name;

    ChatSendStateEnum(int code,String name) {
        this.code = code;
        this.name = name;
    }

}
