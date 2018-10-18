package com.example.wood121.viewdemos.api_part;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.wood121.viewdemos.R;
import com.example.wood121.viewdemos.util.ToastUtil;

import java.net.URL;

public class AsynTaskDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asyn_task_demo);

//        new DownLoadFilesTask().execute(...);
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
            ToastUtil.showToast(AsynTaskDemoActivity.this, "result:" + aLong);
        }
    }

}
