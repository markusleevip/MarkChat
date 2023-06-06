package com.mark.chat.bootstrap;

import com.iohao.game.action.skeleton.ext.spring.ActionFactoryBeanForSpring;
import com.mark.chat.server.MarkChatStart;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

/**
 * @author Mark
 * @date 2023/5/25
 */
@Component
@Slf4j
public class MarkChatBootstrap {

    @Value("${mark-chat.enable-idle:false}")
    private boolean enableIdle;
    @Value("${mark-chat.enable-cluster:false}")
    private boolean enableCluster;
    @Value("${mark-chat.external-port:5050}")
    private int externalPort;
    @Value("${mark-chat.gateway-ip:127.0.0.1}")
    private String gatewayIP;
    @Value("${mark-chat.gateway-port:17070}")
    private int gatewayPort;

    @Bean
    @DependsOn("actionFactoryBean")
    public void startGame() {
        log.info("startGame.enableIdle:{},enableCluster:{},externalPort:{},gatewayPort:{}", enableIdle, enableCluster, externalPort, gatewayPort);
        if (enableCluster) {
        } else {
            new MarkChatStart().run(externalPort, gatewayPort, enableIdle);
        }
    }

    @Bean
    public ActionFactoryBeanForSpring actionFactoryBean() {
        // 将业务框架交给 spring 管理
        return ActionFactoryBeanForSpring.me();
    }
}
