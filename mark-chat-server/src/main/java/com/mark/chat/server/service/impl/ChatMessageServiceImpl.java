package com.mark.chat.server.service.impl;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.Lists;
import com.iohao.game.action.skeleton.core.CmdInfo;
import com.iohao.game.bolt.broker.client.kit.ExternalCommunicationKit;
import com.iohao.game.bolt.broker.core.client.BrokerClientHelper;
import com.mark.chat.common.cmd.CmdBroadcast;
import com.mark.chat.common.doman.dto.PrivateMessageDto;
import com.mark.chat.common.enums.BroadcastEnum;
import com.mark.chat.common.pb.chat.res.ReceivePrivateMessageRes;
import com.mark.chat.server.service.ChatMessageService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Mark
 * @Date: 2023/5/24
 */
@Service
public class ChatMessageServiceImpl implements ChatMessageService {


    private Cache<Long, List<PrivateMessageDto>> msgCache = CacheBuilder.newBuilder().concurrencyLevel(8)
            .expireAfterWrite(3, TimeUnit.DAYS)
            .initialCapacity(10)
            .maximumSize(100000)
            .build();


    @Override
    public void saveMessage(PrivateMessageDto dto) {
        var msgList = msgCache.getIfPresent(dto.getToId());
        if (Objects.isNull(msgList)) {
            msgList = Lists.newArrayList();
        }
        msgList.add(dto);
        msgCache.put(dto.getToId(), msgList);
    }

    @Override
    public void getMessage(Long uid) {
        boolean existUser = ExternalCommunicationKit.existUser(uid);
        if (existUser) {
            var broadcastContext = BrokerClientHelper.getBroadcastContext();
            CmdInfo cmd = CmdInfo.getCmdInfo(CmdBroadcast.cmd, CmdBroadcast.to_user);
            var msgList = msgCache.getIfPresent(uid);
            if (!CollectionUtils.isEmpty(msgList)) {
                for (PrivateMessageDto dto : msgList) {
                    broadcastContext.broadcast(cmd, convertReceivePrivateMessageRes(dto), dto.getToId(), BroadcastEnum.BROADCAST_CHAT_PRIVATE_MSG.getCode());
                }
                msgCache.put(uid, Lists.newArrayList());
            }
        }
    }

    private ReceivePrivateMessageRes convertReceivePrivateMessageRes(PrivateMessageDto dto) {
        var res = new ReceivePrivateMessageRes();
        res.body = dto.getBody();
        res.senderId = dto.getSenderId();
        res.chatType = dto.getChatType();
        res.msgType = dto.getMsgType();
        res.sendTime = dto.getSendTime();
        return res;
    }
}
