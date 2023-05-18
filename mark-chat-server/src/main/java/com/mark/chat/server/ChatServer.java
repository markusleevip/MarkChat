package com.mark.chat.server;

import com.iohao.game.action.skeleton.core.BarSkeleton;
import com.iohao.game.action.skeleton.core.BarSkeletonBuilder;
import com.iohao.game.action.skeleton.core.BarSkeletonBuilderParamConfig;
import com.iohao.game.action.skeleton.core.flow.interal.DebugInOut;
import com.iohao.game.bolt.broker.client.AbstractBrokerClientStartup;
import com.iohao.game.bolt.broker.core.client.BrokerAddress;
import com.iohao.game.bolt.broker.core.client.BrokerClient;
import com.iohao.game.bolt.broker.core.client.BrokerClientBuilder;
import com.iohao.game.common.kit.NetworkKit;
import com.mark.chat.server.action.PrivateMessageAction;
import com.mark.chat.server.action.UserAction;

/**
 * @author Mark
 * @date 2023/5/12
 */
public class ChatServer extends AbstractBrokerClientStartup {

    private int brokerPort = 10200;

    private String brokerIp = NetworkKit.LOCAL_IP;

    public ChatServer(int brokerPort){
        this.brokerPort = brokerPort;
    }

    public ChatServer(int brokerPort,String brokerIp){
        this.brokerPort = brokerPort;
        this.brokerIp = brokerIp;
    }
    @Override
    public BarSkeleton createBarSkeleton() {
        BarSkeletonBuilderParamConfig config = new BarSkeletonBuilderParamConfig()
                .scanActionPackage(PrivateMessageAction.class)
                .scanActionPackage(UserAction.class);

        BarSkeletonBuilder builder = config.createBuilder();
        builder.addInOut(new DebugInOut());
        return builder.build();
    }

    @Override
    public BrokerClientBuilder createBrokerClientBuilder() {
        BrokerClientBuilder builder = BrokerClient.newBuilder();
        builder.appName("聊天服务");
        return builder;
    }

    @Override
    public BrokerAddress createBrokerAddress() {
        return new BrokerAddress(brokerIp, brokerPort);
    }
}
