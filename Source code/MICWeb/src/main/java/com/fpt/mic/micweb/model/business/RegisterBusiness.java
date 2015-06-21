package com.fpt.mic.micweb.model.business;

import com.fpt.mic.micweb.model.dao.ContractDao;
import com.fpt.mic.micweb.model.dao.CustomerDao;
import com.fpt.mic.micweb.model.dao.PaymentDao;
import com.fpt.mic.micweb.model.dto.RegisterInformationDto;
import com.fpt.mic.micweb.model.entity.ContractEntity;
import com.fpt.mic.micweb.model.entity.CustomerEntity;
import com.fpt.mic.micweb.model.entity.PaymentEntity;

/**
 * Created by TriPQMSE60746 on 06/04/2015.
 */
public class RegisterBusiness {
    public RegisterInformationDto registerNewContract(CustomerEntity customerEntity,
                                       ContractEntity contractEntity) {

        ContractDao contractDao = new ContractDao();
        CustomerDao customerDao = new CustomerDao();
        // Validate information

        // Add customer
        // get next customer code - add later
        String customerCode = customerDao.getIncrementId();
        customerEntity.setCustomerCode(customerCode);
        // get customer password - add later
        String customerPassword = "12345678";
        customerEntity.setPassword(customerPassword);
        // get next Contract Code- add later
        contractEntity.setContractCode(contractDao.getIncrementId());
        contractEntity.setCustomerCode(customerCode);

        CustomerEntity customer = customerDao.create(customerEntity);
        if (customer != null) {
            ContractEntity contract = contractDao.create(contractEntity);
            if ( contract != null) {
                return new RegisterInformationDto(contract, customer);
            }

        }
        return null;
    }

    public boolean updateContractPayment(ContractEntity contractEntity, PaymentEntity paymentEntity) {
        ContractDao contractDao = new ContractDao();
        PaymentDao paymentDao = new PaymentDao();
        if (contractDao.update(contractEntity) != null) {
            if (paymentDao.create(paymentEntity) != null){
                return true;
            }
        }
        return false;
    }
}
