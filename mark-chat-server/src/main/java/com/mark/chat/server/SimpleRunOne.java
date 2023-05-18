
package com.mark.chat.server;

import com.iohao.game.action.skeleton.core.ActionCommandRegionGlobalCheckKit;
import com.iohao.game.action.skeleton.core.doc.BarSkeletonDoc;
import com.iohao.game.action.skeleton.toy.IoGameBanner;
import com.iohao.game.bolt.broker.client.AbstractBrokerClientStartup;
import com.iohao.game.bolt.broker.client.BrokerClientApplication;
import com.iohao.game.bolt.broker.server.BrokerServer;
import com.iohao.game.common.kit.ExecutorKit;
import com.iohao.game.common.kit.log.IoGameLoggerFactory;
import com.mark.chat.gateway.ExternalServer;
import com.mark.chat.gateway.bootstrap.ExternalJoinEnum;
import lombok.AccessLevel;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 简单的启动器： 对外服、网关服、逻辑服
 * 谐音:拳皇98中的 round one ready go!
 * <pre>
 *     注意：
 *          这个工具只适合单机的开发或本地一体化的开发, 对于分步式不适合。
 *
 *          当然如果打算开发单体应用，这种方式是很合适的。
 * </pre>
 *
 */
@Setter
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public final class SimpleRunOne {
    static final Logger log = IoGameLoggerFactory.getLoggerCommonStdout();

    final ExecutorService executorService = ExecutorKit.newCacheThreadPool(SimpleRunOne.class.toString());

    /** 对外服 */
    ExternalServer externalServer;
    /** 逻辑服 */
    List<AbstractBrokerClientStartup> logicServerList;

    /** broker 游戏网关 */
    BrokerServer brokerServer;
    /** true 在本地启动 broker （游戏网关） */
    boolean runBrokerServer = true;

    /**
     * 简单的快速启动
     * <pre>
     *     快速启动:
     *          对外服
     *          网关服
     *          逻辑服
     * </pre>
     */
    public void startup() {
        banner();

        // 启动网关
        if (this.runBrokerServer) {

            if (Objects.isNull(this.brokerServer)) {
                this.brokerServer = BrokerServer.newBuilder().build();
            }

            this.executorService.execute(this.brokerServer::startup);
        }

        // 启动逻辑服、对外服
        this.startupLogic();

        // 全局重复路由检测工具
        ActionCommandRegionGlobalCheckKit.checkGlobalExistSubCmd();
    }

    /**
     * 禁用 broker （游戏网关）
     * <pre>
     *     本地不启动游戏网关
     *     团队开发中可以把 broker （游戏网关）单独部署在一台机器上
     *     而本机启动的逻辑服连接到这台单独部署的游戏网关上，这样就可以共用 broker （游戏网关），
     *     不用每次在本机启动，这样调试起来也方便
     * </pre>
     *
     * @return this
     */
    public SimpleRunOne disableBrokerServer() {
        this.runBrokerServer = false;
        return this;
    }

    /**
     * 设置一个连接类型为 websocket 的游戏对外服
     *
     * @param externalPort 游戏对外服端口
     * @return this
     */
    public SimpleRunOne setExternalServer(int externalPort) {
        return this.setExternalServer(ExternalJoinEnum.WEBSOCKET, externalPort);
    }

    /**
     * 设置一个游戏对外服
     *
     * @param externalJoinEnum 连接类型
     * @param externalPort     游戏对外服端口
     * @return this
     */
    public SimpleRunOne setExternalServer(ExternalJoinEnum externalJoinEnum, int externalPort) {
        this.externalServer = SimpleHelper.createExternalServer(externalJoinEnum, externalPort);
        return this;
    }

    /**
     * 设置一个游戏对外服
     *
     * @param externalServer 游戏对外服
     * @return this
     */
    public SimpleRunOne setExternalServer(ExternalServer externalServer) {
        this.externalServer = externalServer;
        return this;
    }

    protected void startupLogic() {

        if (Objects.nonNull(this.logicServerList)) {
            // 启动游戏逻辑服
            this.executorService.execute(() -> this.logicServerList.forEach(BrokerClientApplication::start));
        }

        if (Objects.nonNull(this.externalServer)) {
            // 启动游戏对外服
            this.executorService.execute(() -> this.externalServer.startup());
        }

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
        }

        // 生成游戏文档
        this.executorService.execute(BarSkeletonDoc.me()::buildDoc);
    }

    private void banner() {

        int num = 0;

        if (Objects.nonNull(this.logicServerList)) {
            num += this.logicServerList.size();
        }

        if (Objects.nonNull(this.externalServer)) {
            num++;
        }

        if (this.runBrokerServer) {
            num++;
        }

        IoGameBanner.me().initCountDownLatch(num);

        IoGameBanner.render();

    }

}
