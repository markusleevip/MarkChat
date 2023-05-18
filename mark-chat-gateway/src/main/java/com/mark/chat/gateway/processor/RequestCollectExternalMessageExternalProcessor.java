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
package com.mark.chat.gateway.processor;

import com.alipay.remoting.AsyncContext;
import com.alipay.remoting.BizContext;
import com.iohao.game.action.skeleton.core.exception.ActionErrorEnum;
import com.iohao.game.action.skeleton.core.exception.MsgException;
import com.iohao.game.action.skeleton.protocol.external.RequestCollectExternalMessage;
import com.iohao.game.action.skeleton.protocol.external.ResponseCollectExternalItemMessage;
import com.mark.chat.gateway.ext.ExternalBizRegion;
import com.mark.chat.gateway.ext.ExternalBizRegionContext;
import com.mark.chat.gateway.ext.ExternalBizRegions;
import com.iohao.game.bolt.broker.core.common.AbstractAsyncUserProcessor;
import com.iohao.game.common.kit.log.IoGameLoggerFactory;
import org.slf4j.Logger;

import java.io.Serializable;
import java.util.Objects;

/**
 * 处理来自游戏逻辑服的请求，并响应结果给请求方
 *
 * @author 渔民小镇
 * @date 2022-07-27
 */
public class RequestCollectExternalMessageExternalProcessor extends AbstractAsyncUserProcessor<RequestCollectExternalMessage> {
    static final Logger log = IoGameLoggerFactory.getLoggerCommon();

    @Override
    public void handleRequest(BizContext bizCtx, AsyncContext asyncCtx, RequestCollectExternalMessage request) {

        int bizCode = request.getBizCode();
        // 通过业务码得到对应的业务处理类
        ExternalBizRegion externalBizRegion = ExternalBizRegions.me().getExternalRegion(bizCode);

        // 通常是开发者忘记配置对应的处理类
        if (Objects.isNull(externalBizRegion)) {
            log.error("{} - 游戏对外服对应的业务类不存在", bizCode);

            // 对外服业务处理不存在
            var itemMessage = new ResponseCollectExternalItemMessage();
            itemMessage.setError(ActionErrorEnum.classNotExist);
            // 返回结果给请求端
            asyncCtx.sendResponse(itemMessage);

            return;
        }

        ResponseCollectExternalItemMessage itemMessage = new ResponseCollectExternalItemMessage();

        try {
            ExternalBizRegionContext context = new ExternalBizRegionContext();
            context.setRequestCollectExternalMessage(request);
            Serializable data = externalBizRegion.request(context);
            itemMessage.setData(data);
        } catch (Throwable e) {
            if (e instanceof MsgException msgException) {
                itemMessage.setError(msgException);
            } else {
                // 不是自定义的异常才打印 error 信息
                log.error(e.getMessage(), e);
                itemMessage.setError(ActionErrorEnum.systemOtherErrCode);
            }
        }

        // 返回结果给请求端
        asyncCtx.sendResponse(itemMessage);
    }

    @Override
    public String interest() {
        return RequestCollectExternalMessage.class.getName();
    }
}
