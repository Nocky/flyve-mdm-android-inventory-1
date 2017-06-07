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

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;

public class Battery extends Categories {


	/**
	 * 
	 */
	private static final long serialVersionUID = -4096347994131285426L;
	private String level, voltage, temperature, status, health,
			technology;

	/**
     * 
     */

	public Battery(Context xCtx) {
		super(xCtx);
		// TODO Auto-generated constructor stub
		
		xCtx.registerReceiver(this.myBatteryReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
		this.myBatteryReceiver.onReceive(xCtx, new Intent(Intent.ACTION_BATTERY_CHANGED));
		Category c  = new Category(xCtx, "BATTERIES");
		c.put("CHEMISTRY", technology);
		c.put("TEMPERATURE", temperature);
		c.put("VOLTAGE", voltage);
		c.put("LEVEL", level);
		c.put("HEALTH", health);
		c.put("STATUS", status);
		this.add(c);
	}

	private BroadcastReceiver myBatteryReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context arg0, Intent arg1) {
			// TODO Auto-generated method stub

			if (arg1.getAction().equals(Intent.ACTION_BATTERY_CHANGED)) {
				level = String.valueOf(arg1.getIntExtra("level", 0)) + "%";
				voltage = String
						.valueOf((float) arg1.getIntExtra("voltage", 0) / 1000)
						+ "V";
				temperature = String.valueOf((float) arg1.getIntExtra(
						"temperature", 0) / 10)
						+ "c";
				technology = arg1.getStringExtra("technology");

				int intstatus = arg1.getIntExtra("status",
						BatteryManager.BATTERY_STATUS_UNKNOWN);
				if (intstatus == BatteryManager.BATTERY_STATUS_CHARGING) {
					status = "Charging";
				} else if (intstatus == BatteryManager.BATTERY_STATUS_DISCHARGING) {
					status = "Dis-charging";
				} else if (intstatus == BatteryManager.BATTERY_STATUS_NOT_CHARGING) {
					status = "Not charging";
				} else if (intstatus == BatteryManager.BATTERY_STATUS_FULL) {
					status = "Full";
				} else {
					status = "Unknown";
				}

				int inthealth = arg1.getIntExtra("health",
						BatteryManager.BATTERY_HEALTH_UNKNOWN);
				if (inthealth == BatteryManager.BATTERY_HEALTH_GOOD) {
					health = "Good";
				} else if (inthealth == BatteryManager.BATTERY_HEALTH_OVERHEAT) {
					health = "Over Heat";
				} else if (inthealth == BatteryManager.BATTERY_HEALTH_DEAD) {
					health = "Dead";
				} else if (inthealth == BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE) {
					health = "Over Voltage";
				} else if (inthealth == BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE) {
					health = "Unspecified Failure";
				} else {
					health = "Unknown";
				}

			}
		}

	};
}
