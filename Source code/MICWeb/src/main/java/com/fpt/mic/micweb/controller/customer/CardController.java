package com.fpt.mic.micweb.controller.customer;

import com.fpt.mic.micweb.controller.common.AuthController;
import com.fpt.mic.micweb.framework.responses.JspPage;
import com.fpt.mic.micweb.framework.R;
import com.fpt.mic.micweb.framework.responses.RedirectTo;
import com.fpt.mic.micweb.framework.responses.ResponseObject;
import com.fpt.mic.micweb.model.business.CardBusiness;
import com.fpt.mic.micweb.model.business.CustomerBusiness;
import com.fpt.mic.micweb.model.dto.CheckoutRequestDto;
import com.fpt.mic.micweb.model.dto.UserDto;
import com.fpt.mic.micweb.model.dto.form.NewCardRequestDto;
import com.fpt.mic.micweb.model.entity.CustomerEntity;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;

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
    public ResponseObject postCreateNewCardRequest(R r){
        NewCardRequestDto newCardRequestDto =(NewCardRequestDto) r.ead.entity(NewCardRequestDto.class,"request");
        CustomerBusiness customerBusiness = new CustomerBusiness();
        String customerCode = ((CustomerEntity) getLoggedInUser()).getCustomerCode();
        newCardRequestDto.setCustomerCode(customerCode);

        CardBusiness cardBusiness = new CardBusiness();
        String contractCode = newCardRequestDto.getContractCode();
        if (cardBusiness.getCardByContract(contractCode) != null) {
            // check neu da yeu cau roi
            if (!cardBusiness.isNewCardRequested(contractCode)) {
                // thanh toan
//                HttpSession session = r.equest.getSession();
//                session.setAttribute("CONTRACT_CODE", contractCode);
//                session.setAttribute("SUCCESS_URL", "/customer/contract?action=ContractDetail&code=" + contractCode);
//                session.setAttribute("cancel_message", "Bạn đã hủy thanh toán. Xin vui lòng thực hiện lại hoặc đến thanh toán trực tiếp");
//                session.setAttribute("redirectLink", "/customer/contract?action=ContractDetail&code=" + contractCode);
//
//                CheckoutRequestDto checkoutRequest = new CheckoutRequestDto();
//                checkoutRequest.setPaymentrequest_name(r.equest.getParameter("L_PAYMENTREQUEST_0_NAME0"));
//                checkoutRequest.setPaymentrequest_desc(r.equest.getParameter("L_PAYMENTREQUEST_0_DESC0"));
//                checkoutRequest.setPaymentrequest_qty(r.equest.getParameter("L_PAYMENTREQUEST_0_QTY0"));
//                checkoutRequest.setPaymentrequest_itemamt(r.equest.getParameter("PAYMENTREQUEST_0_ITEMAMT"));
//                checkoutRequest.setPaymentrequest_taxamt(r.equest.getParameter("PAYMENTREQUEST_0_TAXAMT"));
//                checkoutRequest.setPaymentrequest_amt(r.equest.getParameter("PAYMENTREQUEST_0_AMT"));
//                checkoutRequest.setCurrencycodetype(r.equest.getParameter("currencyCodeType"));
//                checkoutRequest.setPaymenttype(r.equest.getParameter("paymentType"));
//                checkoutRequest.setPaymentrequest_amt_l(r.equest.getParameter("PAYMENTREQUEST_0_AMT"));
//                return new RedirectTo("/public/checkout?action=checkout&checkout=true&" + checkoutRequest.getQueryString());
                cardBusiness.requestNewCard(newCardRequestDto);
                return new RedirectTo("contract?action=ContractDetail&code="+contractCode);
            }
        }
        // error + return contract detail page
        return new RedirectTo("contract?action=ContractDetail&code="+contractCode);
    }

    public ResponseObject activeNewCardRequest(R r){

        return new JspPage("");
    }
}
