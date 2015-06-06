package com.fpt.mic.mobile.printer.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * FPT University - Capstone Project - Summer 2015 - PrinterMobileApp
 * Created by dinhquangtrung on 6/5/15.
 */
public class MainActivity extends Activity {
    private ListView lstContracts;
    ArrayAdapter<String> adapter;
    List<String> values = new ArrayList<String>();
    TextView emptyView;
    View prgLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find views
        lstContracts = (ListView) findViewById(R.id.lstContract);
        prgLoading = findViewById(R.id.prgLoading);
        emptyView = (TextView) findViewById(R.id.emptyView);

        // Setup list view
        adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, values);
        lstContracts.setAdapter(adapter);
        lstContracts.setEmptyView(emptyView);
        lstContracts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // Toast.makeText(MainActivity.this, values.get(i), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, InfoActivity.class);
                startActivity(intent);
            }
        });

        // Search button handler
        findViewById(R.id.btnSearch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLoadingEffect();
                Handler handlerTimer = new Handler();
                handlerTimer.postDelayed(new Runnable() {
                    public void run() {
                        searchContracts();
                        updateListView();
                    }
                }, 2000);
            }
        });
    }

    private void showLoadingEffect() {
        emptyView.setVisibility(View.GONE);
        prgLoading.setVisibility(View.VISIBLE);
        lstContracts.setVisibility(View.GONE);
    }

    private void searchContracts() {
        values = Arrays.asList("Android", "iPhone", "WindowsMobile",
                "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
                "Linux", "OS/2");
    }

    private void updateListView() {
        // Load items to list view
        adapter = new ArrayAdapter<String>(
                MainActivity.this, android.R.layout.simple_list_item_1, values);
        lstContracts.setAdapter(adapter);

        // Handle visible
        lstContracts.setVisibility(View.VISIBLE);
        prgLoading.setVisibility(View.GONE);

        // Tricky handle no result
        if (values.size() == 0) {
            emptyView.setText("Không tìm thấy kết quả nào!");
        }
    }
}
