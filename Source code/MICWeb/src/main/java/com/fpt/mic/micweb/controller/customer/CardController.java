package com.fpt.mic.micweb.controller.customer;

import com.fpt.mic.micweb.controller.common.AuthController;
import com.fpt.mic.micweb.framework.responses.JspPage;
import com.fpt.mic.micweb.framework.R;
import com.fpt.mic.micweb.framework.responses.RedirectTo;
import com.fpt.mic.micweb.framework.responses.ResponseObject;
import com.fpt.mic.micweb.model.business.CardBusiness;
import com.fpt.mic.micweb.model.business.CustomerBusiness;
import com.fpt.mic.micweb.model.business.PaymentBusiness;
import com.fpt.mic.micweb.model.dto.CheckoutRequestDto;
import com.fpt.mic.micweb.model.dto.UserDto;
import com.fpt.mic.micweb.model.dto.form.NewCardRequestDto;
import com.fpt.mic.micweb.model.entity.ContractEntity;
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
    @Override
    public List<String> getAllowedRoles() {
        return Collections.singletonList(UserDto.ROLE_CUSTOMER);
    }

    public ResponseObject getView(R r) {
        return new JspPage("/customer/card.jsp");
    }

    public ResponseObject getNewCard(R r) {

        String customerCode = ((CustomerEntity) getLoggedInUser()).getCustomerCode();
        String contractCode = r.equest.getParameter("contractCode");
        r.equest.setAttribute("contractCode", contractCode);
        r.equest.setAttribute("customerCode", customerCode);
        r.equest.setAttribute("newCardFee","" +Constants.PaymentFee.NEW_CARD_REQUEST);
        r.equest.setAttribute("transformFee", "" +Constants.PaymentFee.DELIVERY);
        return new JspPage("/customer/new-card-request.jsp");
    }


    public ResponseObject postCreateNewCardRequest(R r) {
        NewCardRequestDto newCardRequestDto = (NewCardRequestDto) r.ead.entity(NewCardRequestDto.class, "request");
        CustomerBusiness customerBusiness = new CustomerBusiness();
        String contractCode;
        // Gọi hàm validate ở đây
        List errors = r.ead.validate(newCardRequestDto);

        // Nếu có lỗi khi validate
        if (errors.size() > 0) {
            // Gửi lỗi về trang JSP
            r.equest.setAttribute("validateErrors", errors);
            r.equest.setAttribute("submitted", newCardRequestDto);
            r.equest.setAttribute("contractCode", newCardRequestDto.getContractCode());
            r.equest.setAttribute("newCardFee","" +Constants.PaymentFee.NEW_CARD_REQUEST);
            r.equest.setAttribute("transformFee", "" +Constants.PaymentFee.DELIVERY);
            // Gửi dữ liệu mà người dùng đã nhập về trang JSP, gán vào biết submitted

            return getNewCard(r);
        }
        String customerCode = ((CustomerEntity) getLoggedInUser()).getCustomerCode();
        newCardRequestDto.setCustomerCode(customerCode);

        CardBusiness cardBusiness = new CardBusiness();
        contractCode = newCardRequestDto.getContractCode();

        String message = "Hợp đồng chưa có thẻ bảo hiểm. Xin vui lòng đợi phát hành";
        if (cardBusiness.getCardByContractIncludeDeactive(contractCode).size() > 0) {
            message = "Bạn đã yêu cầu thẻ mới trước đó. Vui lòng chờ xử lý";
            if (cardBusiness.getCardByContract(contractCode) != null) {
                HttpSession session = r.equest.getSession();
                // thanh toan truc tien thi chuyen ve trang detail + message
                if (newCardRequestDto.getPayment().equalsIgnoreCase("direct")) {
                    String result = cardBusiness.requestNewCard(newCardRequestDto);
                    if (result != null) {
                        r.equest.setAttribute("error", result);
                        new JspPage("public/error.jsp");
                    }
                    session.setAttribute("message", "Yêu cầu thẻ mới thành công. Vui lòng đến trực tiếp để thanh toán");
//                        return new JspPage("customer/contract-detail.jsp");
                    return new RedirectTo("/customer/contract?action=ContractDetail&code=" + contractCode);

                } else {
                    // thanh toan paypal
                    String returnUrl = "public/return.jsp";
                    session.setAttribute("NEW_CARD_DTO", newCardRequestDto);
                    session.setAttribute("CONTRACT_CODE", contractCode);
                    session.setAttribute("SUCCESS_URL", "/customer/card?action=activeNewCardRequest");
                    session.setAttribute("cancel_message", "Bạn đã hủy thanh toán. Xin vui lòng thực hiện lại hoặc đến thanh toán trực tiếp");
                    session.setAttribute("redirectLink", "/customer/contract?action=ContractDetail&code=" + contractCode);
                    float totalAmount = Constants.PaymentFee.NEW_CARD_REQUEST + Constants.PaymentFee.DELIVERY;
                    CheckoutRequestDto checkoutRequest = new CheckoutRequestDto();
                    checkoutRequest.setPaymentrequest_name("Yêu cầu thẻ mới " + newCardRequestDto.getContractCode());
                    checkoutRequest.setPaymentrequest_desc("Yêu cầu thẻ mới " + newCardRequestDto.getContractCode());
                    checkoutRequest.setPaymentrequest_qty("1");
                    checkoutRequest.setPaymentrequest_itemamt(""+totalAmount); // add constant later
                    checkoutRequest.setPaymentrequest_taxamt("0");
                    checkoutRequest.setPaymentrequest_amt(""+totalAmount);
                    checkoutRequest.setCurrencycodetype("USD");
                    checkoutRequest.setPaymenttype("Sale");
                    checkoutRequest.setPaymentrequest_amt_l(""+totalAmount);
                    return new RedirectTo("/public/checkout?action=checkout&checkout=true&" + checkoutRequest.getQueryString());
                }

            }
        }
        errors.add(message);
        r.equest.setAttribute("validateErrors", errors);
        return getNewCard(r);
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
        String result = cardBusiness.requestNewCard(newCardRequestDto);
        if (result != null) {
            r.equest.setAttribute("error", result);
            new JspPage("public/error.jsp");
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
}
