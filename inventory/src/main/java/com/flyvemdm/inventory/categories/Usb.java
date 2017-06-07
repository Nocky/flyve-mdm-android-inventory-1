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
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.os.Build;
import android.util.Log;

import com.flyvemdm.inventory.FusionInventory;

import java.util.HashMap;
import java.util.Iterator;

public class Usb
        extends Categories {

    /**
     * 
     */
    private static final long serialVersionUID = 4846706700566208666L;

    public Usb(Context xCtx) {
        super(xCtx);
        
        //USB inventory comes with SDK level 12 !
        if(Build.VERSION.SDK_INT > 12) {
	
	        UsbManager manager = (UsbManager) xCtx.getSystemService(Context.USB_SERVICE);
	        HashMap<String, UsbDevice> devices = manager.getDeviceList();
	        Iterator<String> iterator = devices.keySet().iterator();
	        while (iterator.hasNext()) {
	        	String key = (String) iterator.next();
	        	FusionInventory.log(this,key, Log.VERBOSE);
	        	UsbDevice mydevice = devices.get(key);
	        	Category c = new Category(mCtx, "USBDEVICES");
	        	c.put("CLASS", Integer.toString(mydevice.getDeviceClass()));
	        	c.put("PRODUCTID", Integer.toString(mydevice.getProductId()));
	        	c.put("VENDORID", Integer.toString(mydevice.getVendorId()));
	        	c.put("SUBCLASS", Integer.toString(mydevice.getDeviceSubclass()));
	        	this.add(c);
	        }
        }
    }
}
