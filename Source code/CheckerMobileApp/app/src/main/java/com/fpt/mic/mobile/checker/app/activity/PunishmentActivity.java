package com.fpt.mic.mobile.checker.app.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.fpt.mic.mobile.checker.app.R;
import com.fpt.mic.mobile.checker.app.business.ApiBusiness;
import com.fpt.mic.mobile.checker.app.utils.Constants;
import com.fpt.mic.mobile.checker.app.utils.DialogUtils;
import io.filepicker.Filepicker;
import io.filepicker.models.FPFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * FPT University - Capstone Project - Summer 2015 - Checker Mobile App
 * Created by dinhquangtrung on 7/5/15.
 */
public class PunishmentActivity extends Activity {
    private static final int CAMERA_PIC_REQUEST = 22;
    private ImageView image;
    String filename = "image.png";
    Bitmap photo;
    String photoUrl;
    String contractCode;
    View text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_punishment);

        Filepicker.setKey(Constants.FILEPICKER_API_KEY);

        image = (ImageView) findViewById(R.id.btnCamera);
        text = findViewById(R.id.txtClickToCamera);
        final EditText txtTitle = (EditText) findViewById(R.id.txtTitle);

        contractCode = getIntent().getStringExtra("contractCode");

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO Auto-generated method stub
                text.setVisibility(View.GONE);
                try {
                    //Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    //startActivityForResult(cameraIntent, CAMERA_PIC_REQUEST);

                    Intent intent = new Intent(PunishmentActivity.this, Filepicker.class);
                    String[] services = {"CAMERA"};
                    intent.putExtra("services", services);
                    startActivityForResult(intent, Filepicker.REQUEST_CODE_GETFILE);
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

                if (photoUrl == null) {
                    Toast.makeText(PunishmentActivity.this, "Vui lòng chụp ảnh biên bản phạt", Toast.LENGTH_SHORT).show();
                    return;
                }
                ApiBusiness apiBusiness = new ApiBusiness();

                apiBusiness.sendPunishment(contractCode, title, photoUrl, new ApiBusiness.IOnPunishmentResult() {
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
//        try {
//            switch (requestCode) {
//                case CAMERA_PIC_REQUEST:
//                    if (resultCode == RESULT_OK) {
//                        try {
//                            photo = (Bitmap) data.getExtras().get("data");
//
//                            image.setImageBitmap(photo);
//
//                            // Save to file
//                            saveToFile(photo);
//
//                            // Upload
//                            Filepicker.uploadLocalFile(Uri.fromFile(new File(filename)),
//                                    PunishmentActivity.this);
//
//                        } catch (Exception e) {
//                            Toast.makeText(this, "Couldn't load photo", Toast.LENGTH_LONG).show();
//                        }
//                    }
//                    break;
//                default:
//                    break;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        if (requestCode == Filepicker.REQUEST_CODE_GETFILE) {
            if(resultCode == RESULT_OK) {

                // Filepicker always returns array of FPFile objects
                ArrayList<FPFile> fpFiles = data.getParcelableArrayListExtra(Filepicker.FPFILES_EXTRA);

                if (fpFiles != null && fpFiles.size() > 0) {
                    // Option multiple was not set so only 1 object is expected
                    FPFile file = fpFiles.get(0);
                    photoUrl = file.getUrl();
                    try {
                        photo = MediaStore.Images.Media.getBitmap(this.getContentResolver(), Uri.parse(file.getLocalPath()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    image.setImageBitmap(photo);
                }

                // Do something cool with the result
            } else {
                // Handle errors here
                text.setVisibility(View.VISIBLE);
            }

        }
    }

    private void saveToFile(Bitmap photo) {
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(filename);
            photo.compress(Bitmap.CompressFormat.PNG, 100, out); // bmp is your Bitmap instance
            // PNG is a lossless format, the compression factor (100) is ignored
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
