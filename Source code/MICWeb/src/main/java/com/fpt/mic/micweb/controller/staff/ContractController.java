package com.fpt.mic.micweb.controller.staff;

import com.fpt.mic.micweb.controller.common.AuthController;
import com.fpt.mic.micweb.framework.R;
import com.fpt.mic.micweb.framework.responses.JspPage;
import com.fpt.mic.micweb.framework.responses.RedirectTo;
import com.fpt.mic.micweb.framework.responses.ResponseObject;
import com.fpt.mic.micweb.model.business.StaffBusiness;
import com.fpt.mic.micweb.model.dto.form.CancelContractDto;
import com.fpt.mic.micweb.model.dto.form.CreateContractDto;
import com.fpt.mic.micweb.model.dto.form.RenewContractDto;
import com.fpt.mic.micweb.model.entity.ContractEntity;
import com.fpt.mic.micweb.model.entity.ContractTypeEntity;
import com.fpt.mic.micweb.model.entity.CustomerEntity;
import com.fpt.mic.micweb.model.entity.PaymentEntity;

import javax.servlet.annotation.WebServlet;
import java.util.List;

/**
 * Created by dinhquangtrung on 5/23/15.
 */
@WebServlet(name = "ContractController", urlPatterns = {"/staff/contract"})
public class ContractController extends AuthController {

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

        // If contract is not exists, show 404 page
        if (contractDetail == null) {
            return new RedirectTo("/error/404");
        }

        // Get customer detail
        CustomerEntity customerDetail = staffBus.getCustomerDetail(contractDetail.getCustomerCode());
        // Get payment detail
        List<PaymentEntity> listPayment = staffBus.getPaymentByContractCode(contractCode);

        r.equest.setAttribute("CUSTOMER", customerDetail);
        r.equest.setAttribute("CONTRACT", contractDetail);
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
        CreateContractDto dto = (CreateContractDto) r.ead.entity(CreateContractDto.class, "contract");
        List errors = r.ead.validate(dto);

        // If there is validation errors
        if (errors.size() > 0) {
            // Send error messages to JSP page
            r.equest.setAttribute("validateErrors", errors);
            // Send submitted data to JSP page
            r.equest.setAttribute("submitted", dto);
            // Re-call the create page
            return getCreate(r);
        }

        // If the code reached this line that means there is no validation errors

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
        RenewContractDto dto = (RenewContractDto) r.ead.entity(RenewContractDto.class, "renew");

        // Call to business object
        StaffBusiness staffBus = new StaffBusiness();
        boolean result = staffBus.renewContract(dto);

        if (result) {
            msg = "Đã gia hạn hợp đồng thành công";
        } else {
            msg = "Gia hạn hợp đồng thất bại";
        }
        // Set contract code to request scope. Use it in message page.
        r.equest.setAttribute("CODE", dto.getContractCode());
        r.equest.setAttribute("MESSAGE", msg);
        return new JspPage("staff/message.jsp");
    }

    public ResponseObject postCancel(R r) {
        // Get cancel contract information
        CancelContractDto dto = (CancelContractDto) r.ead.entity(CancelContractDto.class, "cancel");

        // Call to business object
        StaffBusiness staffBus = new StaffBusiness();
        boolean result = staffBus.cancelContract(dto);

        if (result) {
            msg = "Đã hủy hợp đồng thành công";
        } else {
            msg = "Hủy hợp đồng thất bại";
        }
        // Set contract code to request scope. Use it in message page.
        r.equest.setAttribute("CODE", dto.getContractCode());
        r.equest.setAttribute("MESSAGE", msg);
        return new JspPage("staff/message.jsp");
    }
}
