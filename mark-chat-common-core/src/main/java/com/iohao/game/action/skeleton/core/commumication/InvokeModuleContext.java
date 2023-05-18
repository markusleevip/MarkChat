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

import com.iohao.game.action.skeleton.core.BarMessageKit;
import com.iohao.game.action.skeleton.core.CmdInfo;
import com.iohao.game.action.skeleton.core.DataCodecKit;
import com.iohao.game.action.skeleton.protocol.RequestMessage;
import com.iohao.game.action.skeleton.protocol.ResponseMessage;
import com.iohao.game.action.skeleton.protocol.SyncRequestMessage;
import com.iohao.game.action.skeleton.protocol.collect.ResponseCollectMessage;

/**
 * 内部模块通讯上下文，内部模块指的是游戏逻辑服
 * <pre>
 *     单个逻辑服与单个逻辑服通信请求-有响应值（可跨进程）
 *     单个逻辑服与单个逻辑服通信请求-无响应值（可跨进程）
 *     单个逻辑服与同类型多个逻辑服通信请求（可跨进程）
 * </pre>
 *
 * @author 渔民小镇
 * @date 2022-06-07
 */
public interface InvokeModuleContext {

    /**
     * 根据路由信息来请求其他子服务器（其他逻辑服）的方法，并且不需要返回值
     * <pre>
     *     相关文档
     *     https://www.yuque.com/iohao/game/nelwuz#gtdrv
     *     https://www.yuque.com/iohao/game/anguu6#cZfdx
     * </pre>
     *
     * @param cmdInfo cmdInfo
     * @param data    请求参数
     */
    default void invokeModuleVoidMessage(CmdInfo cmdInfo, Object data) {
        RequestMessage requestMessage = BarMessageKit.createRequestMessage(cmdInfo, data);
        this.invokeModuleVoidMessage(requestMessage);
    }

    /**
     * 根据路由信息来请求其他子服务器（其他逻辑服）的方法，并且不需要返回值
     * <pre>
     *     相关文档
     *     https://www.yuque.com/iohao/game/nelwuz#gtdrv
     *     https://www.yuque.com/iohao/game/anguu6#cZfdx
     * </pre>
     *
     * @param cmdInfo cmdInfo
     */
    default void invokeModuleVoidMessage(CmdInfo cmdInfo) {
        this.invokeModuleVoidMessage(cmdInfo, null);
    }

    /**
     * 根据路由信息来请求其他子服务器（其他逻辑服）的方法，并且不需要返回值
     * <pre>
     *     相关文档
     *     https://www.yuque.com/iohao/game/nelwuz#gtdrv
     *     https://www.yuque.com/iohao/game/anguu6#cZfdx
     * </pre>
     *
     * @param requestMessage requestMessage
     */
    void invokeModuleVoidMessage(RequestMessage requestMessage);

    /**
     * 根据路由信息来请求其他子服务器（其他逻辑服）的数据
     * <pre>
     *     相关文档
     *     https://www.yuque.com/iohao/game/nelwuz#L9TAJ
     *     https://www.yuque.com/iohao/game/anguu6
     * </pre>
     *
     * @param cmdInfo 路由信息
     * @param data    请求参数
     * @param clazz   pb class
     * @param <T>     t
     * @return pb 对象
     */
    default <T> T invokeModuleMessageData(CmdInfo cmdInfo, Object data, Class<T> clazz) {
        ResponseMessage responseMessage = invokeModuleMessage(cmdInfo, data);
        // 将字节解析成对象
        byte[] dataContent = responseMessage.getData();
        return DataCodecKit.decode(dataContent, clazz);
    }

    /**
     * 根据路由信息来请求其他子服务器（其他逻辑服）的数据
     * <pre>
     *     相关文档
     *     https://www.yuque.com/iohao/game/nelwuz#L9TAJ
     *     https://www.yuque.com/iohao/game/anguu6
     * </pre>
     *
     * @param cmdInfo 路由信息
     * @param clazz   pb class
     * @param <T>     t
     * @return pb 对象
     */
    default <T> T invokeModuleMessageData(CmdInfo cmdInfo, Class<T> clazz) {
        return this.invokeModuleMessageData(cmdInfo, null, clazz);
    }

