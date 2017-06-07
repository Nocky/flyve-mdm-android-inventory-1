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
import android.telephony.TelephonyManager;

public class Simcards extends Categories {

    /**
     * 
     */
    private static final long serialVersionUID = -5532129156981574844L;

    
    public Simcards(Context xCtx) {
        super(xCtx);
        // TODO Auto-generated constructor stub
        
        final TelephonyManager mTM = (TelephonyManager) mCtx
                .getSystemService(Context.TELEPHONY_SERVICE);

        /*
         * Starting SimCards Informations retrieval
         */

        Category c = new Category(mCtx , "SIMCARDS");
        c.put("COUNTRY", mTM.getSimCountryIso());
        c.put("OPERATOR_CODE", mTM.getSimOperator());
        c.put("OPERATOR_NAME", mTM.getSimOperatorName());
        c.put("SERIAL", mTM.getSimSerialNumber());
        
        switch(mTM.getSimState()) {
        case TelephonyManager.SIM_STATE_ABSENT:
            c.put("STATE", "SIM_STATE_ABSENT");
            break;
        case TelephonyManager.SIM_STATE_NETWORK_LOCKED:
            c.put("STATE", "SIM_STATE_NETWORK_LOCKED");
            break;
        case TelephonyManager.SIM_STATE_PIN_REQUIRED:
            c.put("STATE", "SIM_STATE_PIN_REQUIRED");
            break;
        case TelephonyManager.SIM_STATE_PUK_REQUIRED:
            c.put("STATE", "SIM_STATE_PUK_REQUIRED");
            break;
        case TelephonyManager.SIM_STATE_READY:
            c.put("STATE", "SIM_STATE_READY");
            break;
        case TelephonyManager.SIM_STATE_UNKNOWN:
            c.put("STATE", "SIM_STATE_UNKNOWN");
            break;
        
        };

        c.put("LINE_NUMBER", mTM.getLine1Number());
        c.put("SUBSCRIBER_ID", mTM.getSubscriberId());
        
        this.add(c);
    }
}
