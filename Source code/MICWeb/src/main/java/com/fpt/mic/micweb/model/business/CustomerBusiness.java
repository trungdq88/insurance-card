package com.fpt.mic.micweb.model.business;

import com.fpt.mic.micweb.model.dao.*;
import com.fpt.mic.micweb.model.dao.helper.NewCardRequestDao;
import com.fpt.mic.micweb.model.dto.CheckShowingRenewCanceled;
import com.fpt.mic.micweb.model.dto.NotificationBuilder;
import com.fpt.mic.micweb.model.dto.form.CancelContractDto;
import com.fpt.mic.micweb.model.dto.form.ChangePasswordDto;
import com.fpt.mic.micweb.model.dto.form.EditCustomerProfileDto;
import com.fpt.mic.micweb.model.entity.*;
import com.fpt.mic.micweb.utils.ConfigUtils;
import com.fpt.mic.micweb.utils.Constants;
import com.fpt.mic.micweb.utils.DateUtils;
import com.fpt.mic.micweb.utils.StringUtils;
import org.joda.time.LocalDate;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by PhucNguyen on 05/06/2015.
 */
public class CustomerBusiness {
    //get information customer
    public CustomerEntity getCustomer(String customerCode) {
        CustomerDao customerDao = new CustomerDao();
        return customerDao.read(customerCode);
    }

    //get all contract belong to customer
    public List getAllContractByCustomer(String customerCode, int offset, int count) {
        ContractDao contractDao = new ContractDao();
        return contractDao.getContractByCustomerCode(customerCode, offset, count);
    }

    /**
     * get all payment by contractCode
     *
     * @return list payment
     */
    public List<PaymentEntity> getAllPaymentByContractCode(String contractCode) {
        PaymentDao paymentDao = new PaymentDao();
        return paymentDao.getPaymentByContractCode(contractCode);
    }

    /**
     * get all accident by contractCode
     *
     * @return list accident
     */
    public List<AccidentEntity> getAllAccidentByContractCode(String contractCode,
                                                             int offset, int count) {
        AccidentDao accidentDao = new AccidentDao();
        return accidentDao.getAllAccidentByContractCode(contractCode, offset, count);
    }

    public Long getAllContractByCustomerCount(String customerCode) {
        ContractDao contractDao = new ContractDao();
        return contractDao.getContractByCustomerCodeCount(customerCode);
    }

    public Long getAllPaymentByCustomerCount(String customerCode) {
        PaymentDao paymentDao = new PaymentDao();
        return paymentDao.getAllPaymentByCustomerCodeCount(customerCode);
    }

    public List<PaymentEntity> getAllPaymentByCustomerCode(String customerCode, int offset, int count) {
        PaymentDao paymentDao = new PaymentDao();
        return paymentDao.getPaymentByCustomerCode(customerCode, offset, count);
    }

    // get contract detail
    public ContractEntity getContractDetail(String code) {
        ContractDao contractDao = new ContractDao();
        ContractEntity contract = contractDao.read(code);
        if (contract != null) {
            return contract;
        } else {
            return null;
        }
    }

    /**
     * cancel contract
     *
     * @return contract entity
     */
    public ContractEntity cancelContract(CancelContractDto cancelDto) {
        ContractDao contractDao = new ContractDao();
        ContractEntity contract = contractDao.read(cancelDto.getContractCode());

        contract.setCancelDate(cancelDto.getCancelDate());
        contract.setCancelReason(cancelDto.getCancelReason());
        contract.setStatus(Constants.ContractStatus.REQUEST_CANCEL);

        // Concurrency set value
        contract.setLastModified(new Timestamp(new java.util.Date().getTime()));
        contract.setModifyReason(Constants.ContractModify.CUSTOMER_REQUEST_CANCEL);

        // Send notification
        NotificationBusiness bus = new NotificationBusiness();
        bus.send(NotificationBuilder.customerSendRequestCancel(contract));

        contractDao.update(contract);
        return contract;
    }

