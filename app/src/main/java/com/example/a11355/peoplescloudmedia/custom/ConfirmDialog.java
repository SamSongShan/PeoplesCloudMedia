package com.example.a11355.peoplescloudmedia.custom;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.a11355.peoplescloudmedia.R;
import com.example.a11355.peoplescloudmedia.base.BaseDialog;


/**
 * 确认 对话框
 */
public class ConfirmDialog extends BaseDialog implements View.OnClickListener {

    public static ConfirmDialog newInstance(String title, String cancel, String confirm) {
        ConfirmDialog dialog = new ConfirmDialog();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        bundle.putString("cancel", cancel);
        bundle.putString("confirm", confirm);
        dialog.setArguments(bundle);
        return dialog;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_confirm, null);
        AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                .setView(view)
                .create();
        //点击对话框外不可取消
        alertDialog.setCanceledOnTouchOutside(false);
        //进入退出动画
        alertDialog.getWindow().setWindowAnimations(R.style.animTranslateTop);

        TextView tvTitle = (TextView) view.findViewById(R.id.tv_dialogConfirmTitle);
        TextView btnCancel = (TextView) view.findViewById(R.id.btn_cancelDialog);
        TextView btnConfirm = (TextView) view.findViewById(R.id.btn_confirmDialog);

        tvTitle.setText(getArguments().getString("title"));
        btnCancel.setText(getArguments().getString("cancel"));
        btnConfirm.setText(getArguments().getString("confirm"));
        btnCancel.setOnClickListener(this);
        btnConfirm.setOnClickListener(this);
        return alertDialog;
    }

    @Override
    public void onClick(View v) {
        onItemClickListener.onItemClick(v);
        dismiss();
    }
}
