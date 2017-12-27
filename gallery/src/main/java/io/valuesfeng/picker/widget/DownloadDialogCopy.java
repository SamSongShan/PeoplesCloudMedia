package io.valuesfeng.picker.widget;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import io.valuesfeng.picker.R;
import io.valuesfeng.picker.utils.OkHttpUtil;
import okhttp3.Call;

/**
 * 下载进度对话框
 */
public class DownloadDialogCopy extends BaseDialog implements DialogInterface.OnKeyListener {

    private static Context context;
    private TextView tvTitle;
    private ProgressBar pb;
    private TextView btnInstall;

    public static DownloadDialogCopy newInstance(String title, boolean isForce) {
        DownloadDialogCopy downloadDialog = new DownloadDialogCopy();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        bundle.putBoolean("isForce", isForce);
        downloadDialog.setArguments(bundle);
        return downloadDialog;
    }

    public static DownloadDialogCopy newInstance(String title, boolean isForce, Context context) {
        DownloadDialogCopy.context = context;
        DownloadDialogCopy downloadDialog = new DownloadDialogCopy();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        bundle.putBoolean("isForce", isForce);
        downloadDialog.setArguments(bundle);
        return downloadDialog;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_download, null);
        AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                .setView(view)
                .create();
        btnInstall= (TextView) view.findViewById(R.id.btn_installApp);
        tvTitle = (TextView) view.findViewById(R.id.tv_downloadTitle);
        pb = (ProgressBar) view.findViewById(R.id.pb_download);
        tvTitle.setText(getArguments().getString("title"));
        //点击对话框外不可取消
        alertDialog.setCanceledOnTouchOutside(false);

            alertDialog.setOnKeyListener(this);

        return alertDialog;
    }

    @Override
    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK || keyCode == KeyEvent.KEYCODE_SEARCH) {

          if (context!=null){
              cancelPostFile();
          }
            return true;
        } else {
            return false;
        }
    }
    //取消上传
    private void cancelPostFile() {
        Call fileActivity0 = OkHttpUtil.callMap.get("file");
        if (fileActivity0 != null) {
            fileActivity0.cancel();

            Toast.makeText(context,"已取消",Toast.LENGTH_LONG).show();
            OkHttpUtil.callMap.remove("file");
            this.dismiss();
        }
    }
    public ProgressBar getProgressBar() {
        return pb;
    }

    public TextView getBtnInstall() {
        return btnInstall;
    }
}