    /**
     * renew contract
     *
     * @param contractCode,        nexExpried
     * @param paymentTransactionId
     * @return contract
     */
    public boolean renewContract(String contractCode, Timestamp neweEpired, String paymentTransactionId) {
        //init
        boolean result = false;
        ContractDao contractDao = new ContractDao();
        PaymentDao paymentDao = new PaymentDao();
        CardInstanceDao cardInstanceDao = new CardInstanceDao();
        ContractEntity contract = contractDao.read(contractCode);
        PaymentEntity payment = new PaymentEntity();
        CardInstanceEntity card = cardInstanceDao.getActiveCardInstanceByContract(contractCode);
        java.util.Date date = new java.util.Date();
        /////////////////////////
        if (contract != null) {
            // Concurrency set value
            contract.setLastModified(new Timestamp(new java.util.Date().getTime()));
            contract.setModifyReason(Constants.ContractModify.CUSTOMER_RENEW_CONTRACT);

            // Mark payment done
            contract.setNeedRenewPayment(null);

            if (contract.getStatus().equalsIgnoreCase(Constants.ContractStatus.EXPIRED)) {
                payment.setStartDate(DateUtils.currentDateWithoutTime());
            } else {
                payment.setStartDate(contract.getExpiredDate());
            }
            contract.setExpiredDate(neweEpired);
            if (card == null) {
                contract.setStatus(Constants.ContractStatus.NO_CARD);
            } else {
                contract.setStatus(Constants.ContractStatus.READY);
            }
            //update payment
            payment.setExpiredDate(neweEpired);
            payment.setPaidDate(DateUtils.currentDateWithoutTime());
            payment.setPaymentMethod("PayPal");
            payment.setContent("Gia hạn hợp đồng");
            payment.setAmount(contract.getMicContractTypeByContractTypeId().getPricePerYear());
            payment.setPaypalTransId(paymentTransactionId);
            payment.setContractCode(contract.getContractCode());
            if (contractDao.update(contract) != null && paymentDao.create(payment) != null) {
                result = true;
            }
        }

        return result;

    }

    /**
     * reject request cancel contract
     *
     * @param contractCode
     * @return contract
     */
    public ContractEntity rejectCancelContract(String contractCode) {
        CardInstanceDao cardInstanceDao = new CardInstanceDao();
        ContractDao contractDao = new ContractDao();
        CardInstanceEntity card = cardInstanceDao.getActiveCardInstanceByContract(contractCode);
        ContractEntity contract = contractDao.read(contractCode);
        java.util.Date date = new java.util.Date();
        // pending
        if (contract.getStartDate().equals(contract.getExpiredDate())) {
            contract.setStatus(Constants.ContractStatus.PENDING);
        }
        // no card
        else if (card == null) {
            contract.setStatus(Constants.ContractStatus.NO_CARD);
        }
        // ready
        else if ((new Timestamp(date.getTime()).before(contract.getExpiredDate()))) {
            contract.setStatus(Constants.ContractStatus.READY);
        }
        // expired
        else if ((new Timestamp(date.getTime()).after(contract.getExpiredDate()))) {
            contract.setStatus(Constants.ContractStatus.EXPIRED);
        }
        contract.setCancelReason(null);
        contract.setCancelNote(null);

        // Concurrency set value
        contract.setLastModified(new Timestamp(new java.util.Date().getTime()));
        contract.setModifyReason(Constants.ContractModify.CUSTOMER_REJECT_CANCEL_REQUEST);

        if (contractDao.update(contract) != null) {
            return contract;
        } else {
            return null;
        }
    }

    /**
     * payment for contract
     *
     * @param contractCode , paymentTransactionId
     * @return bool result
     */
    public boolean paymentContract(String contractCode, String paymentTransactionId) {
        //init
        boolean result = false;
        java.util.Date date = new java.util.Date();
        ContractDao contractDao = new ContractDao();
        PaymentDao paymentDao = new PaymentDao();
        ContractEntity contract = contractDao.read(contractCode);
        PaymentEntity payment = new PaymentEntity();
        if (contract != null) {
            // Concurrency set value
            contract.setLastModified(new Timestamp(new java.util.Date().getTime()));
            contract.setModifyReason(Constants.ContractModify.CUSTOMER_PAYMENT_CONTRACT);

            // set start date
            Timestamp currentDate = DateUtils.currentDateWithoutTime();
            if (currentDate.after(contract.getStartDate())) {
                contract.setStartDate(currentDate);
            }
            // set expired date = start_date + 1 year
            ConfigUtils configUtils = new ConfigUtils();
            LocalDate configDate = new LocalDate(contract.getStartDate()).plusMonths(configUtils.getContractDefaultTerm());
            Timestamp newExpiredDate = new Timestamp(configDate.toDateTimeAtStartOfDay().getMillis());
            contract.setExpiredDate(newExpiredDate);
            if (contract.getStartDate().after(currentDate)) {
                contract.setStatus(Constants.ContractStatus.PENDING);
            } else {
                contract.setStatus(Constants.ContractStatus.NO_CARD);
            }
            payment.setPaidDate(new Timestamp(date.getTime()));
            payment.setPaymentMethod("PayPal");
            payment.setContent("Đăng ký hợp đồng mới " + contract.getContractCode());
            payment.setAmount(contract.getMicContractTypeByContractTypeId().getPricePerYear());
            payment.setPaypalTransId(paymentTransactionId);
            payment.setContractCode(contract.getContractCode());
            if (contractDao.update(contract) != null && paymentDao.create(payment) != null) {
                result = true;
            }
        }
        return result;
    }

