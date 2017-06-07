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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Memory extends Categories {



	/**
	 * 
	 */
	private static final long serialVersionUID = -494336872000892273L;

	public Memory(Context xCtx) {
		super(xCtx);
		// TODO Auto-generated constructor stub
		Category c = new Category(xCtx, "MEMORIES");
        c.put("DESCRIPTION", "Memory");
        c.put("CAPACITY", getCapacity());

        this.add(c);
	}
	
	public String getCapacity() {
        File f = new File("/proc/meminfo");
        String capacity = "";
        try {

        	BufferedReader br = new BufferedReader(new FileReader(f), 8 * 1024);
        	String line;
			while ((line = br.readLine()) != null) {
        		if (line.startsWith("MemTotal")) {
                    String[] parts = line.split(":");
                    String part1 = parts[1].trim();
                    Long memory = new Long(part1.replaceAll("(.*)\\ kB", "$1"));
                    memory = memory / 1024;
                    capacity =  String.valueOf(memory);
        		}
        	}

            br.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		return capacity;
	}
}
