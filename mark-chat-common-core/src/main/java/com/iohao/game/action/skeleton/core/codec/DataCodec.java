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
package com.iohao.game.action.skeleton.core.codec;

import com.iohao.game.action.skeleton.core.DataCodecKit;

/**
 * 业务数据的编解码器
 * <pre>
 *     see {@link DataCodecKit}
 * </pre>
 *
 * @author 渔民小镇
 * @date 2022-05-18
 */
public interface DataCodec {
    /**
     * 将数据对象编码成字节数组
     *
     * @param data 数据对象
     * @return bytes
     */
    byte[] encode(Object data);

    /**
     * 将字节数组解码成对象
     *
     * @param data      数据对象的字节
     * @param dataClass 数据对象 class
     * @param <T>       t
     * @return 业务参数
     */
    <T> T decode(byte[] data, Class<?> dataClass);

    /**
     * 编解码名
     *
     * @return 编解码名
     */
    default String codecName() {
        return this.getClass().getSimpleName();
    }
}
