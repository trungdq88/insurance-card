package com.fpt.mic.mobile.printer.app.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.fpt.mic.mobile.printer.app.R;
import com.fpt.mic.mobile.printer.app.entity.CardInstanceEntity;

/**
 * FPT University - Capstone Project - Summer 2015 - PrinterMobileApp
 * Created by dinhquangtrung on 6/8/15.
 */
public class SuccessActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);

        CardInstanceEntity cardEntity = getIntent().getParcelableExtra("card");

        TextView txtCardID = (TextView) findViewById(R.id.txtCardID);
        TextView txtContractCode = (TextView) findViewById(R.id.txtContractCode);
        TextView txtActivatedDate = (TextView) findViewById(R.id.txtActivatedDate);

        txtCardID.setText(cardEntity.cardId);
        txtContractCode.setText(cardEntity.contractCode);
        txtActivatedDate.setText(cardEntity.activatedDate.toString());

        findViewById(R.id.btnBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                if (getParent() == null) {
                    setResult(Activity.RESULT_OK);
                } else {
                    getParent().setResult(Activity.RESULT_OK);
                }
            }
        });
    }
}
