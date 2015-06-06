package com.fpt.mic.micweb.controller.customer;
import com.fpt.mic.micweb.model.business.CustomerBusniess;
import com.fpt.mic.micweb.model.entity.ContractEntity;
import com.fpt.mic.micweb.framework.BasicController;
import com.fpt.mic.micweb.framework.JspPage;
import com.fpt.mic.micweb.framework.R;
import com.fpt.mic.micweb.framework.ResponseObject;

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
        return new JspPage("customer/contract-detail.jsp");
    }
    public ResponseObject getNewContract(R r){
        return new JspPage("customer/contract-new.jsp");
    }

}


