package com.fpt.mic.micweb.controller.pub;

import com.fpt.mic.micweb.framework.BasicController;
import com.fpt.mic.micweb.framework.R;
import com.fpt.mic.micweb.framework.responses.ForwardTo;
import com.fpt.mic.micweb.framework.responses.JspPage;
import com.fpt.mic.micweb.framework.responses.RedirectTo;
import com.fpt.mic.micweb.framework.responses.ResponseObject;
import com.fpt.mic.micweb.model.business.ContractBusiness;
import com.fpt.mic.micweb.model.business.RegisterBusiness;
import com.fpt.mic.micweb.model.dao.ContractDao;
import com.fpt.mic.micweb.model.dto.RegisterInformationDto;
import com.fpt.mic.micweb.model.dto.form.PublicHomeFormDto;
import com.fpt.mic.micweb.model.dto.form.PublicRegisterFormDto;
import com.fpt.mic.micweb.model.entity.ContractEntity;
import com.fpt.mic.micweb.model.entity.ContractTypeEntity;
import com.fpt.mic.micweb.model.entity.CustomerEntity;
import com.fpt.mic.micweb.model.entity.PaymentEntity;
import com.fpt.mic.micweb.utils.Constants;
import com.fpt.mic.micweb.utils.DateUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created by dinhquangtrung on 5/24/15.
 */
@WebServlet(name = "RegisterController", urlPatterns = "/public/register")
public class RegisterController extends BasicController {

    public ResponseObject getView(R r) {
        ContractBusiness contractBusiness = new ContractBusiness();
        List<ContractTypeEntity> list = contractBusiness.getAllContractType();
        HashMap<Integer,ContractTypeEntity> mapContractType = new HashMap<Integer, ContractTypeEntity>();
        for (int i = 0; i< list.size();i++) {
            mapContractType.put(list.get(i).getId(),list.get(i));
        }
        r.equest.setAttribute("mapContractType",mapContractType);
        return new JspPage("public/register.jsp"); // Go to homepage
    }

    public ResponseObject postRegister(R r) {
        PublicHomeFormDto publicHomeFormDto = (PublicHomeFormDto) r.ead.entity(PublicHomeFormDto.class,"register");
        // Gọi hàm validate ở đây
        List errors = r.ead.validate(publicHomeFormDto);

        // Nếu có lỗi khi validate
        if (errors.size() > 0) {
            // Gửi lỗi về trang JSP
            r.equest.setAttribute("validateErrors", errors);
            // Gửi dữ liệu mà người dùng đã nhập về trang JSP, gán vào biến submitted
            r.equest.setAttribute("submitted", publicHomeFormDto);
            r.equest.setAttribute("startDate", r.equest.getParameter("register:startDate"));
            return new ForwardTo("/public/home");
        }

        r.equest.setAttribute("startDate", r.equest.getParameter("register:startDate"));
        ContractBusiness contractBusiness = new ContractBusiness();
        List<ContractTypeEntity> list = contractBusiness.getAllContractType();
        HashMap<Integer,ContractTypeEntity> mapContractType = new HashMap<Integer, ContractTypeEntity>();
        for (int i = 0; i< list.size();i++) {
            mapContractType.put(list.get(i).getId(),list.get(i));
        }
        r.equest.setAttribute("mapContractType",mapContractType);
        r.equest.setAttribute("publicHomeFormDto", publicHomeFormDto);
        return new JspPage("public/register.jsp");
    }

    public ResponseObject postCreateContract(R r) {
        String url = "public/error.jsp";
        // Get information...
        PublicRegisterFormDto publicRegisterFormDto= (PublicRegisterFormDto) r.ead.entity(PublicRegisterFormDto.class,"register");
        // Gọi hàm validate ở đây
        List errors = r.ead.validate(publicRegisterFormDto);
        // Nếu có lỗi khi validate
        if (errors.size() > 0) {
            // Gửi lỗi về trang JSP
            r.equest.setAttribute("validateErrors", errors);
            // Gửi dữ liệu mà người dùng đã nhập về trang JSP, gán vào biết submitted
            r.equest.setAttribute("publicHomeFormDto", publicRegisterFormDto);
            r.equest.setAttribute("startDate", r.equest.getParameter("register:startDate"));
            return getView(r);
        }
        // Call to business object
        RegisterBusiness registerBusiness = new RegisterBusiness();
        RegisterInformationDto register = registerBusiness.registerNewContract(publicRegisterFormDto);

        if (register != null) {
            HttpSession session = r.equest.getSession();
            session.setAttribute("CONTRACT_CODE", register.getContractEntity().getContractCode());
            session.setAttribute("SUCCESS_URL", "/public/register?action=activeContract");

            r.equest.setAttribute("register", register);
            return new JspPage("public/register-payment.jsp");
        }

        r.equest.setAttribute("error", "Something wrong");
        return new JspPage(url);
    }

    public ResponseObject getActiveContract(R r) {
        String url = "/error/404";
        HttpSession session = r.equest.getSession(false);
        if (session != null) {
            url = "public/return.jsp";
            Map<String, String> results = new HashMap<String, String>();
            results.putAll((Map<String, String>) session.getAttribute("RESULT"));

            r.equest.setAttribute("result", results);
            r.equest.setAttribute("ack", (String) session.getAttribute("ACK"));
            Float amount = Float.parseFloat((String) session.getAttribute("amountVND"));
            r.equest.setAttribute("amountVND",amount);
            r.equest.setAttribute("redirectLink","home");

            String contractCode = (String) session.getAttribute("CONTRACT_CODE");
            String paypalTransId = results.get("PAYMENTINFO_0_TRANSACTIONID").toString();
            String paymentMethod = "PayPal payment";
            String paymentContent = "Đăng ký hợp đồng mới";

            RegisterBusiness registerBusiness = new RegisterBusiness();
            registerBusiness.updateContractPayment(contractCode, paymentMethod, paymentContent, amount, paypalTransId);
            session.removeAttribute("RESULT");
            session.removeAttribute("CONTRACT_CODE");
            session.removeAttribute("amountVND");
            session.removeAttribute("ACK");
            session.removeAttribute("SUCCESS_URL");
            session.removeAttribute("EXPRESS_MARK");
            session.removeAttribute("payer_id");
            session.removeAttribute("checkoutDetails");
            session.removeAttribute("checkout");
            session.removeAttribute("TOKEN");
            return new JspPage(url);
        }
        //r.equest.setAttribute("error","Phiên giao dịch đã hết thời gian");
        return new RedirectTo(url);
    }

}

