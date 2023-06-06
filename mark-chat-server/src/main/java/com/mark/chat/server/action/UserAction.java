package com.mark.chat.server.action;

import com.iohao.game.action.skeleton.annotation.ActionController;
import com.iohao.game.action.skeleton.annotation.ActionMethod;
import com.iohao.game.action.skeleton.core.exception.MsgException;
import com.iohao.game.action.skeleton.core.flow.FlowContext;
import com.iohao.game.bolt.broker.client.kit.ExternalCommunicationKit;
import com.iohao.game.bolt.broker.client.kit.UserIdSettingKit;
import com.mark.chat.common.cmd.CmdUser;
import com.mark.chat.common.enums.ErrorMsgEnum;
import com.mark.chat.common.pb.req.LoginReq;
import com.mark.chat.common.pb.res.LoginRes;
import com.mark.chat.server.service.ChatMessageService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

/**
 * @Author: Mark
 * @Date: 2023/5/16
 */
@Slf4j
@ActionController(CmdUser.cmd)
@Component
public class UserAction {


    @Resource
    private ThreadPoolTaskExecutor taskExecutor;

    @Resource
    private ChatMessageService chatMessageService;

    @ActionMethod(CmdUser.login)
    public LoginRes login(LoginReq req, FlowContext context) {
        if (req.uid == null || req.uid < 0) {
            // 参数错误
            throw new MsgException(ErrorMsgEnum.USER_LOGIN_ERROR.getCode(), ErrorMsgEnum.USER_LOGIN_ERROR.getMsg());
        }
        // 用户已经登录
        boolean existUser = ExternalCommunicationKit.existUser(req.uid);
        if (existUser) {
            throw new MsgException(ErrorMsgEnum.USER_REPEAT_ERROR.getCode(), ErrorMsgEnum.USER_REPEAT_ERROR.getMsg());
        }
        System.out.println("req:uid:" + req.uid);
        UserIdSettingKit.settingUserId(context, req.uid);
        LoginRes res = new LoginRes();
        res.uid = req.uid;
        res.nickname = "Guest" + req.uid;
        // 获取离线消息
        taskExecutor.execute(() -> {
            System.out.println("Task executed by thread: " + Thread.currentThread().getName());
            chatMessageService.getMessage(req.uid);
        });
        return res;
    }
}
