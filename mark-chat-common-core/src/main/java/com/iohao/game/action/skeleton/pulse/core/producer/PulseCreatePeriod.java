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
package com.iohao.game.action.skeleton.pulse.core.producer;

/**
 * 脉冲创建周期
 *
 * @author 渔民小镇
 * @date 2023-04-30
 */
public interface PulseCreatePeriod {

    /**
     * N 秒执行一次（N 秒发射一次脉冲信号）
     * <pre>
     *     return 120 ； 表示 120 秒执行一次
     *
     *     当为负数时也表示不想执行
     * </pre>
     *
     * @return 秒
     */
    int period();
}
