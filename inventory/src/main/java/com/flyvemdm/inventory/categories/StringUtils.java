/**
 * FusionInventory
 *
 * Copyright (C) 2010-2017 by the FusionInventory Development Team.
 *
 * http://www.fusioninventory.org/
 * https://github.com/fusioninventory/fusioninventory-android
 *
 * ------------------------------------------------------------------------
 *
 * LICENSE
 *
 * This file is part of FusionInventory project.
 *
 * FusionInventory is free software: you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * FusionInventory is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * ------------------------------------------------------------------------------
 * @update    07/06/2017
 * @license   GPLv2 https://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 * @link      https://github.com/fusioninventory/fusioninventory-android
 * @link      http://www.fusioninventory.org/
 * ------------------------------------------------------------------------------
 */

package com.flyvemdm.inventory.categories;

import java.util.Collection;
import java.util.Iterator;
import java.util.Stack;

public class StringUtils {

    private StringUtils() {
        // Utility class.
    }

    public static String join(Collection<String> collection, String delimiter, boolean reversed) {
        if (collection != null) {
            StringBuffer buffer = new StringBuffer();
            Iterator<String> iter = collection.iterator();
            while (iter.hasNext()) {
                if (!reversed) {
                    buffer.append(iter.next());
                    if (iter.hasNext()) {
                        buffer.append(delimiter);

                    }
                } else {
                    buffer.insert(0, iter.next());
                    if (iter.hasNext()) {
                        buffer.insert(0, delimiter);

                    }
                }
            }
            return buffer.toString();
        } else {
            return null;
        }
    }

    public static String join(Collection<String> collection, String delimiter) {

        return StringUtils.join(collection, delimiter, false);

    }

    public static byte[] int_to_byte(int value) {
        return new byte[] { (byte) (value >>> 24), (byte) (value >>> 16), (byte) (value >>> 8), (byte) value };
    }

    public static String int_to_ip(int value) {
        byte[] b = int_to_byte(value);
        Stack<String> stack = new Stack<String>();
        for (byte c : b) {
            stack.push(String.valueOf(0xFF & c));
        }

        return (StringUtils.join(stack, ".", true));
    }
}