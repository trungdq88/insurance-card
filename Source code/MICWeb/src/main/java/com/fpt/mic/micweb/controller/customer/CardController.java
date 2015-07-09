package com.fpt.mic.micweb.controller.customer;

import com.fpt.mic.micweb.controller.common.AuthController;
import com.fpt.mic.micweb.framework.Paginator;
import com.fpt.mic.micweb.framework.responses.JspPage;
import com.fpt.mic.micweb.framework.R;
import com.fpt.mic.micweb.framework.responses.RedirectTo;
import com.fpt.mic.micweb.framework.responses.ResponseObject;
import com.fpt.mic.micweb.model.business.CardBusiness;
import com.fpt.mic.micweb.model.business.ContractBusiness;
import com.fpt.mic.micweb.model.business.CustomerBusiness;
import com.fpt.mic.micweb.model.business.PaymentBusiness;
import com.fpt.mic.micweb.model.dto.CheckoutRequestDto;
import com.fpt.mic.micweb.model.dto.UserDto;
import com.fpt.mic.micweb.model.dto.form.NewCardRequestDto;
import com.fpt.mic.micweb.model.entity.CardEntity;
import com.fpt.mic.micweb.model.entity.CardInstanceEntity;
import com.fpt.mic.micweb.model.entity.CustomerEntity;
import com.fpt.mic.micweb.utils.Constants;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dinhquangtrung on 5/24/15.
 */
@WebServlet(name = "CustomerCardController", urlPatterns = "/customer/card")
public class CardController extends AuthController {
    Paginator requestPaginator = new Paginator();
    Paginator cardPaginator = new Paginator();
    /* Card acess log pagination */
    Paginator calPaginator = new Paginator();
    @Override
    public List<String> getAllowedRoles() {
        return Collections.singletonList(UserDto.ROLE_CUSTOMER);
    }

    public ResponseObject getView(R r) {
        final CardBusiness cardBusiness = new CardBusiness();
        final String customerCode = ((CustomerEntity) getLoggedInUser()).getCustomerCode();
        cardPaginator.setGetItemsCallback(new Paginator.IGetItems() {
            @Override
            public List getItems(int offset, int count) {
                return cardBusiness.getIssuedCard(customerCode,offset, count);
            }
        });
        cardPaginator.setGetItemSizeCallback(new Paginator.IGetItemSize() {
            @Override
            public Long getItemSize() {
                return cardBusiness.getIssuedCardCount(customerCode);
            }
        });
        r.equest.setAttribute("cardPaginator", cardPaginator);

        return new JspPage("/customer/cards.jsp");
    }

    public ResponseObject getDetail(R r) {
        final String cardId = r.equest.getParameter("cardId");

        // Call to business
        final CardBusiness cardBusiness = new CardBusiness();
        CardInstanceEntity cardEntity = cardBusiness.getLastActiveCardInnstance(cardId);

        calPaginator.setGetItemsCallback(new Paginator.IGetItems() {
            @Override
            public List getItems(int offset, int count) {
                return cardBusiness.getCardAccessLog(cardId, offset, count);
            }
        });
        calPaginator.setGetItemSizeCallback(new Paginator.IGetItemSize() {
            @Override
            public Long getItemSize() {
                return cardBusiness.getCardAccessLogCount(cardId);
            }
        });
        Map<Integer, String> newCardMappingRequest = new HashMap();
        newCardMappingRequest = cardBusiness.getMappingWithNewCardRequest();
        Map<String,Integer> newOldCardMappingRequest = new HashMap<String, Integer>();
        newOldCardMappingRequest = cardBusiness.getMappingOldCardIdAndNewCardRequestId();
        r.equest.setAttribute("map", newCardMappingRequest);
        r.equest.setAttribute("mapOldCardAndRequestId", newOldCardMappingRequest);
        r.equest.setAttribute("calPaginator", calPaginator);
        r.equest.setAttribute("CARD", cardEntity);
        return new JspPage("customer/card-detail.jsp");
    }

    public ResponseObject getNewCard(R r) {

        String customerCode = ((CustomerEntity) getLoggedInUser()).getCustomerCode();
        String contractCode = r.equest.getParameter("contractCode");
        r.equest.setAttribute("contractCode", contractCode);
        r.equest.setAttribute("customerCode", customerCode);
        r.equest.setAttribute("newCardFee", "" + Constants.PaymentFee.NEW_CARD_REQUEST_FEE);
        r.equest.setAttribute("transformFee", "" + Constants.PaymentFee.DELIVERY_FEE);
        return new JspPage("/customer/new-card-request.jsp");
    }


