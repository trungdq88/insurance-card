package com.fpt.mic.micweb.controller.staff;

import com.fpt.mic.micweb.controller.common.AuthController;
import com.fpt.mic.micweb.framework.Paginator;
import com.fpt.mic.micweb.framework.R;
import com.fpt.mic.micweb.framework.responses.JspPage;
import com.fpt.mic.micweb.framework.responses.RedirectTo;
import com.fpt.mic.micweb.framework.responses.ResponseObject;
import com.fpt.mic.micweb.model.business.*;
import com.fpt.mic.micweb.model.dto.UserDto;
import com.fpt.mic.micweb.model.dto.form.*;
import com.fpt.mic.micweb.model.entity.*;
import com.fpt.mic.micweb.utils.ConfigUtils;
import com.fpt.mic.micweb.utils.Constants;
import com.fpt.mic.micweb.utils.ContractDetailConfigUtils;

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
    Paginator compensationPaginator = new Paginator("compensation");
    Paginator punishmentPaginator = new Paginator("punishment");
    Paginator accidentPaginator = new Paginator("accident");

    private static String msg = "";
    private static boolean isSuccess;

    @Override
    public List<String> getAllowedRoles() {
        return Collections.singletonList(UserDto.ROLE_STAFF);
    }

    public ResponseObject getView(R r) {

        String status = r.equest.getParameter("status");
        if (status == null) status = "";

        final StaffBusiness staffBus = new StaffBusiness();
        final String finalStatus = status;
        contractPaginator.setGetItemsCallback(new Paginator.IGetItems() {
            @Override
            public List getItems(int offset, int count) {
                return staffBus.getAllContract(finalStatus, offset, count);
            }
        });
        contractPaginator.setGetItemSizeCallback(new Paginator.IGetItemSize() {
            @Override
            public Long getItemSize() {
                return staffBus.getAllContractCount(finalStatus);
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
        final CompensationBusiness compensationBusiness = new CompensationBusiness();
        final PunishmentBusiness punishmentBusiness = new PunishmentBusiness();
        final CustomerBusiness customerBusiness = new CustomerBusiness();
        StaffBusiness staffBus = new StaffBusiness();
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
        List<PaymentEntity> listRenew = staffBus.getRenewsByContractCode(contractDetail.getContractCode());

        // Get contract card instance
        CardBusiness cardBusiness = new CardBusiness();
        List<CardInstanceEntity> listCard = cardBusiness.getCardInstancesIncludeDeactive(contractDetail.getContractCode());

        final String finalContractCode = contractCode;
        // compensation region
        compensationPaginator.setGetItemsCallback(new Paginator.IGetItems() {
            @Override
            public List getItems(int offset, int count) {
                return compensationBusiness.getAllCompensationByContractCode(finalContractCode, offset, count);
            }
        });
        compensationPaginator.setGetItemSizeCallback(new Paginator.IGetItemSize() {
            @Override
            public Long getItemSize() {
                return compensationBusiness.getAllCompensationByContractCodeCount(finalContractCode);
            }
        });
        //
        //punishment region
        punishmentPaginator.setGetItemsCallback(new Paginator.IGetItems() {
            @Override
            public List getItems(int offset, int count) {
                return punishmentBusiness.getAllPunishmentByContractCode(finalContractCode, offset, count);
            }
        });
        punishmentPaginator.setGetItemSizeCallback(new Paginator.IGetItemSize() {
            @Override
            public Long getItemSize() {
                return punishmentBusiness.getAllPunishmentByContractCodeCount(finalContractCode);
            }
        });

        //accident region
        accidentPaginator.setGetItemsCallback(new Paginator.IGetItems() {
            @Override
            public List getItems(int offset, int count) {
                return customerBusiness.getAllAccidentByContractCode(finalContractCode, offset, count);
            }
        });
        accidentPaginator.setGetItemSizeCallback(new Paginator.IGetItemSize() {
            @Override
            public Long getItemSize() {
                return customerBusiness.getAllAccidentByContractCodeCount(finalContractCode);
            }
        });


        r.equest.setAttribute("CUSTOMER", customerDetail);
        r.equest.setAttribute("CONTRACT", contractDetail);
        r.equest.setAttribute("PAYMENT", listPayment);
        r.equest.setAttribute("listRenew", listRenew);

        r.equest.setAttribute("compensationPaginator", compensationPaginator);
        r.equest.setAttribute("punishmentPaginator", punishmentPaginator);
        r.equest.setAttribute("accidentPaginator", accidentPaginator);

        r.equest.setAttribute("CARD", listCard);
        r.equest.setAttribute("CONFIG", new ConfigUtils());
        r.equest.setAttribute("detailConfig", new ContractDetailConfigUtils(contractDetail));

        // Dispatch to JSP page
        return new JspPage("staff/contract/contract-detail.jsp");
    }

    public ResponseObject getCreate(R r) {
        StaffBusiness staffBus = new StaffBusiness();
        List<ContractTypeEntity> listContractType = staffBus.getAllActiveContractType();
        ConfigUtils config = new ConfigUtils();
        r.equest.setAttribute("CONTRACTTYPE", listContractType);
        r.equest.setAttribute("CONFIG", config);
        return new JspPage("staff/contract/create-contract.jsp");
    }

    public ResponseObject getEditVehicle(R r) {
        String contractCode = r.equest.getParameter("code");

        // Receive contractCode from failed validation
        // @see {@link postEditVehicle}
        if (contractCode == null) {
            contractCode = (String) r.equest.getAttribute("contractCode");
        }

        // Call business method
        StaffBusiness staffBusiness = new StaffBusiness();
        ContractEntity contractEntity = staffBusiness.getContractDetail(contractCode);

        // If contract is not exists, show 404 page
        if (contractEntity == null) {
            return new RedirectTo("/error/404");
        }

        // Only check concurrency for the first time
        if (r.equest.getAttribute("submitted") == null) {
            // Save last_modified value for concurrency check
            r.equest.getSession(true).setAttribute(
                    Constants.Session.CONCURRENCY + contractCode, contractEntity.getLastModified());
        }

        r.equest.setAttribute("CONTRACT", contractEntity);

        // Dispatch to JSP page
        return new JspPage("staff/contract/edit-vehicle.jsp");
    }

    public ResponseObject postPreview(R r) {
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

        // Get customer detail
        StaffBusiness staffBusiness = new StaffBusiness();
        CustomerEntity customerDetail = staffBusiness.getCustomerDetail(dto.getCustomerCode());
        ContractTypeEntity contractType = staffBusiness.getContractType(dto.getContractTypeId());
        r.equest.setAttribute("CONTRACT", dto);
        r.equest.setAttribute("CUSTOMER", customerDetail);
        r.equest.setAttribute("TYPE", contractType);
        return new JspPage("staff/contract/create-contract-preview.jsp");
    }

    public ResponseObject postReturnToEdit(R r) {
        // Get contract information
        CreateContractDto dto = (CreateContractDto) r.ead.entity(CreateContractDto.class, "contract");
        r.equest.setAttribute("submitted", dto);
        // Re-call the create page
        return getCreate(r);
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
            isSuccess = false;
            msg = "Tạo hợp đồng thất bại, vui lòng thử lại hoặc liên hệ IT";
            r.equest.setAttribute("MESSAGE", msg);
            r.equest.setAttribute("SUCCESS", isSuccess);
            return new JspPage("staff/message.jsp");
        }
    }

    public ResponseObject postRenew(R r) {
        // Get renew contract information
        RenewContractDto dto = (RenewContractDto) r.ead.entity(RenewContractDto.class, "renew");
        ContractBusiness contractBusiness = new ContractBusiness();
        String contractCode = dto.getContractCode();
        // if contract expired, check if there is any valid contract with this plate number
        if(contractBusiness.getContract(contractCode).getStatus().equalsIgnoreCase(Constants.ContractStatus.EXPIRED)) {
            if(contractBusiness.isExistByPlate(contractBusiness.getContract(contractCode).getPlate())) {
                String activeContractCode = contractBusiness.getActiveContractByPlate(contractBusiness.getContract(contractCode).getPlate()).getContractCode();
                String activeContractLink = r.equest.getScheme() +
                        "://" + r.equest.getServerName() +
                        ":" + r.equest.getServerPort() +
                        r.equest.getContextPath() +
                        "/staff/contract?action=detail&code="+activeContractCode;
                r.equest.setAttribute("result", "Đang có hợp đồng hiệu lực với biển số này: <a href=\"" + activeContractLink + "\">"+activeContractCode+" </a>. Không thể gia hạn!");
                r.equest.setAttribute("contractCode", contractCode);
                return new JspPage("customer/message.jsp");
            }
        }

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

        // Handle request new card
        if (dto.isNewCard()) {
            CardBusiness cardBusiness = new CardBusiness();
            NewCardRequestDto newCardRequestDto = new NewCardRequestDto();
            newCardRequestDto.setContractCode(dto.getContractCode());

            // kiem tra neu hop dong da duoc cap the ( bao gom the cu va moi)
            if (cardBusiness.getCardInstancesIncludeDeactive(dto.getContractCode()).size() > 0) {
                // neu hop dong da dc phat hanh the, kiem tra xem co yeu cau truoc do chua
                if (cardBusiness.getUnresolveNewCardRequest(dto.getContractCode()) == null) {
                    if (cardBusiness.getCardByContract(dto.getContractCode()) != null) {
                        // Send new card request
                        if (dto.isDeliveryNewCard()) {
                            cardBusiness.requestNewCardRequest(newCardRequestDto, true, true);
                        } else {
                            cardBusiness.requestNewCardRequest(newCardRequestDto, false, true);
                        }
                        // Deactivate current card
                        cardBusiness.deactiveCardByContractCode(dto.getContractCode());
                    } else {
                        isSuccess = false;
                        msg = "Hợp đồng này không có thẻ đang hoạt động. Vui lòng xử lý";
                        // Set contract code to request scope. Use it in message page.
                        r.equest.setAttribute("CODE", dto.getContractCode());
                        r.equest.setAttribute("MESSAGE", msg);
                        r.equest.setAttribute("SUCCESS", isSuccess);
                        return new JspPage("staff/message.jsp");
                    }
                } else {
                    isSuccess = false;
                    msg = "Hợp đồng đã yêu cầu thẻ mới trước đó. Vui lòng xử lý";
                    // Set contract code to request scope. Use it in message page.
                    r.equest.setAttribute("CODE", dto.getContractCode());
                    r.equest.setAttribute("MESSAGE", msg);
                    r.equest.setAttribute("SUCCESS", isSuccess);
                    return new JspPage("staff/message.jsp");
                }
            } else {
                isSuccess = false;
                msg = "Hợp đồng chưa có thẻ bảo hiểm. Xin vui lòng phát hành";
                // Set contract code to request scope. Use it in message page.
                r.equest.setAttribute("CODE", dto.getContractCode());
                r.equest.setAttribute("MESSAGE", msg);
                r.equest.setAttribute("SUCCESS", isSuccess);
                return new JspPage("staff/message.jsp");
            }
        }

        // Call to business object
        StaffBusiness staffBus = new StaffBusiness();
        boolean result = staffBus.renewContract(dto, (StaffEntity) getLoggedInUser());

        if (result) {
            isSuccess = true;
            msg = "Đã gia hạn hợp đồng thành công";
        } else {
            isSuccess = false;
            msg = "Gia hạn hợp đồng thất bại";
        }
        // Set contract code to request scope. Use it in message page.
        r.equest.setAttribute("CODE", dto.getContractCode());
        r.equest.setAttribute("MESSAGE", msg);
        r.equest.setAttribute("SUCCESS", isSuccess);
        return new JspPage("staff/message.jsp");
    }

    public ResponseObject postHandleCancelRequest(R r) {
        // Get cancel contract information
        HandleCancelRequestDto dto = (HandleCancelRequestDto) r.ead.entity(HandleCancelRequestDto.class, "handleRequest");

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
        boolean result = false;
        // Decision for this cancel request
        String decision = dto.getDecision();
        if (decision.equalsIgnoreCase("cancelContract")) {
            // Deactivate current card
            CardBusiness cardBusiness = new CardBusiness();
            // If there's a card belongs to this contract
            if (cardBusiness.getCardByContract(dto.getContractCode()) != null) {
                // deactivate it
                cardBusiness.deactiveCardByContractCode(dto.getContractCode());
            }
            // if not, its mean does not have to do anything
            CancelContractDto cancelContractDto = new CancelContractDto();
            cancelContractDto.setContractCode(dto.getContractCode());
            cancelContractDto.setCancelNote(dto.getCancelNote());

            result = staffBus.cancelContract(cancelContractDto);
        } else if (decision.equalsIgnoreCase("rejectRequest")) {
            result = staffBus.rejectCancelContract(dto.getContractCode());
        }

        if (result) {
            isSuccess = true;
            msg = "Đã giải quyết yêu cầu hủy hợp đồng thành công";
        } else {
            isSuccess = false;
            msg = "Giải quyết yêu cầu hủy hợp đồng thất bại";
        }
        // Set contract code to request scope. Use it in message page.
        r.equest.setAttribute("CODE", dto.getContractCode());
        r.equest.setAttribute("MESSAGE", msg);
        r.equest.setAttribute("SUCCESS", isSuccess);
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

        // Deactivate current card
        CardBusiness cardBusiness = new CardBusiness();
        // If there's a card belongs to this contract
        if (cardBusiness.getCardByContract(dto.getContractCode()) != null) {
            // deactivate it
            cardBusiness.deactiveCardByContractCode(dto.getContractCode());
        }
        // if not, its mean does not have to do anything

        // Call to business object
        StaffBusiness staffBus = new StaffBusiness();
        boolean result = staffBus.cancelContract(dto);

        if (result) {
            isSuccess = true;
            msg = "Đã hủy hợp đồng thành công";
        } else {
            isSuccess = false;
            msg = "Hủy hợp đồng thất bại";
        }
        // Set contract code to request scope. Use it in message page.
        r.equest.setAttribute("CODE", dto.getContractCode());
        r.equest.setAttribute("MESSAGE", msg);
        r.equest.setAttribute("SUCCESS", isSuccess);
        return new JspPage("staff/message.jsp");
    }

    public ResponseObject postCreatePayment(R r) {
        // Get payment information
        CreatePaymentDto dto = (CreatePaymentDto) r.ead.entity(CreatePaymentDto.class, "createPayment");
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
        boolean result = staffBus.createPayment(dto, (StaffEntity) getLoggedInUser());
        if (result) {
            isSuccess = true;
            msg = "Đã thêm thông tin thanh toán thành công";
        } else {
            isSuccess = false;
            msg = "Thêm thông tin thanh toán thất bại";
        }
        // Set contract code to request scope. Use it in message page.
        r.equest.setAttribute("CODE", dto.getContractCode());
        r.equest.setAttribute("MESSAGE", msg);
        r.equest.setAttribute("SUCCESS", isSuccess);
        return new JspPage("staff/message.jsp");
    }

    public ResponseObject postCompletePayment(R r) {
        // Get payment information
        CompletePaymentDto dto = (CompletePaymentDto) r.ead.entity(CompletePaymentDto.class, "completePayment");

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
        ContractBusiness contractBusiness = new ContractBusiness();
        if (contractBusiness.isExistByPlate(contractBusiness.getContract(dto.getContractCode()).getPlate())) {
            String activeContractCode = contractBusiness.getActiveContractByPlate(contractBusiness.getContract(dto.getContractCode()).getPlate()).getContractCode();
            String activeContractLink = r.equest.getScheme() +
                    "://" + r.equest.getServerName() +
                    ":" + r.equest.getServerPort() +
                    r.equest.getContextPath() +
                    "/staff/contract?action=detail&code="+activeContractCode;
            msg = "Đang có hợp đồng hiệu lực với biển số này: <a href=\"" + activeContractLink + "\">"+activeContractCode+" </a>. Không thể thanh toán!";
            isSuccess = false;
        } else {
            // If the code reached this line that means there is no validation errors
            // Call business method
            StaffBusiness staffBus = new StaffBusiness();
            boolean result = staffBus.completePayment(dto, (StaffEntity) getLoggedInUser());

            if (result) {
                isSuccess = true;
                msg = "Đã hoàn tất thông tin thanh toán thành công";
            } else {
                isSuccess = false;
                msg = "Thêm thông tin thanh toán thất bại";
            }
        }
        // Set contract code to request scope. Use it in message page.
        r.equest.setAttribute("CODE", dto.getContractCode());
        r.equest.setAttribute("MESSAGE", msg);
        r.equest.setAttribute("SUCCESS", isSuccess);
        return new JspPage("staff/message.jsp");
    }

    public ResponseObject postEditVehicle(R r) {
        // Get edit vehicle information
        EditVehicleDto dto = (EditVehicleDto) r.ead.entity(EditVehicleDto.class, "edit");

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
            r.equest.setAttribute("submitted", dto);
            return getEditVehicle(r);
        }
        // If the code reached this line that means there is no validation errors

        // Call business method
        StaffBusiness staffBus = new StaffBusiness();
        boolean result = staffBus.editVehicleInfo(dto);

        if (result) {
            isSuccess = true;
            msg = "Đã sửa thông tin xe cơ giới thành công";
        } else {
            isSuccess = false;
            msg = "Sửa thông tin xe cơ giới thất bại";
        }
        // Set contract code to request scope. Use it in message page.
        r.equest.setAttribute("CODE", dto.getContractCode());
        r.equest.setAttribute("MESSAGE", msg);
        r.equest.setAttribute("SUCCESS", isSuccess);
        return new JspPage("staff/message.jsp");
    }
}