package com.fpt.mic.micweb.controller.customer;

import com.fpt.mic.micweb.controller.common.AuthController;
import com.fpt.mic.micweb.framework.responses.JspPage;
import com.fpt.mic.micweb.framework.R;
import com.fpt.mic.micweb.framework.responses.RedirectTo;
import com.fpt.mic.micweb.framework.responses.ResponseObject;
import com.fpt.mic.micweb.model.business.CustomerBusiness;
import com.fpt.mic.micweb.model.dto.UserDto;
import com.fpt.mic.micweb.model.dto.form.CancelContractDto;
import com.fpt.mic.micweb.model.dto.form.ChangePasswordDto;
import com.fpt.mic.micweb.model.dto.form.CreateCustomerDto;
import com.fpt.mic.micweb.model.dto.form.EditCustomerProfileDto;
import com.fpt.mic.micweb.model.entity.CustomerEntity;
import com.fpt.mic.micweb.utils.Constants;

import javax.servlet.annotation.WebServlet;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;

/**
 * Created by dinhquangtrung on 5/24/15.
 */
@WebServlet(name = "PersonalController", urlPatterns = "/customer")
public class PersonalController extends AuthController {
    @Override
    public List<String> getAllowedRoles() {
        return Collections.singletonList(UserDto.ROLE_CUSTOMER);
    }

    public ResponseObject getView(R r) {
        final CustomerBusiness customerBusiness = new CustomerBusiness();
        final String customerCode = ((CustomerEntity) getLoggedInUser()).getCustomerCode();
        CustomerEntity customerEntity = customerBusiness.getCustomer(customerCode);

        if (customerEntity == null) {
            return new RedirectTo("/error/404");
        }
        // Save last_modified value for concurrency check
        r.equest.getSession(true).setAttribute(
                Constants.Session.CONCURRENCY + customerEntity.getCustomerCode(),
                customerEntity.getLastModified());
        r.equest.setAttribute("customer", customerEntity);
        return new JspPage("customer/personal-information.jsp");

    }
    public ResponseObject postEditProfile(R r){
        String mess = "";
        boolean result = false;
        String customerCode = r.equest.getParameter("customerCode");
        CustomerBusiness customerBusiness = new CustomerBusiness();
        EditCustomerProfileDto dto = (EditCustomerProfileDto)r.ead.entity(EditCustomerProfileDto.class ,"editCustomer");
        // Get concurrency data
        Timestamp lastModified = (Timestamp) r.equest.getSession(true).getAttribute(
                Constants.Session.CONCURRENCY + dto.getCustomerCode());
        dto.setLastModified(lastModified);
        List errors = r.ead.validate(dto);

        if (errors.size() > 0) {
            r.equest.setAttribute("validateErrors", errors);
            r.equest.setAttribute("submitted", dto);
            r.equest.setAttribute("customer", customerBusiness.getCustomer(customerCode));
            return new JspPage("customer/personal-information.jsp");
        }
        else {
            result = customerBusiness.editCustomerProfile(customerCode, dto);
            if(result == true){
                mess = "Bạn đã thay đổi thông tin cá nhân thành công";
                r.equest.setAttribute("message", mess);
                r.equest.setAttribute("customer", customerBusiness.getCustomer(customerCode));
                return new JspPage("customer/personal-information.jsp");
            }
            else {
                mess = "Thay đổi thông tin cá nhân thất bại";
                r.equest.setAttribute("message", mess);
                r.equest.setAttribute("customer", customerBusiness.getCustomer(customerCode));
                return new JspPage("customer/personal-information.jsp");
            }
        }

    }
    public ResponseObject postChangePassword(R r){
        String mess = "";
        boolean result = false;
        CustomerBusiness customerBusiness = new CustomerBusiness();
        ChangePasswordDto dto = (ChangePasswordDto) r.ead.entity(ChangePasswordDto.class, "newPass");
        if (dto.getCustomerCode() == null) {
            return new RedirectTo("/error/404");
        }
        else {
            List errors = r.ead.validate(dto);

            if (errors.size() > 0) {
                r.equest.setAttribute("validateErrors", errors);
                r.equest.setAttribute("submitted", dto);
                r.equest.setAttribute("customer", customerBusiness.getCustomer(dto.getCustomerCode()));
                return new JspPage("customer/personal-information.jsp");
            }
            else {
                result = customerBusiness.changePassword(dto);
                if(result == true){
                    mess = "Bạn đã đổi mật khẩu thành công";
                    r.equest.setAttribute("message", mess);
                    r.equest.setAttribute("customer", customerBusiness.getCustomer(dto.getCustomerCode()));
                    return new JspPage("customer/personal-information.jsp");
                }
                else {
                    mess = "Thay đổi mật khẩu thất bại";
                    r.equest.setAttribute("message", mess);
                    r.equest.setAttribute("customer", customerBusiness.getCustomer(dto.getCustomerCode()));
                    return new JspPage("customer/personal-information.jsp");
                }
            }
        }
    }

//    public ResponseObject postRejectChangePassword(R r){
//
//    }
}
