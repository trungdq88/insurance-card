package com.fpt.mic.micweb.model.business;

import com.fpt.mic.micweb.model.dao.ContractDao;
import com.fpt.mic.micweb.model.dao.CustomerDao;
import com.fpt.mic.micweb.model.dao.PaymentDao;
import com.fpt.mic.micweb.model.dto.NotificationBuilder;
import com.fpt.mic.micweb.model.dto.RegisterInformationDto;
import com.fpt.mic.micweb.model.dto.form.CustomerCreateContractDto;
import com.fpt.mic.micweb.model.dto.form.PublicRegisterFormDto;
import com.fpt.mic.micweb.model.entity.ContractEntity;
import com.fpt.mic.micweb.model.entity.CustomerEntity;
import com.fpt.mic.micweb.model.entity.PaymentEntity;
import com.fpt.mic.micweb.utils.Constants;
import com.fpt.mic.micweb.utils.DateUtils;
import com.fpt.mic.micweb.utils.EmailUtils;
import com.fpt.mic.micweb.utils.StringUtils;

import javax.servlet.ServletContext;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by TriPQM on 06/04/2015.
 */
public class RegisterBusiness {
    // register new contract by customer
    public RegisterInformationDto customerCreateContract(CustomerCreateContractDto customerCreateContractDto, String customerCode) {
        RegisterInformationDto registerInformationDto = new RegisterInformationDto();
        ContractDao contractDao = new ContractDao();
        CustomerDao customerDao = new CustomerDao();
        CustomerEntity customerEntity = customerDao.read(customerCode);
        ContractEntity contractEntity = new ContractEntity();

        contractEntity.setPlate(customerCreateContractDto.getPlate());
        contractEntity.setBrand(customerCreateContractDto.getBrand());
        contractEntity.setModelCode(customerCreateContractDto.getModel());
        contractEntity.setVehicleType(customerCreateContractDto.getType());
        contractEntity.setColor(customerCreateContractDto.getColor());
        contractEntity.setEngine(customerCreateContractDto.getEngine());
        contractEntity.setChassis(customerCreateContractDto.getChassis());
        contractEntity.setCapacity(customerCreateContractDto.getCapacity());
        contractEntity.setYearOfManufacture(customerCreateContractDto.getYearOfMan());
        contractEntity.setWeight(customerCreateContractDto.getWeight());
        contractEntity.setSeatCapacity(customerCreateContractDto.getSeatCapacity());
        contractEntity.setContractFee(customerCreateContractDto.getContractFee());
        contractEntity.setStatus(Constants.ContractStatus.PENDING);
        contractEntity.setContractTypeId(customerCreateContractDto.getContractType());
        // lay ngay nhap vao
        Timestamp startDate = customerCreateContractDto.getStartDate();
        contractEntity.setStartDate(startDate);
        contractEntity.setExpiredDate(startDate);
        Timestamp currentTime = new Timestamp(new Date().getTime());
        currentTime.setNanos(0);
        contractEntity.setLastModified(currentTime);
        contractEntity.setContractCode(contractDao.getIncrementId());
        contractEntity.setCustomerCode(customerCode);

        ContractEntity contract = contractDao.create(contractEntity);

        // Send notification
        NotificationBusiness notif = new NotificationBusiness();
        ContractEntity notifContract = contractDao.read(contract.getContractCode());
        notif.send(NotificationBuilder.customerCreateContract(notifContract));

        registerInformationDto.setCustomerEntity(customerEntity);
        registerInformationDto.setContractEntity(contractEntity);
        registerInformationDto.setExistCustomer(true);
        return registerInformationDto;
    }


