package com.fpt.mic.micweb.controller.customer;

import com.fpt.mic.micweb.controller.common.AuthController;
import com.fpt.mic.micweb.framework.Paginator;
import com.fpt.mic.micweb.framework.responses.RedirectTo;
import com.fpt.mic.micweb.model.business.*;
import com.fpt.mic.micweb.model.dto.CheckoutRequestDto;
import com.fpt.mic.micweb.model.dto.RegisterInformationDto;
import com.fpt.mic.micweb.model.dto.UserDto;
import com.fpt.mic.micweb.model.dto.form.CancelContractDto;
import com.fpt.mic.micweb.model.dto.form.ConcurrencyDto;
import com.fpt.mic.micweb.model.dto.form.CustomerCreateContractDto;
import com.fpt.mic.micweb.model.entity.*;
import com.fpt.mic.micweb.framework.responses.JspPage;
import com.fpt.mic.micweb.framework.R;
import com.fpt.mic.micweb.framework.responses.ResponseObject;
import com.fpt.mic.micweb.utils.Constants;
import com.fpt.mic.micweb.utils.DateUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created by dinhquangtrung on 5/24/15.
 */
@WebServlet(name = "PaymentController", urlPatterns = "/customer/payment")
public class PaymentController extends AuthController {
    Paginator paymentPaginator = new Paginator();
    @Override
    public List<String> getAllowedRoles() {
        return Collections.singletonList(UserDto.ROLE_CUSTOMER);
    }
    public ResponseObject getView(R r) {
        final CustomerBusiness customerBusiness = new CustomerBusiness();
        final String customerCode = ((CustomerEntity) getLoggedInUser()).getCustomerCode();
        if (customerCode == null ) {
            return new RedirectTo("/error/404");
        }else {
            paymentPaginator.setGetItemsCallback(new Paginator.IGetItems() {
                @Override
                public List getItems(int offset, int count) {
                    return customerBusiness.getAllPaymentByCustomerCode(customerCode, offset, count);
                }
            });
            paymentPaginator.setGetItemSizeCallback(new Paginator.IGetItemSize() {
                @Override
                public Long getItemSize() {
                    return customerBusiness.getAllPaymentByCustomerCount(customerCode);
                }
            });
            r.equest.setAttribute("paymentPaginator", paymentPaginator);
            return new JspPage("customer/payment.jsp");
        }

    }
}
