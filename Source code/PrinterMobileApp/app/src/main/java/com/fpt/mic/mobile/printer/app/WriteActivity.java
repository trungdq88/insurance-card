package com.fpt.mic.mobile.printer.app;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.*;
import android.nfc.tech.Ndef;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;


public class WriteActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);
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
            Tag discoveredTag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);

            byte[] extraID = discoveredTag.getId();

            StringBuilder sb = new StringBuilder();
            for (byte b : extraID) {
                sb.append(String.format("%02X", b));
            }

            String tagID = sb.toString();

            // Send tag ID to server

            // Write data to tag
            writeTag(discoveredTag);
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
        Log.d("NFC", "Data written");
        Toast.makeText(this, "Data written", Toast.LENGTH_LONG).show();
    }
}
