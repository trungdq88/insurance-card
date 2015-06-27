package com.fpt.mic.mobile.printer.app.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.EditText;
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
        showAlert(context, msg, cb, cb2, null);
    }

    public static void showAlert(Context context, String msg,
                                 final IOnOkClicked cb, final IOnCancelClicked cb2, final IOnNeutralClicked cb3) {

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
        if (cb3 != null) {
            builder.setNeutralButton(context.getString(R.string.setting), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    cb3.onClick();
                }
            });
        }

        builder.create().show();
    }

    public static void showInputBox(Context context, String title, final String defaultValue, String message, final IOnTextInput cb) {
        final EditText textBox = new EditText(context);

        // Set the default text to a link of the Queen
        textBox.setText(defaultValue);

        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setView(textBox)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String text = textBox.getText().toString();
                        cb.onInput(text);
                    }
                })
                .setNegativeButton(context.getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        cb.onInput(defaultValue);
                    }
                })
                .show();
    }

    public interface IOnOkClicked {
        void onClick();
    }

    public interface IOnCancelClicked {
        void onClick();
    }

    public interface IOnNeutralClicked {
        void onClick();
    }

    public interface IOnTextInput {
        void onInput(String text);
    }
}
