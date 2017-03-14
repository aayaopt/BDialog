package futurenavi.bdialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.futurenavi.ui.start.ULoginAct;
import com.pkk.utils.NetUtile;


/**
 * Created by zzz40500 on 15/6/15.
 */
public class DialogErr {


    private final CallBack call;
    int code;
    private String msg, name1 = "稍后尝试", name2 = "重新登录";
    private Context mContext;
    private Dialog mDialog;
    private Button mBtnCancel, mBtnSetting;
    private View mDialogContentView;


    public DialogErr(Context context, DialogErr.CallBack call) {
        this.mContext = context;
        this.call = call;
        init();
    }

    public DialogErr(Context context, int code, String msg, DialogErr.CallBack call) {
        this.mContext = context;
        this.msg = msg;
        this.code = code;
        this.call = call;
        init();
    }

    TextView mTvMessage;
    int flag = 0;//0默认1重连网络2重新登录

    private void init() {
        mDialog = new Dialog(mContext, R.style.dialog_loading);
        mDialogContentView = LayoutInflater.from(mContext).inflate(R.layout.dialog_err, null);
        mTvMessage = (TextView) mDialogContentView.findViewById(R.id.tv_dialog_message);
        mBtnCancel = (Button) mDialogContentView.findViewById(R.id.btn_dialog_cancel);
        mBtnSetting = (Button) mDialogContentView.findViewById(R.id.btn_dialog_setting);
        mDialog.setContentView(mDialogContentView);
        mBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });
        if (call != null) {
            mBtnCancel.setVisibility(View.GONE);
            mTvMessage.setText(msg);
            switch (code) {
                case 400:
                case 500:
                    flag = 1;
                    mBtnSetting.setText(name1);
                    break;
                case 700:
                case 900:
                    flag = 2;
                    mBtnSetting.setText(name2);
                    break;
                default:
                    flag = 0;
                    break;
            }
        }
        mBtnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (call != null) {
                    if (flag == 2) {
                        Intent intent = new Intent(mContext, ULoginAct.class);
                        mContext.startActivity(intent);
                        ((Activity)mContext).finish();
                    }
                    call.callOnclick(flag);
                } else {
                    new NetUtile(mContext).openWirelessSettings();
                }
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
        void callOnclick(int flag);
    }
}
