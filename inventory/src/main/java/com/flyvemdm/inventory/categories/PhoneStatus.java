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

public class PhoneStatus
        extends Category {

 
    /**
     * 
     */
    private static final long serialVersionUID = 872718741270132229L;

    public PhoneStatus(Context xCtx, String xType) {
        super(xCtx, xType);
        // TODO Auto-generated constructor stub
        //final TelephonyManager mTM = (TelephonyManager) xCtx
        //.getSystemService(Context.TELEPHONY_SERVICE);
        
        //MyPhoneStateListener mPhoneState = new MyPhoneStateListener();
        //mTM.listen(mPhoneState, PhoneStateListener.LISTEN_SERVICE_STATE);
        //this.add(mPhoneState.getCategory());
    }
/*
    
    // TODO Implements a proper way to retrieve those informations
     
    public class MyPhoneStateListener extends PhoneStateListener {
        private Category c = null;

        @Override
        public void onServiceStateChanged(ServiceState serviceState) {
            // TODO Auto-generated method stub
            c = new Category(mCtx, "PHONE_STATUS");
        	super.onServiceStateChanged(serviceState);
            int iState = serviceState.getState();
            String sState = null;
            switch(iState) {
            case ServiceState.STATE_EMERGENCY_ONLY:
                sState = "STATE_EMERGENCY_ONLY";
                break;
            case ServiceState.STATE_IN_SERVICE:
                sState = "STATE_IN_SERVICE";
                break;
            case ServiceState.STATE_OUT_OF_SERVICE:
                sState = "STATE_OUT_OF_SERVICE";
                break;
            case ServiceState.STATE_POWER_OFF:
                sState = "STATE_POWER_OFF";
                break;
            }
            
            c.put("STATE", sState  );
            c.put("OPERATOR_ALPHA", serviceState.getOperatorAlphaLong());
            c.put("OPERATOR_NUMERIC", serviceState.getOperatorNumeric());
            c.put("ROAMING", new Boolean(serviceState.getRoaming()).toString());
            c.put("NETWORK_SELECTION", serviceState.getIsManualSelection()?"MANUAL":"AUTO");
        }
        
        public Category getCategory() {
        	return c;
        }
        
    }
    
    public PhoneStatus(FusionInventoryApp app) {
        super(app);
        
//        
        final TelephonyManager mTM = (TelephonyManager) ctx
        .getSystemService(Context.TELEPHONY_SERVICE);
//        
//
        MyPhoneStateListener mPhoneState = new MyPhoneStateListener();
//        
        mTM.listen(mPhoneState, PhoneStateListener.LISTEN_SERVICE_STATE);
        this.type = "PHONESTATUS";
//        
//        
        
        this.content.put("", );
        
    //}*/
}
