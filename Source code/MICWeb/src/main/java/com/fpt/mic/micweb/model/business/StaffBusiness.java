package com.fpt.mic.micweb.model.business;

import com.fpt.mic.micweb.model.dao.*;
import com.fpt.mic.micweb.model.dto.CreateCustomerInfoDto;
import com.fpt.mic.micweb.model.dto.form.*;
import com.fpt.mic.micweb.model.entity.*;
import com.fpt.mic.micweb.utils.*;
import org.joda.time.LocalDate;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Kha on 07/06/2015.
 */
public class StaffBusiness {

    public List getAllCustomer(int offset, int count) {
        CustomerDao customerDao = new CustomerDao();
        return customerDao.getAllCustomer(offset, count);
    }

    public CustomerEntity getCustomerDetail(String customerCode) {
        CustomerDao customerDao = new CustomerDao();
        return customerDao.read(customerCode);
    }

    public CreateCustomerInfoDto createCustomer(CreateCustomerDto dto, String loginUrl) {
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
        // Set lastModified is current date
        customerEntity.setLastModified(DateUtils.currentTimeWithoutNanos());
        // get customer password
        String customerPassword = StringUtils.randomString();
        String encryptedPassword = StringUtils.getMD5Hash(customerPassword);

        customerEntity.setPassword(encryptedPassword);

        CustomerEntity newCustomer = customerDao.create(customerEntity);
        if (newCustomer != null) {
            // Send password email
            String content = EmailTemplate.PASSWORD_EMAIL;
            boolean emailSuccess = false;
            if (content != null) {
                content = content
                        .replaceAll("\\{\\{customerCode\\}\\}", newCustomer.getCustomerCode())
                        .replaceAll("\\{\\{password\\}\\}", customerPassword)
                        .replaceAll("\\{\\{loginUrl\\}\\}", loginUrl);
                emailSuccess = EmailUtils.sendMail(newCustomer.getEmail(), EmailUtils.SUBJECT_NEW_CONTRACT, content);
            }
            return new CreateCustomerInfoDto(newCustomer, emailSuccess);
        }
        return null;
    }

    public Long getAllContractCount() {
        ContractDao contractDao = new ContractDao();
        return contractDao.getAllContractCount();
    }

    public List getAllContract(int offset, int count) {
        ContractDao contractDao = new ContractDao();
        return contractDao.getAllContract(offset, count);
    }

    public StaffEntity getStaffDetail(String staffCode) {
        StaffDao staffDao = new StaffDao();
        return staffDao.read(staffCode);
    }

    public Long getAllStaffCount() {
        StaffDao staffDao = new StaffDao();
        return staffDao.getAllStaffCount();
    }

    public Long getAllNewCardRequestCount() {
        StaffDao staffDao = new StaffDao();
        return staffDao.getAllNewCardRequestCount();
    }
    public Long searchAllNewCardRequestCount(String finalKeyword) {
        StaffDao staffDao = new StaffDao();
        return staffDao.searchAllNewCardRequestCount(finalKeyword);
    }

    public Long getUnresolvedNewCardRequestCount() {
        StaffDao staffDao = new StaffDao();
        return staffDao.getUnresolvedNewCardRequestCount();
    }

    public List getOnePageStaff(int offset, int count) {
        StaffDao staffDao = new StaffDao();
        return staffDao.getOnePageStaff(offset, count);
    }

    public List getOnePageNewCardRequest(int offset, int count) {
        StaffDao staffDao = new StaffDao();
        return staffDao.getOnePageNewCardRequest(offset, count);
    }
    public List searchOnePageNewCardRequest(String finalKeyword, int offset, int count) {
        StaffDao staffDao = new StaffDao();
        return staffDao.searchOnePageNewCardRequest(finalKeyword, offset, count);
    }

