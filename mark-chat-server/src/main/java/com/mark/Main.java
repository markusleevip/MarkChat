package com.mark;

import com.iohao.game.bolt.broker.client.AbstractBrokerClientStartup;
import com.iohao.game.bolt.broker.core.client.BrokerAddress;
import com.iohao.game.bolt.broker.server.BrokerServer;
import com.iohao.game.common.kit.NetworkKit;
import com.mark.chat.common.cmd.CmdUser;
import com.mark.chat.gateway.ExternalServer;
import com.mark.chat.gateway.ExternalServerBuilder;
import com.mark.chat.gateway.bootstrap.ExternalJoinEnum;
import com.mark.chat.gateway.config.ExternalGlobalConfig;
import com.mark.chat.server.ChatServer;
import com.mark.chat.server.SimpleRunOne;

import java.util.List;

/**
 * @author Mark
 * @date 2023/5/12
 */
public class Main {
    public static void main(String[] args) {
        int gatewayPort = 3030;
        int externalPort = 5050;
        System.out.println("Hello world!");
        List<AbstractBrokerClientStartup> logicList = List.of(
                new ChatServer(gatewayPort)
        );
        String localIp = NetworkKit.LOCAL_IP;

        ExternalServer externalServer = createExternalServer(externalPort,localIp,gatewayPort,false);

        var accessAuthenticationHook = ExternalGlobalConfig.accessAuthenticationHook;
        accessAuthenticationHook.setVerifyIdentity(true);
        accessAuthenticationHook.addIgnoreAuthenticationCmd(CmdUser.cmd, CmdUser.login);


        var brokerServer = BrokerServer.newBuilder().port(gatewayPort).build();
        new SimpleRunOne()
                // 游戏对外服
                .setExternalServer(externalServer)
                // 网关
                .setBrokerServer(brokerServer)
                // 游戏逻辑服列表
                .setLogicServerList(logicList)

                // 启动 游戏对外服、游戏网关、游戏逻辑服
                .startup();
    }

    public static ExternalServer createExternalServer(int externalPort, String gatewayIP, int gatewayPort, boolean enableIdle) {


        // 游戏对外服 - 构建器
        ExternalServerBuilder builder = ExternalServer.newBuilder(externalPort)
                // websocket 方式连接
                .externalJoinEnum(ExternalJoinEnum.WEBSOCKET)
                // Broker （游戏网关）的连接b地址；
                .brokerAddress(new BrokerAddress(gatewayIP, gatewayPort));
        if (enableIdle) {
            // 开启心跳检测
            builder.enableIdle();
        }
        // 构建游戏对外服
        return builder.build();
    }
}
