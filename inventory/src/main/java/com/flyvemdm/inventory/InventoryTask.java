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

package com.flyvemdm.inventory;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.text.format.DateFormat;
import android.util.Log;
import android.util.Xml;

import com.flyvemdm.inventory.categories.Categories;

import org.xmlpull.v1.XmlSerializer;

import java.io.StringWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;

public class InventoryTask extends AsyncTask<String, Void, String> {

    private ArrayList<Categories> mContent = null;
    private Date mStart = null, mEnd = null;
    private Context ctx = null;
    private static final int OK = 0;
    private static final int NOK = 1;
    private String FusionVersion = "";
    private int progress = 0;
    private OnTaskCompleted listener;

    public InventoryTask(Context context, String appVersion, OnTaskCompleted listener) {
        this.FusionVersion = appVersion;
        this.listener = listener;
        ctx = context;
        Log.v("Inventory","FusionInventoryApp = ");
    }

    private String toXML() {
        Log.i("FusionInventoryApp", "toXML: ");
        if (mContent != null) {

            XmlSerializer serializer = Xml.newSerializer();
            StringWriter writer = new StringWriter();

            try {
                serializer.setOutput(writer);
                serializer
                        .setFeature(
                                "http://xmlpull.org/v1/doc/features.html#indent-output",
                                true);
                // indentation as 3 spaces

                serializer.startDocument("utf-8", true);
                // Start REQUEST
                serializer.startTag(null, "REQUEST");
                // Start CONTENT
                serializer.startTag(null, "QUERY");
                serializer.text("INVENTORY");
                serializer.endTag(null, "QUERY");

                serializer.startTag(null, "VERSIONCLIENT");
                serializer.text(FusionVersion);
                serializer.endTag(null, "VERSIONCLIENT");

                serializer.startTag(null, "DEVICEID");
                serializer.text(Build.SERIAL);
                serializer.endTag(null, "DEVICEID");

                serializer.startTag(null, "CONTENT");
                // Start ACCESSLOG
                serializer.startTag(null, "ACCESSLOG");

                serializer.startTag(null, "LOGDATE");

                serializer.text(DateFormat.format("yyyy-mm-dd hh:MM:ss", mStart)
                        .toString());
                serializer.endTag(null, "LOGDATE");

                serializer.startTag(null, "USERID");
                serializer.text("N/A");
                serializer.endTag(null, "USERID");

                serializer.endTag(null, "ACCESSLOG");
                // End ACCESSLOG

                //Manage accountinfos :: TAG
                //   if (!mFusionApp.getTag().equals("")) {
                serializer.startTag(null, "ACCOUNTINFO");
                serializer.startTag(null, "KEYNAME");
                serializer.text("TAG");
                serializer.endTag(null, "KEYNAME");
                serializer.startTag(null, "KEYVALUE");
                //serializer.text(mFusionApp.getTag());
                serializer.text("");
                serializer.endTag(null, "KEYVALUE");
                serializer.endTag(null, "ACCOUNTINFO");
                //    }

                for (Categories cat : mContent) {

                    cat.toXML(serializer);
                }

                serializer.endTag(null, "CONTENT");
                serializer.endTag(null, "REQUEST");
                serializer.endDocument();

                return writer.toString();

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return "";
    }

    @Override
    protected String doInBackground(String... params) {
        mStart = new Date();

        mContent = new ArrayList<Categories>();

        String [] categories = {
//                "PhoneStatus",
                "Hardware",
                "Bios",
                "Memory",
                "Inputs",
                "Sensors",
                "Drives",
                "Cpus",
                "Simcards",
                "Videos",
                "Cameras",
                "Networks",
//                "LocationProviders",
                "Envs",
                "Jvm",
                "Softwares",
                "Usb",
                "Battery",
//              "BluetoothAdapterCategory", // <- there is already a BluetoothAdapter class in android SDK
        };

        Class<Categories> cat_class;

        for(String c : categories) {
            cat_class = null;
            Log.v("Inventory", String.format("INVENTORY of %s", c));

            try {
                cat_class = (Class <Categories>) Class.forName(String.format("com.flyvemdm.inventory.categories.%s",c));
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if(cat_class!=null) {
                try {
                    Constructor<Categories> co = cat_class.getConstructor(Context.class);
                    mContent.add(co.newInstance(ctx));
                } catch (SecurityException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IllegalArgumentException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();

                }
            }
        }
        Log.v("inventory", "end of inventory");
        mEnd = new Date();
        return "true";
    }

    @Override
    protected void onPostExecute(String result) {
        if(result.equals("true")){
            this.listener.onTaskCompleted( toXML() );
        }
    }

    public interface OnTaskCompleted{
        void onTaskCompleted(String data);
    }
}
