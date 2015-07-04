package com.fpt.mic.micweb.controller.staff;

import com.fpt.mic.micweb.controller.common.AuthController;
import com.fpt.mic.micweb.framework.Paginator;
import com.fpt.mic.micweb.framework.responses.JspPage;
import com.fpt.mic.micweb.framework.R;
import com.fpt.mic.micweb.framework.responses.ResponseObject;
import com.fpt.mic.micweb.model.business.CardBusiness;
import com.fpt.mic.micweb.model.business.StaffBusiness;
import com.fpt.mic.micweb.model.dto.UserDto;

import javax.servlet.annotation.WebServlet;
import java.util.*;

/**
 * Created by dinhquangtrung on 5/23/15.
 */
@WebServlet(name = "CardController", urlPatterns = {"/staff/card"})
public class CardController extends AuthController {
    Paginator requestPaginator = new Paginator();
    @Override
    public List<String> getAllowedRoles() {
        return Collections.singletonList(UserDto.ROLE_STAFF);
    }
    public ResponseObject getView(R r) {
        return new JspPage("staff/cards.jsp");
    }
    public ResponseObject getDetail(R r) {
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
        Map<Integer,String> newCardMapping = new HashMap();
        newCardMapping = cardBusiness.getMappingWithNewCardRequest();
        r.equest.setAttribute("map",newCardMapping);
        r.equest.setAttribute("requestPaginator", requestPaginator);
        r.equest.setAttribute("unresolvedRequestCount",staffBus.getUnresolvedNewCardRequestCount());
        return new JspPage("staff/new-card-requests.jsp");
    }
}
