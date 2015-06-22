package com.fpt.mic.micweb.controller.staff;

import com.fpt.mic.micweb.framework.BasicController;
import com.fpt.mic.micweb.framework.R;
import com.fpt.mic.micweb.framework.responses.JspPage;
import com.fpt.mic.micweb.framework.responses.ResponseObject;
import com.fpt.mic.micweb.model.business.StaffBusiness;
import com.fpt.mic.micweb.model.dto.CreateContractDTO;
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

    private static String msg = "";

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
        // Get contract information
        String customerCode = r.equest.getParameter("txtCustomerCode");
        Integer contractTypeId = Integer.parseInt(r.equest.getParameter("ddlContractType"));
        Timestamp startDate = DateUtils.stringToTime(r.equest.getParameter("txtStartDate"));
        Timestamp expiredDate = DateUtils.stringToTime(r.equest.getParameter("txtExpiredDate"));
        Float contractFee = Float.parseFloat(r.equest.getParameter("txtContractFee"));
        String plate = r.equest.getParameter("txtPlate");
        String brand = r.equest.getParameter("txtBrand");
        String modelCode = r.equest.getParameter("txtModel");
        String vehicleType = r.equest.getParameter("txtType");
        String color = r.equest.getParameter("txtColor");
        String engine = r.equest.getParameter("txtEngine");
        String chassis = r.equest.getParameter("txtChassis");
        String capacity = r.equest.getParameter("txtCapacity");
        String yearOfMan = r.equest.getParameter("txtYearOfMan");
        Integer yearOfManufacture = 0;
        Integer weight = 0;
        Integer seatCapacity = 0;
        if (!yearOfMan.equals("")) {
            yearOfManufacture = Integer.parseInt(yearOfMan);
        }
        String emptyWeight = r.equest.getParameter("txtWeight");
        if (!emptyWeight.equals("")) {
            weight = Integer.parseInt(emptyWeight);
        }
        String seatCapt = r.equest.getParameter("txtSeatCapacity");
        if (!seatCapt.equals("")) {
            seatCapacity = Integer.parseInt(seatCapt);
        }

        // Get payment information
        Timestamp paidDate = DateUtils.stringToTime(r.equest.getParameter("txtPaidDate"));
        Float amount = Float.parseFloat(r.equest.getParameter("txtAmount"));

        // Call DTO constructor to initial object contain value
        CreateContractDTO dto = new CreateContractDTO(customerCode, contractTypeId, startDate, expiredDate,
                contractFee, plate, brand, modelCode, vehicleType, color, engine, chassis, capacity, yearOfManufacture,
                weight, seatCapacity, paidDate, amount);

        // Call to business object
        StaffBusiness staffBus = new StaffBusiness();
        ContractEntity result = staffBus.createContract(dto);

        if (result != null) {
            // Return Success JSP Page
            ContractEntity createdContract = staffBus.getContractDetail(result.getContractCode());
            r.equest.setAttribute("CREATED", createdContract);
            return new JspPage("staff/create-contract-success.jsp");
        } else {
            msg = "Tạo hợp đồng thất bại, vui lòng thử lại hoặc liên hệ IT";
            r.equest.setAttribute("MESSAGE", msg);
            return new JspPage("staff/message.jsp");
        }
    }

    public ResponseObject postRenew(R r) {
        // Get renew contract information
        String contractCode = r.equest.getParameter("txtContractCode");
        Timestamp expiredDate = DateUtils.stringToTime(r.equest.getParameter("txtExpiredDate"));

        // Get renew payment information
        Timestamp paidDate = DateUtils.stringToTime(r.equest.getParameter("txtPaidDate"));
        Float amount = Float.parseFloat(r.equest.getParameter("txtAmount"));

        // Call to business object
        StaffBusiness staffBus = new StaffBusiness();
        boolean result = staffBus.renewContract(contractCode, expiredDate, paidDate, amount);
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
