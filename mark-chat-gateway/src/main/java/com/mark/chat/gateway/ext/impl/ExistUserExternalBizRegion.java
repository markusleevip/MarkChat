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
import com.iohao.game.action.skeleton.core.exception.MsgException;
import com.iohao.game.action.skeleton.protocol.external.RequestCollectExternalMessage;
import com.mark.chat.gateway.ext.ExternalBizRegion;
import com.mark.chat.gateway.ext.ExternalBizRegionContext;
import com.mark.chat.gateway.session.UserSessions;
import com.iohao.game.core.common.client.ExternalBizCodeCont;

import java.io.Serializable;

/**
 * 用户（玩家）是否存在
 * <pre>
 *     对外服业务扩展
 * </pre>
 *
 * @author 渔民小镇
 * @date 2022-07-27
 */
public class ExistUserExternalBizRegion implements ExternalBizRegion {
    @Override
    public int getBizCode() {
        return ExternalBizCodeCont.existUser;
    }

    @Override
    public Serializable request(ExternalBizRegionContext regionContext) throws MsgException {

        RequestCollectExternalMessage request = regionContext.getRequestCollectExternalMessage();

        long userId = request.getData();
        // true 用户存在游戏对外服中
        boolean existUser = UserSessions.me().existUserSession(userId);

        ActionErrorEnum.dataNotExist.assertTrue(existUser);

        return null;
    }
}
