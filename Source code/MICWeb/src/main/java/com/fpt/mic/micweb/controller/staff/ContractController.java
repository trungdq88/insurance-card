package com.fpt.mic.micweb.controller.staff;

import com.fpt.mic.micweb.controller.common.AuthController;
import com.fpt.mic.micweb.framework.Paginator;
import com.fpt.mic.micweb.framework.R;
import com.fpt.mic.micweb.framework.responses.JspPage;
import com.fpt.mic.micweb.framework.responses.RedirectTo;
import com.fpt.mic.micweb.framework.responses.ResponseObject;
import com.fpt.mic.micweb.model.business.AccidentBusiness;
import com.fpt.mic.micweb.model.business.CompensationBusiness;
import com.fpt.mic.micweb.model.business.ContractBusiness;
import com.fpt.mic.micweb.model.business.StaffBusiness;
import com.fpt.mic.micweb.model.dto.UserDto;
import com.fpt.mic.micweb.model.dto.form.CancelContractDto;
import com.fpt.mic.micweb.model.dto.form.CompletePaymentDto;
import com.fpt.mic.micweb.model.dto.form.CreateContractDto;
import com.fpt.mic.micweb.model.dto.form.RenewContractDto;
import com.fpt.mic.micweb.model.entity.*;
import com.fpt.mic.micweb.utils.ConfigUtils;
import com.fpt.mic.micweb.utils.Constants;

import javax.servlet.annotation.WebServlet;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;

/**
 * Created by dinhquangtrung on 5/23/15.
 */
@WebServlet(name = "ContractController", urlPatterns = {"/staff/contract"})
public class ContractController extends AuthController {
    /**
     * Paginator for contract
     */
    Paginator contractPaginator = new Paginator();

    @Override
    public List<String> getAllowedRoles() {
        return Collections.singletonList(UserDto.ROLE_STAFF);
    }

    private static String msg = "";

    public ResponseObject getView(R r) {
        final StaffBusiness staffBus = new StaffBusiness();
        contractPaginator.setGetItemsCallback(new Paginator.IGetItems() {
            @Override
            public List getItems(int offset, int count) {
                return staffBus.getAllContract(offset, count);
            }
        });
        contractPaginator.setGetItemSizeCallback(new Paginator.IGetItemSize() {
            @Override
            public Long getItemSize() {
                return staffBus.getAllContractCount();
            }
        });
        r.equest.setAttribute("contractPaginator", contractPaginator);
        return new JspPage("staff/contract/contracts.jsp");
    }

    public ResponseObject getSearch(R r) {
        String keyword = r.equest.getParameter("keyword");
        if (keyword == null) {
            keyword = "";
        }
        keyword = keyword.trim();

        final ContractBusiness contractBusiness = new ContractBusiness();

        final String finalKeyword = keyword;
        contractPaginator.setGetItemsCallback(new Paginator.IGetItems() {
            @Override
            public List getItems(int offset, int count) {
                return contractBusiness.searchContract(finalKeyword, offset, count);
            }
        });
        contractPaginator.setGetItemSizeCallback(new Paginator.IGetItemSize() {
            @Override
            public Long getItemSize() {
                return contractBusiness.searchContractCount(finalKeyword);
            }
        });

        r.equest.setAttribute("contractPaginator", contractPaginator);
        return new JspPage("staff/contract/contracts.jsp");
    }

    public ResponseObject getDetail(R r) {
        StaffBusiness staffBus = new StaffBusiness();
        CompensationBusiness compenBus = new CompensationBusiness();
        AccidentBusiness accidentBusiness = new AccidentBusiness();
        String contractCode = r.equest.getParameter("code");

        // Receive contractCode from failed validation
        // @see {@link postRenew, postCancel}
        if (contractCode == null) {
            contractCode = (String) r.equest.getAttribute("contractCode");
        }

        // Get contract detail
        ContractEntity contractDetail = staffBus.getContractDetail(contractCode);

        // If contract is not exists, show 404 page
        if (contractDetail == null) {
            return new RedirectTo("/error/404");
        }

        // Save last_modified value for concurrency check
        r.equest.getSession(true).setAttribute(
                Constants.Session.CONCURRENCY + contractCode, contractDetail.getLastModified());

        // Get customer detail
        CustomerEntity customerDetail = staffBus.getCustomerDetail(contractDetail.getCustomerCode());
        // Get contract payment
        List<PaymentEntity> listPayment = staffBus.getPaymentByContractCode(contractDetail.getContractCode());
        // Get contract compensation
        List<CompensationEntity> listCompensation =
                compenBus.getCompensationByContractCode(contractDetail.getContractCode());
        // Get contract accident
        List<AccidentEntity> listAccident =
                accidentBusiness.getAccidentByContractCode(contractDetail.getContractCode());
        // Get contract punishment
        ConfigUtils config = new ConfigUtils();

        r.equest.setAttribute("CUSTOMER", customerDetail);
        r.equest.setAttribute("CONTRACT", contractDetail);
        r.equest.setAttribute("PAYMENT", listPayment);
        r.equest.setAttribute("COMPENSATION", listCompensation);
        r.equest.setAttribute("ACCIDENT", listAccident);
        r.equest.setAttribute("CONFIG", config);

        // Dispatch to JSP page
        return new JspPage("staff/contract/contract-detail.jsp");
    }

