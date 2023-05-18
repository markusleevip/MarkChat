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
package com.iohao.game.action.skeleton.core;

import com.iohao.game.action.skeleton.core.commumication.BrokerClientContext;
import com.iohao.game.action.skeleton.pulse.Pulses;
import com.iohao.game.action.skeleton.pulse.core.PulseTransmit;
import com.iohao.game.common.kit.attr.AttrOption;

/**
 * @author 渔民小镇
 * @date 2023-04-21
 */
public interface SkeletonAttr {
    /** 脉冲管理器 */
    AttrOption<Pulses> pulses = AttrOption.valueOf("pulses");
    /** 当前逻辑服引用 */
    AttrOption<BrokerClientContext> brokerClientContext = AttrOption.valueOf("brokerClientContext");
    /** 服务器唯一标识 hash */
    AttrOption<Integer> logicServerIdHash = AttrOption.valueOf("logicServerIdHash");
    /** 脉冲生产者的发射器 */
    AttrOption<PulseTransmit> producerPulseTransmit = AttrOption.valueOf("producerPulseTransmit");
    /** 脉冲消费者的发射器 */
    AttrOption<PulseTransmit> consumerPulseTransmit = AttrOption.valueOf("consumerPulseTransmit");
}
