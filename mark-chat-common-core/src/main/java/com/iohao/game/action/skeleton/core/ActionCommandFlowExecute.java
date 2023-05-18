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
package com.iohao.game.action.skeleton.core;

import com.iohao.game.action.skeleton.core.flow.FlowContext;

/**
 * 命令流程执行器
 *
 * @author 渔民小镇
 * @date 2021-12-20
 */
public interface ActionCommandFlowExecute {
    /**
     * 模板方法模式：
     * <pre>
     * 在一个方法中定义一个算法的骨架，而将一些步骤延迟到子类中。
     * 模板方法使得子类可以在不改变算法结构的情况下，重新定义算法中的某些步骤。
     * 要点：
     * - “模板方法”定义了算法的步骤，把这些步骤的实现延迟到子类。
     * - 模板方法模式为我们提供了一种代码复用的重要技巧。
     * - 模板方法的抽象类可以定义具体方法、抽象方法和钩子。
     * - 抽象方法由子类实现。
     * - 钩子是一种方法，它在抽象类中不做事，或者只做默认的事情，子类可以选择要不要去覆盖它。
     * - 为了防止子类改变模板方法中的算法，可以将模板方法声明为final。
     * - 好莱坞原则告诉我们，将决策权放在高层模块中，以便决定如何以及何时调用低层模块。
     * - 你将在真实世界代码中看到模板方法模式的许多变体，不要期待它们全都是一眼就可以被你一眼认出的。
     * - 策略模式和模板方法模式都封装算法，一个用组合，一个用继承。
     * - 工厂方法是模板方法的一种特殊版本。
     * </pre>
     *
     * <pre>
     * 命令的执行入口, 类似模板方法模式, 但却并不是真正的模板方法模式
     * 这里在处理请求
     * 提供了用户可配置的多个 (执行前的钩子, 执行后的钩子)
     * </pre>
     *
     * @param flowContext FlowContext
     */
    void execute(FlowContext flowContext);
}
