package com.fpt.mic.mobile.checker.app.activity;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.NfcA;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.fpt.mic.mobile.checker.app.R;
import com.fpt.mic.mobile.checker.app.business.ApiBusiness;
import com.fpt.mic.mobile.checker.app.entity.CardInstanceEntity;

import java.util.Date;


public class MainActivity extends Activity {

    NfcAdapter mAdapter;
    IntentFilter[] mFilters;
    PendingIntent mPendingIntent;
    // Setup a tech list for all NfcF tags
    String[][] mTechLists = new String[][] { new String[] { NfcA.class.getName() } };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btnCheck).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText txtCardID = (EditText) findViewById(R.id.txtCardID);
                readCard(txtCardID.getText().toString());
            }
        });


        mPendingIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);

        IntentFilter ndef1 = new IntentFilter(NfcAdapter.ACTION_TECH_DISCOVERED);
        //IntentFilter ndef2 = new IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED);
        mFilters = new IntentFilter[] {
                ndef1,
                //ndef2,
        };

        try {
            ndef1.addDataType("*/*");
            //ndef2.addDataType("*/*");
        } catch (IntentFilter.MalformedMimeTypeException e) {
            throw new RuntimeException("fail", e);
        }
        mAdapter = NfcAdapter.getDefaultAdapter(this);

        if (getIntent() != null){
            resolveIntent(getIntent());
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        if (mAdapter != null) {
            mAdapter.enableForegroundDispatch(this, mPendingIntent, mFilters, mTechLists);
        }
    }

    @Override
    public void onNewIntent(Intent intent) {
        Log.i("Foreground dispatch", "Discovered tag with intent: " + intent);
        resolveIntent(intent);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mAdapter != null) {
            mAdapter.disableForegroundDispatch(this);
        }
    }


    void resolveIntent(Intent intent) {

        // 1) Parse the intent and get the action that triggered this intent
        String action = intent.getAction();
        // 2) Check if it was triggered by a tag discovered interruption.
        if (NfcAdapter.ACTION_TECH_DISCOVERED.equals(action)) {
            //  3) Get an instance of the TAG from the NfcAdapter
            Tag tagFromIntent = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
            byte[] extraID = tagFromIntent.getId();

            StringBuilder sb = new StringBuilder();
            for (byte b : extraID) {
                sb.append(String.format("%02X", b));
            }

            String tagID = sb.toString();
            Log.e("nfc ID", tagID);

            readCard(tagID);
        }
    }

    private void readCard(String tagID) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_LONG;
        ApiBusiness apiBusiness = new ApiBusiness();
        apiBusiness.checkCard(tagID, new ApiBusiness.IOnCheckContract() {
            @Override
            public void onCheckCardResult(CardInstanceEntity result, Date checkTime) {
                Intent intent = new Intent(MainActivity.this, InfoActivity.class);
                intent.putExtra("card", result);
                intent.putExtra("checkTime", checkTime.getTime());
                startActivity(intent);
            }
        });

        Toast toast = Toast.makeText(context, "Đang đọc thông tin thẻ...", duration);
        toast.show();
    }
}
