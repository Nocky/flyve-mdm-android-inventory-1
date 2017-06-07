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
import android.os.Environment;
import android.os.StatFs;
import android.util.Log;

import com.flyvemdm.inventory.FusionInventory;

import java.io.File;

public class Drives extends Categories {


    /**
	 * 
	 */
	private static final long serialVersionUID = 6073387379988815108L;

	public Drives(Context xCtx) {
        super(xCtx);
        
        this.addStorage(xCtx, Environment.getRootDirectory());
        this.addStorage(xCtx, Environment.getExternalStorageDirectory());
	    this.addStorage(xCtx, Environment.getDataDirectory());
	    this.addStorage(xCtx, Environment.getDownloadCacheDirectory());
    }
    
    /**
     * Add a storage to inventory
     * @param xCtx the Context
     * @param f the partition to inventory
     */
    private void addStorage(Context xCtx, File f) {
        int toMega = 1048576;
    	Category c = new Category(xCtx, "DRIVES");
        c.put("VOLUMN", f.toString());

        FusionInventory.log(this, "Inventory volum "+f.toString() , Log.VERBOSE);
        
        //Android 2.3.3 or higher
        if(Build.VERSION.SDK_INT > 8) {
        	FusionInventory.log(this, "SDK > 8, use SDK to get total and free disk space", Log.VERBOSE);
        	Long total = f.getTotalSpace();
	        total = total / toMega;
	      	c.put("TOTAL", total.toString());
	        Long free = f.getFreeSpace();
	        free = free / toMega;
	      	c.put("FREE", free.toString());
        //Android < 2.3.3
        } else {
            FusionInventory.log(this, "SDK < 8 use StatFS", Log.VERBOSE);

            StatFs stat = new StatFs(f.toString());
            long blockSize = stat.getBlockSize();
            long totalBlocks = stat.getBlockCount();
            double total = totalBlocks * blockSize /toMega;
        	//double total = (stat.getBlockSize() * stat.getBlockSize()) / toMega;
        	
        	c.put("TOTAL", String.valueOf(total));
            long freeBlocks = stat.getFreeBlocks();
        	double free = freeBlocks * blockSize / toMega;
        	c.put("FREE", String.valueOf(free));
        }
        this.add(c);
    }

}
