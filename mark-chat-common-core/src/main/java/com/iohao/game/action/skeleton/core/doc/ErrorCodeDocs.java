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
package com.iohao.game.action.skeleton.core.doc;

import com.iohao.game.action.skeleton.core.exception.MsgExceptionInfo;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * 错误码文档相关
 *
 * @author 渔民小镇
 * @date 2022-02-03
 */
public class ErrorCodeDocs {

    @Getter
    List<ErrorCodeDoc> errorCodeDocList = new ArrayList<>();

    public void addMsgExceptionInfo(MsgExceptionInfo msgExceptionInfo) {
        ErrorCodeDoc errorCodeDoc = new ErrorCodeDoc()
                .setCode(msgExceptionInfo.getCode())
                .setMsg(msgExceptionInfo.getMsg());

        this.errorCodeDocList.add(errorCodeDoc);
    }

}
