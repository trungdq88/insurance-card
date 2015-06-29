package com.fpt.mic.micweb.controller.staff;

import com.fpt.mic.micweb.controller.common.AuthController;
import com.fpt.mic.micweb.framework.Paginator;
import com.fpt.mic.micweb.framework.R;
import com.fpt.mic.micweb.framework.responses.JspPage;
import com.fpt.mic.micweb.framework.responses.ResponseObject;
import com.fpt.mic.micweb.model.business.StaffBusiness;
import com.fpt.mic.micweb.model.dto.CreateCustomerInfoDto;
import com.fpt.mic.micweb.model.dto.UserDto;
import com.fpt.mic.micweb.model.dto.form.CreateCustomerDto;
import com.fpt.mic.micweb.model.entity.ContractEntity;
import com.fpt.mic.micweb.model.entity.CustomerEntity;

import javax.servlet.annotation.WebServlet;
import java.util.Collections;
import java.util.List;

/**
 * Created by dinhquangtrung on 5/23/15.
 * Implemented by nguyenchikha on 6/7/15.
 */
@WebServlet(name = "CustomerController", urlPatterns = {"/staff/customer"})
public class CustomerController extends AuthController {
    /**
     * Paginator for customer
     */
    Paginator customerPaginator = new Paginator();

    @Override
    public List<String> getAllowedRoles() {
        return Collections.singletonList(UserDto.ROLE_STAFF);
    }

    public ResponseObject getView(R r) {
        final StaffBusiness staffBus = new StaffBusiness();

        customerPaginator.setGetItemsCallback(new Paginator.IGetItems() {
            @Override
            public List getItems(int offset, int count) {
                return staffBus.getAllCustomer(offset, count);
            }
        });
        customerPaginator.setGetItemSizeCallback(new Paginator.IGetItemSize() {
            @Override
            public Long getItemSize() {
                return staffBus.getAllCustomerCount();
            }
        });

        r.equest.setAttribute("customerPaginator", customerPaginator);
        return new JspPage("staff/customers.jsp");
    }

    public ResponseObject getSearch(R r) {
        String keyword = r.equest.getParameter("keyword");
        if (keyword == null) {
            keyword = "";
        }
        keyword = keyword.trim();

        final StaffBusiness staffBus = new StaffBusiness();

        final String finalKeyword = keyword;
        customerPaginator.setGetItemsCallback(new Paginator.IGetItems() {
            @Override
            public List getItems(int offset, int count) {
                return staffBus.searchCustomerByNameOrCode(finalKeyword, offset, count);
            }
        });
        customerPaginator.setGetItemSizeCallback(new Paginator.IGetItemSize() {
            @Override
            public Long getItemSize() {
                return staffBus.searchCustomerByNameOrCodeCount(finalKeyword);
            }
        });

        r.equest.setAttribute("customerPaginator", customerPaginator);
        return new JspPage("staff/customers.jsp");
    }

    public ResponseObject getDetail(R r) {
        StaffBusiness staffBus = new StaffBusiness();
        String customerCode = r.equest.getParameter("code");
        System.out.println(customerCode);

        // Get customer detail
        CustomerEntity customerDetail = staffBus.getCustomerDetail(customerCode);
        r.equest.setAttribute("CUSTOMER", customerDetail);

        // Get customer's contracts
        List<ContractEntity> listCustomerContract =
                staffBus.getContractByCustomerCode(customerDetail.getCustomerCode(), 0, 10);
        r.equest.setAttribute("CONTRACTS", listCustomerContract);

        // Dispatch to JSP page
        return new JspPage("staff/customer-detail.jsp");
    }

    public ResponseObject getCreate(R r) {
        return new JspPage("staff/create-customer.jsp");
    }

    public ResponseObject postCreate(R r) {
        CreateCustomerDto dto = (CreateCustomerDto) r.ead.entity(CreateCustomerDto.class, "customer");
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

        String loginUrl = r.equest.getScheme() +
                "://" + r.equest.getServerName() +
                ":" + r.equest.getServerPort() +
                r.equest.getContextPath() +
                "/user?action=login";

        // Business object
        StaffBusiness staffBus = new StaffBusiness();
        CreateCustomerInfoDto result = staffBus.createCustomer(dto, getServletContext(), loginUrl);

        if (result != null) {
            // Get created customer information
            r.equest.setAttribute("CUSTOMER", result);
            // Return Success JSP Page
            return new JspPage("staff/create-customer-success.jsp");
        } else {
            r.equest.setAttribute("error", "Something wrong");
            return new JspPage("staff/message.jsp");
        }
    }
}
