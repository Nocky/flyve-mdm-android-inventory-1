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

import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.app.Service;
import android.content.Context;
import android.os.Build;
import android.provider.Settings.Secure;
import android.text.format.DateFormat;

import java.util.Properties;

public class Hardware
        extends Categories {

    /**
     * 
     */
    private static final long serialVersionUID = 3528873342443549732L;

    public Hardware(Context xCtx) {
        super(xCtx);
        // TODO Auto-generated constructor stub
        
        Properties props = System.getProperties();
        
        ActivityManager activityManager = (ActivityManager) mCtx.getSystemService(Service.ACTIVITY_SERVICE);
        MemoryInfo info = new MemoryInfo();
        activityManager.getMemoryInfo(info);
        
        Category c = new Category(mCtx,"HARDWARE");
        
        c.put("CHECKSUM" , String.valueOf(0xFFFF));
        c.put("DATELASTLOGGEDUSER",String.valueOf(DateFormat.format("MM/dd/yy", Build.TIME)) );
        if (!Build.USER.equals(Build.UNKNOWN)) {
            c.put("LASTLOGGEDUSER",Build.USER);
        } else { 
            String user = (String)props.getProperty("user.name");
            if (!user.equals("")) {
                c.put("LASTLOGGEDUSER", (String)props.getProperty("user.name"));
            }
        }

        c.put("NAME", Build.MODEL);
        c.put("OSNAME", "Android");
        c.put("OSVERSION", Build.VERSION.RELEASE);
        c.put("ARCHNAME", (String)props.getProperty("os.arch"));
        c.put("SDK", new Integer(Build.VERSION.SDK_INT).toString());
        String deviceId = Secure.getString(xCtx.getContentResolver(), Secure.ANDROID_ID);
        c.put("UUID", deviceId);
        
        //For OCS compatibility
        Memory memory = new Memory(xCtx);
        c.put("MEMORY", memory.getCapacity());

        Cpus cpu = new Cpus(xCtx);
        c.put("PROCESSORT", cpu.getCpuName());
        c.put("PROCESSORS", cpu.getCpuFrequency());
        this.add(c);

    }
}
