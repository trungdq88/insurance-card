package com.fpt.mic.mobile.printer.app.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import com.fpt.mic.mobile.printer.app.R;
import com.fpt.mic.mobile.printer.app.business.ApiBusiness;
import com.fpt.mic.mobile.printer.app.utils.DialogUtils;
import com.fpt.mic.mobile.printer.app.utils.Settings;

public class SplashScreen extends Activity {

    final int CHECK_TIMEOUT = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash_screen);

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                startCheck();
            }
        };

        (new Handler()).postDelayed(runnable, 1000);
    }

    void startCheck() {

        Runnable checkInternetRunnable = new Runnable() {
            @Override
            public void run() {
                final Runnable self = this;
                ApiBusiness apiBusiness = new ApiBusiness();
                apiBusiness.checkConnection(new ApiBusiness.IOnConnectionResult() {
                    @Override
                    public void onConnectionResult(boolean result) {
                        if (result) {
                            openMainActivity();
                        } else {
                            DialogUtils.showAlert(SplashScreen.this,
                                    getApplicationContext().getText(R.string.noConnection).toString(),
                                    new DialogUtils.IOnOkClicked() {
                                        @Override
                                        public void onClick() {
                                            // Try again
                                            (new Handler()).postDelayed(self, CHECK_TIMEOUT);
                                        }
                                    }, new DialogUtils.IOnCancelClicked() {
                                        @Override
                                        public void onClick() {
                                            finish();
                                        }
                                    }, new DialogUtils.IOnNeutralClicked() {
                                        @Override
                                        public void onClick() {
                                            setCustomServerIp(new IServerSetUpDone() {
                                                @Override
                                                public void onServerSetUp() {
                                                    // Try again with new set up
                                                    (new Handler()).postDelayed(self, CHECK_TIMEOUT);
                                                }
                                            });
                                        }
                                    });
                        }
                    }
                });

            }
        };
        (new Handler()).postDelayed(checkInternetRunnable, CHECK_TIMEOUT);

    }

    private void setCustomServerIp(final IServerSetUpDone cb) {
        DialogUtils.showInputBox(this, "Cài đặt", Settings.getServerIp(),
                "Vui lòng nhập địa chỉ IP của server", new DialogUtils.IOnTextInput() {
                    @Override
                    public void onInput(String text) {
                        Settings.setServerIp(text);
                        cb.onServerSetUp();
                    }
                });
    }


    void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        // Prevent exit app in splash screen
        // super.onBackPressed();
    }

    public interface IServerSetUpDone {
        void onServerSetUp();
    }
}
