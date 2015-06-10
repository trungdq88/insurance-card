package com.fpt.mic.mobile.printer.app.activity;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.*;
import android.nfc.tech.Ndef;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import com.fpt.mic.mobile.printer.app.R;
import com.fpt.mic.mobile.printer.app.business.ContractBusiness;
import com.fpt.mic.mobile.printer.app.dto.ContractSearchResult;
import com.fpt.mic.mobile.printer.app.entity.CardEntity;
import com.fpt.mic.mobile.printer.app.utils.DialogUtils;

import java.io.IOException;


public class WriteActivity extends Activity {

    ContractSearchResult contractSearchResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        contractSearchResult = getIntent().getParcelableExtra("contract");
    }

    @Override
    public void onResume() {
        super.onResume();
        enableNfcWrite();
    }

    @Override
    public void onPause() {
        super.onPause();
        disableNfcWrite();
    }

    private PendingIntent getPendingIntent() {
        return PendingIntent.getActivity(this, 0, new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
    }

    private void enableNfcWrite() {
        NfcAdapter adapter = NfcAdapter.getDefaultAdapter(this);

        if (adapter == null || !adapter.isEnabled()) {
            Toast.makeText(this, "Vui lòng bật chức năng NFC trên điện thoại!", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        IntentFilter tagDetected = new IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED);
        IntentFilter[] writeTagFilters = new IntentFilter[]{tagDetected};
        adapter.enableForegroundDispatch(this, getPendingIntent(), writeTagFilters, null);
    }

    private void disableNfcWrite() {
        NfcAdapter adapter = NfcAdapter.getDefaultAdapter(this);
        if (adapter != null && adapter.isEnabled()) {
            adapter.disableForegroundDispatch(this);
        }
    }

    public void onNewIntent(Intent intent) {
        if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(intent.getAction())) {
            final Tag discoveredTag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);

            byte[] extraID = discoveredTag.getId();

            StringBuilder sb = new StringBuilder();
            for (byte b : extraID) {
                sb.append(String.format("%02X", b));
            }

            String tagID = sb.toString();

            // Write information to tag
            writeTag(discoveredTag);

            Toast.makeText(this, "Đang in thẻ...", Toast.LENGTH_SHORT).show();

            // Send tag ID to server
            ContractBusiness contractBusiness = new ContractBusiness();
            contractBusiness.updateCardForContract(
                    contractSearchResult.contractEntity.contractCode, tagID, new ContractBusiness.IOnApiResult() {
                        @Override
                        public void onApiResult(CardEntity result) {
                            if (result != null) {
                                // Show result
                                finish();
                                Intent intent = new Intent(WriteActivity.this, SuccessActivity.class);
                                intent.putExtra("card", result);
                                startActivity(intent);
                            } else {
                                // There was error when update card for contract
                                // TODO: handle 2 cases:
                                // 1. Contract already has card and the card is still activated
                                //      => Ask user if they want to deactivate the old card
                                // 2. This card ID is already exists in the system
                                //      => Tell user that this card is no longer usable.
                                DialogUtils.showAlert(WriteActivity.this, "Không thể in thẻ! Thẻ đang được sử dụng bởi một hợp đồng khác!");
                            }
                        }
                    });

        }
    }

    private void writeTag(Tag tag) {
        Ndef ndefTag = Ndef.get(tag);
        byte[] stringBytes = "Hello World".getBytes();
        NdefRecord dataToWrite = NdefRecord.createMime("mic/nfc", stringBytes);
        try {
            ndefTag.connect();
            ndefTag.writeNdefMessage(new NdefMessage(dataToWrite,
                    NdefRecord.createApplicationRecord("com.fpt.mic.mobile.checker.app")));
            ndefTag.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FormatException e) {
            e.printStackTrace();
        }
    }
}
