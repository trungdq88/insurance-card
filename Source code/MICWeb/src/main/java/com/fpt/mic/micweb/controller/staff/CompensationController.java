package com.fpt.mic.micweb.controller.staff;

import com.fpt.mic.micweb.controller.common.AuthController;
import com.fpt.mic.micweb.framework.Paginator;
import com.fpt.mic.micweb.framework.responses.JspPage;
import com.fpt.mic.micweb.framework.R;
import com.fpt.mic.micweb.framework.responses.ResponseObject;
import com.fpt.mic.micweb.model.business.CompensationBusiness;
import com.fpt.mic.micweb.model.dto.UserDto;
import com.fpt.mic.micweb.model.dto.form.CreateCompensationDto;
import com.fpt.mic.micweb.model.entity.CompensationEntity;
import com.fpt.mic.micweb.utils.ConfigUtils;

import javax.servlet.annotation.WebServlet;
import java.util.Arrays;
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

    public ResponseObject getDetail(R r) {
        String compensationCode = r.equest.getParameter("code");

        // Get compensation detail information
        CompensationBusiness compenBus = new CompensationBusiness();
        CompensationEntity compenEntity = compenBus.getCompensationDetail(compensationCode);

        r.equest.setAttribute("COMPENSATION", compenEntity);
        return new JspPage("staff/compensation-detail.jsp");
    }

    public ResponseObject getCreate(R r) {
        return new JspPage("staff/create-compensation.jsp");
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
            msg = "Tạo yêu cầu bồi thường thất bại, vui lòng thử lại hoặc liên hệ IT";
            r.equest.setAttribute("MESSAGE", msg);
            return new JspPage("staff/message.jsp");
        }
    }
}
