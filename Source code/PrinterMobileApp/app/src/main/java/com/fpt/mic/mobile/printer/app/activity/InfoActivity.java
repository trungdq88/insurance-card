package com.fpt.mic.mobile.printer.app.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.fpt.mic.mobile.printer.app.R;
import com.fpt.mic.mobile.printer.app.dto.ContractSearchResult;
import com.fpt.mic.mobile.printer.app.entity.ContractEntity;
import com.fpt.mic.mobile.printer.app.utils.Constants;
import com.fpt.mic.mobile.printer.app.utils.DialogUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by dinhquangtrung on 5/29/15.
 */
public class InfoActivity extends Activity {
    ContractSearchResult contractSearchResult;

    TextView txtContractCode;
    TextView txtStatus;
    TextView txtName;
    TextView txtAddress;
    TextView txtPhone;
    TextView txtPlate;
    TextView txtChassis;
    TextView txtEngine;
    TextView txtType;
    TextView txtStartDate;
    TextView txtExpiredDate;
//    TextView txtContractFee;
//    TextView txtFeeDate;
//    TextView txtContractStaff;
//    TextView txtDatePublish;
//    TextView txtHotline;
    private int SELF_CLOSE = 1;

    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        contractSearchResult = getIntent().getParcelableExtra("contract");

        txtContractCode = (TextView) findViewById(R.id.txtContractCode);
        txtStatus = (TextView) findViewById(R.id.txtStatus);
        txtName = (TextView) findViewById(R.id.txtName);
        txtAddress = (TextView) findViewById(R.id.txtAddress);
        txtPhone = (TextView) findViewById(R.id.txtPhone);
        txtPlate = (TextView) findViewById(R.id.txtPlate);
        txtChassis = (TextView) findViewById(R.id.txtChassis);
        txtEngine = (TextView) findViewById(R.id.txtEngine);
        txtType = (TextView) findViewById(R.id.txtType);
        txtStartDate = (TextView) findViewById(R.id.txtStartDate);
        txtExpiredDate = (TextView) findViewById(R.id.txtExpiredDate);
//        txtContractFee = (TextView) findViewById(R.id.txtContractFee);
//        txtFeeDate = (TextView) findViewById(R.id.txtFeeDate);
//        txtContractStaff = (TextView) findViewById(R.id.txtContractStaff);
//        txtDatePublish = (TextView) findViewById(R.id.txtDatePublish);
//        txtHotline = (TextView) findViewById(R.id.txtHotline);

        txtContractCode.setText(contractSearchResult.contractEntity.contractCode);
        // txtStatus.setText(contractSearchResult.contractEntity.status);
        setStatus();
        txtName.setText(contractSearchResult.customerEntity.name);
        txtAddress.setText(contractSearchResult.customerEntity.address);
        txtPhone.setText(contractSearchResult.customerEntity.phone);
        txtPlate.setText(contractSearchResult.contractEntity.plate);
        txtChassis.setText(contractSearchResult.contractEntity.chassis);
        txtEngine.setText(contractSearchResult.contractEntity.engine);
        txtType.setText(contractSearchResult.contractEntity.vehicleType);
        txtStartDate.setText(format.format(new Date(contractSearchResult.contractEntity.startDate.getTime())));
        txtExpiredDate.setText(format.format(new Date(contractSearchResult.contractEntity.expiredDate.getTime())));
//        txtContractFee.setText(contractSearchResult.contractEntity.contractFee + " đồng");
        // txtFeeDate.setText(contractSearchResult.contractEntity.);
        // txtContractStaff.setText(contractSearchResult.);
        // txtDatePublish.setText(contractSearchResult.contractEntity.);
        // txtHotline.setText(contractSearchResult.contractEntity.);

        View btnSaveToCard = findViewById(R.id.btnWrite);
        btnSaveToCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeToCard(false);
            }
        });
        btnSaveToCard.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                writeToCard(true);
                return true;
            }
        });
    }

    private void setStatus() {
        String status = contractSearchResult.contractEntity.status;
        if (status.equals(Constants.ContractStatus.PENDING)) {
            txtStatus.setText("Chưa kích hoạt");
            txtStatus.setBackgroundColor(Color.parseColor(Constants.StatusColor.PENDING));
        } else if (status.equals(Constants.ContractStatus.NO_CARD)) {
            txtStatus.setText("Chưa có thẻ");
            txtStatus.setBackgroundColor(Color.parseColor(Constants.StatusColor.NO_CARD));
        } else if (status.equals(Constants.ContractStatus.READY)) {
            txtStatus.setText("Sẵn sàng");
            txtStatus.setBackgroundColor(Color.parseColor(Constants.StatusColor.READY));
        } else if (status.equals(Constants.ContractStatus.REQUEST_CANCEL)) {
            txtStatus.setText("Yêu cầu hủy");
            txtStatus.setBackgroundColor(Color.parseColor(Constants.StatusColor.REQUEST_CANCEL));
        } else if (status.equals(Constants.ContractStatus.EXPIRED)) {
            txtStatus.setText("Hết hạn");
            txtStatus.setBackgroundColor(Color.parseColor(Constants.StatusColor.EXPIRED));
        } else if (status.equals(Constants.ContractStatus.CANCELLED)) {
            txtStatus.setText("Đã hủy");
            txtStatus.setBackgroundColor(Color.parseColor(Constants.StatusColor.CANCELLED));
        }

    }

    private void writeToCard(final boolean ignoreNFC) {
        ContractEntity contract = contractSearchResult.contractEntity;
        if (contract.status
                .equals(Constants.ContractStatus.NO_CARD)) {
            Intent intent = new Intent(InfoActivity.this, WriteActivity.class);
            intent.putExtra("contract", contractSearchResult);
            intent.putExtra("ignoreNFC", ignoreNFC);
            startActivityForResult(intent, SELF_CLOSE);
        } else if (contract.status.equals(Constants.ContractStatus.PENDING) &&
                contract.startDate.equals(contract.expiredDate)) {
            DialogUtils.showAlert(InfoActivity.this, "Hợp đồng này chưa được thanh toán! " +
                    "Vui lòng thanh toán cho hợp đồng trước khi in thẻ!");
        } else if (contract.status
                .equals(Constants.ContractStatus.CANCELLED)) {
            DialogUtils.showAlert(InfoActivity.this, "Hợp đồng này đã bị huỷ. Không thể " +
                    "in thẻ cho hợp đồng đã bị huỷ!");
        } else {
            // READY / EXPIRED / REQUEST_CANCEL
            // DialogUtils.showAlert(InfoActivity.this, "Hợp đồng này đã có 1 thẻ đang được sử dụng." +
            //         " Bạn có muốn in thẻ mới cho hợp đồng này không? (Thẻ cũ sẽ bị vô hiệu hoá)", new DialogUtils.IOnOkClicked() {
            //     @Override
            //     public void onClick() {
                    // Override card
                    Intent intent = new Intent(InfoActivity.this, WriteActivity.class);
                    intent.putExtra("contract", contractSearchResult);
                    intent.putExtra("ignoreNFC", ignoreNFC);
                    startActivityForResult(intent, SELF_CLOSE);
            //     }
            // }, new DialogUtils.IOnCancelClicked() {
            //     @Override
            //     public void onClick() {
            //         // Do nothing
            //     }
            // });
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SELF_CLOSE && resultCode == Activity.RESULT_OK) {
            finish();
        }
    }
}
