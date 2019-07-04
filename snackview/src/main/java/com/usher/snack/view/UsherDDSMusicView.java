package com.usher.snack.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.usher.snack.R;
import com.usher.snack.util.CommonUtils;
import com.usher.snack.view.base.UsherBaseCard;
import com.usher.snack.view.listener.OnUsherAudioOperaListener;

public class UsherDDSMusicView extends UsherBaseCard implements SeekBar.OnSeekBarChangeListener, View.OnClickListener {

    private TextView mTvMusicTitle, mTvMusicSinger, mTvTimePosition, mTvTimeTotal;
    private SimpleDraweeView mSdvMusicPng;
    private ImageView mIvMusicNavigation;
    private SeekBar mSbMusic;
    private ImageView mIvExitMusic;
    private OnUsherAudioOperaListener mListener;
    private boolean mPlay = false;

    /**
     * init main views & actions
     *
     * @param context context
     * @param attrs   attrs
     */
    public UsherDDSMusicView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void initContent(Context context) {
        super.initContent(context);
        setBackgroundColor(Color.TRANSPARENT);
        View.inflate(context, R.layout.view_usher_dds_music, this);
        //////////////////////////////////////////////////////////// init views
        mTvMusicTitle = findViewById(R.id.tv_music_title);
        mTvMusicSinger = findViewById(R.id.tv_audio_type);
        mSdvMusicPng = findViewById(R.id.sdv_music_png);
        mIvMusicNavigation = findViewById(R.id.iv_music_navigation);
        mSbMusic = findViewById(R.id.sb_music);
        mTvTimePosition = findViewById(R.id.tv_time_position);
        mTvTimeTotal = findViewById(R.id.tv_time_total);
        mIvExitMusic = findViewById(R.id.iv_exit_music);
        //////////////////////////////////////////////////////////// init actions
        mSbMusic.setOnSeekBarChangeListener(this);
        mIvMusicNavigation.setOnClickListener(this);
        mIvExitMusic.setOnClickListener(this);
        initPlayStatusReset();
    }

    @Override
    protected void onHideFinish() {
        super.onHideFinish();
        initPlayStatusReset();
    }

    //////////////////////////////////////////////////////////////////////////////// business

    /**
     * 设置音乐信息
     *
     * @param type      type
     *                  1 - music
     *                  2 - other
     * @param title     title
     * @param sub_title sub_title
     * @param img       img
     */
    public void initMusicInfo(
            int type,
            String title, String sub_title, String img
    ) {
        mTvMusicTitle.setText(title);
        mTvMusicSinger.setText(sub_title);
        if (1 == type) {
            mSdvMusicPng.setVisibility(View.VISIBLE);
            mSdvMusicPng.setImageURI(img);
        } else {
            mSdvMusicPng.setVisibility(View.GONE);
        }

    }

    /**
     * 开始准备播放
     */
    public void initPlayStart() {
        show();
        initPlayStatus(true);
        mIvMusicNavigation.setEnabled(true);
    }

    /**
     * 设置播放控制按钮状态
     *
     * @param play play
     */
    public void initPlayStatus(boolean play) {
        mPlay = play;
        mIvMusicNavigation.setImageResource(mPlay ? R.drawable.icon_music_stop : R.drawable.icon_music_play);
    }

    /**
     * 设置播放进度
     *
     * @param currentTime currentTime
     * @param totalTime   totalTime
     */
    public void initPlayProgressStatus(int currentTime, int totalTime) {
        mTvTimePosition.setText(CommonUtils.initFormatMusicTime(currentTime));
        mTvTimeTotal.setText(CommonUtils.initFormatMusicTime(totalTime));
        mSbMusic.setMax(totalTime);
        mSbMusic.setProgress(currentTime);
    }

    /**
     * 重置所有播放状态，包含默认状态与播放完成的状态
     */
    public void initPlayStatusReset() {
        mTvMusicTitle.setText(R.string.app_none);
        mTvMusicSinger.setText(R.string.app_none);
        mSdvMusicPng.setActualImageResource(R.drawable.icon_music_holder);
        mIvMusicNavigation.setEnabled(false);
        initPlayStatus(false);
        mSbMusic.setMax(0);
        mSbMusic.setProgress(0);
        mTvTimePosition.setText(R.string.app_music_time_null);
        mTvTimeTotal.setText(R.string.app_music_time_null);
    }

    /**
     * @param mListener 注册操作的监听
     */
    public void setUsherListener(OnUsherAudioOperaListener mListener) {
        this.mListener = mListener;
    }

    /**
     * 结束的时候消失
     */
    public void dismiss() {
        hide();
    }

    //////////////////////////////////////////////////////////////////////////////// view events

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == mIvMusicNavigation.getId()) {
            if (mListener != null) {
                if (mPlay) {
                    mListener.onClick2Pause();
                } else {
                    mListener.onClick2Play();
                }
                initPlayStatus(!mPlay);
            }
            return;
        }
        if (id == mIvExitMusic.getId()) {
            if (mListener != null) {
                mListener.onClick2Exit();
            }
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (mListener != null && fromUser) {
            mListener.onDragProgress(progress);
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

}
