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
package com.mark.chat.gateway.ext.impl;

import com.iohao.game.action.skeleton.core.exception.ActionErrorEnum;
import com.iohao.game.action.skeleton.protocol.external.RequestCollectExternalMessage;
import com.mark.chat.gateway.bootstrap.ExternalKit;
import com.mark.chat.gateway.bootstrap.message.ExternalMessage;
import com.mark.chat.gateway.ext.ExternalBizRegion;
import com.mark.chat.gateway.ext.ExternalBizRegionContext;
import com.mark.chat.gateway.session.UserSession;
import com.mark.chat.gateway.session.UserSessions;
import com.iohao.game.core.common.client.ExternalBizCodeCont;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;

import java.io.Serializable;

/**
 * 强制用户（玩家）下线
 * <pre>
 *     对外服业务扩展
 * </pre>
 *
 * @author 渔民小镇
 * @date 2022-07-27
 */
public class ForcedOfflineExternalBizRegion implements ExternalBizRegion {

    @Override
    public int getBizCode() {
        return ExternalBizCodeCont.forcedOffline;
    }

    @Override
    public Serializable request(ExternalBizRegionContext regionContext) {
        RequestCollectExternalMessage request = regionContext.getRequestCollectExternalMessage();

        long userId = request.getData();
        UserSessions userSessions = UserSessions.me();

        // true 表示用户存在对外服
        boolean existUser = userSessions.existUserSession(userId);

        if (existUser) {
            // 强制下线
            UserSession userSession = userSessions.getUserSession(userId);
            // 发送强制下线消息
            this.sendForcedOfflineMsg(userSession);
        }

        return null;
    }

    private void sendForcedOfflineMsg(UserSession userSession) {

        ExternalMessage externalMessage = ExternalKit.createExternalMessage();
        // 强制玩家下线 状态码
        externalMessage.setResponseStatus(ActionErrorEnum.forcedOffline.getCode());
        externalMessage.setValidMsg(ActionErrorEnum.forcedOffline.getMsg());

        Channel channel = userSession.getChannel();
        channel.writeAndFlush(externalMessage).addListener((ChannelFutureListener) future -> {
            // 回调 UserSessions 中移除对应的玩家
            UserSessions.me().removeUserSession(userSession);
        });
    }
}
