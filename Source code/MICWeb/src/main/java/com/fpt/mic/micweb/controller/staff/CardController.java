package com.fpt.mic.micweb.controller.staff;

import com.fpt.mic.micweb.controller.common.AuthController;
import com.fpt.mic.micweb.framework.Paginator;
import com.fpt.mic.micweb.framework.R;
import com.fpt.mic.micweb.framework.responses.JspPage;
import com.fpt.mic.micweb.framework.responses.RedirectTo;
import com.fpt.mic.micweb.framework.responses.ResponseObject;
import com.fpt.mic.micweb.model.business.CardBusiness;
import com.fpt.mic.micweb.model.business.StaffBusiness;
import com.fpt.mic.micweb.model.dto.UserDto;
import com.fpt.mic.micweb.model.dto.form.RecycleCardDto;
import com.fpt.mic.micweb.model.entity.CardInstanceEntity;

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
        String cardId = r.equest.getParameter("cardId");

        // Get id from fail validation
        if (cardId == null) {
            cardId = (String) r.equest.getAttribute("cardId");
        }

        // Call to business
        final CardBusiness cardBusiness = new CardBusiness();
        CardInstanceEntity cardEntity = cardBusiness.getLastActiveCardInnstance(cardId);

        final String finalCardId = cardId;
        calPaginator.setGetItemsCallback(new Paginator.IGetItems() {
            @Override
            public List getItems(int offset, int count) {
                return cardBusiness.getCardAccessLog(finalCardId, offset, count);
            }
        });
        calPaginator.setGetItemSizeCallback(new Paginator.IGetItemSize() {
            @Override
            public Long getItemSize() {
                return cardBusiness.getCardAccessLogCount(finalCardId);
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
        r.equest.setAttribute("map", newCardMappingRequest);
        r.equest.setAttribute("requestPaginator", requestPaginator);
        r.equest.setAttribute("unresolvedRequestCount", staffBus.getUnresolvedNewCardRequestCount());
        return new JspPage("staff/new-card-requests.jsp");
    }

    public ResponseObject postRecycle(R r) {
        RecycleCardDto dto = (RecycleCardDto)
                r.ead.entity(RecycleCardDto.class, "recycle");
        List errors = r.ead.validate(dto);

        // If there is validation errors
        if (errors.size() > 0) {
            // Send error messages to JSP page
            r.equest.setAttribute("validateErrors", errors);
            r.equest.setAttribute("cardId", dto.getCardId());
            // Re-call the create page
            return getDetail(r);
        }

        CardBusiness cardBusiness = new CardBusiness();
        cardBusiness.recycleCard(dto);

        return new RedirectTo("/staff/card?action=detail&cardId=" + dto.getCardId());
    }
}
