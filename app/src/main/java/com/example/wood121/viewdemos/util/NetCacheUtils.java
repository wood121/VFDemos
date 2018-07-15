package com.example.wood121.viewdemos.util;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;

/**
 * Created by wood121 on 2018/7/14.
 */

public class NetCacheUtils {
    private static final String TAG = "NetCacheUtils";
    private ImageView imageView;

    public void getBitmapFromNet(ImageView imageView, String url) {
        //2.执行  ：异步加载类对象.excute(Params... params);
        //异步下载图片
        new BitmapTask().execute(imageView, url);
    }

    /**
     * 1.自定义异步加载类
     *
     * @Params Params:输入参数。对应的是调用自定义的AsyncTask的类中调用excute()方法中传递的参数。如果不需要传递参数，则直接设为Void即可。
     * @Params Progress：后台任务子线程执行的百分比
     * @Params Result：后台执行任务最终返回结果。和doInBackground()方法的返回值类型保持一致。
     */
    class BitmapTask extends AsyncTask<Object, Void, Bitmap> {//Params,Progress,Result
        private ImageView imageView;
        private String url;

        //这里是最终用户调用Excute时的接口，当任务执行之前开始调用此方法，可以在这里显示进度对话框。
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        /**
         * 后台线程执行异步任务，将result告知UI线程。
         * 返回值类型和Result保持一致。参数：若无就传递Void；若有，就可用Params
         */
        @Override
        protected Bitmap doInBackground(Object... params) {
            imageView = (ImageView) params[0];
            url = (String) params[1];
//            imageView.setTag(url);//给当前ImageView打标签
            //使用url下载图片
//            Bitmap bitmap = download(url);
            return null;
        }

        /**
         * 相当于Handler 处理UI的方式，在这里面可以使用在doInBackground 得到的结果处理操作UI。 此方法在主线程执行，任务执行的结果作为此方法的参数返回
         * 参数：和Result保持一致
         */
        @Override
        protected void onPostExecute(Bitmap result) {
            if (result != null) {
                //给ImageView设置图片
                //由于ListView的重用机制,导致某个item有可能展示它所重用的那个item的图片, 导致图片错乱
                //解决方案:确保当前设置的图片和当前显示的imageview完全匹配
                String url = (String) imageView.getTag();//获取和当前ImageView绑定个url
                if (this.url.equals(url)) {//判断当前下载的图片的url是否和imageView的url一致, 如果一致,说明图片正确
                    imageView.setImageBitmap(result);
                    System.out.println("从网络下载图片啦!!!");
                }
            }
        }
    }
}
