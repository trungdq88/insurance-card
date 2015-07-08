package com.fpt.mic.mobile.printer.app.activity;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.*;
import android.nfc.tech.Ndef;
import android.os.Bundle;
import android.widget.Toast;
import com.fpt.mic.mobile.printer.app.R;
import com.fpt.mic.mobile.printer.app.business.ContractBusiness;
import com.fpt.mic.mobile.printer.app.dto.ContractSearchResult;
import com.fpt.mic.mobile.printer.app.entity.CardInstanceEntity;
import com.fpt.mic.mobile.printer.app.utils.DialogUtils;

import java.io.IOException;


public class WriteActivity extends Activity {

    ContractSearchResult contractSearchResult;
    boolean ignoreNFC = false;
    private int SELF_CLOSE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        contractSearchResult = getIntent().getParcelableExtra("contract");
        ignoreNFC = getIntent().getBooleanExtra("ignoreNFC", false);

        if (ignoreNFC) {
            DialogUtils.showInputBox(this, "Giả lập NFC Writter", "",
                    "Nhập mã thẻ để giả lập quá trình ghi thẻ", new DialogUtils.IOnTextInput() {
                @Override
                public void onInput(String text) {
                    syncCardId(text);
                }
            });
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!ignoreNFC) {
            enableNfcWrite();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (!ignoreNFC) {
            disableNfcWrite();
        }
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

            syncCardId(tagID);


        }
    }

    private void syncCardId(String tagID) {
        // Send tag ID to server
        ContractBusiness contractBusiness = new ContractBusiness();
        contractBusiness.updateCardForContract(
                contractSearchResult.contractEntity.contractCode, tagID, new ContractBusiness.IOnApiResult() {
                    @Override
                    public void onApiResult(CardInstanceEntity result) {
                        if (result != null) {
                            // Show result
                            Intent intent = new Intent(WriteActivity.this, SuccessActivity.class);
                            intent.putExtra("card", result);
                            startActivityForResult(intent, SELF_CLOSE);
                        } else {
                            // This card ID is already exists in the system
                            //      => Tell user that this card is no longer usable.
                            DialogUtils.showAlert(WriteActivity.this, "Không thể in thẻ!" +
                                    " Thẻ này đã được sử dụng trong hệ thống!");
                        }
                    }
                });
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SELF_CLOSE || resultCode == Activity.RESULT_OK) {

            if (getParent() == null) {
                setResult(Activity.RESULT_OK);
            } else {
                getParent().setResult(Activity.RESULT_OK);
            }
            finish();
        }
    }
}
