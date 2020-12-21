package com.example.bookreader;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {
    private SeekBar sbTextSize;
    private StackAdapter adapterViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager vpPager = (ViewPager) findViewById(R.id.vpContent);


        Log.d("cacaInfo", "onCreate");
        new DownloadFileFromURL(new LoaderListener() {
            @Override
            public void onLoaded(String result) {
                Log.d("cacaInfo", String.valueOf(result.length()));
                adapterViewPager = new StackAdapter(getSupportFragmentManager(), 600, result);
                vpPager.setAdapter(adapterViewPager);
            }

            @Override
            public void onFailure(String error) {
                Toast.makeText(getApplicationContext(), "ERROR   " + error, Toast.LENGTH_LONG).show();
            }
        }).execute("https://www.gutenberg.org/cache/epub/19543/pg19543.txt");

        sbTextSize = (SeekBar) findViewById(R.id.sbTextSize);
        sbTextSize.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (adapterViewPager != null)
                    adapterViewPager.setNewSize(progress + 12);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    public static class DownloadFileFromURL extends AsyncTask<String, String, String> {
        private LoaderListener listener;

        public DownloadFileFromURL(LoaderListener listener) {
            this.listener = listener;
        }

        /**
         * Before starting background thread Show Progress Bar Dialog
         */
        @Override
        protected void onPreExecute() {
            Log.d("cacaInfo", "onPreExcute");
            super.onPreExecute();
        }

        /**
         * Downloading file in background thread
         */
        @Override
        protected String doInBackground(String... urls) {
            Log.d("cacaInfo", "doInBackground");
            String line = "", result = "";
            try {
                URL url = new URL(urls[0]);
                URLConnection urlConnection = url.openConnection();
                InputStream is = urlConnection.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));


                while (line != null) {
                    line = br.readLine();
                    if (line != null) {
                        if (line.isEmpty()) result += "\n";
                        else result += " " + line;
                        Log.d("cacaInfo", line);
                    }
                }
                br.close();
                return result;
            } catch (Exception e) {
                Log.e("Error: ", e.getMessage());
                listener.onFailure(e.getMessage());
            }
            return result;
        }

        /**
         * After completing background task Dismiss the progress dialog
         **/
        @Override
        protected void onPostExecute(String content) {
            // dismiss the dialog after the file was downloaded
            listener.onLoaded(content);
        }
    }


}
