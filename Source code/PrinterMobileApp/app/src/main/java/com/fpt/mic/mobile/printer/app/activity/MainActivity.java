package com.fpt.mic.mobile.printer.app.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.*;
import com.fpt.mic.mobile.printer.app.R;
import com.fpt.mic.mobile.printer.app.business.ContractBusiness;
import com.fpt.mic.mobile.printer.app.dto.ContractSearchResult;
import com.fpt.mic.mobile.printer.app.utils.ApiRequest;
import com.fpt.mic.mobile.printer.app.utils.Constants;

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
    EditText txtKeyword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find views
        lstContracts = (ListView) findViewById(R.id.lstContract);
        prgLoading = findViewById(R.id.prgLoading);
        emptyView = (TextView) findViewById(R.id.emptyView);
        txtKeyword = (EditText) findViewById(R.id.txtKeyword);

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
                searchContracts();
            }
        });
    }

    private void showLoadingEffect() {
        emptyView.setVisibility(View.GONE);
        prgLoading.setVisibility(View.VISIBLE);
        lstContracts.setVisibility(View.GONE);
    }

    private void searchContracts() {
        String keyword = txtKeyword.getText().toString();

        ContractBusiness contractBusiness = new ContractBusiness();

        contractBusiness.searchContracts(keyword, new ContractBusiness.IOnSearchResult() {
            @Override
            public void onSearchResult(List<ContractSearchResult> results) {
                values = new ArrayList<String>();
                for (ContractSearchResult result : results) {
                    values.add(result.contractEntity.contractCode +
                            " - " + result.customerEntity.name);
                }

                updateListView();
            }
        });
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
            if (txtKeyword.getText().toString().isEmpty()) {
                emptyView.setText(getString(R.string.enter_keyword));
            } else {
                emptyView.setText("Không tìm thấy kết quả nào!");
            }
        }
    }
}
