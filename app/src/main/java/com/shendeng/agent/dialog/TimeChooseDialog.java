package com.shendeng.agent.dialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.shendeng.agent.R;
import com.shendeng.agent.util.UIHelper;


public class TimeChooseDialog extends Dialog {


    private OnDialogItemClickListener mListener;

    private TextView tvQueDing;
    private TextView tvQuXiao;
    private Context context;
    private TimePicker timePicker;

    public TimeChooseDialog(Context context, OnDialogItemClickListener mListener) {
        this(context, R.style.dialogBaseBlur);
        this.context = context;
        this.mListener = mListener;
    }

    public TimeChooseDialog(Context context, int theme) {
        super(context, theme);
        init();
    }

    private void init() {
        setCanceledOnTouchOutside(true);
        setContentView(R.layout.time_choose);
        tvQueDing = findViewById(R.id.tv_confirm);
        tvQuXiao = findViewById(R.id.tv_cancel);
        timePicker = findViewById(R.id.timepicker);
        timePicker.setIs24HourView(true);
        tvQueDing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                    String str = String.valueOf(timePicker.getHour()) + ":" + String.valueOf(timePicker.getMinute());
                    mListener.clickRight(str);

                }
            }
        });
        tvQuXiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.clickLeft();
                dismiss();
            }
        });

    }


    public interface OnDialogItemClickListener {
        void clickLeft();

        void clickRight(String time);
    }


}
