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

import android.content.Context;
import android.os.Build;

import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;
import java.util.LinkedHashMap;

public class Category extends LinkedHashMap<String, String>{

    /**
     * 
     */
    private static final long serialVersionUID = 6443019125036309325L;
    public Context mCtx;
    public String mType;

    public Category(Context xCtx, String xType) {
        mCtx = xCtx;
        mType = xType;
    }

    public String put(String key, String value) {
       //Do not add value if it's null, blank or "unkown"
       if (value != null && !value.equals("") && !value.equals(Build.UNKNOWN)) {
    	   return super.put(key, value);
       } else {
    	   return "";
       }
    }
    public void toXML(XmlSerializer serializer) throws IllegalArgumentException, IllegalStateException, IOException {
        
            serializer.startTag(null, mType);

            for (String prop : this.keySet()) {

                serializer.startTag(null, prop);
                serializer.text(String.valueOf(this.get(prop)));
                serializer.endTag(null, prop);
            }

            serializer.endTag(null, mType);
        

    }
}