    // register new contract by guest
    public RegisterInformationDto registerNewContract(
            PublicRegisterFormDto publicRegisterFormDto, ServletContext context, String loginUrl) {
        CustomerEntity customerEntity = new CustomerEntity();
        ContractEntity contractEntity = new ContractEntity();

        customerEntity.setPhone(publicRegisterFormDto.getPhone());
        customerEntity.setAddress(publicRegisterFormDto.getAddress());
        customerEntity.setEmail(publicRegisterFormDto.getEmail());
        customerEntity.setName(publicRegisterFormDto.getName());
        customerEntity.setPersonalId(publicRegisterFormDto.getPersonalId());
        customerEntity.setLastModified(new Timestamp(new Date().getTime()));

        contractEntity.setPlate(publicRegisterFormDto.getPlate());
        contractEntity.setBrand(publicRegisterFormDto.getBrand());
        contractEntity.setModelCode(publicRegisterFormDto.getModel());
        contractEntity.setVehicleType(publicRegisterFormDto.getType());
        contractEntity.setColor(publicRegisterFormDto.getColor());
        contractEntity.setEngine(publicRegisterFormDto.getEngine());
        contractEntity.setChassis(publicRegisterFormDto.getChassis());
        contractEntity.setCapacity(publicRegisterFormDto.getCapacity());
        contractEntity.setYearOfManufacture(publicRegisterFormDto.getYearOfMan());
        contractEntity.setWeight(publicRegisterFormDto.getWeight());
        contractEntity.setSeatCapacity(publicRegisterFormDto.getSeatCapacity());
        contractEntity.setContractFee(publicRegisterFormDto.getContractFee());
        contractEntity.setStatus(Constants.ContractStatus.PENDING);
        contractEntity.setContractTypeId(publicRegisterFormDto.getContractType());
        // lay ngay nhap vao
        Timestamp startDate = publicRegisterFormDto.getStartDate();
        contractEntity.setStartDate(startDate);
        contractEntity.setExpiredDate(startDate);
        Timestamp currentTime = new Timestamp(new Date().getTime());
        currentTime.setNanos(0);
        contractEntity.setLastModified(currentTime);

        ContractDao contractDao = new ContractDao();
        CustomerDao customerDao = new CustomerDao();

        // Add customer
        // get next customer code
        String customerCode = customerDao.getIncrementId();
        customerEntity.setCustomerCode(customerCode);
        // get customer password
        String customerPassword = StringUtils.randomString();
        String encryptedPassword = StringUtils.getMD5Hash(customerPassword);
        customerEntity.setPassword(encryptedPassword);
        // get next Contract Code
        contractEntity.setContractCode(contractDao.getIncrementId());
        contractEntity.setCustomerCode(customerCode);

        CustomerEntity customer = customerDao.create(customerEntity);
        if (customer != null) {
            ContractEntity contract = contractDao.create(contractEntity);
            if (contract != null) {
                // Send email
                boolean emailSuccess = sendPasswordEmail(context, loginUrl, customerPassword, customer);

                // Send notification
                NotificationBusiness notif = new NotificationBusiness();
                ContractEntity notifContract = contractDao.read(contract.getContractCode());
                notif.send(NotificationBuilder.customerCreateContract(notifContract));

                return new RegisterInformationDto(contract, customer, emailSuccess);
            }

        }

        return null;
    }

    public boolean sendPasswordEmail(ServletContext context, String loginUrl, String customerPassword, CustomerEntity customer) {
        // Send password email
        InputStream resourceAsStream =
                context.getResourceAsStream("/WEB-INF/templates/password-email.html");
        String content = StringUtils.getString(resourceAsStream);
        boolean emailSuccess = false;
        if (content != null) {
            content = content
                    .replaceAll("\\{\\{customerCode\\}\\}", customer.getCustomerCode())
                    .replaceAll("\\{\\{password\\}\\}", customerPassword)
                    .replaceAll("\\{\\{loginUrl\\}\\}", loginUrl);
            emailSuccess = EmailUtils.sendMail(customer.getEmail(), content);
        }
        return emailSuccess;
    }
    public boolean isPaidContract(String contractCode){
        PaymentDao paymentDao = new PaymentDao();
        return paymentDao.isPaidContract(contractCode);
    }

    public boolean isExistByPlate(String plate){
        ContractDao contractDao = new ContractDao();
        return contractDao.isExistByPlate(plate);
    }
    public String updateContractPayment(String contractCode, String paymentMethod, String paymentContent, Float amount, String paypalTransId) {
        ContractEntity contractEntity;
        ContractDao contractDao = new ContractDao();
        PaymentEntity paymentEntity = new PaymentEntity();

        // get contract just added by contract code
        contractEntity = contractDao.read(contractCode);
        if (contractDao.isExistByPlate(contractEntity.getPlate())) {

            return "Đã có hợp đồng hiệu lực với xe có biển số này";
        }

        // set start date
        Timestamp currentDate = DateUtils.currentDateWithoutTime();
        if (currentDate.after(contractEntity.getStartDate()))
        {
            contractEntity.setStartDate(currentDate);
        }
        // set expired date = start_date + 1 year
        contractEntity.setExpiredDate(DateUtils.addOneYear(contractEntity.getStartDate()));

        // set new last modified time
        contractEntity.setLastModified(new Timestamp(new Date().getTime()));

        if(contractEntity.getStartDate().after(currentDate)) {
            contractEntity.setStatus(Constants.ContractStatus.PENDING);
        } else {
            contractEntity.setStatus(Constants.ContractStatus.NO_CARD);
        }

        paymentEntity.setPaidDate(new Timestamp(new Date().getTime()));
        paymentEntity.setPaymentMethod(paymentMethod);
        paymentEntity.setContent(paymentContent);
        paymentEntity.setAmount(amount);
        paymentEntity.setPaypalTransId(paypalTransId);
        paymentEntity.setContractCode(contractCode);

        PaymentDao paymentDao = new PaymentDao();
        if (contractDao.update(contractEntity) != null) {
            if (paymentDao.create(paymentEntity) != null) {
                return null;
            }
        }
        return "Giao dịch không thành công";
    }
}
