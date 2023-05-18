package com.mark.chat.common.enums;

import lombok.Getter;

/**
 * 聊天类型
 * @author Mark
 * @date 2023/4/28
 */
@Getter
public enum ChatTypeEnum {


    PRIVATE_MSG(1,"私聊消息"),
    GROUP_MSG(2,"群聊消息"),
    SYSTEM_MSG(3,"系统消息"),;

    private int code;
    private String name;

    ChatTypeEnum(int code, String name){
        this.code = code;
        this.name = name;
    }
}
