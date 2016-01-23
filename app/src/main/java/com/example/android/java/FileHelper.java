package com.example.android.java;

import android.content.Context;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileHelper {

    public static String copyAssetToCache(Context context, String fileName) {

        String cachedFileName = context.getCacheDir() + fileName;
        File cachedFile = new File(cachedFileName);

        FileOutputStream fos = null;
//      If the file doesn't exist in cache, copy it from assets
        if (!cachedFile.exists()) {
            try {
                InputStream inputStream = context.getAssets().open(fileName);
                int size = inputStream.available();
                byte[] buffer = new byte[size];
                int ignore = inputStream.read(buffer);
                inputStream.close();
                fos = new FileOutputStream(cachedFile);
                fos.write(buffer);
                fos.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                if (fos != null) {
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
        return cachedFileName;
    }
}
