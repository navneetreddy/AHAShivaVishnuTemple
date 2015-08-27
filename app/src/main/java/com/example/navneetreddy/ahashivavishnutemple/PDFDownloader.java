package com.example.navneetreddy.ahashivavishnutemple;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Created by NavneetReddy on 8/26/15.
 */
public class PDFDownloader {

    private static final int MEGABYTE = 1024 * 1024;

    public static void downloadFile(String fileUrl, File fileDirectory) throws IOException {
        FileOutputStream fileOutputStream = null;

        try {
            URL url = new URL(fileUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            InputStream inputStream = urlConnection.getInputStream();
            fileOutputStream = new FileOutputStream(fileDirectory);

            byte[] buffer = new byte[MEGABYTE];
            int bufferLength;

            while ((bufferLength = inputStream.read(buffer)) > 0) {
                fileOutputStream.write(buffer, 0, bufferLength);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
        }
    }
}