    public List getContractByCustomerCode(String customerCode, int offset, int count) {
        ContractDao contractDao = new ContractDao();
        return contractDao.getContractByCustomerCode(customerCode, offset, count);
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
        Timestamp currentDate = DateUtils.currentDateWithoutTime();
        if (contractEntity.getStartDate().after(currentDate)) {
            contractEntity.setStatus(Constants.ContractStatus.PENDING);
        } else {
            contractEntity.setStatus(Constants.ContractStatus.NO_CARD);
        }
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
        // Set lastModified is current date
        contractEntity.setLastModified(DateUtils.currentTimeWithoutNanos());
        contractEntity.setModifyReason(Constants.ContractModify.STAFF_CREATE_CONTRACT);
        // Create new contract
        ContractEntity newContract = contractDao.create(contractEntity);
        // Check contract to add payment
        if (newContract != null) {
            // Add payment info
            if (currentDate.equals(dto.getPaidDate())) {
                paymentEntity.setPaidDate(DateUtils.currentTimeWithoutNanos());
            } else {
                paymentEntity.setPaidDate(dto.getPaidDate());
            }
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
        CardInstanceDao cardInstanceDao = new CardInstanceDao();
        ContractEntity contractEntity = contractDao.read(dto.getContractCode());
        PaymentEntity paymentEntity = new PaymentEntity();
        CardInstanceEntity cardEntity = cardInstanceDao.getActiveCardInstanceByContract(dto.getContractCode());
        Timestamp startDate = dto.getStartDate();

        // Check contract
        if (contractEntity != null) {
            // Update contract information
            contractEntity.setExpiredDate(dto.getExpiredDate());
            contractEntity.setContractFee(dto.getContractFee());
            // Set check renew contract request to false (null)
            contractEntity.setNeedRenewPayment(null);
            // Concurrency set value
            contractEntity.setLastModified(DateUtils.currentTimeWithoutNanos());
            contractEntity.setModifyReason(Constants.ContractModify.STAFF_RENEW_CONTRACT);

            if (cardEntity == null || dto.isNewCard()) {
                contractEntity.setStatus(Constants.ContractStatus.NO_CARD);
            } else {
                contractEntity.setStatus(Constants.ContractStatus.READY);
            }
            if (contractDao.update(contractEntity) != null) {
                // Add payment information
                Timestamp currentDate = DateUtils.currentDateWithoutTime();
                if (currentDate.equals(dto.getPaidDate())) {
                    paymentEntity.setPaidDate(DateUtils.currentTimeWithoutNanos());
                } else {
                    paymentEntity.setPaidDate(dto.getPaidDate());
                }
                paymentEntity.setAmount(dto.getAmount());
                paymentEntity.setStartDate(startDate);
                paymentEntity.setExpiredDate(dto.getExpiredDate());
                paymentEntity.setReceiver(receiver.getStaffCode());
                paymentEntity.setContractCode(dto.getContractCode());
                // Set payment information when renew
                paymentEntity.setPaymentMethod("Trực tiếp");
                if (dto.isNewCard()) {
                    paymentEntity.setContent("Gia hạn hợp đồng, đổi thẻ mới");
                } else if (dto.isDeliveryNewCard()) {
                    paymentEntity.setContent("Gia hạn hợp đồng, đổi thẻ mới, vận chuyển thẻ");
                } else {
                    paymentEntity.setContent("Gia hạn hợp đồng");
                }
                if (paymentDao.create(paymentEntity) != null) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean rejectCancelContract(String contractCode) {
        ContractDao contractDao = new ContractDao();
        CardInstanceDao cardInstanceDao = new CardInstanceDao();
        PaymentDao paymentDao = new PaymentDao();
        ContractEntity contractEntity = contractDao.read(contractCode);
        CardInstanceEntity card = cardInstanceDao.getActiveCardInstanceByContract(contractCode);
        boolean isPaidContract = paymentDao.isPaidContract(contractCode);
        Timestamp currentTime = DateUtils.currentTimeWithoutNanos();

        // Check contract
        if (contractEntity != null) {
            // Update contract information
            contractEntity.setCancelDate(null);
            contractEntity.setCancelReason(null);
            contractEntity.setCancelNote(null);
            // Concurrency set value
            contractEntity.setLastModified(currentTime);
            contractEntity.setModifyReason(Constants.ContractModify.STAFF_REJECT_CANCEL_CONTRACT);
            Timestamp currentDate = DateUtils.currentDateWithoutTime();
            if (!isPaidContract || contractEntity.getStartDate().after(currentDate)) {
                contractEntity.setStatus(Constants.ContractStatus.PENDING);
            } else if (card == null) {
                contractEntity.setStatus(Constants.ContractStatus.NO_CARD);
            } else if (currentTime.after(contractEntity.getExpiredDate())) {
                contractEntity.setStatus(Constants.ContractStatus.EXPIRED);
            } else {
                contractEntity.setStatus(Constants.ContractStatus.READY);
            }

            if (contractDao.update(contractEntity) != null) {
                return true;
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
            Timestamp cancelDate = dto.getCancelDate();
            if (cancelDate != null) {
                if (cancelDate.equals(DateUtils.currentDateWithoutTime())) {
                    contractEntity.setCancelDate(DateUtils.currentTimeWithoutNanos());
                } else {
                    contractEntity.setCancelDate(cancelDate);
                }
            }
            if (dto.getCancelReason() != null) {
                contractEntity.setCancelReason(dto.getCancelReason());
            }
            contractEntity.setCancelNote(dto.getCancelNote());
            contractEntity.setStatus(Constants.ContractStatus.CANCELLED);
            // Concurrency set value
            contractEntity.setLastModified(DateUtils.currentTimeWithoutNanos());
            contractEntity.setModifyReason(Constants.ContractModify.STAFF_CANCEL_CONTRACT);
            if (contractDao.update(contractEntity) != null) {
                return true;
            }
        }
        return false;
    }

    public boolean editVehicleInfo(EditVehicleDto dto) {
        ContractDao contractDao = new ContractDao();
        ContractEntity contractEntity = contractDao.read(dto.getContractCode());

        // Check contract
        if (contractEntity != null) {
            // Update vehicle information
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
            // Set lastModified is current date
            contractEntity.setLastModified(DateUtils.currentTimeWithoutNanos());
            contractEntity.setModifyReason(Constants.ContractModify.STAFF_CHANGED_CONTRACT_INFO);

            if (contractDao.update(contractEntity) != null) {
                return true;
            }
        }
        return false;
    }

    public List<ContractTypeEntity> getAllContractType() {
        ContractTypeDao contractTypeDao = new ContractTypeDao();
        return contractTypeDao.getAllContractType();
    }

    public List<ContractTypeEntity> getAllActiveContractType() {
        ContractTypeDao contractTypeDao = new ContractTypeDao();
        return contractTypeDao.getAllActiveContractType();
    }

    public ContractTypeEntity getContractType(int contractTypeId) {
        ContractTypeDao contractTypeDao = new ContractTypeDao();
        return contractTypeDao.read(contractTypeId);
    }

    public List<PaymentEntity> getPaymentByContractCode(String contractCode) {
        PaymentDao paymentDao = new PaymentDao();
        return paymentDao.getPaymentByContractCode(contractCode);
    }

    public PaymentEntity getPaymentDetail(Integer id) {
        PaymentDao paymentDao = new PaymentDao();
        return paymentDao.read(id);
    }

    public boolean createPayment(CreatePaymentDto dto, StaffEntity receiver) {
        PaymentDao paymentDao = new PaymentDao();
        PaymentEntity paymentEntity = new PaymentEntity();

        // Set payment information from dto to entity
        paymentEntity.setContractCode(dto.getContractCode());
        Timestamp currentDate = DateUtils.currentDateWithoutTime();
        if (currentDate.equals(dto.getPaidDate())) {
            paymentEntity.setPaidDate(DateUtils.currentTimeWithoutNanos());
        } else {
            paymentEntity.setPaidDate(dto.getPaidDate());
        }
        paymentEntity.setContent(dto.getContent());
        paymentEntity.setAmount(dto.getAmount());
        // Set others payment information
        paymentEntity.setPaymentMethod("Trực tiếp");
        paymentEntity.setReceiver(receiver.getStaffCode());

        if (paymentDao.create(paymentEntity) != null) {
            return true;
        }
        return false;
    }

    public boolean completePayment(CompletePaymentDto dto, StaffEntity receiver) {
        ContractDao contractDao = new ContractDao();
        PaymentDao paymentDao = new PaymentDao();
        CardInstanceDao cardInstanceDao = new CardInstanceDao();
        ContractEntity contractEntity = contractDao.read(dto.getContractCode());
        PaymentEntity paymentEntity = new PaymentEntity();
        CardInstanceEntity cardEntity = cardInstanceDao.getActiveCardInstanceByContract(dto.getContractCode());

        // Check contract
        if (contractEntity != null) {
            // Set start date
            Timestamp currentDate = DateUtils.currentDateWithoutTime();
            if (currentDate.after(contractEntity.getStartDate())) {
                contractEntity.setStartDate(currentDate);
            }
            // Set expired date = start_date + contract defaultTerm
            ConfigUtils configUtils = new ConfigUtils();
            LocalDate maxExpDate = new LocalDate(contractEntity.getStartDate()).plusMonths(configUtils.getContractDefaultTerm());
            Timestamp maxExpiredDate = new Timestamp(maxExpDate.toDateTimeAtStartOfDay().getMillis());
            contractEntity.setExpiredDate(maxExpiredDate);

            // Concurrency check value
            contractEntity.setLastModified(DateUtils.currentTimeWithoutNanos());
            contractEntity.setModifyReason(Constants.ContractModify.STAFF_PAYMENT_CONTRACT);

            // Update contract status
            // kiem tra neu chua den ngay start hop dong thi de la pending, nguoc lai thi no_card
            if (contractEntity.getStartDate().after(currentDate)) {
                contractEntity.setStatus(Constants.ContractStatus.PENDING);
            } else if (cardEntity == null) {
                contractEntity.setStatus(Constants.ContractStatus.NO_CARD);
            } else {
                contractEntity.setStatus(Constants.ContractStatus.READY);
            }

            if (contractDao.update(contractEntity) != null) {
                // Set payment information from dto to entity
                if (currentDate.equals(dto.getPaidDate())) {
                    paymentEntity.setPaidDate(DateUtils.currentTimeWithoutNanos());
                } else {
                    paymentEntity.setPaidDate(dto.getPaidDate());
                }
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

    public List searchCustomerByNameOrCode(String keyword, int offset, int count) {
        CustomerDao customerDao = new CustomerDao();
        return customerDao.searchCustomerByNameOrCode(keyword, offset, count);
    }

    public Long getAllCustomerCount() {
        CustomerDao customerDao = new CustomerDao();
        return customerDao.getAllCustomerCount();
    }

    public Long searchCustomerByNameOrCodeCount(String keyword) {
        CustomerDao customerDao = new CustomerDao();
        return customerDao.searchCustomerByNameOrCodeCount(keyword);
    }

    /**
     * Returns true if the contract has changed
     * Returns false if the contract is not changed or the contract code is not exists
     *
     * @param contractCode
     * @param lastModified
     * @return boolean, true is changed, false is not change yet
     */
    public boolean isContractChanged(String contractCode, Timestamp lastModified) {
        ContractDao contractDao = new ContractDao();
        ContractEntity contractEntity = contractDao.read(contractCode);
        return contractEntity != null && !contractEntity.getLastModified().equals(lastModified);
    }

    public boolean updateStaffProfile(EditStaffProfileDto editStaffProfileDto, String staffCode) {
        StaffDao staffDao = new StaffDao();
        StaffEntity staffEntity = staffDao.read(staffCode);
        if (staffEntity != null) {
            staffEntity.setEmail(editStaffProfileDto.getEmail());
            staffEntity.setName(editStaffProfileDto.getName());
            staffEntity.setPhone(editStaffProfileDto.getPhone());
            staffDao.update(staffEntity);
            return true;
        }
        return false;
    }

    /**
     * Edit Profile
     */
    public Boolean updateCustomerProfile(EditCustomerProfileByStaffDto dto) {
        CustomerDao customerDao = new CustomerDao();
        CustomerEntity customerEntity = customerDao.read(dto.getCustomerCode());
        if (customerEntity != null) {
            customerEntity.setAddress(dto.getAddress());
            customerEntity.setName(dto.getName());
            customerEntity.setEmail(dto.getEmail());
            customerEntity.setPhone(dto.getPhone());
            customerEntity.setPersonalId(dto.getPersonalId());
            customerEntity.setLastModified(new Timestamp(new java.util.Date().getTime()));
            if (customerDao.update(customerEntity) != null) {
                return true;
            }
        }
        return false;

    }

    public boolean changePassword(StaffChangePasswordDto dto) {
        StaffDao staffDao = new StaffDao();
        StaffEntity staffEntity = staffDao.read(dto.getStaffCode());
        if (staffEntity != null) {
            String encryptedPassword = StringUtils.getMD5Hash(dto.getCurrentPassword());
            if (staffEntity.getPassword().equals(encryptedPassword)) {
                if (dto.getConfirmPassword().equals(dto.getNewPassword())) {
                    String encryptedConfirmPassword = StringUtils.getMD5Hash(dto.getConfirmPassword());
                    staffEntity.setPassword(encryptedConfirmPassword);
                    if (staffDao.update(staffEntity) != null) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
