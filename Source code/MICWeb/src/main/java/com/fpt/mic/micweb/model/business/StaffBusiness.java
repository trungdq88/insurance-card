package com.fpt.mic.micweb.model.business;

import com.fpt.mic.micweb.model.dao.ContractDao;
import com.fpt.mic.micweb.model.dao.CustomerDao;
import com.fpt.mic.micweb.model.dao.PaymentDao;
import com.fpt.mic.micweb.model.dto.CustomerDTO;
import com.fpt.mic.micweb.model.entity.ContractEntity;
import com.fpt.mic.micweb.model.entity.CustomerEntity;
import com.fpt.mic.micweb.model.entity.PaymentEntity;

import java.util.List;
import java.util.Random;

/**
 * Created by Kha on 07/06/2015.
 */
public class StaffBusiness {

    public List<CustomerEntity> getAllCustomer() {
        CustomerDao customerDao = new CustomerDao();
        //List<CustomerDTO> listCustomer = customerDao.getAllCustomer();
        List<CustomerEntity> listCustomer = customerDao.getAllCustomer();
        return listCustomer;
    }

    public CustomerEntity getCustomerDetail(String customerCode) {
        CustomerDao customerDao = new CustomerDao();
        return customerDao.read(customerCode);
    }

    public boolean createCustomer(CustomerEntity customerEntity) {
        CustomerDao customerDao = new CustomerDao();

        // Validate data

        // Create customer
        // get next customer code - add later
        String customerCode = "KH" + (new Random().nextInt(8999) + 1000);
        customerEntity.setCustomerCode(customerCode);
        // get customer password - add later
        String customerPassword = "123456";
        customerEntity.setPassword(customerPassword);

        return customerDao.create(customerEntity) != null;
    }

    public List<ContractEntity> getAllContract() {
        ContractDao contractDao = new ContractDao();
        List<ContractEntity> listContract = contractDao.getAllContract();
        return listContract;
    }

    public List<ContractEntity> getContractByCustomerCode(String custCode) {
        ContractDao contractDao = new ContractDao();
        List<ContractEntity> listCustomerContract = contractDao.getContractByCustomerCode(custCode);
        return listCustomerContract;
    }

    public ContractEntity getContractDetail(String contractCode) {
        ContractDao contractDao = new ContractDao();
        return contractDao.read(contractCode);
    }

    public boolean createContract(ContractEntity contractEntity, PaymentEntity paymentEntity) {
        ContractDao contractDao = new ContractDao();
        PaymentDao paymentDao = new PaymentDao();
        // Validate information

        // Add contract
        // get next contract code - add later
        String contractCode = "HD" + (new Random().nextInt(8999) + 1000);
        contractEntity.setContractCode(contractCode);
        System.out.println(contractEntity.getContractCode());
        contractEntity.setStatus("No Card");

        // Add payment
        paymentEntity.setPaymentMethod("Direct");
        paymentEntity.setContent("Đăng ký hợp đồng mới");
        paymentEntity.setContractCode(contractCode);

        // Add contract
        if (contractDao.create(contractEntity) != null) {
            // Add payment info
            // check if user choose direct payment, or payment process failed
            if (paymentDao.create(paymentEntity) != null) {
                return true;
            }
        }
        return false;
    }
}
