package com.fpt.mic.micweb.controller.staff;

import com.fpt.mic.micweb.controller.common.AuthController;
import com.fpt.mic.micweb.framework.Paginator;
import com.fpt.mic.micweb.framework.R;
import com.fpt.mic.micweb.framework.responses.JspPage;
import com.fpt.mic.micweb.framework.responses.RedirectTo;
import com.fpt.mic.micweb.framework.responses.ResponseObject;
import com.fpt.mic.micweb.model.business.CompensationBusiness;
import com.fpt.mic.micweb.model.business.StaffBusiness;
import com.fpt.mic.micweb.model.dto.UserDto;
import com.fpt.mic.micweb.model.dto.form.CreateCompensationDto;
import com.fpt.mic.micweb.model.dto.form.EditCompensationDto;
import com.fpt.mic.micweb.model.dto.form.ResolveCompensationDto;
import com.fpt.mic.micweb.model.entity.CompensationEntity;
import com.fpt.mic.micweb.model.entity.ContractEntity;
import com.fpt.mic.micweb.model.entity.CustomerEntity;
import com.fpt.mic.micweb.utils.Constants;

import javax.servlet.annotation.WebServlet;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;

/**
 * Created by dinhquangtrung on 5/23/15.
 */
@WebServlet(name = "CompensationController", urlPatterns = {"/staff/compensation"})
public class CompensationController extends AuthController {
    @Override
    public List<String> getAllowedRoles() {
        return Collections.singletonList(UserDto.ROLE_STAFF);
    }

    private static String msg = "";
    private static boolean isSuccess;

    Paginator compenPaginator = new Paginator();

    public ResponseObject getView(R r) {
        final CompensationBusiness compenBus = new CompensationBusiness();

        compenPaginator.setGetItemsCallback(new Paginator.IGetItems() {
            @Override
            public List getItems(int offset, int count) {
                return compenBus.getAllCompensation(offset, count);
            }
        });
        compenPaginator.setGetItemSizeCallback(new Paginator.IGetItemSize() {
            @Override
            public Long getItemSize() {
                return compenBus.getAllCompensationCount();
            }
        });

        r.equest.setAttribute("compenPaginator", compenPaginator);
        return new JspPage("staff/compensations.jsp");
    }

    public ResponseObject getSearch(R r) {
        String keyword = r.equest.getParameter("keyword");
        if (keyword == null) {
            keyword = "";
        }
        keyword = keyword.trim();

        final CompensationBusiness compenBus = new CompensationBusiness();

        final String finalKeyword = keyword;
        compenPaginator.setGetItemsCallback(new Paginator.IGetItems() {
            @Override
            public List getItems(int offset, int count) {
                return compenBus.searchCompensation(finalKeyword, offset, count);
            }
        });
        compenPaginator.setGetItemSizeCallback(new Paginator.IGetItemSize() {
            @Override
            public Long getItemSize() {
                return compenBus.searchCompensationCount(finalKeyword);
            }
        });

        r.equest.setAttribute("compenPaginator", compenPaginator);
        return new JspPage("staff/compensations.jsp");
    }

    public ResponseObject getDetail(R r) {
        String compensationCode = r.equest.getParameter("code");

        // Receive compensationCode from failed validation
        // @see {@link postResolve}
        if (compensationCode == null) {
            compensationCode = (String) r.equest.getAttribute("compensationCode");
        }

        // Get compensation detail information
        CompensationBusiness compenBus = new CompensationBusiness();
        CompensationEntity compenEntity = compenBus.getCompensationDetail(compensationCode);

        // If contract is not exists, show 404 page
        if (compenEntity == null) {
            return new RedirectTo("/error/404");
        }

        // Save last_modified value for concurrency check
        r.equest.getSession(true).setAttribute(
                Constants.Session.CONCURRENCY + compensationCode, compenEntity.getLastModified());

        r.equest.setAttribute("COMPENSATION", compenEntity);
        return new JspPage("staff/compensation-detail.jsp");
    }

    public ResponseObject getCreate(R r) {
        return new JspPage("staff/create-compensation.jsp");
    }

