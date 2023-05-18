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
package com.iohao.game.action.skeleton.core.flow.parser;

import com.iohao.game.action.skeleton.core.ActionCommand;
import com.iohao.game.action.skeleton.core.DataCodecKit;
import com.iohao.game.action.skeleton.protocol.wrapper.StringValue;
import com.iohao.game.action.skeleton.protocol.wrapper.StringValueList;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author 渔民小镇
 * @date 2023-02-05
 */
final class StringValueMethodParser implements MethodParser {
    @Override
    public Class<?> getActualClazz(ActionCommand.MethodParamResultInfo methodParamResultInfo) {
        return methodParamResultInfo.isList() ? StringValueList.class : StringValue.class;
    }

    @Override
    public Object parseParam(byte[] data, ActionCommand.ParamInfo paramInfo) {

        if (paramInfo.isList()) {

            if (Objects.isNull(data)) {
                return Collections.emptyList();
            }

            StringValueList valueList = DataCodecKit.decode(data, StringValueList.class);
            return valueList.values;
        }

        if (Objects.isNull(data)) {
            return null;
        }

        StringValue stringValue = DataCodecKit.decode(data, StringValue.class);
        return stringValue.value;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Object parseResult(ActionCommand.ActionMethodReturnInfo actionMethodReturnInfo, Object methodResult) {

        if (actionMethodReturnInfo.isList()) {
            StringValueList valueList = new StringValueList();
            valueList.values = (List<String>) methodResult;
            return valueList;
        }

        StringValue stringValue = new StringValue();
        stringValue.value = String.valueOf(methodResult);
        return stringValue;
    }

    private StringValueMethodParser() {
    }

    public static StringValueMethodParser me() {
        return Holder.ME;
    }

    /** 通过 JVM 的类加载机制, 保证只加载一次 (singleton) */
    private static class Holder {
        static final StringValueMethodParser ME = new StringValueMethodParser();
    }
}
