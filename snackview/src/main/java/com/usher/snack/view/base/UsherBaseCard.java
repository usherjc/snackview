package com.usher.snack.view.base;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.animation.AccelerateDecelerateInterpolator;

public class UsherBaseCard extends CardView {

    protected boolean mShow = false;
    protected ValueAnimator mShowAnimator, mHideAnimator;

    {
        mShowAnimator = ValueAnimator.ofFloat(0f, 1f).setDuration(800);
        mShowAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        mShowAnimator.addUpdateListener(animation -> setAlpha((Float) animation.getAnimatedValue()));
        mShowAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                setVisibility(VISIBLE);
            }
        });

        mHideAnimator = ValueAnimator.ofFloat(1f, 0f).setDuration(800);
        mHideAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        mHideAnimator.addUpdateListener(animation -> setAlpha((Float) animation.getAnimatedValue()));
        mHideAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                setVisibility(GONE);
                onHideFinish();
            }
        });
    }

    public UsherBaseCard(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initContent(context);
        setVisibility(GONE);
        setAlpha(0f);
    }

    protected void initContent(Context context) {

    }

    protected void onHideFinish() {

    }

    protected void show() {
        if (!mShow) {
            mShow = true;
            mShowAnimator.start();
        }
    }

    protected void hide() {
        if (mShow) {
            mShow = false;
            mHideAnimator.start();
        }
    }

}
