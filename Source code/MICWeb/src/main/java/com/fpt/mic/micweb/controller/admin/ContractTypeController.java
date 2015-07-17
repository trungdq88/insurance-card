package com.fpt.mic.micweb.controller.admin;

import com.fpt.mic.micweb.framework.BasicController;
import com.fpt.mic.micweb.framework.Paginator;
import com.fpt.mic.micweb.framework.R;
import com.fpt.mic.micweb.framework.responses.JspPage;
import com.fpt.mic.micweb.framework.responses.RedirectTo;
import com.fpt.mic.micweb.framework.responses.ResponseObject;
import com.fpt.mic.micweb.model.business.ContractBusiness;
import com.fpt.mic.micweb.model.dto.form.ContractTypeDto;

import java.util.List;

import javax.servlet.annotation.WebServlet;

/**
 * Created by TriPQM on 07/15/2015.
 */

@WebServlet(name = "ContractTypeController", urlPatterns = {"/admin/contractType"})
public class ContractTypeController extends BasicController {
    Paginator contractTypePaginator = new Paginator();
    public ResponseObject getView(R r){
        final ContractBusiness contractBusiness = new ContractBusiness();
        contractTypePaginator.setGetItemsCallback(new Paginator.IGetItems() {
            @Override
            public List getItems(int offset, int count) {
                return contractBusiness.getOnePageContractTypes(offset,count);
            }
        });
        contractTypePaginator.setGetItemSizeCallback(new Paginator.IGetItemSize() {
            @Override
            public Long getItemSize() {
                return contractBusiness.getAllContractTypeCount();
            }
        });
        r.equest.setAttribute("contractTypePaginator", contractTypePaginator);
        return new JspPage("admin/contract-type.jsp");
    }

    public ResponseObject postAddContractType(R r){
        ContractTypeDto contractTypeDto =(ContractTypeDto) r.ead.entity(ContractTypeDto.class,"contractType");
        List errors = r.ead.validate(contractTypeDto);
        // Nếu có lỗi khi validate
        if (errors.size() > 0) {
            // Gửi lỗi về trang JSP
            r.equest.setAttribute("validateErrors", errors);
            // Gửi dữ liệu mà người dùng đã nhập về trang JSP, gán vào biến submitted
            r.equest.setAttribute("submitted", contractTypeDto);
            return getView(r);
        }
        ContractBusiness contractBusiness = new ContractBusiness();
        if (contractBusiness.addContractType(contractTypeDto)){
            return new RedirectTo("/admin/contractType?info=2");
        } else {
            return new RedirectTo("/admin/contractType?info=3");
        }
        //return new RedirectTo("/admin/contractType?info=Thêm loại hợp đồng thành công");*
    }

    public ResponseObject postDeleteContractType(R r){
        ContractBusiness contractBusiness = new ContractBusiness();
        int contractTypeId = Integer.parseInt(r.equest.getParameter("contractTypeId"));
        // check if contractTypeId is used
        if (contractBusiness.getCountOfContractByContractType(contractTypeId) == 0){
            contractBusiness.deleteContractType(contractTypeId);
            return new RedirectTo("/admin/contractType?action=view&info=1&page="+r.equest.getParameter("page"));
        } else {
            return new RedirectTo("/admin/contractType?action=view&info=0&page="+r.equest.getParameter("page"));
        }

    }
}