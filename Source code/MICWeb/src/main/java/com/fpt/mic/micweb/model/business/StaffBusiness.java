package com.fpt.mic.micweb.model.business;

import com.fpt.mic.micweb.model.dao.*;
import com.fpt.mic.micweb.model.dto.CreateCustomerInfoDto;
import com.fpt.mic.micweb.model.dto.form.*;
import com.fpt.mic.micweb.model.entity.*;
import com.fpt.mic.micweb.utils.Constants;
import com.fpt.mic.micweb.utils.EmailUtils;
import com.fpt.mic.micweb.utils.StringUtils;

import javax.servlet.ServletContext;
import java.io.InputStream;
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

    public CreateCustomerInfoDto createCustomer(CreateCustomerDto dto, ServletContext context, String loginUrl) {
        CustomerDao customerDao = new CustomerDao();
        CustomerEntity customerEntity = new CustomerEntity();
        // get next customer code
        String customerCode = customerDao.getIncrementId();
        customerEntity.setCustomerCode(customerCode);
        // Set customer information to entity
        customerEntity.setName(dto.getName());
        customerEntity.setAddress(dto.getAddress());
        customerEntity.setEmail(dto.getEmail());
        customerEntity.setPhone(dto.getPhone());
        customerEntity.setPersonalId(dto.getPersonalID());
        // get customer password
        String customerPassword = StringUtils.randomString();
        // TODO: encrypt password
        customerEntity.setPassword(customerPassword);

        CustomerEntity newCustomer = customerDao.create(customerEntity);
        if (newCustomer != null) {
            // Send password email
            InputStream resourceAsStream =
                    context.getResourceAsStream("WEB-INF/templates/password-email.html");
            String content = StringUtils.getString(resourceAsStream);
            boolean emailSuccess = false;
            if (content != null) {
                content = content.replaceAll("\\{\\{password\\}\\}", customerPassword)
                        .replaceAll("\\{\\{loginUrl\\}\\}", loginUrl);
                emailSuccess = EmailUtils.sendMail(newCustomer.getEmail(), content);
            }
            return new CreateCustomerInfoDto(newCustomer, emailSuccess);
        }
        return null;
    }

    public List<ContractEntity> getAllContract() {
        ContractDao contractDao = new ContractDao();
        List<ContractEntity> listContract = contractDao.getAllContract();
        return listContract;
    }


    public List getAllContract(int offset, int count) {
        ContractDao contractDao = new ContractDao();
        return contractDao.getAllContract(offset, count);
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
        // Code of the staff created this contract
        contractEntity.setStaffCode(receiver.getStaffCode());
        // Create new contract
        ContractEntity newContract = contractDao.create(contractEntity);
        // Check contract to add payment
        if (newContract != null) {
            // Add payment info
            paymentEntity.setPaidDate(dto.getPaidDate());
            paymentEntity.setPaymentMethod("Trực tiếp");
            paymentEntity.setContent("Đăng ký hợp đồng mới " + newContract.getContractCode());
            paymentEntity.setAmount(dto.getAmount());
            paymentEntity.setReceiver(receiver.getStaffCode());
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
        CardDao cardDao = new CardDao();
        ContractEntity contractEntity = contractDao.read(dto.getContractCode());
        PaymentEntity paymentEntity = new PaymentEntity();
        CardEntity cardEntity = cardDao.getCardByContract(dto.getContractCode());

        // Check contract
        if (contractEntity != null) {
            // Update contract information
            contractEntity.setExpiredDate(dto.getExpiredDate());
            contractEntity.setContractFee(dto.getContractFee());
            if (cardEntity == null) {
                contractEntity.setStatus(Constants.ContractStatus.NO_CARD);
            } else {
                contractEntity.setStatus(Constants.ContractStatus.READY);
            }
            if (contractDao.update(contractEntity) != null) {
                // Add payment information
                paymentEntity.setPaidDate(dto.getPaidDate());
                paymentEntity.setAmount(dto.getAmount());
                paymentEntity.setPaymentMethod("Trực tiếp");
                paymentEntity.setContent("Gia hạn hợp đồng");
                paymentEntity.setReceiver(receiver.getStaffCode());
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

    public boolean completePayment(CompletePaymentDto dto, StaffEntity receiver) {
        ContractDao contractDao = new ContractDao();
        PaymentDao paymentDao = new PaymentDao();
        CardDao cardDao = new CardDao();
        ContractEntity contractEntity = contractDao.read(dto.getContractCode());
        PaymentEntity paymentEntity = new PaymentEntity();
        CardEntity cardEntity = cardDao.getCardByContract(dto.getContractCode());

        // Check contract
        if (contractEntity != null) {
            // Update contract status
            if (cardEntity == null) {
                contractEntity.setStatus(Constants.ContractStatus.NO_CARD);
            } else {
                contractEntity.setStatus(Constants.ContractStatus.READY);
            }
            if (contractDao.update(contractEntity) != null) {
                // Set payment information from dto to entity
                paymentEntity.setPaidDate(dto.getPaidDate());
                paymentEntity.setAmount(dto.getAmount());
                paymentEntity.setContractCode(dto.getContractCode());
                // Set others payment information
                paymentEntity.setPaymentMethod("Trực tiếp");
                paymentEntity.setContent("Đăng ký hợp đồng mới " + contractEntity.getContractCode());
                paymentEntity.setReceiver(receiver.getStaffCode());

                if (paymentDao.create(paymentEntity) != null) {
                    return true;
                }
            }
        }
        return false;
    }

    public List searchCustomerByNameOrCode(String keyword) {
        CustomerDao customerDao = new CustomerDao();
        return customerDao.searchCustomerByNameOrCode(keyword);
    }
}
