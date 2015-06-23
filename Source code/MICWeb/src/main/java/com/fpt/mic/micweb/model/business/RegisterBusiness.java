package com.fpt.mic.micweb.model.business;

import com.fpt.mic.micweb.model.dao.ContractDao;
import com.fpt.mic.micweb.model.dao.CustomerDao;
import com.fpt.mic.micweb.model.dao.PaymentDao;
import com.fpt.mic.micweb.model.dto.RegisterInformationDto;
import com.fpt.mic.micweb.model.dto.form.PublicRegisterFormDto;
import com.fpt.mic.micweb.model.entity.ContractEntity;
import com.fpt.mic.micweb.model.entity.CustomerEntity;
import com.fpt.mic.micweb.model.entity.PaymentEntity;
import com.fpt.mic.micweb.utils.Constants;
import com.fpt.mic.micweb.utils.DateUtils;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by TriPQM on 06/04/2015.
 */
public class RegisterBusiness {
    public RegisterInformationDto registerNewContract(PublicRegisterFormDto publicRegisterFormDto) {
        CustomerEntity customerEntity = new CustomerEntity();
        ContractEntity contractEntity = new ContractEntity();

        customerEntity.setPhone(publicRegisterFormDto.getPhone());
        customerEntity.setAddress(publicRegisterFormDto.getAddress());
        customerEntity.setEmail(publicRegisterFormDto.getEmail());
        customerEntity.setName(publicRegisterFormDto.getName());
        customerEntity.setPersonalId(publicRegisterFormDto.getPersonalId());

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

        ContractDao contractDao = new ContractDao();
        CustomerDao customerDao = new CustomerDao();
        // Validate information

        // Add customer
        // get next customer code
        String customerCode = customerDao.getIncrementId();
        customerEntity.setCustomerCode(customerCode);
        // get customer password - add later
        String customerPassword = "12345678";
        customerEntity.setPassword(customerPassword);
        // get next Contract Code
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
    public boolean updateContractPayment(String contractCode, String paymentMethod, String paymentContent, Float amount, String paypalTransId) {
        ContractEntity contractEntity = new ContractEntity();
        ContractDao contractDao = new ContractDao();
        PaymentEntity paymentEntity = new PaymentEntity();

        // get contract just added by contract code
        contractEntity = contractDao.read(contractCode);

        // set start date
        Timestamp currentDate = new Timestamp(new Date().getTime());
        contractEntity.setStartDate(currentDate);
        // set expired date = start_date + 1 year
        contractEntity.setExpiredDate(DateUtils.addOneYear(contractEntity.getStartDate()));
        contractEntity.setStatus(Constants.ContractStatus.NO_CARD);

        paymentEntity.setPaidDate(new Timestamp(new Date().getTime()));
        paymentEntity.setPaymentMethod(paymentMethod);
        paymentEntity.setContent(paymentContent);
        paymentEntity.setAmount(amount);
        paymentEntity.setPaypalTransId(paypalTransId);
        paymentEntity.setContractCode(contractCode);

        PaymentDao paymentDao = new PaymentDao();
        if (contractDao.update(contractEntity) != null) {
            if (paymentDao.create(paymentEntity) != null){
                return true;
            }
        }
        return false;
    }
}