    /**
     * 根据路由信息来请求其他子服务器（其他逻辑服）的数据
     * <pre>
     *     相关文档
     *     https://www.yuque.com/iohao/game/nelwuz#L9TAJ
     *     https://www.yuque.com/iohao/game/anguu6
     * </pre>
     *
     * @param cmdInfo cmdInfo
     * @param data    请求参数
     * @return ResponseMessage
     */
    default ResponseMessage invokeModuleMessage(CmdInfo cmdInfo, Object data) {
        RequestMessage requestMessage = BarMessageKit.createRequestMessage(cmdInfo, data);

        return this.invokeModuleMessage(requestMessage);
    }

    /**
     * 根据路由信息来请求其他子服务器（其他逻辑服）的数据
     * <pre>
     *     相关文档
     *     https://www.yuque.com/iohao/game/nelwuz#L9TAJ
     *     https://www.yuque.com/iohao/game/anguu6
     * </pre>
     *
     * @param cmdInfo cmdInfo
     * @return ResponseMessage
     */
    default ResponseMessage invokeModuleMessage(CmdInfo cmdInfo) {
        return this.invokeModuleMessage(cmdInfo, null);
    }

    /**
     * 根据路由信息来请求其他子服务器（其他逻辑服）的数据
     * <pre>
     *     相关文档
     *     https://www.yuque.com/iohao/game/anguu6
     * </pre>
     *
     * @param requestMessage requestMessage
     * @return ResponseMessage
     */
    ResponseMessage invokeModuleMessage(RequestMessage requestMessage);

    /**
     * 模块之间的访问，访问【同类型】的多个逻辑服
     * <pre>
     *     模块A 访问 模块B 的某个方法，因为只有模块B持有这些数据，这里的模块指的是逻辑服。
     *     假设启动了多个模块B，分别是：模块B-1、模块B-2、模块B-3、模块B-4 等。框架支持访问【同类型】的多个逻辑服，并把多个相同逻辑服结果收集到一起。
     *
     *     具体的意思可以参考文档中的说明
     *     https://www.yuque.com/iohao/game/nelwuz#gSdya
     *     https://www.yuque.com/iohao/game/rf9rb9
     * </pre>
     *
     * @param cmdInfo 路由信息
     * @param data    业务数据
     * @return ResponseCollectMessage
     */
    default ResponseCollectMessage invokeModuleCollectMessage(CmdInfo cmdInfo, Object data) {
        SyncRequestMessage requestMessage = new SyncRequestMessage();

        BarMessageKit.employ(requestMessage, cmdInfo, data);

        return this.invokeModuleCollectMessage(requestMessage);
    }

    /**
     * 模块之间的访问，访问【同类型】的多个逻辑服
     * <pre>
     *     模块A 访问 模块B 的某个方法，因为只有模块B持有这些数据，这里的模块指的是逻辑服。
     *     假设启动了多个模块B，分别是：模块B-1、模块B-2、模块B-3、模块B-4 等。框架支持访问【同类型】的多个逻辑服，并把多个相同逻辑服结果收集到一起。
     *
     *     具体的意思可以参考文档中的说明
     *     https://www.yuque.com/iohao/game/nelwuz#gSdya
     *     https://www.yuque.com/iohao/game/rf9rb9
     * </pre>
     *
     * @param cmdInfo 路由信息
     * @return ResponseCollectMessage
     */
    default ResponseCollectMessage invokeModuleCollectMessage(CmdInfo cmdInfo) {
        return invokeModuleCollectMessage(cmdInfo, null);
    }

    /**
     * 模块之间的访问，访问【同类型】的多个逻辑服
     * <pre>
     *     模块A 访问 模块B 的某个方法，因为只有模块B持有这些数据，这里的模块指的是逻辑服。
     *     假设启动了多个模块B，分别是：模块B-1、模块B-2、模块B-3、模块B-4 等。
     *     框架支持访问【同类型】的多个逻辑服，并把多个相同逻辑服结果收集到一起。
     *
     *     具体的意思可以参考文档中的说明
     *     https://www.yuque.com/iohao/game/nelwuz#gSdya
     *     https://www.yuque.com/iohao/game/rf9rb9
     * </pre>
     *
     * @param requestMessage requestMessage
     * @return ResponseAggregationMessage
     */
    ResponseCollectMessage invokeModuleCollectMessage(RequestMessage requestMessage);
}
