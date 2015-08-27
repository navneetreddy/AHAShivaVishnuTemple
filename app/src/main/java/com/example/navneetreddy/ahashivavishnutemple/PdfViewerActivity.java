package com.example.navneetreddy.ahashivavishnutemple;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Bundle;

import java.io.File;
import java.io.IOException;


public class PdfViewerActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_viewer);

        String pdfUrl = "";      //TODO
        String pdfFileName = ""; //TODO

        new DownloadFile().execute(pdfUrl, pdfFileName);
    }

    private class DownloadFile extends AsyncTask<String, Void, Void> {

        private final static String folderName = "AHA_Downloads";
        private String fileName;

        @Override
        protected Void doInBackground(String... params) {
            String fileUrl = params[0];
            fileName = params[1];

            String externalStorageDirectory = Environment.getExternalStorageDirectory().toString();
            File folder = new File(externalStorageDirectory, folderName);
            folder.mkdir();

            File pdfFile = new File(folder, fileName);   //TODO

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
            File pdfFile = new File(Environment.getExternalStorageDirectory() +
                    "/" + folderName + "/" + fileName);
            Uri path = Uri.fromFile(pdfFile);

            Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
            pdfIntent.setDataAndType(path, "application/pdf");
            pdfIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            try {
                startActivity(pdfIntent);
            } catch (ActivityNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}