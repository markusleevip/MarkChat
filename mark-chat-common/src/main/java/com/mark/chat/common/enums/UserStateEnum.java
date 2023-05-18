package com.mark.chat.common.enums;

import lombok.Getter;

/**
 * @author Mark
 * @date 2023/4/14
 */
@Getter
public enum UserStateEnum {

    /**
     * 正常用户
     */
    NORMAL(1),
    /**
     * 被封杀
     */
    BLOCKED(2),
    ;
    private Integer state;

    UserStateEnum(Integer state) {
        this.state = state;
    }

    public static UserStateEnum getInstance(Integer state) {
        for (UserStateEnum value : UserStateEnum.values()) {
            if (value.getState() == state){
                return value;
            }
        }
        return null;
    }
}
