/*
 * ioGame
 * Copyright (C) 2021 - 2023  渔民小镇 （262610965@qq.com、luoyizhu@gmail.com） . All Rights Reserved.
 * # iohao.com . 渔民小镇
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General  License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General  License for more details.
 *
 * You should have received a copy of the GNU General  License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package com.iohao.game.action.skeleton.core.flow;

/**
 * ActionMethod 结果包装器
 * <pre>
 *     将 action 业务方法产生的结果或者错误码 包装到响应(ResponseMessage)对象中 。
 * </pre>
 *
 * @author 渔民小镇
 * @date 2021-12-20
 */
public interface ActionMethodResultWrap {
    /**
     * 包装结果
     * <pre>
     *     将 action 业务方法产生的结果或者错误码 包装到响应(ResponseMessage)对象中 。
     * </pre>
     *
     * @param flowContext flow 上下文
     */
    void wrap(FlowContext flowContext);
}
