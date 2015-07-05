package com.fpt.mic.mobile.checker.app.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.fpt.mic.mobile.checker.app.R;
import com.fpt.mic.mobile.checker.app.business.ApiBusiness;
import com.fpt.mic.mobile.checker.app.utils.DialogUtils;

/**
 * FPT University - Capstone Project - Summer 2015 - Checker Mobile App
 * Created by dinhquangtrung on 7/5/15.
 */
public class PunishmentActivity extends Activity {
    private static final int CAMERA_PIC_REQUEST = 22;
    private ImageView image;
    Bitmap photo;
    String contractCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_punishment);

        image = (ImageView) findViewById(R.id.btnCamera);
        final View text = findViewById(R.id.txtClickToCamera);
        final EditText txtTitle = (EditText) findViewById(R.id.txtTitle);

        contractCode = getIntent().getStringExtra("contractCode");

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO Auto-generated method stub
                text.setVisibility(View.GONE);
                try {
                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_PIC_REQUEST);
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Couldn't load photo", Toast.LENGTH_LONG).show();
                }
            }
        });


        findViewById(R.id.btnSend).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = txtTitle.getText().toString();
                if (title.isEmpty()) {
                    Toast.makeText(PunishmentActivity.this, "Vui lòng nhập nội dung vi phạm", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (photo == null) {
                    Toast.makeText(PunishmentActivity.this, "Vui lòng chụp ảnh biên bản phạt", Toast.LENGTH_SHORT).show();
                    return;
                }
                ApiBusiness apiBusiness = new ApiBusiness();

                apiBusiness.sendPunishment(contractCode, title, photo, new ApiBusiness.IOnPunishmentResult() {
                    @Override
                    public void onPunishmentResult(final boolean result) {
                        String message = result ? "Đã thêm thông tin vi phạm thành công" : "Không thể thêm thông tin vi phạm, vui lòng thử lại.";
                        DialogUtils.showAlert(PunishmentActivity.this, message, new DialogUtils.IOnOkClicked() {
                            @Override
                            public void onClick() {
                                if (result) {
                                    finish();
                                }
                            }
                        });
                    }
                });

            }
        });
    }

    @Override
    public void onActivityResult(final int requestCode, int resultCode, Intent data) {
        try {
            switch (requestCode) {
                case CAMERA_PIC_REQUEST:
                    if (resultCode == RESULT_OK) {
                        try {
                            photo = (Bitmap) data.getExtras().get("data");

                            image.setImageBitmap(photo);

                        } catch (Exception e) {
                            Toast.makeText(this, "Couldn't load photo", Toast.LENGTH_LONG).show();
                        }
                    }
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
