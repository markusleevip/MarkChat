/*
 * ioGame
 * Copyright (C) 2021 - 2023  渔民小镇 （262610965@qq.com、luoyizhu@gmail.com） . All Rights Reserved.
 * # iohao.com . 渔民小镇
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package com.mark.chat.gateway.bootstrap.handler;

import com.mark.chat.gateway.bootstrap.heart.IdleHook;
import com.mark.chat.gateway.bootstrap.message.ExternalMessage;
import com.mark.chat.gateway.bootstrap.message.ExternalMessageCmdCode;
import com.mark.chat.gateway.session.UserSessions;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;

import java.util.Objects;

/**
 * 心跳 handler
 *
 * @author 渔民小镇
 * @date 2022-03-13
 */
@ChannelHandler.Sharable
public final class IdleHandler extends ChannelInboundHandlerAdapter {

    /** 心跳事件回调 */
    final IdleHook idleHook;
    /** true : 响应心跳给客户端 */
    final boolean pong;

    public IdleHandler(IdleHook idleHook, boolean pong) {
        this.idleHook = idleHook;
        this.pong = pong;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {

        ExternalMessage externalMessage = (ExternalMessage) msg;

        // 心跳处理
        int cmdCode = externalMessage.getCmdCode();

        if (cmdCode == ExternalMessageCmdCode.idle) {

            if (this.pong) {
                ctx.writeAndFlush(externalMessage);
            }

            return;
        }

        // 不是心跳请求. 交给下一个业务处理 (handler) , 下一个业务指的是你编排 handler 时的顺序
        ctx.fireChannelRead(msg);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent event) {

            boolean close = true;

            var userSession = UserSessions.me().getUserSession(ctx);

            // 执行用户定义的心跳回调事件处理
            if (Objects.nonNull(idleHook)) {
                close = idleHook.callback(ctx, event, userSession);
            }

            // close ctx
            if (close) {
                UserSessions.me().removeUserSession(userSession);
            }

        } else {
            super.userEventTriggered(ctx, evt);
        }
    }
}
