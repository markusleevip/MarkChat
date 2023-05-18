/*
 * ioGame
 * Copyright (C) 2021 - 2023  渔民小镇 （262610965@qq.com、luoyizhu@gmail.com） . All Rights Reserved.
 * # iohao.com . 渔民小镇
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General  License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General  License for more details.
 *
 * You should have received a copy of the GNU General  License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package com.mark.chat.gateway.processor;

import com.alipay.remoting.AsyncContext;
import com.alipay.remoting.BizContext;
import com.iohao.game.action.skeleton.protocol.HeadMetadata;
import com.iohao.game.action.skeleton.protocol.ResponseMessage;
import com.mark.chat.gateway.bootstrap.ExternalKit;
import com.mark.chat.gateway.bootstrap.message.ExternalMessage;
import com.mark.chat.gateway.session.UserChannelId;
import com.mark.chat.gateway.session.UserSession;
import com.mark.chat.gateway.session.UserSessions;
import com.iohao.game.bolt.broker.core.common.AbstractAsyncUserProcessor;
import com.iohao.game.bolt.broker.core.common.IoGameGlobalConfig;
import com.iohao.game.common.kit.log.IoGameLoggerFactory;
import io.netty.channel.Channel;
import org.slf4j.Logger;

import java.util.Objects;


/**
 * 接收来自网关的响应
 * <pre>
 *     把响应 write 到客户端
 * </pre>
 *
 * @author 渔民小镇
 * @date 2022-01-18
 */
public class ResponseMessageExternalProcessor extends AbstractAsyncUserProcessor<ResponseMessage> {
    static final Logger log = IoGameLoggerFactory.getLoggerMsg();

    final UserChannelId emptyUserChannelId = new UserChannelId("empty");

    @Override
    public void handleRequest(BizContext bizCtx, AsyncContext asyncCtx, ResponseMessage responseMessage) {
        if (IoGameGlobalConfig.isExternalLog()) {
            log.info("接收来自网关的响应 {}", responseMessage);
        }

        ExternalMessage message = ExternalKit.convertExternalMessage(responseMessage);

        UserSession userSession = getUserSession(responseMessage);

        // 响应结果给用户
        if (userSession == null) {
            return;
        }

        Channel channel = userSession.getChannel();
        channel.writeAndFlush(message);
    }

    private UserSession getUserSession(ResponseMessage responseMessage) {
        HeadMetadata headMetadata = responseMessage.getHeadMetadata();
        long userId = headMetadata.getUserId();

        try {

            // 当存在 userId 时，并且可以找到对应的 UserSession
            if (userId > 0 && UserSessions.me().existUserSession(userId)) {
                return UserSessions.me().getUserSession(userId);
            } else {

                String channelId = headMetadata.getChannelId();

                final UserChannelId userChannelId = Objects.isNull(channelId)
                        ? emptyUserChannelId
                        : new UserChannelId(channelId);

                // 通过 channelId 来查找 UserSession
                return UserSessions.me().getUserSession(userChannelId);
            }

        } catch (RuntimeException e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }

    /**
     * 指定感兴趣的请求数据类型，该 UserProcessor 只对感兴趣的请求类型的数据进行处理；
     * 假设 除了需要处理 MyRequest 类型的数据，还要处理 java.lang.String 类型，有两种方式：
     * 1、再提供一个 UserProcessor 实现类，其 interest() 返回 java.lang.String.class.getName()
     * 2、使用 MultiInterestUserProcessor 实现类，可以为一个 UserProcessor 指定 List<String> multiInterest()
     *
     * @return 自定义处理器
     */
    @Override
    public String interest() {
        return ResponseMessage.class.getName();
    }
}
