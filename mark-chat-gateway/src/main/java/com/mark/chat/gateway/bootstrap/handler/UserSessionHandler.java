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

import com.mark.chat.gateway.session.UserSession;
import com.mark.chat.gateway.session.UserSessions;
import com.iohao.game.common.kit.log.IoGameLoggerFactory;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;

/**
 * 管理 UserSession 的 Handler
 *
 * @author 渔民小镇
 * @date 2023-02-14
 */
@ChannelHandler.Sharable
public final class UserSessionHandler extends ChannelInboundHandlerAdapter {
    static final Logger log = IoGameLoggerFactory.getLoggerCommon();

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        // 加入到 session 管理
        UserSessions.me().add(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        // 从 session 管理中移除
        UserSession userSession = UserSessions.me().getUserSession(ctx);
        UserSessions.me().removeUserSession(userSession);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        log.error(cause.getMessage(), cause);

        // 从 session 管理中移除
        UserSession userSession = UserSessions.me().getUserSession(ctx);
        UserSessions.me().removeUserSession(userSession);
    }
}
