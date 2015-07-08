package com.fpt.mic.micweb.controller.staff;

import com.fpt.mic.micweb.controller.common.AuthController;
import com.fpt.mic.micweb.framework.Paginator;
import com.fpt.mic.micweb.framework.R;
import com.fpt.mic.micweb.framework.responses.JspPage;
import com.fpt.mic.micweb.framework.responses.RedirectTo;
import com.fpt.mic.micweb.framework.responses.ResponseObject;
import com.fpt.mic.micweb.model.business.CardBusiness;
import com.fpt.mic.micweb.model.business.PaymentBusiness;
import com.fpt.mic.micweb.model.business.StaffBusiness;
import com.fpt.mic.micweb.model.dto.UserDto;
import com.fpt.mic.micweb.model.dto.form.CreateNewCardPaymentDto;
import com.fpt.mic.micweb.model.entity.CardEntity;
import com.fpt.mic.micweb.model.entity.StaffEntity;
import com.fpt.mic.micweb.utils.ConfigUtils;
import com.fpt.mic.micweb.utils.Constants;

import javax.servlet.annotation.WebServlet;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dinhquangtrung on 5/23/15.
 */
@WebServlet(name = "CardController", urlPatterns = {"/staff/card"})
public class CardController extends AuthController {
    Paginator requestPaginator = new Paginator();
    Paginator cardPaginator = new Paginator();
    /* Card acess log pagination */
    Paginator calPaginator = new Paginator();

    @Override
    public List<String> getAllowedRoles() {
        return Collections.singletonList(UserDto.ROLE_STAFF);
    }

    public ResponseObject getView(R r) {
        final CardBusiness cardBusiness = new CardBusiness();
        cardPaginator.setGetItemsCallback(new Paginator.IGetItems() {
            @Override
            public List getItems(int offset, int count) {
                return cardBusiness.getIssuedCard(offset, count);
            }
        });
        cardPaginator.setGetItemSizeCallback(new Paginator.IGetItemSize() {
            @Override
            public Long getItemSize() {
                return cardBusiness.getIssuedCardCount();
            }
        });

        r.equest.setAttribute("cardPaginator", cardPaginator);
        return new JspPage("staff/cards.jsp");
    }

    public ResponseObject getDetail(R r) {
        final String cardId = r.equest.getParameter("cardId");

        // Call to business
        final CardBusiness cardBusiness = new CardBusiness();
        CardEntity cardEntity = cardBusiness.getCardDetail(cardId);

        calPaginator.setGetItemsCallback(new Paginator.IGetItems() {
            @Override
            public List getItems(int offset, int count) {
                return cardBusiness.getCardAccessLog(cardId, offset, count);
            }
        });
        calPaginator.setGetItemSizeCallback(new Paginator.IGetItemSize() {
            @Override
            public Long getItemSize() {
                return cardBusiness.getCardAccessLogCount(cardId);
            }
        });

        r.equest.setAttribute("calPaginator", calPaginator);
        r.equest.setAttribute("CARD", cardEntity);
        return new JspPage("staff/card-detail.jsp");
    }

    public ResponseObject getNewCardRequest(R r) {
        final StaffBusiness staffBus = new StaffBusiness();
        requestPaginator.setGetItemsCallback(new Paginator.IGetItems() {
            @Override
            public List getItems(int offset, int count) {
                return staffBus.getOnePageNewCardRequest(offset, count);
            }
        });
        requestPaginator.setGetItemSizeCallback(new Paginator.IGetItemSize() {
            @Override
            public Long getItemSize() {
                return staffBus.getAllNewCardRequestCount();
            }
        });
        CardBusiness cardBusiness = new CardBusiness();
        Map<Integer, String> newCardMappingRequest = new HashMap();
        newCardMappingRequest = cardBusiness.getMappingWithNewCardRequest();
        ConfigUtils configUtils = new ConfigUtils();
        r.equest.setAttribute("config",configUtils);
        r.equest.setAttribute("newCardFee", Constants.PaymentFee.NEW_CARD_REQUEST_FEE);
        r.equest.setAttribute("deliveryFee", Constants.PaymentFee.DELIVERY_FEE);

        r.equest.setAttribute("map", newCardMappingRequest);
        r.equest.setAttribute("requestPaginator", requestPaginator);
        r.equest.setAttribute("unresolvedRequestCount", staffBus.getUnresolvedNewCardRequestCount());
        return new JspPage("staff/new-card-requests.jsp");
    }

    public ResponseObject postCreateNewCardPayment(R r) {
        CreateNewCardPaymentDto createNewCardPaymentDto =(CreateNewCardPaymentDto) r.ead.entity(CreateNewCardPaymentDto.class,"createNewCardPayment");
        PaymentBusiness paymentBusiness = new PaymentBusiness();
        String contractCode = createNewCardPaymentDto.getContractCode();
        String msg = null;
        // neu thanh toan thanh cong
        if( null != paymentBusiness.createNewCardRequestPayment(createNewCardPaymentDto, ((StaffEntity) getLoggedInUser()).getStaffCode())) {
            // cap nhat request da paid
            CardBusiness cardBusiness = new CardBusiness();
            if (null != cardBusiness.updatePaidNewCardRequest(contractCode)){
                msg = "Đã thêm thông tin thanh toán thành công";
            } else  {
                msg = "Thêm thông tin thanh toán thất bại";
            }
        } else {
            msg = "Thêm thông tin thanh toán thất bại";
        }
        // Set contract code to request scope. Use it in message page.
        r.equest.setAttribute("CODE", contractCode);
        r.equest.setAttribute("MESSAGE", msg);
        return new JspPage("staff/message.jsp");
        //return new RedirectTo("/staff/card?action=newCardRequest");
    }
}
