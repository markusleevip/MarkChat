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
package com.iohao.game.action.skeleton.core.exception;

import java.util.Objects;

/**
 * 异常消息
 * <pre>
 *     关于异常机制的解释可以参考这里:
 *     https://www.yuque.com/iohao/game/avlo99
 * </pre>
 *
 * @author 渔民小镇
 * @date 2022-01-14
 */
public interface MsgExceptionInfo {
    /**
     * 异常消息
     *
     * @return 消息
     */
    String getMsg();

    /**
     * 异常码
     *
     * @return 异常码
     */
    int getCode();

    /**
     * 断言为 true, 就抛出异常
     *
     * @param v1 断言值
     * @throws MsgException e
     */
    default void assertTrueThrows(boolean v1) throws MsgException {
        if (v1) {
            throw new MsgException(this);
        }
    }

    /**
     * 断言必须是 true, 否则抛出异常
     *
     * @param v1 断言值
     * @throws MsgException e
     */
    default void assertTrue(boolean v1) throws MsgException {
        if (v1) {
            return;
        }

        throw new MsgException(this);
    }

    /**
     * 断言必须是 非null, 否则抛出异常
     *
     * @param value 断言值
     * @throws MsgException e
     */
    default void assertNonNull(Object value) throws MsgException {
        assertTrue(Objects.nonNull(value));
    }

    /**
     * 断言必须是 false, 否则抛出异常
     *
     * @param v1 断言值
     * @throws MsgException e
     */
    default void assertFalse(boolean v1) throws MsgException {
        this.assertTrue(!v1);
    }

    /**
     * 断言必须是 false, 否则抛出异常
     *
     * @param v1  断言值
     * @param msg 自定义消息
     * @throws MsgException e
     */
    default void assertFalse(boolean v1, String msg) throws MsgException {
        this.assertTrue(!v1, msg);
    }


    /**
     * 断言必须是 true, 否则抛出异常
     *
     * @param v1  断言值
     * @param msg 自定义消息
     * @throws MsgException e
     */
    default void assertTrue(boolean v1, String msg) throws MsgException {
        if (v1) {
            return;
        }

        int code = this.getCode();
        throw new MsgException(code, msg);
    }
}
