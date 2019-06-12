package com.example.wood121.viewdemos.views.widgets_self.widgets;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

public class CommomPopWindow extends PopupWindow {
    public CommomPopWindow(View mContentView, int width, int height, boolean focusable) {
        super(mContentView, width, height, focusable);
    }

    public static class Builder {
        private CommomPopWindow popupWindow;  //PopupWindow
        private Context context;
        private View contentView;   //弹窗视图
        private boolean isFullWidth = false;    //宽度是否充满全屏，默认不充满
        private boolean isFullHeight = false;   //高度是否充满全屏，默认不充满
        private boolean isFocusable = true; //是否聚焦，默认聚焦
        private boolean isOutsideTouchable = true;  //点击外部区域是否消失，默认点击消失
        private boolean isClippingEnabled = true;  //ClippingEnabled，默认true
        private Drawable backDrawable;  //设置背景
        private int animationStyle; //设置进出动画
        private int location = BOTTOM;  //相对于指定布局的位置，默认下方
        private int xOffset = 0;    //X方向偏移量
        private int yOffset = 0;    //Y方向偏移量
        private float backgroundAlpha = 1.0f;
        private OnDismissListener onDismissListener;

        /*相对于指定布局位置的相关常量*/
        public static final int LEFT = 1;
        public static final int TOP = 2;
        public static final int RIGHT = 3;
        public static final int BOTTOM = 4;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setContentView(View contentView) {
            this.contentView = contentView;
            return this;
        }

        public Builder setFullWidth(boolean fullWidth) {
            isFullWidth = fullWidth;
            return this;
        }

        public Builder setFullHeight(boolean fullHeight) {
            isFullHeight = fullHeight;
            return this;
        }

        public Builder setFocusable(boolean focusable) {
            isFocusable = focusable;
            return this;
        }

        public Builder setOutsideTouchable(boolean outsideTouchable) {
            isOutsideTouchable = outsideTouchable;
            return this;
        }

        public Builder setBackDrawable(Drawable backDrawable) {
            this.backDrawable = backDrawable;
            return this;
        }

        public Builder setBackgroundAlpha(float alpha) {
            this.backgroundAlpha = alpha;
            return this;
        }

        public Builder setAnimationStyle(int animationStyle) {
            this.animationStyle = animationStyle;
            return this;
        }

        public Builder setLocation(int location) {
            this.location = location;
            return this;
        }

        public Builder setXOffset(int xOffset) {
            this.xOffset = xOffset;
            return this;
        }

        public Builder setYOffset(int yOffset) {
            this.yOffset = yOffset;
            return this;
        }

        public Builder setClippingEnabled(boolean clippingEnabled) {
            this.isClippingEnabled = clippingEnabled;
            return this;
        }

        public void hide() {
            if (popupWindow != null) {
                popupWindow.dismiss();
            }
        }

        public boolean isShowing() {
            if (popupWindow != null) {
                return popupWindow.isShowing();
            }
            return false;
        }

        public Builder setOnDismissListener(OnDismissListener onDismissListener) {
            this.onDismissListener = onDismissListener;
            return this;
        }

        @TargetApi(Build.VERSION_CODES.CUPCAKE)
        public void show(View v) {
            if (contentView == null) {
                return;
            }
            popupWindow = new CommomPopWindow(contentView,
                    isFullWidth ? LinearLayout.LayoutParams.MATCH_PARENT : LinearLayout.LayoutParams.WRAP_CONTENT,
                    isFullHeight ? LinearLayout.LayoutParams.MATCH_PARENT : LinearLayout.LayoutParams.WRAP_CONTENT,
                    isFocusable);
            popupWindow.setAnimationStyle(animationStyle);
            popupWindow.setOutsideTouchable(isOutsideTouchable);
            popupWindow.setClippingEnabled(isClippingEnabled);
            if (backDrawable != null) {
                popupWindow.setBackgroundDrawable(backDrawable);
            } else {
                popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            }
            backgroundAlpha(backgroundAlpha);
            popupWindow.setOnDismissListener(new PopupDismissListener());
            measureView(contentView);
            int[] vLocation = new int[2];
            v.getLocationOnScreen(vLocation);
//            Log.i("CommonPopupWindow:" + "X:" + vLocation[0] + "Y:" + vLocation[1] + "Height:" + contentView
//                    .getMeasuredHeight() + "Width:" + contentView.getMeasuredWidth());
            switch (location) {
                case LEFT:
                    popupWindow.showAtLocation(v, Gravity.NO_GRAVITY, vLocation[0] - contentView.getMeasuredWidth() +
                            xOffset, vLocation[1] + yOffset);
                    break;
                case TOP:
                    popupWindow.showAtLocation(v, Gravity.NO_GRAVITY, vLocation[0] + xOffset, vLocation[1] -
                            contentView.getMeasuredHeight() + yOffset);
                    break;
                case RIGHT:
                    popupWindow.showAtLocation(v, Gravity.NO_GRAVITY, vLocation[0] + v.getWidth() + xOffset,
                            vLocation[1] + yOffset);
                    break;
                case BOTTOM:
                    popupWindow.showAsDropDown(v, xOffset, yOffset);
                    break;
                default:
                    popupWindow.showAsDropDown(v, xOffset, yOffset);
                    break;
            }
        }

        private void backgroundAlpha(float bgAlpha) {
            WindowManager.LayoutParams lp = ((Activity) context).getWindow()
                    .getAttributes();
            lp.alpha = bgAlpha;
            if (bgAlpha == 1) {
                //不移除该Flag的话,在有视频的页面上的视频会出现黑屏的bug
                ((Activity) context).getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            } else {
                //此行代码主要是解决在华为手机上半透明效果无效的bug
                ((Activity) context).getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            }
            ((Activity) context).getWindow().setAttributes(lp);
        }

        class PopupDismissListener implements OnDismissListener {
            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
                if (onDismissListener != null) {
                    onDismissListener.onDismiss();
                }
            }
        }
    }

    /**
     * 测量这个view
     * 最后通过getMeasuredWidth()获取宽度和高度.
     *
     * @param view 要测量的view
     * @return 测量过的view
     */
    public static void measureView(View view) {
        ViewGroup.LayoutParams p = view.getLayoutParams();
        if (p == null) {
            p = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
        }

        int childWidthSpec = ViewGroup.getChildMeasureSpec(0, 0, p.width);
        int lpHeight = p.height;
        int childHeightSpec;
        if (lpHeight > 0) {
            childHeightSpec = View.MeasureSpec.makeMeasureSpec(lpHeight,
                    View.MeasureSpec.EXACTLY);
        } else {
            childHeightSpec = View.MeasureSpec.makeMeasureSpec(0,
                    View.MeasureSpec.UNSPECIFIED);
        }
        view.measure(childWidthSpec, childHeightSpec);
    }
}
