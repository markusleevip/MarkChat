package com.mark.chat.common.cmd;

/**
 * 用户路由
 *
 * @author Mark
 * @date 2023/4/12
 */
public interface CmdUser {
    int cmd = CmdModule.user_cmd;

    /**
     * 登录
     */
    int login = 1;

    /**
     * 退出
     */
    int logout = 2;

}
