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
package com.mark.chat.gateway.bootstrap.option;

import com.mark.chat.gateway.bootstrap.ServerBootstrapEventLoopGroupOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.ServerChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 服务端 for other nio 处理类
 *
 * <pre>
 * other system:
 *     Windows, Solaris
 * </pre>
 *
 * @author 渔民小镇
 * @date 2022-01-09
 */
public class ServerBootstrapEventLoopGroupOptionForOther implements ServerBootstrapEventLoopGroupOption {
    @Override
    public EventLoopGroup bossGroup() {
        return new NioEventLoopGroup(
                1,
                ServerBootstrapEventLoopGroupOption.bossThreadFactory()
        );
    }

    @Override
    public EventLoopGroup workerGroup() {
        int availableProcessors = Runtime.getRuntime().availableProcessors() << 1;

        return new NioEventLoopGroup(
                availableProcessors,
                ServerBootstrapEventLoopGroupOption.workerThreadFactory()
        );
    }

    @Override
    public Class<? extends ServerChannel> channelClass() {
        return NioServerSocketChannel.class;
    }
}
