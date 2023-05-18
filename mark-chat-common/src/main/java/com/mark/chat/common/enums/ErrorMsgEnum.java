package com.mark.chat.common.enums;

/**
 * @author Mark
 * @date 2023/4/6
 */
public enum ErrorMsgEnum {
    ROUTE_ERROR(1002, "路由错误"),
    HEARTBEAT_TIMEOUT(1003, "心跳超时"),
    USER_LOGIN_ERROR(2001, "登录失败"),
    USER_REPEAT_ERROR(2002, "用户重复登录"),
    USER_BLOCKED(2003, "账号被封杀"),
    USER_LOGOUT(2004, "用户退出"),
    ;

    private int code;
    private String msg;

    ErrorMsgEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static ErrorMsgEnum getInstance(int code) {
        for (ErrorMsgEnum value : ErrorMsgEnum.values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        return null;
    }
}
