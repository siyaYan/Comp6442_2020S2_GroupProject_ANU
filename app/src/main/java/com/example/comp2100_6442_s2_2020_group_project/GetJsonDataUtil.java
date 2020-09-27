package com.example.comp2100_6442_s2_2020_group_project;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* @author: Xiran Yan
 *  @uid: 7167582
 * */
public class GetJsonDataUtil {
    //json transfer to string
    public static String getJson(String fileName, Context context) {
        //string builder
        StringBuilder stringBuilder = new StringBuilder();
        try {
            //getAssetManager
            AssetManager assetManager = context.getAssets();
            //open file by assetManager and read by bufferedReader
            BufferedReader bf = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName)));
            //read by line
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);//append more
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }


}