    public List searchCustomerContractByCode(String customerCode, String keyword, int offset, int count) {
        ContractDao contractDao = new ContractDao();
        return contractDao.getCustomerContractByCode(customerCode, keyword, offset, count);
    }

    public Long searchCustomerContractByCodeCount(String customerCode, String keyword) {
        ContractDao contractDao = new ContractDao();
        return contractDao.getCustomerContractByCodeCount(customerCode, keyword);
    }

    /**
     * change password
     *
     * @param , currentPassword, newPass, confirmPass
     * @return bool result
     */
    public boolean changePassword(ChangePasswordDto dto) {
        boolean result = false;
        CustomerDao customerDao = new CustomerDao();
        CustomerEntity customerEntity = customerDao.read(dto.getCustomerCode());
        if (customerEntity != null) {
            String encryptedPassword = StringUtils.getMD5Hash(dto.getCurrentPassword());
            if (customerEntity.getPassword().equals(encryptedPassword)) {
                if (dto.getConfirmPassword().equals(dto.getNewPassword())) {
                    String encryptedConfirmPassword = StringUtils.getMD5Hash(dto.getConfirmPassword());
                    customerEntity.setPassword(encryptedConfirmPassword);
                    customerEntity.setIsDefaultPassword(1);
                    result = customerDao.update(customerEntity) != null;
                }
            }
        }
        return result;
    }

    /**
     * reject change password
     *
     * @param , customerCode
     * @return bool result
     */
    public boolean rejectChangePassword(String customerCode) {
        boolean result = false;
        CustomerDao customerDao = new CustomerDao();
        CustomerEntity customerEntity = customerDao.read(customerCode);
        customerEntity.setIsDefaultPassword(1);
        if (customerDao.update(customerEntity) != null) {
            result = true;
        }
        return result;
    }

    /**
     * Returns true if the contract has changed
     * Returns false if the contract is not changed or the contract code is not exists
     *
     * @param contractCode
     * @param lastModified
     * @return
     */
    public boolean isContractChanged(String contractCode, Timestamp lastModified) {
        ContractDao contractDao = new ContractDao();
        ContractEntity contractEntity = contractDao.read(contractCode);
        return contractEntity != null && !contractEntity.getLastModified().equals(lastModified);
    }

    public List getOnePageNewCardRequest(String customerCode, int offset, int count) {
        NewCardRequestDao newCardRequestDao = new NewCardRequestDao();
        return newCardRequestDao.getOnePageNewCardRequest(customerCode, offset, count);
    }

    public Long getAllNewCardRequestCount(String customerCode) {
        NewCardRequestDao newCardRequestDao = new NewCardRequestDao();
        return newCardRequestDao.getAllNewCardRequestCount(customerCode);
    }

    public List searchOnePageNewCardRequest(String keyword, String customerCode, int offset, int count) {
        NewCardRequestDao newCardRequestDao = new NewCardRequestDao();
        return newCardRequestDao.searchOnePageNewCardRequest(keyword, customerCode, offset, count);
    }

    public Long searchAllNewCardRequestCount(String keyword, String customerCode) {
        NewCardRequestDao newCardRequestDao = new NewCardRequestDao();
        return newCardRequestDao.searchAllNewCardRequestCount(keyword, customerCode);
    }

    public Long getUnresolvedNewCardRequestCount(String customerCode) {
        NewCardRequestDao newCardRequestDao = new NewCardRequestDao();
        return newCardRequestDao.getUnresolvedNewCardRequestCount(customerCode);
    }

    /**
     * Edit Profile
     */
    public Boolean editCustomerProfile(String customerCode, EditCustomerProfileDto dto) {
        boolean result = false;
        CustomerDao customerDao = new CustomerDao();
        CustomerEntity customerEntity = customerDao.read(customerCode);
        if (customerEntity != null) {
            customerEntity.setAddress(dto.getAddress());
            customerEntity.setEmail(dto.getEmail());
            customerEntity.setPhone(dto.getPhone());
            customerEntity.setPersonalId(dto.getPersonalID());
            customerEntity.setLastModified(new Timestamp(new java.util.Date().getTime()));
            if (customerDao.update(customerEntity) != null) {
                result = true;
            }
        }
        return result;

    }

    public Long getAllAccidentByContractCodeCount(String code) {
        AccidentDao accidentDao = new AccidentDao();
        return accidentDao.getAllAccidentByContractCodeCount(code);
    }

