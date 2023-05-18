package com.mark.chat.common.cmd;

/**
 * @author Mark
 * @date 2023/4/27
 */
public interface CmdChat {

    int cmd = CmdModule.chat_cmd;

    /**
     * 发送私信
     */
    int send_private_msg = 1;

    /**
     * 发送群组消息
     */
    int send_group_msg = 2;

    /**
     * 接收私信
     */
    int receive_private_msg = 3;

    /**
     * 接收群消息
     */
    int receive_group_msg = 4;

}
