package com.fpt.mic.micweb.controller.admin;

import com.fpt.mic.micweb.framework.BasicController;
import com.fpt.mic.micweb.framework.Paginator;
import com.fpt.mic.micweb.framework.R;
import com.fpt.mic.micweb.framework.responses.JspPage;
import com.fpt.mic.micweb.framework.responses.RedirectTo;
import com.fpt.mic.micweb.framework.responses.ResponseObject;
import com.fpt.mic.micweb.model.business.AdminBusiness;
import com.fpt.mic.micweb.model.business.StaffBusiness;
import com.fpt.mic.micweb.model.dto.form.CreateStaffDto;

import javax.servlet.annotation.WebServlet;
import java.util.List;

/**
 * Created by TriPQM on 07/02/2015.
 */
@WebServlet(name = "ManageController", urlPatterns = {"/admin"})
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
    public ResponseObject postViewCreateStaff(R r){
        return  new JspPage("admin/create-staff.jsp");
    }
    public ResponseObject postCreateStaff(R r) {
        CreateStaffDto createStaffDto= (CreateStaffDto) r.ead.entity(CreateStaffDto.class,"staff");
        // Gọi hàm validate ở đây
        List errors = r.ead.validate(createStaffDto);

        // Nếu có lỗi khi validate
        if (errors.size() > 0) {
            // Gửi lỗi về trang JSP
            r.equest.setAttribute("validateErrors", errors);
            // Gửi dữ liệu mà người dùng đã nhập về trang JSP, gán vào biến submitted
            r.equest.setAttribute("submitted", createStaffDto);
            return postViewCreateStaff(r);
        }
        AdminBusiness adminBusiness = new AdminBusiness();
        String staffCode = adminBusiness.createStaff(createStaffDto).getStaffCode();
        return new RedirectTo("/admin?action=staffDetail&code="+staffCode);
    }
    public ResponseObject getDeleteStaff(R r){
        AdminBusiness adminBusiness = new AdminBusiness();
        String staffCode = r.equest.getParameter("code");
        if (adminBusiness.deleteStaff(staffCode)){
            r.equest.setAttribute("message","Đã xóa thành công nhân viên "+staffCode);
        } else {
          r.equest.setAttribute("message","Xóa không thành công");
        }
        return getView(r);
    }
}
