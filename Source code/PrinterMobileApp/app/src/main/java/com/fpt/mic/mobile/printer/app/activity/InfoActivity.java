package com.fpt.mic.mobile.printer.app.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.fpt.mic.mobile.printer.app.R;
import com.fpt.mic.mobile.printer.app.dto.ContractSearchResult;

/**
 * Created by dinhquangtrung on 5/29/15.
 */
public class InfoActivity extends Activity {
    ContractSearchResult contractSearchResult;

    TextView txtName;
    TextView txtAddress;
    TextView txtPhone;
    TextView txtPlate;
    TextView txtChassis;
    TextView txtEngine;
    TextView txtType;
    TextView txtStartDate;
    TextView txtExpiredDate;
    TextView txtContractFee;
    TextView txtFeeDate;
    TextView txtContractStaff;
    TextView txtDatePublish;
    TextView txtHotline;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        contractSearchResult = getIntent().getParcelableExtra("contract");

        txtName = (TextView) findViewById(R.id.txtName);
        txtAddress = (TextView) findViewById(R.id.txtAddress);
        txtPhone = (TextView) findViewById(R.id.txtPhone);
        txtPlate = (TextView) findViewById(R.id.txtPlate);
        txtChassis = (TextView) findViewById(R.id.txtChassis);
        txtEngine = (TextView) findViewById(R.id.txtEngine);
        txtType = (TextView) findViewById(R.id.txtType);
        txtStartDate = (TextView) findViewById(R.id.txtStartDate);
        txtExpiredDate = (TextView) findViewById(R.id.txtExpiredDate);
        txtContractFee = (TextView) findViewById(R.id.txtContractFee);
        txtFeeDate = (TextView) findViewById(R.id.txtFeeDate);
        txtContractStaff = (TextView) findViewById(R.id.txtContractStaff);
        txtDatePublish = (TextView) findViewById(R.id.txtDatePublish);
        txtHotline = (TextView) findViewById(R.id.txtHotline);

        txtName.setText(contractSearchResult.customerEntity.name);
        txtAddress.setText(contractSearchResult.customerEntity.address);
        txtPhone.setText(contractSearchResult.customerEntity.phone);
        txtPlate.setText(contractSearchResult.contractEntity.plate);
        txtChassis.setText(contractSearchResult.contractEntity.chassis);
        txtEngine.setText(contractSearchResult.contractEntity.engine);
        txtType.setText(contractSearchResult.contractEntity.vehicleType);
        txtStartDate.setText(contractSearchResult.contractEntity.startDate.toString());
        txtExpiredDate.setText(contractSearchResult.contractEntity.expiredDate.toString());
        txtContractFee.setText(contractSearchResult.contractEntity.contractFee + " đồng");
        // txtFeeDate.setText(contractSearchResult.contractEntity.);
        // txtContractStaff.setText(contractSearchResult.);
        // txtDatePublish.setText(contractSearchResult.contractEntity.);
        // txtHotline.setText(contractSearchResult.contractEntity.);

        findViewById(R.id.btnWrite).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InfoActivity.this, WriteActivity.class);
                intent.putExtra("contract", contractSearchResult);
                startActivity(intent);
            }
        });
    }

}
