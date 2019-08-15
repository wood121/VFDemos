package com.example.wood121.viewdemos.views.charts.piechart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by wood121 on 2017/9/4.
 */

public class FanShapedView extends View {
    private float startAngle = -90.0f;
    private float radius;
    private ArrayList<Element> datasList = new ArrayList<>();
    private Paint paint = new Paint();

    class Element {
        public int Color;
        public int Weight;

        public Element(int c, int w) {
            Color = c;
            Weight = w;
        }
    }

    public FanShapedView(Context context) {
        super(context);
    }

    public FanShapedView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FanShapedView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setData(int[][] array) {
        datasList.clear();
        if (array != null && array.length > 0) {
            for (int i = 0; i < array.length; i++) {
                datasList.add(new Element(array[i][0], array[i][1]));
            }
        }
        postInvalidate();
    }

    public void setStartAngle(float startAngleValue) {
        this.startAngle = startAngleValue;
        postInvalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float center_x = canvas.getWidth() / 2;
        float center_y = canvas.getHeight() / 2;
        float drawRectWidth = canvas.getWidth() > canvas.getHeight() ? canvas
                .getHeight() : canvas.getWidth();
        // float gap_w = max_text_w > strokeWidth ? max_text_w / 2.0f
        // : strokeWidth / 2.0f;

        radius = drawRectWidth / 2;
        int countWeight = 0;
        if (datasList != null && datasList.size() > 0) {
            for (int i = 0; i < datasList.size(); i++) {
                countWeight += datasList.get(i).Weight;
            }
        }
        if (datasList != null && datasList.size() > 0 && countWeight > 0) {
            int drawAngle;
            int totalDrawAngle = 0;
            for (int i = 0; i < datasList.size(); i++) {
                paint.reset();
                paint.setAntiAlias(true);
                paint.setStyle(Paint.Style.FILL);
                if (i < datasList.size() - 1) {
                    drawAngle = datasList.get(i).Weight * 360 / countWeight;
                } else {
                    drawAngle = 360 - totalDrawAngle;
                }
                paint.setColor(datasList.get(i).Color);
                canvas.drawArc(new RectF(center_x - radius, center_y - radius,
                                center_x + radius, center_y + radius), startAngle,
                        drawAngle, true, paint);
                startAngle += drawAngle;
                totalDrawAngle += drawAngle;
            }
        }
    }
}
