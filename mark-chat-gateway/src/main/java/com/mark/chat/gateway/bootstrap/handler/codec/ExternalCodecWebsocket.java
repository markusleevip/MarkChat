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
package com.mark.chat.gateway.bootstrap.handler.codec;

import com.iohao.game.action.skeleton.core.DataCodecKit;
import com.mark.chat.common.ChatConstant;
import com.mark.chat.common.enums.RouteEnum;
import com.mark.chat.gateway.bootstrap.ExternalKit;
import com.mark.chat.gateway.bootstrap.message.ExternalMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;

import java.util.List;
import java.util.Objects;

/**
 * websocket  编解码
 *
 * @author 渔民小镇
 * @date 2022-01-22
 */
@ChannelHandler.Sharable
public class ExternalCodecWebsocket extends MessageToMessageCodec<BinaryWebSocketFrame, ExternalMessage> {
    @Override
    protected void encode(ChannelHandlerContext ctx, ExternalMessage msg, List<Object> out) throws Exception {
        // 【对外服】 发送消息 给 游戏客户端
        if (Objects.isNull(msg)) {
            throw new Exception("The encode ExternalMessage is null");
        }
        int state = msg.getResponseStatus();

        int dataLength = 0;

        byte[] dataBytes = msg.getData();
        if (dataBytes != null) {
            dataLength = dataBytes.length;
        }
        // 使用默认 buffer 。如果没有做任何配置，通常默认实现为池化的 direct （直接内存，也称为堆外内存）
        ByteBuf byteBuf = ctx.alloc().buffer(dataLength + ChatConstant.RESPONSE_HEAD_SIZE);
        byteBuf.writeInt(msg.getRouteId());
        byteBuf.writeInt(state);
        if (dataBytes != null) {
            byteBuf.writeBytes(dataBytes);
        }
        BinaryWebSocketFrame socketFrame = new BinaryWebSocketFrame(byteBuf);
        out.add(socketFrame);
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, BinaryWebSocketFrame binary, List<Object> out) {
        // 解码器 - 字节数组 ---> ExternalMessage
        ByteBuf content = binary.content();
        int routeId = content.readInt();
        byte[] msgBytes = new byte[content.readableBytes()];
        content.readBytes(msgBytes);
        RouteEnum routeEnum = RouteEnum.getRoute(routeId);
        ExternalMessage message = ExternalKit.createExternalMessage(routeEnum);
        message.setData(msgBytes);
        message.setRouteId(routeId);
        // 【对外服】 接收 游戏客户端的消息
        out.add(message);
    }
}
