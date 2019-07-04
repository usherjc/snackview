package com.usher.snack.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @name : usherchen
 * @date : 2019/6/21
 * @item : midea fridge
 * @desc : --
 */
public class UsherDDSSoundView extends View {

    private Paint mPaint;
    private int mParentW, mParentH;

    private int mMainLineH = 50;
    private int mLastLineH = 5;
    private int mLineSpace = 20;

    {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.WHITE);
        mPaint.setStrokeWidth(5f);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
    }

    public UsherDDSSoundView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mParentW = getMeasuredWidth();
        mParentH = getMeasuredHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int value = (mMainLineH - mLastLineH) / 4;
        int main_x = mParentW / 2;
        int main_y = mParentH / 2;
        //////////////////////////////////////////////////////////// 绘制中间那条
        mPaint.setColor(Color.parseColor("#f44336"));
        canvas.drawLine(
                main_x, main_y - mMainLineH / 2,
                main_x, main_y + mMainLineH / 2,
                mPaint
        );
        //////////////////////////////////////////////////////////// 绘制左侧四条
        mPaint.setColor(Color.parseColor("#e91e63"));
        canvas.drawLine(
                main_x - mLineSpace * 1, main_y - (mMainLineH - value * 1) / 2,
                main_x - mLineSpace * 1, main_y + (mMainLineH - value * 1) / 2,
                mPaint
        );
        mPaint.setColor(Color.parseColor("#9c27b0"));
        canvas.drawLine(
                main_x - mLineSpace * 2, main_y - (mMainLineH - value * 2) / 2,
                main_x - mLineSpace * 2, main_y + (mMainLineH - value * 2) / 2,
                mPaint
        );
        mPaint.setColor(Color.parseColor("#673ab7"));
        canvas.drawLine(
                main_x - mLineSpace * 3, main_y - (mMainLineH - value * 3) / 2,
                main_x - mLineSpace * 3, main_y + (mMainLineH - value * 3) / 2,
                mPaint
        );
        mPaint.setColor(Color.parseColor("#3f51b5"));
        canvas.drawLine(
                main_x - mLineSpace * 4, main_y - (mMainLineH - value * 4) / 2,
                main_x - mLineSpace * 4, main_y + (mMainLineH - value * 4) / 2,
                mPaint
        );
        //////////////////////////////////////////////////////////// 绘制右侧四条
        mPaint.setColor(Color.parseColor("#03a9f4"));
        canvas.drawLine(
                main_x + mLineSpace * 1, main_y - (mMainLineH - value * 1) / 2,
                main_x + mLineSpace * 1, main_y + (mMainLineH - value * 1) / 2,
                mPaint
        );
        mPaint.setColor(Color.parseColor("#009688"));
        canvas.drawLine(
                main_x + mLineSpace * 2, main_y - (mMainLineH - value * 2) / 2,
                main_x + mLineSpace * 2, main_y + (mMainLineH - value * 2) / 2,
                mPaint
        );
        mPaint.setColor(Color.parseColor("#4caf50"));
        canvas.drawLine(
                main_x + mLineSpace * 3, main_y - (mMainLineH - value * 3) / 2,
                main_x + mLineSpace * 3, main_y + (mMainLineH - value * 3) / 2,
                mPaint
        );
        mPaint.setColor(Color.parseColor("#ff9800"));
        canvas.drawLine(
                main_x + mLineSpace * 4, main_y - (mMainLineH - value * 4) / 2,
                main_x + mLineSpace * 4, main_y + (mMainLineH - value * 4) / 2,
                mPaint
        );
    }

    /**
     * @param value 设置音量大小
     */
    public void initSoundValue(int value) {
        if (value / 2 < mLastLineH) {
            mMainLineH = mLastLineH;
        } else {
            mMainLineH = value / 2;
        }
        invalidate();
    }

}

