package com.fpt.mic.mobile.printer.app.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.fpt.mic.mobile.printer.app.R;
import com.fpt.mic.mobile.printer.app.dto.ContractSearchResult;
import com.fpt.mic.mobile.printer.app.entity.ContractEntity;
import com.fpt.mic.mobile.printer.app.utils.Constants;

import java.util.ArrayList;

/**
 * FPT University - Capstone Project - Summer 2015 - Printer Mobile App
 * Created by dinhquangtrung on 8/7/15.
 */
public class ResultAdapter extends ArrayAdapter<ContractSearchResult> {
    public ResultAdapter(Context context, ArrayList<ContractSearchResult> results) {
        super(context, 0, results);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        ContractSearchResult result = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_contract, parent, false);
            viewHolder.viwStatus = convertView.findViewById(R.id.viwStatus);
            viewHolder.txtName = (TextView) convertView.findViewById(R.id.txtName);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // Populate the data into the template view using the data object
//        viewHolder.viwStatus.setText(user.name);
//        viewHolder.txtName.setText(user.hometown);
        viewHolder.viwStatus.setBackgroundColor(getStatusColor(result.contractEntity));
        viewHolder.txtName.setText(result.contractEntity.contractCode +
                " - " + result.customerEntity.name);
        // Return the completed view to render on screen
        return convertView;
    }
    private int getStatusColor(ContractEntity contract) {
        String status = contract.status;
        if (status.equals(Constants.ContractStatus.PENDING)) {
            return Color.parseColor(Constants.StatusColor.PENDING);
        } else if (status.equals(Constants.ContractStatus.NO_CARD)) {
            return Color.parseColor(Constants.StatusColor.NO_CARD);
        } else if (status.equals(Constants.ContractStatus.READY)) {
            return Color.parseColor(Constants.StatusColor.READY);
        } else if (status.equals(Constants.ContractStatus.REQUEST_CANCEL)) {
            return Color.parseColor(Constants.StatusColor.REQUEST_CANCEL);
        } else if (status.equals(Constants.ContractStatus.EXPIRED)) {
            return Color.parseColor(Constants.StatusColor.EXPIRED);
        } else if (status.equals(Constants.ContractStatus.CANCELLED)) {
            return Color.parseColor(Constants.StatusColor.CANCELLED);
        }
        return 0;
    }
    // View lookup cache
    private static class ViewHolder {
        View viwStatus;
        TextView txtName;
    }
}
