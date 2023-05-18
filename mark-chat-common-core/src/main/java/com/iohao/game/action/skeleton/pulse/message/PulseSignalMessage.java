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
package com.iohao.game.action.skeleton.pulse.message;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.io.Serial;
import java.io.Serializable;

/**
 * 脉冲信号消息父类
 *
 * @author 渔民小镇
 * @date 2023-04-20
 */
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PROTECTED)
public sealed class PulseSignalMessage implements Serializable permits PulseSignalRequest, PulseSignalResponse {

    @Serial
    private static final long serialVersionUID = -954007335024894018L;

    /**
     * 信号类型
     * see {@link SignalType}
     */
    int signalType;

    /**
     * 信号频道
     * <pre>
     *     不需要开发者设置，所在 PulseChannel 的 channel 值
     * </pre>
     */
    String channel;

    /** 业务数据 */
    Serializable data;

    /**
     * 来源逻辑服 client id
     * <pre>
     *     see {@link com.iohao.game.common.kit.MurmurHash3}
     * </pre>
     */
    int sourceClientId;

    @SuppressWarnings("unchecked")
    public <T> T getData() {
        return (T) data;
    }

    /**
     * 信号类型添加
     *
     * @param signalType {@link SignalType}
     */
    public void addSignalType(int signalType) {
        this.signalType |= signalType;
    }

    /**
     * 信号类型是否存在
     *
     * @param signalType {@link SignalType}
     * @return true 存在
     */
    public boolean containsSignalType(int signalType) {
        return (this.signalType & signalType) == signalType;
    }
}
