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
package com.mark.chat.gateway.bootstrap.heart;

import com.iohao.game.action.skeleton.core.exception.ActionErrorEnum;
import com.mark.chat.gateway.bootstrap.ExternalKit;
import com.mark.chat.gateway.bootstrap.message.ExternalMessage;
import com.mark.chat.gateway.bootstrap.message.ExternalMessageCmdCode;
import com.mark.chat.gateway.session.UserSession;
import com.iohao.game.common.kit.log.IoGameLoggerFactory;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import org.slf4j.Logger;

/**
 * 默认的心跳事件回调，只做简单打印
 * <pre>
 *     关于心跳相关的可以参考这里：
 *     https://www.yuque.com/iohao/game/uueq3i
 * </pre>
 *
 * @author 渔民小镇
 * @date 2022-03-14
 */
public class IdleHookDefault implements IdleHook {
    static final Logger log = IoGameLoggerFactory.getLoggerCommon();

    @Override
    public boolean callback(ChannelHandlerContext ctx, IdleStateEvent event, UserSession userSession) {
        IdleState state = event.state();
        if (state == IdleState.READER_IDLE) {
            /* 读超时 */
            log.debug("READER_IDLE 读超时");
        } else if (state == IdleState.WRITER_IDLE) {
            /* 写超时 */
            log.debug("WRITER_IDLE 写超时");
        } else if (state == IdleState.ALL_IDLE) {
            /* 总超时 */
            log.debug("ALL_IDLE 总超时");
        }

        ExternalMessage externalMessage = ExternalKit.createExternalMessage();
        // 请求命令类型: 心跳
        externalMessage.setCmdCode(ExternalMessageCmdCode.idle);
        // 错误码
        externalMessage.setResponseStatus(ActionErrorEnum.idleErrorCode.getCode());
        // 错误消息
        externalMessage.setValidMsg(ActionErrorEnum.idleErrorCode.getMsg() + " : " + state.name());

        // 通知客户端，触发了心跳事件
        ctx.writeAndFlush(externalMessage);

        return true;
    }
}
