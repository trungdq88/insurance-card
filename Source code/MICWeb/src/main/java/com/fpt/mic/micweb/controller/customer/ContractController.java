package com.fpt.mic.micweb.controller.customer;

import com.fpt.mic.micweb.model.business.CustomerBusniess;
import com.fpt.mic.micweb.model.entity.ContractEntity;
import com.fpt.mic.micweb.framework.BasicController;
import com.fpt.mic.micweb.framework.responses.JspPage;
import com.fpt.mic.micweb.framework.R;
import com.fpt.mic.micweb.framework.responses.ResponseObject;

import javax.servlet.annotation.WebServlet;
import java.util.List;

/**
 * Created by PhucNguyen on 06/05/15.
 */
@WebServlet(name = "CustomerContractController", urlPatterns = "/customer/contract")
public class ContractController extends BasicController {

    public ResponseObject getView(R r) {
        CustomerBusniess customerBusiness = new CustomerBusniess();
        List<ContractEntity> listContract = customerBusiness.getAllContract();
        r.equest.setAttribute("listContract", listContract);
        return new JspPage("customer/contract.jsp");
    }

    public ResponseObject getContractDetail(R r) {
        CustomerBusniess customerBusiness = new CustomerBusniess();
        String code = r.equest.getParameter("code");
        ContractEntity contract = customerBusiness.getContractDetail(code);
        r.equest.setAttribute("contract", contract);
        return new JspPage("customer/contract-detail.jsp");
    }

    /* Handle canncel contract */
    public ResponseObject postCancelContract(R r) {
        CustomerBusniess customerBusiness = new CustomerBusniess();
        String result = "";
        String contractcode = r.equest.getParameter("contractcode");
        String reasoncancel = r.equest.getParameter("txtReason");
        String anotherReason = r.equest.getParameter("txtAnotherReason");
        if (reasoncancel != null && reasoncancel != "") {
            if (customerBusiness.CancelContract(contractcode, reasoncancel, 1) == true) {
                result = "Yêu Cầu Hủy Hợp Đồng Thành Công ";
            } else result = "Yêu Cầu Hủy Thất Bại";
            r.equest.setAttribute("result", result);
        } else if (anotherReason != null && anotherReason != "") {
            if (customerBusiness.CancelContract(contractcode, anotherReason , 2) == true) {
                result = "Yêu Cầu Hủy Hợp Đồng Thành Công ";
            } else result = "Yêu Cầu Hủy Thất Bại";
            r.equest.setAttribute("result", result);
        }

        return new JspPage("customer/contract-detail.jsp");
    }

    public ResponseObject getNewContract(R r) {
        return new JspPage("customer/contract-new.jsp");
    }

}


