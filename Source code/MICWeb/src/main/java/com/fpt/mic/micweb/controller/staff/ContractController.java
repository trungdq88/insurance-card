package com.fpt.mic.micweb.controller.staff;

import com.fpt.mic.micweb.framework.BasicController;
import com.fpt.mic.micweb.framework.R;
import com.fpt.mic.micweb.framework.responses.JspPage;
import com.fpt.mic.micweb.framework.responses.ResponseObject;
import com.fpt.mic.micweb.model.business.StaffBusiness;
import com.fpt.mic.micweb.model.entity.ContractEntity;
import com.fpt.mic.micweb.model.entity.ContractTypeEntity;
import com.fpt.mic.micweb.model.entity.CustomerEntity;
import com.fpt.mic.micweb.model.entity.PaymentEntity;
import com.fpt.mic.micweb.utils.DateUtils;

import javax.servlet.annotation.WebServlet;
import java.sql.Timestamp;
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

        // Get contract detail
        ContractEntity contractDetail = staffBus.getContractDetail(contractCode);
        long diff = contractDetail.getExpiredDate().getTime() - contractDetail.getStartDate().getTime();
        long diffDays = 0;
        if (diff > 0) {
            diffDays = diff / (24 * 60 * 60 * 1000);
        }
        r.equest.setAttribute("REMAIN", diffDays);
        r.equest.setAttribute("CONTRACT", contractDetail);

        // Get customer detail
        CustomerEntity customerDetail = staffBus.getCustomerDetail(contractDetail.getCustomerCode());
        r.equest.setAttribute("CUSTOMER", customerDetail);

        // Get payment detail
        List<PaymentEntity> listPayment = staffBus.getPaymentByContractCode(contractCode);
        r.equest.setAttribute("PAYMENT", listPayment);

        // Dispatch to JSP page
        return new JspPage("staff/contract-detail.jsp");
    }

    public ResponseObject getCreate(R r) {
        StaffBusiness staffBus = new StaffBusiness();
        List<ContractTypeEntity> listContractType = staffBus.getAllContractType();
        r.equest.setAttribute("CONTRACTTYPE", listContractType);
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
        contractEntity.setStartDate(DateUtils.stringToTime(startDate));
        String expiredDate = r.equest.getParameter("txtExpiredDate");
        contractEntity.setExpiredDate(DateUtils.stringToTime(expiredDate));
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
        paymentEntity.setPaidDate(DateUtils.stringToTime(paidDate));

        paymentEntity.setAmount(Float.parseFloat(r.equest.getParameter("txtAmount")));

        // Call to business object
        StaffBusiness staffBus = new StaffBusiness();
        boolean result = staffBus.createContract(contractEntity, paymentEntity);

        if (result) {
            // Return Success JSP Page
            return new JspPage("staff/create-contract-success.jsp");
        } else {
            r.equest.setAttribute("error", "Something wrong");
            return new JspPage("staff/message.jsp");
        }
    }

    public ResponseObject postRenew(R r) {
        // Get renew contract information
        String contractCode = r.equest.getParameter("txtContractCode");
        String newStartDate = r.equest.getParameter("txtNewStartDate");
        Timestamp startDate = DateUtils.stringToTime(newStartDate);
        String newExpiredDate = r.equest.getParameter("txtExpiredDate");
        Timestamp expiredDate = DateUtils.stringToTime(newExpiredDate);

        // Get renew payment information
        PaymentEntity paymentEntity = new PaymentEntity();
        String paidDate = r.equest.getParameter("txtPaidDate");
        paymentEntity.setPaidDate(DateUtils.stringToTime(paidDate));
        paymentEntity.setAmount(Float.parseFloat(r.equest.getParameter("txtAmount")));

        // Call to business object
        StaffBusiness staffBus = new StaffBusiness();
        boolean result = staffBus.renewContract(contractCode, startDate, expiredDate, paymentEntity);
        String msg = "";
        if (result) {
            msg = "Đã gia hạn hợp đồng thành công";
        } else {
            msg = "Gia hạn hợp đồng thất bại";
        }
        r.equest.setAttribute("CODE", contractCode);
        r.equest.setAttribute("MESSAGE", msg);
        return new JspPage("staff/message.jsp");
    }

    public ResponseObject postCancel(R r) {
        // Get cancel contract information
        String contractCode = r.equest.getParameter("txtContractCode");
        String inputDate = r.equest.getParameter("txtCancelDate");
        Timestamp cancelDate = DateUtils.stringToTime(inputDate);
        String cancelReason = r.equest.getParameter("txtCancelReason");
        String cancelNote = r.equest.getParameter("txtCancelNote");

        // Call to business object
        StaffBusiness staffBus = new StaffBusiness();
        boolean result = staffBus.cancelContract(contractCode, cancelDate, cancelReason, cancelNote);
        String msg = "";
        if (result) {
            msg = "Đã hủy hợp đồng thành công";
        } else {
            msg = "Hủy hợp đồng thất bại";
        }
        r.equest.setAttribute("CODE", contractCode);
        r.equest.setAttribute("MESSAGE", msg);
        return new JspPage("staff/message.jsp");
    }
}
