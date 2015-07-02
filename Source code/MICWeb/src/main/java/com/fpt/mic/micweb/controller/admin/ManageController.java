package com.fpt.mic.micweb.controller.admin;

import com.fpt.mic.micweb.framework.BasicController;
import com.fpt.mic.micweb.framework.Paginator;
import com.fpt.mic.micweb.framework.R;
import com.fpt.mic.micweb.framework.responses.JspPage;
import com.fpt.mic.micweb.framework.responses.ResponseObject;
import com.fpt.mic.micweb.model.business.StaffBusiness;

import javax.servlet.annotation.WebServlet;
import java.util.List;

/**
 * Created by TriPQM on 07/02/2015.
 */
@WebServlet(name = "ManageController", urlPatterns = {"/admin/home"})
public class ManageController extends BasicController{
    /**
     * Paginator for contract
     */
    Paginator staffPaginator = new Paginator();
    public ResponseObject getView(R r) {
        final StaffBusiness staffBus = new StaffBusiness();
        staffPaginator.setGetItemsCallback(new Paginator.IGetItems() {
            @Override
            public List getItems(int offset, int count) {
                return staffBus.getOnePageStaff(offset, count);
            }
        });
        staffPaginator.setGetItemSizeCallback(new Paginator.IGetItemSize() {
            @Override
            public Long getItemSize() {
                return staffBus.getAllStaffCount();
            }
        });
        r.equest.setAttribute("staffPaginator", staffPaginator);
        return new JspPage("/admin/manage.jsp");
    }
    public ResponseObject getStaffDetail(R r) {
        StaffBusiness staffBusiness = new StaffBusiness();
        String staffCode = r.equest.getParameter("code");
        r.equest.setAttribute("staff",staffBusiness.getStaffDetail(staffCode));
        return new JspPage("admin/staff-detail.jsp");

    }
}
