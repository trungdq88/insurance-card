package com.fpt.mic.micweb.controller.pub;

import com.fpt.mic.micweb.framework.BasicController;
import com.fpt.mic.micweb.framework.R;
import com.fpt.mic.micweb.framework.responses.ForwardTo;
import com.fpt.mic.micweb.framework.responses.JspPage;
import com.fpt.mic.micweb.framework.responses.RedirectTo;
import com.fpt.mic.micweb.framework.responses.ResponseObject;
import com.fpt.mic.micweb.model.business.ContractBusiness;
import com.fpt.mic.micweb.model.business.RegisterBusiness;
import com.fpt.mic.micweb.model.dto.RegisterInformationDto;
import com.fpt.mic.micweb.model.dto.form.ConcurrencyDto;
import com.fpt.mic.micweb.model.dto.form.PublicHomeFormDto;
import com.fpt.mic.micweb.model.dto.form.PublicRegisterFormDto;
import com.fpt.mic.micweb.model.entity.ContractTypeEntity;
import com.fpt.mic.micweb.utils.Constants;

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
        PublicRegisterFormDto publicRegisterFormDto =
                (PublicRegisterFormDto) r.ead.entity(PublicRegisterFormDto.class,"register");

        String loginUrl = r.equest.getScheme() +
                "://" + r.equest.getServerName() +
                ":" + r.equest.getServerPort() +
                r.equest.getContextPath() +
                "/user?action=login";

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
        RegisterInformationDto register =
                registerBusiness.registerNewContract(publicRegisterFormDto, loginUrl);

        if (register != null) {
            HttpSession session = r.equest.getSession();

            // Save last_modified value for concurrency check
            Timestamp lastModified = register.getContractEntity().getLastModified();
            session.setAttribute(
                    Constants.Session.CONCURRENCY + register.getContractEntity().getContractCode(),
                    lastModified);

            session.setAttribute("CONTRACT_CODE", register.getContractEntity().getContractCode());
            session.setAttribute("SUCCESS_URL", "/public/register?action=activeContract");
            session.setAttribute("cancel_message","Bạn đã hủy thanh toán. Xin vui lòng <a href='/user'>Đăng nhập</a> để thanh toán lại hoặc đến thanh toán trực tiếp");
            session.setAttribute("redirectLink","home");

            r.equest.setAttribute("register", register);
            return new JspPage("public/register-payment.jsp");
        }

        r.equest.setAttribute("error", "Không thể tạo hợp đồng. Xin thử lại");
        return new JspPage(url);
    }

    public ResponseObject getActiveContract(R r) {
        String errorUrl = "/error/404";
        HttpSession session = r.equest.getSession(false);
        if (session != null) {
            String returnUrl = "public/return.jsp";


            Map<String, String> results = new HashMap<String, String>();
            results.putAll((Map<String, String>) session.getAttribute("RESULT"));

            r.equest.setAttribute("result", results);
            r.equest.setAttribute("ack", (String) session.getAttribute("ACK"));
            Float amount = Float.parseFloat((String) session.getAttribute("amountVND"));
            r.equest.setAttribute("amountVND", amount);
            r.equest.setAttribute("redirectLink", "home");
            String contractCode = (String) session.getAttribute("CONTRACT_CODE");
            String paypalTransId = results.get("PAYMENTINFO_0_TRANSACTIONID");
            String paymentMethod = "PayPal";
            String paymentContent = (String) session.getAttribute("descVN");

            RegisterBusiness registerBusiness = new RegisterBusiness();
            String result = registerBusiness.updateContractPayment(contractCode, paymentMethod, paymentContent, amount, paypalTransId);
            if (result == null) {
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
                r.equest.setAttribute("message", "Thanh toán thành công");
                return new JspPage(returnUrl);
            }
            else {
                session.removeAttribute("cancel_message");
                r.equest.setAttribute("cancel_message",result);
                return new JspPage("/public/cancel.jsp");
            }


        }
        //r.equest.setAttribute("error","Phiên giao dịch đã hết thời gian");
        return new RedirectTo(errorUrl);
    }

}