    public ResponseObject getCreate(R r) {
        StaffBusiness staffBus = new StaffBusiness();
        List<ContractTypeEntity> listContractType = staffBus.getAllContractType();
        ConfigUtils config = new ConfigUtils();
        r.equest.setAttribute("CONTRACTTYPE", listContractType);
        r.equest.setAttribute("CONFIG", config);
        return new JspPage("staff/contract/create-contract.jsp");
    }

    public ResponseObject getPreview(R r) {
        return new JspPage("staff/contract/create-contract-preview.jsp");
    }

    public ResponseObject getSuccess(R r) {
        return new JspPage("staff/contract/create-contract-success.jsp");
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
        ContractEntity result = staffBus.createContract(dto, (StaffEntity) getLoggedInUser());

        if (result != null) {
            // Get created contract information to display success page
            ContractEntity createdContract = staffBus.getContractDetail(result.getContractCode());
            r.equest.setAttribute("CONTRACT", createdContract);
            // Return Success JSP Page
            return new JspPage("staff/contract/create-contract-success.jsp");
        } else {
            msg = "Tạo hợp đồng thất bại, vui lòng thử lại hoặc liên hệ IT";
            r.equest.setAttribute("MESSAGE", msg);
            return new JspPage("staff/message.jsp");
        }
    }

    public ResponseObject postRenew(R r) {
        // Get renew contract information
        RenewContractDto dto = (RenewContractDto) r.ead.entity(RenewContractDto.class, "renew");

        // Get concurrency data
        Timestamp lastModified = (Timestamp) r.equest.getSession(true).getAttribute(
                Constants.Session.CONCURRENCY + dto.getContractCode());
        dto.setLastModified(lastModified);

        List errors = r.ead.validate(dto);

        // If there is validation errors
        if (errors.size() > 0) {
            // Send error messages to JSP page
            r.equest.setAttribute("validateErrors", errors);
            // This is a form in a popup, we don't need to display data again since
            // the popup will not automatically open when the page is reloaded
            // r.equest.setAttribute("submitted", dto);
            // Re-call the contract detail page
            r.equest.setAttribute("contractCode", dto.getContractCode());
            return getDetail(r);
        }

        // If the code reached this line that means there is no validation errors

        // Call to business object
        StaffBusiness staffBus = new StaffBusiness();
        boolean result = staffBus.renewContract(dto, (StaffEntity) getLoggedInUser());

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

        // Get concurrency data
        Timestamp lastModified = (Timestamp) r.equest.getSession(true).getAttribute(
                Constants.Session.CONCURRENCY + dto.getContractCode());
        dto.setLastModified(lastModified);

        List errors = r.ead.validate(dto);

        // If there is validation errors
        if (errors.size() > 0) {
            // Send error messages to JSP page
            r.equest.setAttribute("validateErrors", errors);
            // This is a form in a popup, we don't need to display data again since
            // the popup will not automatically open when the page is reloaded
            // r.equest.setAttribute("submitted", dto);
            // Re-call the contract detail page
            r.equest.setAttribute("contractCode", dto.getContractCode());
            return getDetail(r);
        }

        // If the code reached this line that means there is no validation errors

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

    public ResponseObject postCompletePayment(R r) {
        // Get payment information
        CompletePaymentDto dto = (CompletePaymentDto) r.ead.entity(CompletePaymentDto.class, "payment");

        // Get concurrency data
        Timestamp lastModified = (Timestamp) r.equest.getSession(true).getAttribute(
                Constants.Session.CONCURRENCY + dto.getContractCode());
        dto.setLastModified(lastModified);
        System.out.println("postCompletePayment"+dto.getContractCode());


        List errors = r.ead.validate(dto);
        // If there is validation errors
        if (errors.size() > 0) {
            // Send error messages to JSP page
            r.equest.setAttribute("validateErrors", errors);
            // This is a form in a popup, we don't need to display data again since
            // the popup will not automatically open when the page is reloaded
            // r.equest.setAttribute("submitted", dto);
            // Re-call the contract detail page
            r.equest.setAttribute("contractCode", dto.getContractCode());
            return getDetail(r);
        }
        // If the code reached this line that means there is no validation errors

        // Call business method
        StaffBusiness staffBus = new StaffBusiness();
        boolean result = staffBus.completePayment(dto, (StaffEntity) getLoggedInUser());

        if (result) {
            msg = "Đã hoàn tất thông tin thanh toán thành công";
        } else {
            msg = "Thêm thông tin thanh toán thất bại";
        }
        // Set contract code to request scope. Use it in message page.
        r.equest.setAttribute("CODE", dto.getContractCode());
        r.equest.setAttribute("MESSAGE", msg);
        return new JspPage("staff/message.jsp");
    }
}
