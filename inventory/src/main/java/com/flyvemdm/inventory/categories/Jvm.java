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

import java.util.Properties;

public class Jvm
        extends Categories {


    /**
	 * 
	 */
	private static final long serialVersionUID = 3291981487537599599L;

	public Jvm(Context xCtx) {
        super(xCtx);
        // TODO Auto-generated constructor stub

        Category c = new Category(mCtx,"JVMS");
        Properties props = System.getProperties();
        /*
        for(Object prop: props.keySet() ) {
            FusionInventory.log(this, String.format("PROP %s = %s" , (String)prop, props.get(prop) ) , Log.VERBOSE);
        }
         */
        c.put("NAME", (String)props.getProperty("java.vm.name"));
        String language = (String)props.getProperty("user.language");
        language += '_';
        language += (String)props.getProperty("user.region");
        c.put("VENDOR", (String)props.getProperty("java.vm.vendor"));
        c.put("LANGUAGE", language);
        c.put("RUNTIME", (String)props.getProperty("java.runtime.version"));
        c.put("HOME", (String)props.getProperty("java.home"));
        c.put("VERSION", (String)props.getProperty("java.vm.version"));
        c.put("CLASSPATH", (String)props.getProperty("java.class.path"));
        this.add(c);
    }
}