    public ResponseObject postCreateNewCardRequest(R r) {
        NewCardRequestDto newCardRequestDto = (NewCardRequestDto) r.ead.entity(NewCardRequestDto.class, "request");
        String contractCode;
        // Gọi hàm validate ở đây
        List errors = r.ead.validate(newCardRequestDto);

        // Nếu có lỗi khi validate
        if (errors.size() > 0) {
            // Gửi lỗi về trang JSP
            r.equest.setAttribute("validateErrors", errors);
            r.equest.setAttribute("submitted", newCardRequestDto);
            r.equest.setAttribute("contractCode", newCardRequestDto.getContractCode());
            r.equest.setAttribute("newCardFee", "" + Constants.PaymentFee.NEW_CARD_REQUEST_FEE);
            r.equest.setAttribute("transformFee", "" + Constants.PaymentFee.DELIVERY_FEE);
            // Gửi dữ liệu mà người dùng đã nhập về trang JSP, gán vào biết submitted

            return getNewCard(r);
        }
        String customerCode = ((CustomerEntity) getLoggedInUser()).getCustomerCode();
        newCardRequestDto.setCustomerCode(customerCode);

        CardBusiness cardBusiness = new CardBusiness();
        contractCode = newCardRequestDto.getContractCode();

        String message;
        // kiem tra neu hop dong da duoc cap the ( bao gom the cu va moi)
        if (cardBusiness.getCardInstancesIncludeDeactive(contractCode).size() > 0) {

            // neu hop dong da dc phat hanh the, kiem tra xem co yeu cau truoc do chua
            if (cardBusiness.isNewCardRequested(contractCode) == false) {
                // neu chua thi tien hanh thanh toan
                HttpSession session = r.equest.getSession();
                // thanh toan truc tiep
                if (newCardRequestDto.getPayment().equalsIgnoreCase("direct")) {
                    // gui yeu cau the moi
                    if(cardBusiness.requestNewCardRequest(newCardRequestDto, false, false) == false){
                        message = "Có lỗi xảy ra. Xin thử lại";
                        r.equest.setAttribute("result", message);
                        r.equest.setAttribute("contractCode",newCardRequestDto.getContractCode());
                        new JspPage("customer/message.jsp");
                    }
                    // huy the cu neu yeu cau
                    if (newCardRequestDto.isDeactiveCardRequested() == true){
                        cardBusiness.deactiveCardByContractCode(newCardRequestDto.getContractCode());
                        ContractBusiness contractBusiness = new ContractBusiness();
                        // cap nhat contract status: no card
                        contractBusiness.updateContractStatus(newCardRequestDto.getContractCode(), Constants.ContractStatus.NO_CARD);
                    }

                    message = "Yêu cầu thẻ mới thành công. Vui lòng đến trực tiếp để thanh toán";
                    r.equest.setAttribute("info", message);
                    r.equest.setAttribute("contractCode",newCardRequestDto.getContractCode());
                    return new JspPage("customer/message.jsp");

                } else {
                    // thanh toan paypal
                    session.setAttribute("NEW_CARD_DTO", newCardRequestDto);
                    session.setAttribute("CONTRACT_CODE", contractCode);
                    session.setAttribute("SUCCESS_URL", "/customer/card?action=activeNewCardRequest");
                    session.setAttribute("cancel_message", "Bạn đã hủy thanh toán. Xin vui lòng thực hiện lại hoặc đến thanh toán trực tiếp");
                    session.setAttribute("redirectLink", "/customer/contract?action=detail&code=" + contractCode);
                    float totalAmount;
                    // neu yeu cau delivery thi + them phi
                    if(newCardRequestDto.isDeliveryRequested()){
                        totalAmount = Constants.PaymentFee.NEW_CARD_REQUEST_FEE + Constants.PaymentFee.DELIVERY_FEE;
                    } else {
                        totalAmount = Constants.PaymentFee.NEW_CARD_REQUEST_FEE;
                    }
                    CheckoutRequestDto checkoutRequest = new CheckoutRequestDto();
                    checkoutRequest.setPaymentrequest_name("Yêu cầu thẻ mới " + newCardRequestDto.getContractCode());
                    checkoutRequest.setPaymentrequest_desc("Yêu cầu thẻ mới " + newCardRequestDto.getContractCode());
                    checkoutRequest.setPaymentrequest_qty("1");
                    checkoutRequest.setPaymentrequest_itemamt("" + totalAmount);
                    checkoutRequest.setPaymentrequest_taxamt("0");
                    checkoutRequest.setPaymentrequest_amt("" + totalAmount);
                    checkoutRequest.setCurrencycodetype("USD");
                    checkoutRequest.setPaymenttype("Sale");
                    checkoutRequest.setPaymentrequest_amt_l("" + totalAmount);
                    return new RedirectTo("/public/checkout?action=checkout&checkout=true&" + checkoutRequest.getQueryString());
                }

            } else {
                message = "Bạn đã yêu cầu thẻ mới trước đó. Vui lòng chờ xử lý";
            }
        } else {
            message = "Hợp đồng chưa có thẻ bảo hiểm. Xin vui lòng đợi phát hành";
        }

        r.equest.setAttribute("result", message);
        r.equest.setAttribute("contractCode", newCardRequestDto.getContractCode());
        return new JspPage("customer/message.jsp");
    }

