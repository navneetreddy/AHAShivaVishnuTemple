package com.navneetreddy.ahashivavishnutemple;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Downloads a PDF from an URL.
 *
 * @author NavneetReddy.
 */
public class PDFDownloader {

    private static final int MEGABYTE = 1024 * 1024;

    /**
     * Downloads a file from the given URL and stores the file in the given file directory.
     *
     * @param fileUrl URL of the file to download.
     * @param fileDirectory File path to the directory where the file needs to be stored.
     * @throws IOException Exception occurring while reading the file from the URL
     * or writing the file to the directory.
     */
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