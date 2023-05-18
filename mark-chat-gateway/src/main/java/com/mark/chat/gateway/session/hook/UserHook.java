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
package com.mark.chat.gateway.session.hook;

import com.iohao.game.bolt.broker.client.kit.UserIdSettingKit;
import com.mark.chat.gateway.session.UserSession;

/**
 * UserHook 钩子接口，上线时、下线时会触发
 * <pre>
 *     参考
 *     <a href="https://www.yuque.com/iohao/game/tywkqv">用户连接登录编写文档</a>
 * </pre>
 *
 * <pre>
 *     实际上需要真正登录过，才会触发 ：into和quit 方法
 *     see {@link UserIdSettingKit#settingUserId}
 *
 *     这里是改变用户的验证状态
 *
 *     验证状态变更为 true -------- 真正登录过
 *     see {@link UserSession#setUserId}
 *     channel.attr(UserSessionAttr.verifyIdentity).set(true);
 *
 *     利用好该接口，可以把用户当前在线状态通知到逻辑服，比如使用 redis PubSub 之类的。
 * </pre>
 *
 * @author 渔民小镇
 * @date 2022-03-14
 */
public interface UserHook {
    /**
     * 用户进入，可以理解为上线
     *
     * @param userSession userSession
     */
    void into(UserSession userSession);

    /**
     * 用户退出，可以理解为下线、离线通知等
     *
     * @param userSession userSession
     */
    void quit(UserSession userSession);
}
