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
import com.mark.chat.gateway.session.UserChannelId;
import com.mark.chat.gateway.session.UserSessions;
import com.iohao.game.bolt.broker.core.common.AbstractAsyncUserProcessor;
import com.iohao.game.bolt.broker.core.common.IoGameGlobalConfig;
import com.iohao.game.bolt.broker.core.message.SettingUserIdMessage;
import com.iohao.game.bolt.broker.core.message.SettingUserIdMessageResponse;
import com.iohao.game.common.kit.log.IoGameLoggerFactory;
import org.slf4j.Logger;

/**
 * 设置 userId 的处理器
 *
 * @author 渔民小镇
 * @date 2022-01-18
 */
public class SettingUserIdMessageExternalProcessor extends AbstractAsyncUserProcessor<SettingUserIdMessage> {
    static final Logger log = IoGameLoggerFactory.getLoggerCommon();

    @Override
    public void handleRequest(BizContext bizCtx, AsyncContext asyncCtx, SettingUserIdMessage request) {

        long userId = request.getUserId();
        String channelId = request.getUserChannelId();

        UserChannelId userChannelId = new UserChannelId(channelId);

        // 当设置好玩家 id ，也表示着已经身份验证了（表示登录过了）。
        boolean result = UserSessions.me().settingUserId(userChannelId, userId);

        SettingUserIdMessageResponse response = new SettingUserIdMessageResponse();
        response.setSuccess(result);
        response.setUserId(userId);

        asyncCtx.sendResponse(response);

        if (IoGameGlobalConfig.isExternalLog()) {
            log.info("3 对外服设置用户id, userChannelId:{}, 真实userId:{}", userChannelId, userId);
        }
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
        return SettingUserIdMessage.class.getName();
    }
}
