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


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @Override
    public void onResume(){
        super.onResume();
        enableNfcWrite();
    }

    @Override
    public void onPause(){
        super.onPause();
        disableNfcWrite();
    }

    private PendingIntent getPendingIntent() {
        return PendingIntent.getActivity(this, 0, new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
    }

    private void enableNfcWrite(){
        NfcAdapter adapter = NfcAdapter.getDefaultAdapter(this);
        IntentFilter tagDetected = new IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED);
        IntentFilter[] writeTagFilters = new IntentFilter[] { tagDetected };
        adapter.enableForegroundDispatch(this, getPendingIntent(), writeTagFilters, null);
    }

    private void disableNfcWrite(){
        NfcAdapter adapter = NfcAdapter.getDefaultAdapter(this);
        adapter.disableForegroundDispatch(this);
    }

    public void onNewIntent(Intent intent) {
        if(NfcAdapter.ACTION_TAG_DISCOVERED.equals(intent.getAction())) {
            Tag discoveredTag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
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