    public ResponseObject getActiveNewCardRequest(R r) {
        String returnUrl = "public/return.jsp";
        HttpSession session = r.equest.getSession(false);
        if (session == null) {
            return new RedirectTo("error/404");
        }
        Map<String, String> results = new HashMap<String, String>();
        results.putAll((Map<String, String>) session.getAttribute("RESULT"));
        NewCardRequestDto newCardRequestDto = (NewCardRequestDto) session.getAttribute("NEW_CARD_DTO");
        CardBusiness cardBusiness = new CardBusiness();
        String message;
        if(cardBusiness.requestNewCardRequest(newCardRequestDto,newCardRequestDto.isDeliveryRequested(),true) == false){
            message = "Có lỗi xảy ra. Xin thử lại";
            r.equest.setAttribute("result", message);
            r.equest.setAttribute("contractCode",newCardRequestDto.getContractCode());
            return new JspPage("customer/message.jsp");
        }
        // huy the cu neu yeu cau
        if (newCardRequestDto.isDeactiveCardRequested() == true){
            cardBusiness.deactiveCardByContractCode(newCardRequestDto.getContractCode());
            ContractBusiness contractBusiness = new ContractBusiness();
            // cap nhat contract status: no card
            contractBusiness.updateContractStatus(newCardRequestDto.getContractCode(),Constants.ContractStatus.NO_CARD);

        }


        r.equest.setAttribute("result", results);
        r.equest.setAttribute("ack", (String) session.getAttribute("ACK"));
        Float amount = Float.parseFloat((String) session.getAttribute("amountVND"));
        r.equest.setAttribute("amountVND", amount);
        r.equest.setAttribute("redirectLink", (String) session.getAttribute("redirectLink"));
        String contractCode = (String) session.getAttribute("CONTRACT_CODE");
        String paypalTransId = results.get("PAYMENTINFO_0_TRANSACTIONID");
        String paymentMethod = "PayPal";
        String paymentContent = (String) session.getAttribute("descVN");

        PaymentBusiness paymentBusiness = new PaymentBusiness();
        paymentBusiness.updateNewCardRequestPayment(contractCode, paymentMethod, paymentContent, amount, paypalTransId);
        // TODO notify new card request
        session.removeAttribute("RESULT");
        session.removeAttribute("CONTRACT_CODE");
        session.removeAttribute("amountVND");
        session.removeAttribute("ACK");
        session.removeAttribute("SUCCESS_URL");
        session.removeAttribute("EXPRESS_MARK");
        session.removeAttribute("payer_id");
        session.removeAttribute("checkoutDetails");
        session.removeAttribute("checkout");
        session.removeAttribute("TOKEN");
        session.removeAttribute("NEW_CARD_DTO");
        session.removeAttribute("cancel_message");
        r.equest.setAttribute("message", "Thanh toán thành công");
        return new JspPage(returnUrl);
    }

    public ResponseObject getViewNewCardRequests(R r) {
        final CustomerBusiness customerBusiness = new CustomerBusiness();
        final String customerCode = ((CustomerEntity) getLoggedInUser()).getCustomerCode();
        requestPaginator.setGetItemsCallback(new Paginator.IGetItems() {
            @Override
            public List getItems(int offset, int count) {
                return customerBusiness.getOnePageNewCardRequest(customerCode,offset, count);
            }
        });
        requestPaginator.setGetItemSizeCallback(new Paginator.IGetItemSize() {
            @Override
            public Long getItemSize() {
                return customerBusiness.getAllNewCardRequestCount(customerCode);
            }
        });
        CardBusiness cardBusiness = new CardBusiness();
        Map<Integer,String> newCardMapping = new HashMap();
        newCardMapping = cardBusiness.getMappingWithNewCardRequest();
        r.equest.setAttribute("map", newCardMapping);
        Map<String,String> newCardMappingContract = new HashMap();
        newCardMappingContract = cardBusiness.getMappingWithContract();
        r.equest.setAttribute("mapCardContract",newCardMappingContract);
        r.equest.setAttribute("requestPaginator", requestPaginator);
        r.equest.setAttribute("unresolvedRequestCount",customerBusiness.getUnresolvedNewCardRequestCount(customerCode));
        return new JspPage("customer/view-new-card-requests.jsp");
    }
}
