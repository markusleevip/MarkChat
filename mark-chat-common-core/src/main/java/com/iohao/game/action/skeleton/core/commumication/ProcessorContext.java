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
package com.iohao.game.action.skeleton.core.commumication;

/**
 * 通讯方式之一 用于各服务器之前的 processor 通信
 * <pre>
 *     各服务器指的是
 *          对外服、游戏网关、游戏逻辑服
 *
 *      用于 bolt 扩展 processor，这个种扩展方式是自由度是最大的
 *      但需要开发者自己编写 AsyncUserProcessor 来处理这种消息
 *
 *      实际内部是调用的是 RpcClient#oneway(Url, Object) 方法，所以这个接口是用来区分通讯类型
 * </pre>
 *
 * @author 渔民小镇
 * @date 2022-05-28
 */
public interface ProcessorContext {
    /**
     * oneway 异步调用
     *
     * @param message message
     */
    void invokeOneway(Object message);
}
