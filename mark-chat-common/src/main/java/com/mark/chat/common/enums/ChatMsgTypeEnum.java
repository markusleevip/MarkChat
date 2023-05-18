package com.mark.chat.common.enums;

import lombok.Getter;

/**
 * 聊天消息类型
 * @author Mark
 * @date 2023/4/27
 */
@Getter
public enum ChatMsgTypeEnum {


    TEXT(1,"文本消息"),
    IMAGE(2,"图片消息"),
    VOICE(3,"语音消息"),
    MUSIC(4,"音乐消息"),
    VIDEO(5,"视频消息"),;

    private int code;
    private String name;

    ChatMsgTypeEnum(int code, String name){
        this.code = code;
        this.name = name;
    }
}
