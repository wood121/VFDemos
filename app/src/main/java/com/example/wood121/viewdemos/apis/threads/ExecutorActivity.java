package com.example.wood121.viewdemos.apis.threads;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.wood121.viewdemos.R;
import com.example.wood121.viewdemos.util.ThreadPoolManager;

import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ExecutorActivity extends AppCompatActivity {

    @BindView(R.id.tv_name)
    TextView tvName;
    private Future<?> future;
    private ThreadPoolExecutor poolExecutor;
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_executor);
        ButterKnife.bind(this);

//        long keepAliveTime = 3000;
//        TimeUnit milliseconds = TimeUnit.MILLISECONDS;
//        BlockingQueue<Runnable> workQueue = new LinkedBlockingDeque<>();
//        ThreadFactory threadFactory = Executors.defaultThreadFactory();
//        RejectedExecutionHandler handler = new ThreadPoolExecutor.DiscardPolicy();
//        poolExecutor = new ThreadPoolExecutor(3, 5, keepAliveTime, milliseconds, workQueue, threadFactory, handler);
        runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                runOnUiThread(new Runnable() {
                                  @Override
                                  public void run() {
                                      tvName.setText("我更新啦！！！");
                                  }
                              }
                );
            }
        };
//
////        poolExecutor.execute(runnable);
//        future = poolExecutor.submit(runnable);

        future = ThreadPoolManager.getInstance().submit(runnable);

    }

    @Override
    protected void onDestroy() {
        if (future != null) {
            if (!future.isDone()) {
                poolExecutor.remove(runnable);
                Log.e("wood121", "移除了任务");
            }
        }

        ThreadPoolManager.shutDownNow();

        super.onDestroy();
    }
}
