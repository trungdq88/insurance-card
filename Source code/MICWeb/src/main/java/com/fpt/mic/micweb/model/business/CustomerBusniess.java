package com.fpt.mic.micweb.model.business;

import com.fpt.mic.micweb.model.dao.CustomerDao;
import com.fpt.mic.micweb.model.dao.ContractDao;
import com.fpt.mic.micweb.model.entity.CustomerEntity;
import com.fpt.mic.micweb.model.entity.ContractEntity;
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

}
