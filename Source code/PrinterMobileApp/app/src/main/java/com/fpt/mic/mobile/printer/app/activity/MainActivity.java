package com.fpt.mic.mobile.printer.app.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.*;
import com.fpt.mic.mobile.printer.app.R;
import com.fpt.mic.mobile.printer.app.adapter.ResultAdapter;
import com.fpt.mic.mobile.printer.app.business.ContractBusiness;
import com.fpt.mic.mobile.printer.app.dto.ContractSearchResult;

import java.util.ArrayList;
import java.util.List;

/**
 * FPT University - Capstone Project - Summer 2015 - PrinterMobileApp
 * Created by dinhquangtrung on 6/5/15.
 */
public class MainActivity extends Activity {
    private ListView lstContracts;
    ResultAdapter adapter;
    // List<String> values = new ArrayList<String>();
    TextView emptyView;
    View prgLoading;
    EditText txtKeyword;
    private ArrayList<ContractSearchResult> searchContractResults;

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
//        adapter = new ArrayAdapter<String>(
//                this, android.R.layout.simple_list_item_1, values);
        lstContracts.setAdapter(adapter);
        lstContracts.setEmptyView(emptyView);
        lstContracts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // Toast.makeText(MainActivity.this, values.get(i), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, InfoActivity.class);
                intent.putExtra("contract", searchContractResults.get(i));
                startActivity(intent);
            }
        });

        // Search button handler
        findViewById(R.id.btnSearch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchContracts();
            }
        });

        txtKeyword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    searchContracts();
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Show 'No card' contracts first
        searchContracts();
    }

    private void showLoadingEffect() {
        emptyView.setVisibility(View.GONE);
        prgLoading.setVisibility(View.VISIBLE);
        lstContracts.setVisibility(View.GONE);
    }

    private void searchContracts() {
        showLoadingEffect();

        String keyword = txtKeyword.getText().toString();

        ContractBusiness contractBusiness = new ContractBusiness();

        contractBusiness.searchContracts(keyword, new ContractBusiness.IOnSearchResult() {
            @Override
            public void onSearchResult(ArrayList<ContractSearchResult> results) {
                // values = new ArrayList<String>();
                searchContractResults = results;
                updateListView();
            }
        });
    }

    private void updateListView() {
        // Load items to list view
//        adapter = new ArrayAdapter<String>(
//                MainActivity.this, android.R.layout.simple_list_item_1, values);
        adapter = new ResultAdapter(this, searchContractResults);
        lstContracts.setAdapter(adapter);

        // Handle visible
        lstContracts.setVisibility(View.VISIBLE);
        prgLoading.setVisibility(View.GONE);

        // Tricky handle no result
        if (searchContractResults.size() == 0) {
            if (txtKeyword.getText().toString().isEmpty()) {
                emptyView.setText(getString(R.string.enter_keyword));
            } else {
                emptyView.setText("Không tìm thấy kết quả nào!");
            }
        }
    }
}
