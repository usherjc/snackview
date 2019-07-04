package com.usher.snack.view;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.usher.snack.R;
import com.usher.snack.view.base.UsherBaseCard;

public class UsherDDSChatsView extends UsherBaseCard {

    private TextView mTvSpeakDds;

    public UsherDDSChatsView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * @param context 初始化操作
     */
    @Override
    protected void initContent(Context context) {
        super.initContent(context);
        setBackgroundColor(Color.TRANSPARENT);
        View.inflate(context, R.layout.view_usher_dds_chats, this);
        mTvSpeakDds = findViewById(R.id.tv_speak_dds);
    }

    /**
     * @param content 显示文字
     */
    public void initSpeakText(String content) {
        show();
        mTvSpeakDds.setText(content);
    }

    /**
     * 重置聊天窗口状态
     */
    public void initSpeakReset() {
        hide();
    }

    /**
     * 当隐藏结束以后
     */
    @Override
    protected void onHideFinish() {
        super.onHideFinish();
        mTvSpeakDds.setText("");
    }
}