package com.fpt.mic.micweb.model.business;

import com.fpt.mic.micweb.model.dao.ContractDao;
import com.fpt.mic.micweb.model.dao.ContractTypeDao;
import com.fpt.mic.micweb.model.dao.CustomerDao;
import com.fpt.mic.micweb.model.dao.PaymentDao;
import com.fpt.mic.micweb.model.dto.form.CancelContractDto;
import com.fpt.mic.micweb.model.dto.form.CreateContractDto;
import com.fpt.mic.micweb.model.dto.form.CreateCustomerDto;
import com.fpt.mic.micweb.model.dto.form.RenewContractDto;
import com.fpt.mic.micweb.model.entity.*;
import com.fpt.mic.micweb.utils.Constants;

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

    public CustomerEntity createCustomer(CreateCustomerDto dto) {
        CustomerDao customerDao = new CustomerDao();
        CustomerEntity customerEntity = new CustomerEntity();

        // Set customer information to entity
        customerEntity.setName(dto.getName());
        customerEntity.setAddress(dto.getAddress());
        customerEntity.setEmail(dto.getEmail());
        customerEntity.setPhone(dto.getPhone());
        customerEntity.setPersonalId(dto.getPersonalID());

        // get next customer code
        String customerCode = customerDao.getIncrementId();
        customerEntity.setCustomerCode(customerCode);
        // get customer password
        String customerPassword = "123456";
        customerEntity.setPassword(customerPassword);

        CustomerEntity newCustomer = customerDao.create(customerEntity);
        if (newCustomer != null) {
            return newCustomer;
        } else {
            return null;
        }
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

    public ContractEntity createContract(CreateContractDto dto, StaffEntity receiver) {
        ContractEntity contractEntity = new ContractEntity();
        PaymentEntity paymentEntity = new PaymentEntity();
        ContractDao contractDao = new ContractDao();
        PaymentDao paymentDao = new PaymentDao();

        // Get next contract code
        String contractCode = contractDao.getIncrementId();
        contractEntity.setContractCode(contractCode);
        // Set contract entity from DTO
        contractEntity.setCustomerCode(dto.getCustomerCode());
        contractEntity.setContractTypeId(dto.getContractTypeId());
        contractEntity.setStartDate(dto.getStartDate());
        contractEntity.setExpiredDate(dto.getExpiredDate());
        // Set contract status by pre-defined constants
        contractEntity.setStatus(Constants.ContractStatus.NO_CARD);
        // Set contract entity from DTO
        contractEntity.setContractFee(dto.getContractFee());
        // Vehicle information
        contractEntity.setPlate(dto.getPlate());
        contractEntity.setBrand(dto.getBrand());
        contractEntity.setModelCode(dto.getModelCode());
        contractEntity.setVehicleType(dto.getVehicleType());
        contractEntity.setColor(dto.getColor());
        contractEntity.setEngine(dto.getEngine());
        contractEntity.setChassis(dto.getChassis());
        contractEntity.setCapacity(dto.getCapacity());
        contractEntity.setYearOfManufacture(dto.getYearOfManufacture());
        contractEntity.setWeight(dto.getWeight());
        contractEntity.setSeatCapacity(dto.getSeatCapacity());
        // Create new contract
        ContractEntity newContract = contractDao.create(contractEntity);
        // Check contract to add payment
        if (newContract != null) {
            // Add payment info
            paymentEntity.setPaidDate(dto.getPaidDate());
            paymentEntity.setPaymentMethod("Direct");
            paymentEntity.setContent("Đăng ký hợp đồng mới");
            paymentEntity.setAmount(dto.getAmount());
            paymentEntity.setReceiver(receiver.getName() + " (" + receiver.getStaffCode() + ")");
            paymentEntity.setContractCode(contractCode);
            if (paymentDao.create(paymentEntity) != null) {
                return newContract;
            }
        }
        return null;
    }

    public boolean renewContract(RenewContractDto dto, StaffEntity receiver) {
        ContractDao contractDao = new ContractDao();
        PaymentDao paymentDao = new PaymentDao();
        ContractEntity contractEntity = contractDao.read(dto.getContractCode());
        PaymentEntity paymentEntity = new PaymentEntity();

        // Validate information

        // Check contract
        if (contractEntity != null) {
            // Update contract information
            contractEntity.setExpiredDate(dto.getExpiredDate());
            contractEntity.setContractFee(dto.getContractFee());
            contractEntity.setStatus(Constants.ContractStatus.READY);
            if (contractDao.update(contractEntity) != null) {
                // Add payment information
                paymentEntity.setPaidDate(dto.getPaidDate());
                paymentEntity.setAmount(dto.getAmount());
                paymentEntity.setPaymentMethod("Direct");
                paymentEntity.setContent("Gia hạn hợp đồng");
                paymentEntity.setReceiver(receiver.getName() + " (" + receiver.getStaffCode() + ")");
                paymentEntity.setContractCode(dto.getContractCode());
                if (paymentDao.create(paymentEntity) != null) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean cancelContract(CancelContractDto dto) {
        ContractDao contractDao = new ContractDao();
        ContractEntity contractEntity = contractDao.read(dto.getContractCode());
        // Validate information

        // Check contract
        if (contractEntity != null) {
            // Update contract information
            contractEntity.setCancelDate(dto.getCancelDate());
            contractEntity.setCancelReason(dto.getCancelReason());
            contractEntity.setCancelNote(dto.getCancelNote());
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
