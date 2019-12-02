package com.androidavanzado.capitalsocial.common;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.view.KeyEvent;
import android.view.WindowManager;

import static com.androidavanzado.capitalsocial.common.Constants.progressDialog;

public class GetProgressDialog {

    private static ProgressDialog getProgressDialog;

    public void startProgressDialog(final Activity mContext)
    {
        getProgressDialog = new ProgressDialog(mContext);
        getProgressDialog.setMessage("Loading...");
        getProgressDialog.show();
        getProgressDialog.setCanceledOnTouchOutside(false);
        mContext.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        getProgressDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event)
            {
                if (keyCode == KeyEvent.KEYCODE_BACK)
                {
                    //the user pressed back button - do whatever here
                    //normally you dismiss the dialog like dialog.dismiss();
                    mContext.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                }
                return false;
            }
        });
        progressDialog = true;
    }

    public void stopProgressDialog(Activity mContext)
    {
        if(progressDialog){
            getProgressDialog.dismiss();
            mContext.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        }
        progressDialog = false;
    }

}
