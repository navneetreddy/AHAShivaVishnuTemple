package com.example.navneetreddy.ahashivavishnutemple;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.File;
import java.io.IOException;


public class PDFViewerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_viewer);

        String pdfUrl = Singleton.getEventToDisplay().getPdfLink();
        String pdfFileName = pdfUrl.substring(pdfUrl.lastIndexOf('/') + 1);

        new DownloadFile().execute(pdfUrl, pdfFileName);

        // TODO - add progress bar while waiting for the file to download.
        // TODO - check if the file already exists.
    }

    private class DownloadFile extends AsyncTask<String, Void, Void> {

        private final static String folderName = "AHA_Downloads";
        private String fileName;
        private File pdfFile;

        @Override
        @SuppressWarnings("ResultOfMethodCallIgnored")
        protected Void doInBackground(String... params) {
            String fileUrl = params[0];
            fileName = params[1];

            String externalStorageDirectory = Environment.getExternalStorageDirectory().toString();
            File folder = new File(externalStorageDirectory, folderName);
            folder.mkdir();

            pdfFile = new File(folder, fileName);

            try {
                pdfFile.createNewFile();
                PDFDownloader.downloadFile(fileUrl, pdfFile);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Uri path = Uri.fromFile(pdfFile);

            Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
            pdfIntent.setDataAndType(path, "application/pdf");
            pdfIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            try {
                startActivity(pdfIntent);
            } catch (ActivityNotFoundException e) {
                // TODO - go to play store page of Adobe Reader.
                e.printStackTrace();
            }
        }
    }
}