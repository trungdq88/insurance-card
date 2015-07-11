package com.fpt.mic.micweb.controller.admin;

import com.fpt.mic.micweb.framework.BasicController;
import com.fpt.mic.micweb.framework.Paginator;
import com.fpt.mic.micweb.framework.R;
import com.fpt.mic.micweb.framework.responses.JspPage;
import com.fpt.mic.micweb.framework.responses.ResponseObject;
import com.fpt.mic.micweb.model.business.AdminBusiness;
import com.fpt.mic.micweb.model.business.StaffBusiness;
import com.fpt.mic.micweb.model.dto.form.BusinessRulesDto;

import javax.servlet.annotation.WebServlet;
import java.util.List;

/**
 * Created by TriPQM on 07/05/2015.
 */
@WebServlet(name = "ConfigController", urlPatterns = {"/admin/config"})
public class ConfigController extends BasicController{

    Paginator businessRulesPaginator = new Paginator();
    public ResponseObject getView(R r){
        final AdminBusiness adminBusiness = new AdminBusiness();
        r.equest.setAttribute("submitted", adminBusiness.getLastActiveBusinessRule());
        final StaffBusiness staffBus = new StaffBusiness();
        businessRulesPaginator.setGetItemsCallback(new Paginator.IGetItems() {
            @Override
            public List getItems(int offset, int count) {
                return adminBusiness.getOnePageBusinessRules(offset, count);
            }
        });
        businessRulesPaginator.setGetItemSizeCallback(new Paginator.IGetItemSize() {
            @Override
            public Long getItemSize() {
                return adminBusiness.getAllBusinessRulesCount();
            }
        });
        r.equest.setAttribute("businessRulesPaginator", businessRulesPaginator);
        return new JspPage("admin/business-config-rules.jsp");
    }
    public ResponseObject getDetail(R r){
        Integer configId =Integer.parseInt(r.equest.getParameter("configId"));
        AdminBusiness adminBusiness = new AdminBusiness();
        r.equest.setAttribute("submitted",adminBusiness.getBusinessRules(configId));
        return new JspPage("admin/business-config-rules-detail.jsp");
    }
    public ResponseObject postEditConfig(R r){
        BusinessRulesDto businessRulesDto = (BusinessRulesDto) r.ead.entity(BusinessRulesDto.class,"config");
        List errors = r.ead.validate(businessRulesDto);

        // Nếu có lỗi khi validate
        if (errors.size() > 0) {
            // Gửi lỗi về trang JSP
            r.equest.setAttribute("validateErrors", errors);
            // Gửi dữ liệu mà người dùng đã nhập về trang JSP, gán vào biến submitted
            r.equest.setAttribute("submitted", businessRulesDto);
            return new JspPage("admin/business-config-rules.jsp");
        }
        AdminBusiness adminBusiness = new AdminBusiness();
        adminBusiness.createBusinessRules(businessRulesDto);
        r.equest.setAttribute("info","Cập nhật thành công");
        return getView(r);
    }
}
