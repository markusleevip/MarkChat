package com.mark.chat.common.cmd;

/**
 * 广播路由
 * @author Mark
 * @date 2023/4/7
 */
public interface CmdBroadcast {
    int cmd = CmdModule.broadcast_cmd;

    int to_user = 1;

    int logout = 2;

    /**
     * 用户上线
     */
    int online = 3;

    /**
     * 用户下线
     */
    int offline = 4;

    /**
     * 广播用户列表
     */
    int online_user_list = 5;
}
