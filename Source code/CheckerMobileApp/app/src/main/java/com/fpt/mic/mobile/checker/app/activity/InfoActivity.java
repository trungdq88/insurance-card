package com.fpt.mic.mobile.checker.app.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.fpt.mic.mobile.checker.app.R;
import com.fpt.mic.mobile.checker.app.entity.CardInstanceEntity;
import com.fpt.mic.mobile.checker.app.entity.CheckCardResponseDto;
import com.fpt.mic.mobile.checker.app.utils.Constants;

import java.util.Date;

/**
 * Created by dinhquangtrung on 5/29/15.
 */
public class InfoActivity extends Activity {
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
    TextView txtContractFee;
    TextView txtFeeDate;
    TextView txtContractStaff;
    TextView txtDatePublish;
    TextView txtHotline;
    TextView txtCardId;
    private CardInstanceEntity card;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        findViewById(R.id.btnMenu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InfoActivity.this.openOptionsMenu();
            }
        });
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
        txtContractFee = (TextView) findViewById(R.id.txtContractFee);
        txtFeeDate = (TextView) findViewById(R.id.txtFeeDate);
        txtContractStaff = (TextView) findViewById(R.id.txtContractStaff);
        txtDatePublish = (TextView) findViewById(R.id.txtDatePublish);
        txtHotline = (TextView) findViewById(R.id.txtHotline);
        txtCardId = (TextView) findViewById(R.id.txtCardId);

        CardInstanceEntity card = (CardInstanceEntity) getIntent().getParcelableExtra("card");
        int result = getIntent().getIntExtra("result", CheckCardResponseDto.RESULT_INVALID_CARD);
        showInfo(card, result);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.info_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.add_punishment) {
            if (card != null) {
                Intent intent = new Intent(this, PunishmentActivity.class);
                intent.putExtra("contractCode", card.contractCode);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Thẻ không hợp lệ!", Toast.LENGTH_SHORT).show();
            }
            return true;
        } else if (item.getItemId() == R.id.close) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    public void showInfo(CardInstanceEntity card, int result) {

        this.card = card;

        if (card == null) {
            showInvalidCard();
            return;
        }

        switch (result) {
            case CheckCardResponseDto.RESULT_VALID_CARD:
                showValidCard();
                break;
            case CheckCardResponseDto.RESULT_INVALID_CARD:
                showInvalidCard();
                return;
            case CheckCardResponseDto.RESULT_EXPIRED_CARD:
                showExpiredCard();
                break;
            case CheckCardResponseDto.RESULT_NEARLY_EXPIRED_CARD:
                showNearlyExpiredCard();
                break;
            default:
                showInvalidCard();
                return;
        }

        txtName.setText(card.micContractByContractCode.micCustomerByCustomerCode.name);
        txtAddress.setText(card.micContractByContractCode.micCustomerByCustomerCode.address);
        txtPhone.setText(card.micContractByContractCode.micCustomerByCustomerCode.phone);
        txtPlate.setText(card.micContractByContractCode.plate);
        txtChassis.setText(card.micContractByContractCode.chassis);
        txtEngine.setText(card.micContractByContractCode.engine);
        txtType.setText(card.micContractByContractCode.vehicleType);
        txtStartDate.setText(card.micContractByContractCode.startDate.toString());
        txtExpiredDate.setText(card.micContractByContractCode.expiredDate.toString());
        txtContractFee.setText(card.micContractByContractCode.contractFee + " đồng");
        txtCardId.setText(card.cardId);

        // TODO: more status
    }

    private void showExpiredCard() {
        txtStatus.setText("THẺ HẾT HẠN");
        txtStatus.setBackgroundColor(Color.RED);
    }

    private void showNearlyExpiredCard() {
        txtStatus.setText("THẺ SẮP HẾT HẠN");
        txtStatus.setBackgroundColor(Color.YELLOW);
    }

    private void showValidCard() {
        txtStatus.setText("THẺ HỢP LỆ");
        txtStatus.setBackgroundColor(Color.GREEN);
    }

    private void showInvalidCard() {
        txtStatus.setText("THẺ KHÔNG HỢP LỆ");
        txtStatus.setBackgroundColor(Color.RED);
        findViewById(R.id.lytInfo1).setVisibility(View.GONE);
        findViewById(R.id.lytInfo2).setVisibility(View.GONE);
        findViewById(R.id.lytInfo3).setVisibility(View.GONE);
        findViewById(R.id.lytInvalid).setVisibility(View.VISIBLE);
    }
}
