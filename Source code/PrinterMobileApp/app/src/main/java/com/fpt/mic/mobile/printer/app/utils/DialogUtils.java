package com.fpt.mic.mobile.printer.app.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import com.fpt.mic.mobile.printer.app.R;

/**
 * Created by dinhquangtrung on 10/17/14.
 */
public class DialogUtils {
    public static void showAlert(Context context, String msg) {
        showAlert(context, msg, null, null);
    }
    public static void showAlert(Context context, String msg, IOnOkClicked cb) {
        showAlert(context, msg, cb, null);
    }

    public static void showAlert(Context context, String msg,
                                 final IOnOkClicked cb, final IOnCancelClicked cb2) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setTitle(context.getString(R.string.alert))
                .setMessage(msg)
                .setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (cb != null) {
                            cb.onClick();
                        }
                    }
                });
        if (cb2 != null) {
            builder.setNegativeButton(context.getString(R.string.cancel), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    cb2.onClick();
                }
            });
        }

        builder.create().show();
    }


    public static interface IOnOkClicked {
        void onClick();
    }

    public static interface IOnCancelClicked {
        void onClick();
    }
}
