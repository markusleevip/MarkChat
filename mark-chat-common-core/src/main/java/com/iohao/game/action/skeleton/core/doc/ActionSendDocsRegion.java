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

import com.iohao.game.action.skeleton.core.CmdKit;
import org.jctools.maps.NonBlockingHashMap;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author 渔民小镇
 * @date 2022-02-01
 */
public class ActionSendDocsRegion {
    Map<Integer, ActionSendDoc> actionSendDocMap = new NonBlockingHashMap<>();

    public void addActionSendDocs(ActionSendDocs actionSendDocs) {
        this.actionSendDocMap.putAll(actionSendDocs.getActionSendDocMap());
    }

    public ActionSendDoc getActionSendDoc(int cmd, int subCmd) {
        return this.getActionSendDoc(CmdKit.merge(cmd, subCmd));
    }

    public ActionSendDoc getActionSendDoc(int cmdMerge) {
        ActionSendDoc actionSendDoc = actionSendDocMap.get(cmdMerge);

        if (Objects.nonNull(actionSendDoc)) {
            actionSendDoc.setRead(true);
        }

        return actionSendDoc;
    }

    public List<ActionSendDoc> listActionSendDoc() {
        return this.actionSendDocMap.values()
                .stream()
                .filter(actionSendDoc -> !actionSendDoc.isRead())
                .toList();
    }

}
