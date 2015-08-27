package com.example.navneetreddy.ahashivavishnutemple;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;

import java.io.File;
import java.io.IOException;


/**
 * Downloads a PDF file from a URL in an async task.
 *
 * @author NavneetReddy.
 */
public class PDFDownloadAsyncTask extends AsyncTask<String, Void, Void> {

    private final static String folderName = "AHA_Downloads";
    private File pdfFile;

    @Override
    @SuppressWarnings("ResultOfMethodCallIgnored")
    protected Void doInBackground(String... params) {
        String fileUrl = params[0];
        String fileName = params[1];

        String externalStorageDirectory = Environment.getExternalStorageDirectory().toString();
        File folder = new File(externalStorageDirectory, folderName);
        folder.mkdir();

        pdfFile = new File(folder, fileName);

        /* Creates a new PDF file if the file doesn't exist. Otherwise opens the existing file. */
        if (!pdfFile.exists()) {
            try {
                pdfFile.createNewFile();
                PDFDownloader.downloadFile(fileUrl, pdfFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        Uri path = Uri.fromFile(pdfFile);

        /* Finds a PDF viewing app and opens the PDF file in the app. */
        Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
        pdfIntent.setDataAndType(path, "application/pdf");
        pdfIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        try {
            Singleton.getContext().startActivity(pdfIntent);
        } catch (ActivityNotFoundException e) {
            /* Directs the user to the play store to downloader Adobe Reader
            if no PDF viewing app was found. */
            Intent adobePlayStoreIntent = new Intent(Intent.ACTION_VIEW);
            adobePlayStoreIntent.setData(Uri.parse("market://details?id=com.adobe.reader"));
            Singleton.getContext().startActivity(adobePlayStoreIntent);
        }
    }
}