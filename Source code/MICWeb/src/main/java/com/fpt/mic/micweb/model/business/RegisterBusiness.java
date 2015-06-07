package com.fpt.mic.micweb.model.business;

import com.fpt.mic.micweb.model.dao.ContractDao;
import com.fpt.mic.micweb.model.dao.CustomerDao;
import com.fpt.mic.micweb.model.dao.PaymentDao;
import com.fpt.mic.micweb.model.entity.ContractEntity;
import com.fpt.mic.micweb.model.entity.CustomerEntity;
import com.fpt.mic.micweb.model.entity.PaymentEntity;

import java.util.Random;

/**
 * Created by TriPQMSE60746 on 06/04/2015.
 */
public class RegisterBusiness {
    public boolean registerNewContract(CustomerEntity customerEntity,
                                       ContractEntity contractEntity,
                                       PaymentEntity paymentEntity) {

        ContractDao contractDao = new ContractDao();
        CustomerDao customerDao = new CustomerDao();
        PaymentDao paymentDao = new PaymentDao();
        // Validate information

        // Add customer
        // get next customer code - add later
        String customerCode ="" + (new Random().nextInt(8999) + 1000);
        customerEntity.setCustomerCode(customerCode);
        // get customer password - add later
        String customerPassword = "12345678";
        customerEntity.setPassword(customerPassword);
        // get next Contract Code- add later
        contractEntity.setContractCode("" + new Random().nextInt(8999) + 1000);
        contractEntity.setCustomerCode(customerCode);

        if (customerDao.create(customerEntity) != null) {
            // Add contract
            if (contractDao.create(contractEntity) != null) {
                // Add payment info
                // check if user choose direct payment, or payment process failed
                if (paymentEntity!= null) {
                    paymentDao.create(paymentEntity);
                }
            }
        }




        return true;
    }
}
