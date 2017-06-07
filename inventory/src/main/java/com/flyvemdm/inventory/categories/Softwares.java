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
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.PackageStats;

import java.util.List;

public class Softwares
        extends Categories {

    /**
     * 
     */
    private static final long serialVersionUID = 4846706700566208666L;

    public Softwares(Context xCtx) {
        super(xCtx);
        // TODO Auto-generated constructor stub
        PackageManager PM = mCtx.getPackageManager();

        List<ApplicationInfo> packages = PM.getInstalledApplications(PackageManager.GET_META_DATA);

        for (ApplicationInfo p : packages) {
            //FusionInventory.log(this, "SOFTWARES " + p.packageName, Log.VERBOSE);
//            FusionInventory.log(this, "   " + p.name, Log.VERBOSE);
//            FusionInventory.log(this, "   " + p.className, Log.VERBOSE);
            

            Category c = new Category(mCtx, "SOFTWARES");
            PackageStats stats = new PackageStats(p.packageName);
            //FusionInventory.log(this, "   " + stats.packageName, Log.VERBOSE);
            //c.put("NAME", p.packageName);
            if (p.name != null) {
                c.put("NAME", p.name);
            } else if (p.className != null) {
                c.put("NAME", p.className);
            } else if (p.packageName != null) {
                c.put("NAME", p.packageName);
            }

            try {
                PackageInfo pi = PM.getPackageInfo(p.packageName, PackageManager.GET_META_DATA);
                c.put("VERSION", pi.versionName);

            } catch (NameNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            
            //Bundle b = p.metaData;
            //if (b != null) {
            //for (String bname : b.keySet()) {
            //    FusionInventory.log(this, bname + " " + String.valueOf(b.get(bname)),Log.WARN);
            //}
            //}
            //FusionInventory.log(this, "   " + stats.cacheSize + " " + stats.codeSize + " " + stats.dataSize, Log.VERBOSE);
            c.put("FILESIZE", String.valueOf(stats.cacheSize + stats.codeSize + stats.dataSize));
            c.put("FROM", "apk");
            this.add(c);
        }
    }

    
}
