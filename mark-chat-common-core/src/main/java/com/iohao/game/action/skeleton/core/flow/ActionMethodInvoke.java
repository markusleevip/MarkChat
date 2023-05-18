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

import com.iohao.game.action.skeleton.annotation.ActionController;
import com.iohao.game.action.skeleton.annotation.ActionMethod;

/**
 * ActionMethod Invoke
 * <pre>
 *     调用业务层的方法 (即对外提供的方法)
 * </pre>
 *
 * @author 渔民小镇
 * @date 2021-12-20
 */
public interface ActionMethodInvoke {
    /**
     * 具体的业务方法调用
     * <pre>
     *     类有上有注解 {@link ActionController}
     *     方法有注解 {@link ActionMethod}
     *     只要有这两个注解的，就是业务类
     * </pre>
     *
     * @param flowContext flow 上下文
     * @return 返回值
     */
    Object invoke(FlowContext flowContext);
}
