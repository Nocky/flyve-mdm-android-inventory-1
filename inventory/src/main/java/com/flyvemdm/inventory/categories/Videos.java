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

import android.app.Service;
import android.content.Context;
import android.view.Display;
import android.view.WindowManager;

public class Videos
        extends Categories {

    /**
     * 
     */
    private static final long serialVersionUID = 6953895287405000489L;

    public Videos(Context xCtx) {
        super(xCtx);
        // TODO Auto-generated constructor stub
        WindowManager lWinMgr = (WindowManager) mCtx.getSystemService(Service.WINDOW_SERVICE);
        
        Category c = new Category(mCtx , "VIDEOS");
        
        Display d = lWinMgr.getDefaultDisplay();
        
        c.put("RESOLUTION" , String.format("%dx%d" , d.getWidth(),d.getHeight()) );
         this.add(c);
    }

}
