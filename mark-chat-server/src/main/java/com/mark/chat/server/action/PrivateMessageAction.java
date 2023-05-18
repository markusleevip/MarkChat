package com.mark.chat.server.action;

import com.iohao.game.action.skeleton.core.CmdInfo;
import com.iohao.game.bolt.broker.client.kit.ExternalCommunicationKit;
import com.iohao.game.bolt.broker.core.client.BrokerClientHelper;
import com.mark.chat.common.cmd.CmdBroadcast;
import com.mark.chat.common.cmd.CmdChat;
import com.mark.chat.common.enums.BroadcastEnum;
import com.mark.chat.common.enums.ChatMsgTypeEnum;
import com.mark.chat.common.enums.ChatSendStateEnum;
import com.mark.chat.common.enums.ChatTypeEnum;
import com.mark.chat.common.pb.chat.req.SendPrivateMessageReq;
import com.mark.chat.common.pb.chat.res.ReceivePrivateMessageRes;
import com.mark.chat.common.pb.chat.res.SendMessageRes;
import com.mark.chat.common.utils.StrUtils;
import com.iohao.game.action.skeleton.annotation.ActionController;
import com.iohao.game.action.skeleton.annotation.ActionMethod;
import com.iohao.game.action.skeleton.core.flow.FlowContext;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

/**
 * 发送私信消息
 *
 * @author Mark
 * @date 2023/4/27
 */
@Slf4j
@ActionController(CmdChat.cmd)
public class PrivateMessageAction {


    /**
     * 发送私信
     *
     * @param req
     * @return
     */
    @ActionMethod(CmdChat.send_private_msg)
    public SendMessageRes sendPrivateMessage(SendPrivateMessageReq req, FlowContext context) {
        log.info("messageReq,toId:{},msgType:{},body:{}", req.toId, req.msgType, req.body);
        var res = new SendMessageRes();
        res.state = ChatSendStateEnum.SUCCESS.getCode();
        boolean existUser = ExternalCommunicationKit.existUser(req.toId);
        if (existUser) {
            var broadcastContext = BrokerClientHelper.getBroadcastContext();
            CmdInfo cmd = CmdInfo.getCmdInfo(CmdBroadcast.cmd, CmdBroadcast.to_user);
            // BroadcastEnum.BROADCAST_CHAT_PRIVATE_MSG.getCode()
            broadcastContext.broadcast(cmd, convertReceivePrivateMessageRes(req, context.getUserId()), req.toId, BroadcastEnum.BROADCAST_CHAT_PRIVATE_MSG.getCode());

        }
        return res;
    }

    private ReceivePrivateMessageRes convertReceivePrivateMessageRes(SendPrivateMessageReq req, long uid) {
        var res = new ReceivePrivateMessageRes();
        res.body = req.body;
        res.senderId = uid;
        res.chatType = ChatTypeEnum.PRIVATE_MSG.getCode();
        res.msgType = ChatMsgTypeEnum.TEXT.getCode();
        res.sendTime = System.currentTimeMillis() / 1000;
        return res;
    }

}
