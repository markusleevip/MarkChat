package com.mark.chat.common.enums;

import com.mark.chat.common.cmd.CmdChat;
import com.mark.chat.common.cmd.CmdUser;
import lombok.Getter;

/**
 * @author Mark
 * @date 2023/4/20
 */
@Getter
public enum RouteEnum {
    HEART_BEAT("心跳接口", 0, 0, 10001),
    USER_LOGIN("用户登录", CmdUser.cmd, CmdUser.login, 10003),
    USER_LOGOUT("用户退出", CmdUser.cmd, CmdUser.logout, 10005),
    CHAT_SEND_PRIVATE("发送私信", CmdChat.cmd, CmdChat.send_private_msg, 30001),
    CHAT_SEND_GROUP("发送群组消息", CmdChat.cmd, CmdChat.send_group_msg, 30003),
    CHAT_RECEIVE_PRIVATE("接收私信", CmdChat.cmd, CmdChat.receive_private_msg, 30005),
    CHAT_RECEIVE_GROUP("接收群消息", CmdChat.cmd, CmdChat.receive_group_msg, 30007),
    ;
    private final String name;
    private final int cmd;
    private final int subCmd;
    private final int routeId;


    RouteEnum(String name, int cmd, int subCmd, int routeId) {
        this.name = name;
        this.cmd = cmd;
        this.subCmd = subCmd;
        this.routeId = routeId;
    }

    public static RouteEnum getRoute(int routeId) {
        for (RouteEnum value : RouteEnum.values()) {
            if (value.routeId == routeId) {
                return value;
            }
        }
        return null;
    }


}
