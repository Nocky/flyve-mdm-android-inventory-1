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
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.util.Log;

import com.flyvemdm.inventory.FusionInventory;

import java.util.List;

public class Sensors extends Categories {

	/**
     * 
     */
	private static final long serialVersionUID = 4846706700566208666L;

	public Sensors(Context xCtx) {
		super(xCtx);
		// TODO Auto-generated constructor stub
		SensorManager sensorManager = (SensorManager) mCtx
				.getSystemService(Context.SENSOR_SERVICE);

		List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
		FusionInventory.log(this, "Get sensors ", Log.VERBOSE);

		for (Sensor s : sensors) {
			Category c = new Category(mCtx, "SENSORS");
			c.put("NAME", s.getName());
			c.put("MANUFACTURER", s.getVendor());
			int type = s.getType();
			String strtype = "";
			switch (type) {
				case Sensor.TYPE_ACCELEROMETER:
					strtype = "ACCELEROMETER";
					break;
				case Sensor.TYPE_GRAVITY:
					strtype = "GRAVITY";
					break;
				case Sensor.TYPE_GYROSCOPE:
					strtype = "GYROSCOPE";
					break;
				case Sensor.TYPE_LINEAR_ACCELERATION:
					strtype = "LINEAR ACCELERATION";
					break;
				case Sensor.TYPE_MAGNETIC_FIELD:
					strtype = "MAGNETIC FIELD";
					break;
				case Sensor.TYPE_ORIENTATION:
					strtype = "ORIENTATION";
					break;
				case Sensor.TYPE_PRESSURE:
					strtype = "PRESSURE";
					break;
				case Sensor.TYPE_PROXIMITY:
					strtype = "PROXIMITY";
					break;
				case Sensor.TYPE_ROTATION_VECTOR:
					strtype = "ROTATION VECTOR";
					break;
				case Sensor.TYPE_TEMPERATURE:
					strtype = "TEMPERATURE";
					break;
				default:
					strtype = "";
					break;
			}
			c.put("TYPE", strtype);
			Float f = s.getPower();
			c.put("POWER", f.toString());
			Integer version = s.getVersion();
			c.put("VERSION", version.toString());
			this.add(c);
		}
	}
}
