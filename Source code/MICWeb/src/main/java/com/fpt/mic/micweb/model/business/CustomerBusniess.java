package com.fpt.mic.micweb.model.business;

import com.fpt.mic.micweb.model.dao.CustomerDao;
import com.fpt.mic.micweb.model.dao.ContractDao;
import com.fpt.mic.micweb.model.entity.CustomerEntity;
import com.fpt.mic.micweb.model.entity.ContractEntity;
import sun.util.calendar.LocalGregorianCalendar;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Created by PhucNguyen on 05/06/2015.
 */
public class CustomerBusniess {
    //get all contract belong to customer
    public List<ContractEntity> getAllContract() {
        ContractDao contractDa0 = new ContractDao();
        List<ContractEntity> listContract = contractDa0.getListContract();
        return listContract;
    }

    // get contract detail
    public ContractEntity getContractDetail(String code) {
        ContractDao contractDa0 = new ContractDao();
        return contractDa0.read(code);
    }

    //cancel contract
    public boolean CancelContract(String code, String cancelReason) {
        ContractDao contractDa0 = new ContractDao();
        ContractEntity contract = contractDa0.read(code);
        boolean result = false;
        //get date now
        Calendar currentDate = Calendar.getInstance(); //Get the current date
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MMM/dd HH:mm:ss"); //format it as per your requirement
        String dateNow = formatter.format(currentDate.getTime());

        if (contract != null) {
            //contract.setCancelDate(dateNow.toString());
            contract.setCancelReason(cancelReason);
            if (contractDa0.update(contract) != null) {
                result = true;
            }
        }
        return result;
    }
}