    public ResponseObject getCreateFromContract(R r) {
        String contractCode = r.equest.getParameter("code");
        StaffBusiness staffBusiness = new StaffBusiness();
        ContractEntity contractEntity = staffBusiness.getContractDetail(contractCode);
        if (contractEntity != null) {
            CustomerEntity customerEntity = staffBusiness.getCustomerDetail(contractEntity.getCustomerCode());
            CreateCompensationDto dto = new CreateCompensationDto();
            dto.setDriverName(customerEntity.getName());
            dto.setDriverAddress(customerEntity.getAddress());
            dto.setDriverPhone(customerEntity.getPhone());
            r.equest.setAttribute("submitted", dto);
        }
        return new JspPage("staff/create-compensation.jsp");
    }

    public ResponseObject getEdit(R r) {
        String compensationCode = r.equest.getParameter("code");

        // Receive compensationCode from failed validation
        // @see {@link postEdit}
        if (compensationCode == null) {
            compensationCode = (String) r.equest.getAttribute("compensationCode");
        }

        // Get compensation detail information
        CompensationBusiness compenBus = new CompensationBusiness();
        CompensationEntity compenEntity = compenBus.getCompensationDetail(compensationCode);

        // If contract is not exists, show 404 page
        if (compenEntity == null) {
            return new RedirectTo("/error/404");
        }

        // Save last_modified value for concurrency check
        r.equest.getSession(true).setAttribute(
                Constants.Session.CONCURRENCY + compensationCode, compenEntity.getLastModified());

        r.equest.setAttribute("COMPENSATION", compenEntity);
        return new JspPage("staff/edit-compensation.jsp");
    }

    public ResponseObject postCreate(R r) {
        CreateCompensationDto dto = (CreateCompensationDto) r.ead.entity(CreateCompensationDto.class, "compensation");
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
        CompensationBusiness compenBus = new CompensationBusiness();
        CompensationEntity result = compenBus.createCompensation(dto);

        if (result != null) {
            r.equest.setAttribute("COMPENSATION", result);
            return new JspPage("staff/create-compensation-success.jsp");
        } else {
            isSuccess = false;
            msg = "Tạo yêu cầu bồi thường thất bại, vui lòng thử lại hoặc liên hệ IT";
            r.equest.setAttribute("MESSAGE", msg);
            r.equest.setAttribute("SUCCESS", isSuccess);
            return new JspPage("staff/message.jsp");
        }
    }

    public ResponseObject postResolve(R r) {
        ResolveCompensationDto dto = (ResolveCompensationDto) r.ead.entity(ResolveCompensationDto.class, "resolve");

        // Get concurrency data
        Timestamp lastModified = (Timestamp) r.equest.getSession(true).getAttribute(
                Constants.Session.CONCURRENCY + dto.getCompensationCode());
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
            r.equest.setAttribute("compensationCode", dto.getCompensationCode());
            return getDetail(r);
        }
        // If the code reached this line that means there is no validation errors

        // Call to business object
        CompensationBusiness compenBus = new CompensationBusiness();
        boolean result = compenBus.resolveCompensation(dto);

        return new RedirectTo("compensation?action=detail&code=" + dto.getCompensationCode());
    }

    public ResponseObject postEdit(R r) {
        EditCompensationDto dto = (EditCompensationDto) r.ead.entity(EditCompensationDto.class, "edit");

        // Get concurrency data
        Timestamp lastModified = (Timestamp) r.equest.getSession(true).getAttribute(
                Constants.Session.CONCURRENCY + dto.getCompensationCode());
        dto.setLastModified(lastModified);

        List errors = r.ead.validate(dto);

        // If there is validation errors
        if (errors.size() > 0) {
            // Send error messages to JSP page
            r.equest.setAttribute("validateErrors", errors);
            // This is a form in a popup, we don't need to display data again since
            // the popup will not automatically open when the page is reloaded
            // r.equest.setAttribute("submitted", dto);
            // Send submitted data to JSP page
            r.equest.setAttribute("submitted", dto);
            // Re-call the contract detail page
            r.equest.setAttribute("compensationCode", dto.getCompensationCode());
            return getEdit(r);
        }
        // If the code reached this line that means there is no validation errors

        // Call to business object
        CompensationBusiness compenBus = new CompensationBusiness();
        boolean result = compenBus.editCompensation(dto);

        return new RedirectTo("compensation?action=detail&code=" + dto.getCompensationCode());
    }
}
