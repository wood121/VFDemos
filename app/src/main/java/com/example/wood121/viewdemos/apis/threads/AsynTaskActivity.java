package com.example.wood121.viewdemos.apis.threads;

import android.os.AsyncTask;
import android.os.Bundle;

import com.example.wood121.viewdemos.R;
import com.example.wood121.viewdemos.util.ToastUtil;

import java.net.URL;
import java.util.TimerTask;

import androidx.appcompat.app.AppCompatActivity;

public class AsynTaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asyn_task_demo);

//        TimerTask
        
        new DownLoadFilesTask().execute(new Runnable() {
            @Override
            public void run() {

            }
        });
    }

    private class DownLoadFilesTask extends AsyncTask<URL, Integer, Long> {

        @Override
        protected Long doInBackground(URL... params) {
            int count = params.length;
            int totalSize = 0;

            for (int i = 0; i < count; i++) {

//              int totalSize =  DownLoader.downloadFile(params[i]);
                publishProgress(i / count);
                if (isCancelled()) {
                    break;

                }
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
//            setProgressPercent(values[0]);
        }

        @Override
        protected void onPostExecute(Long aLong) {
            ToastUtil.showToast(AsynTaskActivity.this, "result:" + aLong);
        }
    }

}
