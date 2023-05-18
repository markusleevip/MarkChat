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
package com.iohao.game.action.skeleton.toy;

/**
 * @author 渔民小镇
 * @date 2023-01-30
 */

final class ToyLine {
    String key;
    String value;

    ToyTableRegion region;
    String prefix = " ";
    String suffix = " ";
    String fill = " ";

    String render() {
        StringBuilder bodyBuilder = new StringBuilder();

        // body line key
        bodyBuilder.append(prefix);
        bodyBuilder.append(key);

        int appendNum = region.keyMaxLen - key.length();
        append(bodyBuilder, fill, appendNum);

        // body line key
        String keyValueFix = "|";
        bodyBuilder.append(keyValueFix);
        bodyBuilder.append(prefix);
        bodyBuilder.append(value);

        appendNum = region.valueMaxLen - value.length() - keyValueFix.length();
        append(bodyBuilder, fill, appendNum);

        bodyBuilder.append(suffix);
        return bodyBuilder.toString();
    }

    private void append(StringBuilder builder, String c, int num) {
        builder.append(String.valueOf(c).repeat(Math.max(0, num + 1)));
    }
}
