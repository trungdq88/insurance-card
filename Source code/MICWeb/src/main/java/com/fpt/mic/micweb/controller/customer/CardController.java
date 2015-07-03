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
import com.fpt.mic.micweb.model.entity.CustomerEntity;

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
        return new JspPage("/customer/new-card-request.jsp");
    }

    public ResponseObject postCreateNewCardRequest(R r) {
        NewCardRequestDto newCardRequestDto = (NewCardRequestDto) r.ead.entity(NewCardRequestDto.class, "request");
        CustomerBusiness customerBusiness = new CustomerBusiness();
        String customerCode = ((CustomerEntity) getLoggedInUser()).getCustomerCode();
        newCardRequestDto.setCustomerCode(customerCode);

        CardBusiness cardBusiness = new CardBusiness();
        String contractCode = newCardRequestDto.getContractCode();
        String message = "Hợp đồng chưa có thẻ bảo hiểm. Xin vui lòng đợi phát hành";
        if (cardBusiness.getCardByContractIncludeDeactive(contractCode).size() > 0) {
            message = "Bạn đã yêu cầu thẻ mới trước đó. Vui lòng chờ xử lý";
            if (cardBusiness.getCardByContract(contractCode) != null) {
                // thanh toan
                String returnUrl = "public/return.jsp";
                HttpSession session = r.equest.getSession();
                session.setAttribute("NEW_CARD_DTO", newCardRequestDto);
                session.setAttribute("CONTRACT_CODE", contractCode);
                session.setAttribute("SUCCESS_URL", "/customer/card?action=activeNewCardRequest");
                session.setAttribute("cancel_message", "Bạn đã hủy thanh toán. Xin vui lòng thực hiện lại hoặc đến thanh toán trực tiếp");
                session.setAttribute("redirectLink", "/customer/contract?action=ContractDetail&code=" + contractCode);

                CheckoutRequestDto checkoutRequest = new CheckoutRequestDto();
                checkoutRequest.setPaymentrequest_name("Yêu cầu thẻ mới " + newCardRequestDto.getContractCode());
                checkoutRequest.setPaymentrequest_desc("Yêu cầu thẻ mới " + newCardRequestDto.getContractCode());
                checkoutRequest.setPaymentrequest_qty("1");
                checkoutRequest.setPaymentrequest_itemamt("50000");
                checkoutRequest.setPaymentrequest_taxamt("0");
                checkoutRequest.setPaymentrequest_amt("50000");
                checkoutRequest.setCurrencycodetype("USD");
                checkoutRequest.setPaymenttype("Sale");
                checkoutRequest.setPaymentrequest_amt_l("50000");
                return new RedirectTo("/public/checkout?action=checkout&checkout=true&" + checkoutRequest.getQueryString());

            }
        }

        r.equest.setAttribute("error",message);
        return new JspPage("public/error.jsp");
    }

    public ResponseObject getActiveNewCardRequest(R r) {
        String returnUrl = "public/return.jsp";
        HttpSession session = r.equest.getSession(false);
        if (session == null) {
            return new RedirectTo("error/404");
        }
        Map<String, String> results = new HashMap<String, String>();
        results.putAll((Map<String, String>) session.getAttribute("RESULT"));

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
