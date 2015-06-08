package com.fpt.mic.micweb.controller.staff;

import com.fpt.mic.micweb.framework.BasicController;
import com.fpt.mic.micweb.framework.R;
import com.fpt.mic.micweb.framework.responses.JspPage;
import com.fpt.mic.micweb.framework.responses.ResponseObject;
import com.fpt.mic.micweb.model.business.StaffBusiness;
import com.fpt.mic.micweb.model.entity.ContractEntity;
import com.fpt.mic.micweb.model.entity.PaymentEntity;

import javax.servlet.annotation.WebServlet;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by dinhquangtrung on 5/23/15.
 */
@WebServlet(name = "ContractController", urlPatterns = {"/staff/contract"})
public class ContractController extends BasicController {

    public ResponseObject getView(R r) {
        StaffBusiness staffBus = new StaffBusiness();
        List<ContractEntity> listContract = staffBus.getAllContract();
        r.equest.setAttribute("INFO", listContract);
        return new JspPage("staff/contracts.jsp");
    }

    public ResponseObject getDetail(R r) {
        StaffBusiness staffBus = new StaffBusiness();
        String contractCode = r.equest.getParameter("code");
        System.out.println(contractCode);
        ContractEntity contractDetail = staffBus.getContractDetail(contractCode);
        return new JspPage("staff/detail-contract.jsp");
    }

    public ResponseObject getCreate(R r) {
        return new JspPage("staff/create-contract.jsp");
    }

    public ResponseObject getPreview(R r) {
        return new JspPage("staff/create-contract-preview.jsp");
    }

    public ResponseObject getSuccess(R r) {
        return new JspPage("staff/create-contract-success.jsp");
    }

    public ResponseObject postCreate(R r) {
        ContractEntity contractEntity = new ContractEntity();
        PaymentEntity paymentEntity = new PaymentEntity();

        // Get contract information
        contractEntity.setCustomerCode(r.equest.getParameter("txtCustomerCode"));
        contractEntity.setContractTypeId(Integer.parseInt(r.equest.getParameter("ddlContractType")));

        String startDate = r.equest.getParameter("txtStartDate");
        System.out.println(startDate);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = dateFormat.parse(startDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long time = date.getTime();
        contractEntity.setStartDate(new Timestamp(time));

        String expiredDate = r.equest.getParameter("txtExpiredDate");
        System.out.println(expiredDate);
        try {
            date = dateFormat.parse(expiredDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        time = date.getTime();
        contractEntity.setExpiredDate(new Timestamp(time));
        contractEntity.setContractFee(Float.parseFloat(r.equest.getParameter("txtContractFee")));
        contractEntity.setPlate(r.equest.getParameter("txtPlate"));
        contractEntity.setBrand(r.equest.getParameter("txtBrand"));
        contractEntity.setModelCode(r.equest.getParameter("txtModel"));
        contractEntity.setVehicleType(r.equest.getParameter("txtType"));
        contractEntity.setColor(r.equest.getParameter("txtColor"));
        contractEntity.setEngine(r.equest.getParameter("txtEngine"));
        contractEntity.setChassis(r.equest.getParameter("txtChassis"));
        contractEntity.setCapacity(r.equest.getParameter("txtCapacity"));
        contractEntity.setYearOfManufacture(Integer.parseInt(r.equest.getParameter("txtYearOfMan")));
        contractEntity.setWeight(Integer.parseInt(r.equest.getParameter("txtWeight")));
        contractEntity.setSeatCapacity(Integer.parseInt(r.equest.getParameter("txtSeatCapacity")));

        // Get payment information
        String paidDate = r.equest.getParameter("txtPaidDate");
        System.out.println(expiredDate);
        try {
            date = dateFormat.parse(expiredDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        time = date.getTime();
        paymentEntity.setPaidDate(new Timestamp(time));
        paymentEntity.setAmount(Float.parseFloat(r.equest.getParameter("txtAmount")));
        paymentEntity.setReceiver(r.equest.getParameter("txtReceiver"));

        // Call to business object
        StaffBusiness staffBus = new StaffBusiness();
        boolean result = staffBus.createContract(contractEntity, paymentEntity);

        if (result) {
            // Return Success JSP Page
            return new JspPage("staff/create-contract-success.jsp");
        } else {
            r.equest.setAttribute("error", "Something wrong");
            return new JspPage("error/msg.jsp");
        }
    }
}
