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
package com.iohao.game.action.skeleton.pulse.core.consumer;

import com.iohao.game.action.skeleton.pulse.core.PulseChannel;
import com.iohao.game.action.skeleton.pulse.message.PulseSignalRequest;

import java.io.Serializable;

/**
 * 接收-脉冲信号请求
 *
 * @author 渔民小镇
 * @date 2023-04-23
 */
public interface PulseSignalRequestAccept<T> extends PulseChannel {
    /**
     * 接收脉冲生产者的业务消息
     *
     * @param message 业务消息
     * @param request 脉冲信号响应
     * @return 响应业务消息给脉冲生产者，如果为 null 表示不需要响应数据
     */
    Serializable accept(T message, PulseSignalRequest request);
}