    /**
     * Payment befor startDate
     */
    public boolean isPayment(String contractCode) {
        PaymentDao paymentDao = new PaymentDao();
        List<PaymentEntity> listPayment = paymentDao.getPaymentByContractCode(contractCode);
        if (listPayment != null && listPayment.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * calculator new day base on number of month follow config
     */
    public Timestamp newDate(String contractCode) {
        Timestamp currentDate = DateUtils.currentDateWithoutTime();
        ConfigUtils configUtils = new ConfigUtils();
        //pay contract
        ContractDao contractDao = new ContractDao();
        ContractEntity contractEntity = contractDao.read(contractCode);
        PaymentDao paymentDao = new PaymentDao();
        List<PaymentEntity> listPayment = paymentDao.getPaymentByContractCode(contractCode);
        if (contractEntity.getStatus().equals(Constants.ContractStatus.PENDING)) {
            if (listPayment == null || listPayment.size() <= 0) {
                currentDate = DateUtils.addMonth(contractEntity.getCreatedDate(), configUtils.getContractDefaultTerm());
            }
        } else if (contractEntity.getStatus().equals(Constants.ContractStatus.EXPIRED)) {
            currentDate = DateUtils.addMonth(currentDate, configUtils.getContractDefaultTerm());
        } else if (contractEntity.getStatus().equals(Constants.ContractStatus.READY) ||
                contractEntity.getStatus().equals(Constants.ContractStatus.NO_CARD)) {
            currentDate = DateUtils.addMonth(contractEntity.getExpiredDate(), configUtils.getContractDefaultTerm());
        }
        return currentDate;
    }

    /**
     * compare date now and expired date
     */
    public long countDateRemain(Timestamp expiredDate) {
        long countNumDate = DateUtils.dateBetween(DateUtils.currentDateWithoutTime(), DateUtils.convertDateTimeToDate(expiredDate));
        return countNumDate;
    }

    /**
     * check status for datetime of contract
     */
    public String messageContract(Timestamp expiredDate, String contractCode) {
        String mesg = "";
        if (isPayment(contractCode) == true) {
            long countNumDate = DateUtils.dateBetween(DateUtils.currentDateWithoutTime(), DateUtils.convertDateTimeToDate(expiredDate));
            if (countNumDate >= 0) {
                mesg = "Còn hạn: " + countNumDate + " ngày";
            } else {
                mesg = "Quá hạn: " + Math.abs(countNumDate) + " ngày";
            }
        } else {
            mesg = "Chưa thanh toán";
        }
        return mesg;
    }

    /**
     * check show or hide button renew and canceled
     */
    public CheckShowingRenewCanceled handleShowingButton(Timestamp expiredDate, String status) {
        CheckShowingRenewCanceled handle = new CheckShowingRenewCanceled();
        ConfigUtils configUtils = new ConfigUtils();
        long countNumDate = DateUtils.dateBetween(DateUtils.currentDateWithoutTime(), DateUtils.convertDateTimeToDate(expiredDate));

        //Ready
        if (status.equals(Constants.ContractStatus.READY)) {
            if (Math.abs(countNumDate) > configUtils.getContractRenewLimit()) {
                handle.setCheckRenew("hide");
            }
        }
        //Cancelled
        else if (status.equals(Constants.ContractStatus.CANCELLED)) {
            handle.setCheckRenew("hide");
            handle.setCheckCancelled("hide");
        }
        //Expired
        else if (status.equals(Constants.ContractStatus.EXPIRED)) {
            handle.setCheckCancelled("hide");
        }
        //Pending
        else if (status.equals(Constants.ContractStatus.PENDING)) {
            handle.setCheckRenew("hide");
        }
        //Request cancel
        else if (status.equals(Constants.ContractStatus.REQUEST_CANCEL)) {
            handle.setCheckRenew("hide");
            handle.setCheckCancelled("hide");
        }
        //No card
        else if (status.equals(Constants.ContractStatus.NO_CARD)) {
            if (Math.abs(countNumDate) > configUtils.getContractRenewLimit()) {
                handle.setCheckRenew("hide");
            }
        }
        return handle;
    }

    public float countFeeContract(float feePerYear) {
        ConfigUtils configUtils = new ConfigUtils();
        float contractDefaultTerm = configUtils.getContractDefaultTerm();
        float numberMonthToCount = (contractDefaultTerm / 12);

        float realFee = feePerYear * numberMonthToCount;
        // fix 1000
        if(realFee % 1000 != 0){
            realFee = realFee - (realFee % 1000);
            realFee += 1000;
        }

        return realFee;
    }

    public List<PaymentEntity> getPaymentByContractCode(String contractCode) {
        PaymentDao paymentDao = new PaymentDao();
        return paymentDao.getPaymentByContractCode(contractCode);
    }

    public List<PaymentEntity> getRenewsByContractCode(String contractCode) {
        PaymentDao paymentDao = new PaymentDao();
        return paymentDao.getRenewsByContractCode(contractCode);
    }
}
