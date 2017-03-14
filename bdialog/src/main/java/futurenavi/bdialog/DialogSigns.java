package futurenavi.bdialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


/**
 * Created by zzz40500 on 15/6/15.
 */
public class DialogSigns {


    private final CallBack call;
    int code;
    private String msg, name2 = "重新登录", name1 = "稍后尝试";
    private Context mContext;
    private Dialog mDialog;
    private Button mBtnCancel, mBtnSetting;
    private View mDialogContentView;


    public DialogSigns(Context context, DialogSigns.CallBack call) {
        this.mContext = context;
        this.call = call;
        init();
    }


    TextView mTvMessage;
    int flag = 0;//0默认1重连网络2重新登录

    private void init() {
        mDialog = new Dialog(mContext, R.style.dialog_loading);
        mDialogContentView = LayoutInflater.from(mContext).inflate(R.layout.dialog_signs, null);
        mDialog.setContentView(mDialogContentView);
        mDialogContentView.findViewById(R.id.btn_dialog_signs_cancel)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mDialog.dismiss();
                    }
                });
        mDialogContentView.findViewById(R.id.btn_dialog_signs_over)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        call.callOnclick();
                        mDialog.dismiss();
                    }
                });
    }

    public void setBackground(int color) {
//        GradientDrawable gradientDrawable= (GradientDrawable) mDialogContentView.getBackground();
//        gradientDrawable.setColor(color);
    }

    public void setLoadingText(CharSequence charSequence) {
    }

    public void show() {
        mDialog.show();
    }

    public void dismiss() {
        mDialog.dismiss();
    }

    public Dialog getDialog() {
        return mDialog;
    }

    public void setCanceledOnTouchOutside(boolean cancel) {
        mDialog.setCanceledOnTouchOutside(cancel);
    }

    public interface CallBack {
        void callOnclick();
    }
}
