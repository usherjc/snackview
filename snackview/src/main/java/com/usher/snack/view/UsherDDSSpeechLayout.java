package com.usher.snack.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.usher.snack.R;


/**
 * @name : usherchen
 * @date : 2019/6/21
 * @item : midea fridge
 * @desc : usher dds speech layout
 */
public class UsherDDSSpeechLayout extends FrameLayout {

    private FrameLayout mFlContent;
    private UsherDDSSoundView mUshersvMain;
    private ImageView mIvMain;
    private ImageView mIvMainSpeak;
    private FrameLayout mFlLoading;
    private ImageView mIvMainLoadA, mIvMainLoadB;

    private Animation mDefaultAnimation, mSpeakAnimation;

    {
        mDefaultAnimation = new AlphaAnimation(1f, 0f);
        mDefaultAnimation.setRepeatMode(Animation.REVERSE);
        mDefaultAnimation.setRepeatCount(Animation.INFINITE);
        mDefaultAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        mDefaultAnimation.setDuration(1200);

        mSpeakAnimation = new ScaleAnimation(
                1f, 0.5f,
                1f, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
        );
        mSpeakAnimation.setRepeatMode(Animation.REVERSE);
        mSpeakAnimation.setRepeatCount(Animation.INFINITE);
        mSpeakAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        mSpeakAnimation.setDuration(500);
    }

    private ValueAnimator mOpenAnimator, mCloseAnimator;

    {
        mOpenAnimator = ValueAnimator.ofInt(100, 300);
        mOpenAnimator.setRepeatCount(0);
        mOpenAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        mOpenAnimator.setDuration(300);
        mOpenAnimator.addUpdateListener(animation -> {
            try {
                int value = (int) animation.getAnimatedValue();
                mFlContent.getLayoutParams().width = value;
                mFlContent.requestLayout();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        mCloseAnimator = ValueAnimator.ofInt(300, 100);
        mCloseAnimator.setRepeatCount(0);
        mCloseAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        mCloseAnimator.setDuration(300);
        mCloseAnimator.addUpdateListener(animation -> {
            try {
                int value = (int) animation.getAnimatedValue();
                mFlContent.getLayoutParams().width = value;
                mFlContent.requestLayout();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }

    private Animation mLoadAnimationA, mLoadAnimationB;

    {
        mLoadAnimationA = new ScaleAnimation(
                1f, 0.5f,
                1f, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
        );
        mLoadAnimationA.setRepeatMode(Animation.REVERSE);
        mLoadAnimationA.setRepeatCount(Animation.INFINITE);
        mLoadAnimationA.setInterpolator(new AccelerateDecelerateInterpolator());
        mLoadAnimationA.setDuration(800);

        mLoadAnimationB = new ScaleAnimation(
                1f, 2f,
                1f, 2f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
        );
        mLoadAnimationB.setRepeatMode(Animation.REVERSE);
        mLoadAnimationB.setRepeatCount(Animation.INFINITE);
        mLoadAnimationB.setInterpolator(new AccelerateDecelerateInterpolator());
        mLoadAnimationB.setDuration(800);
    }

    private boolean mIsUnderstanding = false;

    public UsherDDSSpeechLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.view_dds_speech, this, true);
        //////////////////////////////////////////////////////////// init views
        mFlContent = findViewById(R.id.fl_content);
        mUshersvMain = findViewById(R.id.ushersv_main);
        mIvMain = findViewById(R.id.iv_main);
        mIvMainSpeak = findViewById(R.id.iv_main_speak);
        mFlLoading = findViewById(R.id.fl_loading);
        mIvMainLoadA = findViewById(R.id.iv_main_load_a);
        mIvMainLoadB = findViewById(R.id.iv_main_load_b);
        //////////////////////////////////////////////////////////// init actions
        initDefaultState();
    }

    /**
     * 初始化默认状态
     */
    public void initDefaultState() {
        initResetStatus();
        ////////////////////////////////////////////////////////////
        initCloseLayout();
        mIvMain.setVisibility(VISIBLE);
        mIvMain.startAnimation(mDefaultAnimation);
    }

    /**
     * 初始化说话状态
     */
    public void initSpeakState() {
        initResetStatus();
        ////////////////////////////////////////////////////////////
        initCloseLayout();
        mIvMainSpeak.setVisibility(VISIBLE);
        mIvMainSpeak.startAnimation(mSpeakAnimation);
    }

    /**
     * 初始化倾听状态
     */
    public void initListeningState(int volume) {
        initResetStatus();
        ////////////////////////////////////////////////////////////
        initOpenLayout();
        mUshersvMain.setVisibility(VISIBLE);
        mUshersvMain.initSoundValue(volume);
    }

    /**
     * 初始化理解状态
     */
    public void initUnderstandState() {
        initResetStatus();
        ////////////////////////////////////////////////////////////
        initCloseLayout();
        mIvMainLoadA.startAnimation(mLoadAnimationA);
        mIvMainLoadB.startAnimation(mLoadAnimationB);
    }

    /**
     * 重置所有状态
     */
    private void initResetStatus() {
        try {
            /////////// 重置默认状态
            mIvMain.clearAnimation();
            mIvMain.setVisibility(GONE);
            /////////// 重置说话状态
            mIvMainSpeak.clearAnimation();
            mIvMainSpeak.setVisibility(GONE);
            /////////// 重置倾听状态
            mUshersvMain.initSoundValue(0);
            mUshersvMain.setVisibility(GONE);
            /////////// 重置理解状态
            mIvMainLoadA.clearAnimation();
            mIvMainLoadB.clearAnimation();
            mFlLoading.setVisibility(GONE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 展开页面
     */
    private void initOpenLayout() {
        if (!mIsUnderstanding) {
            mIsUnderstanding = true;
            mOpenAnimator.start();
        }
    }

    /**
     * 收起页面
     */
    private void initCloseLayout() {
        if (mIsUnderstanding) {
            mIsUnderstanding = false;
            mCloseAnimator.start();
        }
    }

}
