package com.example.wood121.viewdemos.views.widgets_self.danmu;

import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.UserHandle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.wood121.viewdemos.R;
import com.example.wood121.viewdemos.base.BaseActivity;
import com.example.wood121.viewdemos.util.DisplayUtils;
import com.example.wood121.viewdemos.util.SharePreferencesUtil;
import com.example.wood121.viewdemos.views.utils.SofeKeyBroadManager;
import com.example.wood121.viewdemos.views.widgets_self.model.BarrageListModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import master.flame.danmaku.controller.DrawHandler;
import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.DanmakuTimer;
import master.flame.danmaku.danmaku.model.IDanmakus;
import master.flame.danmaku.danmaku.model.IDisplayer;
import master.flame.danmaku.danmaku.model.android.DanmakuContext;
import master.flame.danmaku.danmaku.model.android.Danmakus;
import master.flame.danmaku.danmaku.parser.BaseDanmakuParser;
import master.flame.danmaku.ui.widget.DanmakuView;

/**
 * 弹幕播放、弹幕收缩动画、弹幕收缩状态
 * 弹幕发送
 */
public class DanMuActivity extends BaseActivity implements View.OnClickListener {
    private static final String DANMU_OPEN = "SleepReportActivity_DanmuOpen";
    private boolean showDanmaku;
    private DanmakuView mDanmakuView;
    private ImageView mIvInput;
    private ImageView mIvClose;
    private DanmakuContext mDanmakuContext;
    private DanmuContentDialog mDialog;
    private RelativeLayout mRlContainer;
    private String mSaveDanmuContent = "";
    private RevealAnimationLayout mRevealLayout;
    private TextView mTvDanmu;
    private String lastDanmuContent = "";
    private Handler mHandler;
    private List<BarrageListModel> danmuList = new ArrayList<>();
    private int barrageIndex;
    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            //插入完最后一条
            if (barrageIndex == danmuList.size()) {
                Log.e("wood", "barrageIndex:" + barrageIndex);
                barrageIndex = 0;
                return;
            }
            BarrageListModel model = danmuList.get(barrageIndex);
            barrageIndex++;
//            runOnUiThread(() -> addDanmaku(model, false));
            addDanmaku(model, false);
            mHandler.postDelayed(mRunnable, 1000);
        }
    };
    private SofeKeyBroadManager mSofeKeyBroadManager;
    private boolean mDanmuWasOpen;

    @Override
    protected void initData(Bundle savedInstanceState) {
        for (int i = 0; i < 50; i++) {
            BarrageListModel model = new BarrageListModel(i + "你在说什么" + i + "," + i, "#009900");
            danmuList.add(model);
        }
        mHandler = new Handler();
    }

    @Override
    protected int initPageLayoutId() {
        return R.layout.activity_dan_mu;
    }

    @Override
    protected void initEvent() {
        mRlContainer = findViewById(R.id.rl_container);
        mRevealLayout = findViewById(R.id.reveal_layout);
        LinearLayout llContent = findViewById(R.id.ll_content);
        mIvClose = findViewById(R.id.iv_close);
        mDanmakuView = findViewById(R.id.danmaku_view);
        mIvInput = findViewById(R.id.iv_input);
        mTvDanmu = findViewById(R.id.tv_danmu);

        mIvClose.setOnClickListener(this);
        mTvDanmu.setOnClickListener(this);
        mIvInput.setOnClickListener(this);

        prepareDatas();
    }

    private void prepareDatas() {
        initDamuContext();
        calCulateParams();
        mDanmuWasOpen = SharePreferencesUtil.getBoolean(this, DANMU_OPEN);
        openOrCloseDanmu(mDanmuWasOpen);
        mSofeKeyBroadManager = new SofeKeyBroadManager(mIvInput);
        mSofeKeyBroadManager.addSoftKeyboardStateListener(new SofeKeyBroadManager.SoftKeyboardStateListener() {
            @Override
            public void onSoftKeyboardOpened(int keyboardHeightInPx) {

            }

            @Override
            public void onSoftKeyboardClosed() {
                closeInputDialog();
            }
        });
    }

    private void calCulateParams() {
        mTvDanmu.post(() -> {
            int top = mTvDanmu.getTop();
            int left = mTvDanmu.getLeft();
            int width = mTvDanmu.getWidth();
            int height = mTvDanmu.getHeight();

            int cX = left + width / 2;
            int cY = top + height / 2;

            int llTop = mRlContainer.getTop();
            int llLeft = mRlContainer.getLeft();

            mRevealLayout.setCenter(cX, cY);
            mRevealLayout.setExpandPoint(0, 0);
        });
    }

    private void openOrCloseDanmu(boolean danmuWasOpen) {
        if (danmuWasOpen) {
            Log.e("wood", "openOrCloseDanmu");
            mTvDanmu.setVisibility(View.INVISIBLE);
            mRevealLayout.setLayoutVisible();
            showDanmaku = true;
        }
    }

    private BaseDanmakuParser parser = new BaseDanmakuParser() {
        @Override
        protected IDanmakus parse() {
            return new Danmakus();
        }
    };

    private void initDamuContext() {
        //全局配置进行设定，如设置字体、设置最大显示行数等。
        mDanmakuContext = DanmakuContext.create();
        // 设置弹幕的最大显示行数
        HashMap<Integer, Integer> maxLinesPair = new HashMap<>();
        maxLinesPair.put(BaseDanmaku.TYPE_SCROLL_RL, 3); // 滚动弹幕最大显示3行
        // 设置是否禁止重叠
        HashMap<Integer, Boolean> overlappingEnablePair = new HashMap<>();
        overlappingEnablePair.put(BaseDanmaku.TYPE_SCROLL_RL, true);
        overlappingEnablePair.put(BaseDanmaku.TYPE_SCROLL_LR, true);
        overlappingEnablePair.put(BaseDanmaku.TYPE_FIX_BOTTOM, true);
        overlappingEnablePair.put(BaseDanmaku.TYPE_FIX_TOP, true);

        mDanmakuContext.setDanmakuStyle(IDisplayer.DANMAKU_STYLE_STROKEN, 3)
                .setDuplicateMergingEnabled(false)   //是否启用合并重复弹幕
                .setScrollSpeedFactor(2f) //弹幕滚动速度系数
                .setScaleTextSize(1.2f)
                .setMaximumLines(maxLinesPair)
                .preventOverlapping(overlappingEnablePair); //是否禁止重叠，null不禁止
        //提升绘制效率
        mDanmakuView.enableDanmakuDrawingCache(true);
        //设置回调
        mDanmakuView.setCallback(new DrawHandler.Callback() {
            @Override
            public void prepared() {
                runOnUiThread(() -> {
                    mDanmakuView.start();
                });
                Log.e("wood", "prepared:------");
            }

            @Override
            public void updateTimer(DanmakuTimer timer) {
//                Log.e("wood", "updateTimer:" + timer.currMillisecond);
            }

            @Override
            public void danmakuShown(BaseDanmaku danmaku) {
                Log.e("wood", "danmakuShown:" + danmaku.text);
            }

            @Override
            public void drawingFinished() {
                Log.e("wood", "drawingfinished:------");
                insertDanmu();
            }
        });
        mDanmakuView.prepare(parser, mDanmakuContext);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_danmu:
                expandDanmu();
                break;
            case R.id.iv_input:
                showInputDialog();
                break;
            case R.id.iv_close:
                unExpandDanmu();
                break;
        }
    }

    /**
     * 打开弹幕：
     * 1.按钮隐藏、弹幕以揭露动画打开
     * 2.记录状态、开启弹幕播放、插入数据
     */
    private void expandDanmu() {
        showDanmaku = true;
        resumeDanmu();
        mTvDanmu.setVisibility(View.INVISIBLE);
        mRevealLayout.setOnLayoutAnimaotrEndListener(() -> {
            insertDanmu();
            Log.e("wood", "弹幕打开了");
        });
        mRevealLayout.startAnimal(RevealAnimationLayout.AnimaType.Circle);
    }

    /**
     * 关闭弹幕
     */
    private void unExpandDanmu() {
        showDanmaku = false;
        pauseDanmu();
        stopInstertDanmu();
        mRevealLayout.setOnLayoutAnimaotrEndListener(() -> {
            mTvDanmu.setVisibility(View.VISIBLE);
            Log.e("wood", "弹幕关闭了");
        });
        mRevealLayout.startAnimal(RevealAnimationLayout.AnimaType.BackCircle);
    }

    private void insertDanmu() {
        mHandler.postDelayed(mRunnable, 0);
    }

    private void stopInstertDanmu() {
        mHandler.removeCallbacks(mRunnable);
    }

    private void showInputDialog() {
        if (mDialog == null) {
            mDialog = new DanmuContentDialog(this, mSaveDanmuContent);
        }
        mDialog.setOnSendListener(trim -> {
            if (!TextUtils.isEmpty(trim)) {
                this.lastDanmuContent = trim;
                addDanmaku(new BarrageListModel(trim, "#00ff00"), true);
            }
        });
        mDialog.setOnSaveDanmuContentListener(trim -> {
            this.mSaveDanmuContent = trim;
        });
        mDialog.show();
    }

    private void closeInputDialog() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        closeInputDialog();
        pauseDanmu();
    }

    private void pauseDanmu() {
        if (mDanmakuView != null && mDanmakuView.isPrepared()) {
            mDanmakuView.pause();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        resumeDanmu();
    }

    private void resumeDanmu() {
        if (mDanmakuView != null && mDanmakuView.isPrepared() && mDanmakuView.isPaused()) {
            mDanmakuView.resume();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopInstertDanmu();
        if (mDanmakuView != null) {
            mDanmakuView.release();
            mDanmakuView = null;
        }
        SharePreferencesUtil.putBoolean(this, DANMU_OPEN, showDanmaku);
    }

//    @Override
//    public void showBarrageList(List<BarrageListModel> data) {
//        if (null != data && data.size() > 0) {
//            Log.e("wood", "size:" + data.size() + ",data content:" + data.get(0).toString());
//            danmuList = data;
//            if (showDanmaku) {
//                insertDanmu();
//            }
//        }
//    }
//
//    @Override
//    public void addBarrageContent() {
//        Log.e("wood", "弹幕发送成功");
//        BarrageListModel newModel = new BarrageListModel(lastDanmuContent, "#00ff00");
//        addDanmaku(newModel, false);
//        danmuList.add(0, newModel);
//    }

    /**
     * 向弹幕View中添加一条弹幕
     * 弹幕的内容、字体大小、颜色、显示时间等各种细节进行配置
     */
    private void addDanmaku(BarrageListModel model, boolean withBorder) {
        BaseDanmaku danmaku = mDanmakuContext.mDanmakuFactory.createDanmaku(BaseDanmaku.TYPE_SCROLL_RL);
        danmaku.text = model.getSysBarrageMsg();
        danmaku.padding = DisplayUtils.sp2px(this, 6);
        danmaku.textSize = DisplayUtils.sp2px(this, 14);
        danmaku.textColor = Color.parseColor(model.getColor());
        danmaku.setTime(mDanmakuView.getCurrentTime());
        if (withBorder) {
            danmaku.borderColor = Color.GREEN;
        }
        mDanmakuView.addDanmaku(danmaku);
    }

}