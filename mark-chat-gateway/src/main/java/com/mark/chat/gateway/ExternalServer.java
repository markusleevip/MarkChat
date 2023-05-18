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
package com.mark.chat.gateway;

import com.iohao.game.action.skeleton.toy.IoGameBanner;
import com.iohao.game.bolt.broker.client.BrokerClientApplication;
import com.mark.chat.gateway.bootstrap.ExternalJoinEnum;
import com.mark.chat.gateway.simple.ExternalBrokerClientStartup;
import com.iohao.game.bolt.broker.core.client.BrokerAddress;
import com.iohao.game.bolt.broker.core.client.BrokerClient;
import com.iohao.game.bolt.broker.core.common.IoGameGlobalConfig;
import com.iohao.game.common.kit.log.IoGameLoggerFactory;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;

import java.net.InetSocketAddress;
import java.util.Objects;

/**
 * 对外的服务器
 *
 * @author 渔民小镇
 * @date 2022-01-09
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
public final class ExternalServer {
    static final Logger log = IoGameLoggerFactory.getLoggerCommonStdout();

    /** netty 服务器，与真实用户对接 */
    final ServerBootstrap bootstrap;
    /** ip */
    final String ip;
    /** 对外服端口 */
    final int port;
    final ExternalBrokerClientStartup externalBoltBrokerClientStartup;
    final ExternalJoinEnum externalJoinEnum;
    /** 设置 broker （游戏网关）连接地址 */
    BrokerAddress brokerAddress;

    ExternalServer(ExternalServerBuilder builder) {
        this.port = builder.port;
        this.ip = builder.ip;
        this.bootstrap = builder.bootstrap;
        this.externalBoltBrokerClientStartup = builder.externalBoltBrokerClientStartup;
        this.brokerAddress = builder.brokerAddress;
        this.externalJoinEnum = builder.externalJoinEnum;
    }

    /**
     * 启动对外服
     *
     * @throws InterruptedException e
     */
    private void doStart() throws InterruptedException {
        // channelFuture
        ChannelFuture channelFuture = bootstrap.bind(new InetSocketAddress(ip, port)).sync();

        if (IoGameGlobalConfig.openLog) {
            log.info("启动游戏对外服 port: [{}] 连接方式: [{}] ", port, externalJoinEnum.getName());
        }

        IoGameBanner.render();

        channelFuture.channel().closeFuture().sync();
    }

    private void startupExternalBoltBrokerClient() {

        // 保存与 broker 通信的 client
        var brokerClientBuilder = BrokerClientApplication.initConfig(this.externalBoltBrokerClientStartup);

        // 重新设置 broker 的连接地址，以对外服的为准
        if (Objects.nonNull(this.brokerAddress)) {
            brokerClientBuilder.brokerAddress(this.brokerAddress);
        }

        BrokerClient brokerClient = BrokerClientApplication.start(brokerClientBuilder);
        ExternalHelper.me().brokerClient = brokerClient;

        this.externalBoltBrokerClientStartup.startupSuccess(brokerClient);
    }

    /**
     * 启动对外服
     */
    public void startup() {

        // 启动内部逻辑服, 用于连接 broker （游戏网关）服务器
        this.startupExternalBoltBrokerClient();

        try {
            // 启动对外服
            this.doStart();
        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
        }
    }

    public void shutdown() {

    }

    public static ExternalServerBuilder newBuilder(int port) {
        return new ExternalServerBuilder(port);
    }

}
