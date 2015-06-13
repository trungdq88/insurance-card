package com.fpt.mic.micweb.model.business;

import com.fpt.mic.micweb.model.dao.ContractDao;
import com.fpt.mic.micweb.model.dao.ContractTypeDao;
import com.fpt.mic.micweb.model.dao.CustomerDao;
import com.fpt.mic.micweb.model.dao.PaymentDao;
import com.fpt.mic.micweb.model.entity.*;
import com.fpt.mic.micweb.utils.Constants;
import com.fpt.mic.micweb.model.entity.ContractEntity;
import com.fpt.mic.micweb.model.entity.ContractTypeEntity;
import com.fpt.mic.micweb.model.entity.CustomerEntity;
import com.fpt.mic.micweb.model.entity.PaymentEntity;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Kha on 07/06/2015.
 */
public class StaffBusiness {

    public List<CustomerEntity> getAllCustomer() {
        CustomerDao customerDao = new CustomerDao();
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
        // get next customer code
        String customerCode = customerDao.getIncrementId();
        customerEntity.setCustomerCode(customerCode);
        // get customer password
        String customerPassword = "123456";
        customerEntity.setPassword(customerPassword);

        return customerDao.create(customerEntity) != null;
    }

    public List<ContractEntity> getAllContract() {
        ContractDao contractDao = new ContractDao();
        List<ContractEntity> listContract = contractDao.getAllContract();
        return listContract;
    }

    public List<ContractEntity> getContractByCustomerCode(String customerCode) {
        ContractDao contractDao = new ContractDao();
        List<ContractEntity> listCustomerContract = contractDao.getContractByCustomerCode(customerCode);
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
        // get next contract code
        String contractCode = contractDao.getIncrementId();
        contractEntity.setContractCode(contractCode);
        contractEntity.setStatus(Constants.ContractStatus.NO_CARD);
        ContractEntity newContract = contractDao.create(contractEntity);
        // Add contract
        if (newContract != null) {
            // Add payment info
            paymentEntity.setPaymentMethod("Direct");
            paymentEntity.setContent("Đăng ký hợp đồng mới");
            paymentEntity.setReceiver("KhaNC");
            paymentEntity.setContractCode(contractCode);
            if (paymentDao.create(paymentEntity) != null) {
                return true;
            }
        }
        return false;
    }

    public boolean renewContract(String contractCode, Timestamp startDate, Timestamp expiredDate,
                                 PaymentEntity paymentEntity) {
        ContractDao contractDao = new ContractDao();
        PaymentDao paymentDao = new PaymentDao();
        ContractEntity contractEntity = contractDao.read(contractCode);

        // Validate information

        // Check contract
        if (contractEntity != null) {
            // Update contract information
            contractEntity.setStartDate(startDate);
            contractEntity.setExpiredDate(expiredDate);
            contractEntity.setStatus(Constants.ContractStatus.READY);
            if (contractDao.update(contractEntity) != null) {
                // Add payment information
                paymentEntity.setPaymentMethod("Direct");
                paymentEntity.setContent("Gia hạn hợp đồng");
                paymentEntity.setReceiver("KhaNC");
                paymentEntity.setContractCode(contractCode);
                if (paymentDao.create(paymentEntity) != null) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean cancelContract(String contractCode, Timestamp cancelDate, String cancelReason, String cancelNote) {
        ContractDao contractDao = new ContractDao();
        ContractEntity contractEntity = contractDao.read(contractCode);
        // Validate information

        // Check contract
        if (contractEntity != null) {
            // Update contract information
            contractEntity.setCancelDate(cancelDate);
            contractEntity.setCancelReason(cancelReason);
            contractEntity.setCancelNote(cancelNote);
            contractEntity.setStatus(Constants.ContractStatus.CANCELLED);
            if (contractDao.update(contractEntity) != null) {
                return true;
            }
        }
        return false;
    }

    public List<ContractTypeEntity> getAllContractType() {
        ContractTypeDao contractTypeDao = new ContractTypeDao();
        List<ContractTypeEntity> listContractType = contractTypeDao.getAllContractType();
        return listContractType;
    }

    public List<PaymentEntity> getPaymentByContractCode(String contractCode) {
        PaymentDao paymentDao = new PaymentDao();
        List<PaymentEntity> listPayment = paymentDao.getPaymentByCustomerCode(contractCode);
        return listPayment;
    }

    public PaymentEntity getPaymentDetail(Integer id) {
        PaymentDao paymentDao = new PaymentDao();
        return paymentDao.read(id);
    }
}
