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
import com.fpt.mic.micweb.model.dto.form.RecycleCardDto;
import com.fpt.mic.micweb.model.entity.CardInstanceEntity;
import com.fpt.mic.micweb.model.entity.StaffEntity;
import com.fpt.mic.micweb.utils.ConfigUtils;

import javax.servlet.annotation.WebServlet;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
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

    private static String msg = "";
    private static boolean isSuccess;

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

    public ResponseObject getSearch(R r) {
        String keyword = r.equest.getParameter("keyword");
        if (keyword == null) {
            keyword = "";
        }
        keyword = keyword.trim();

        final CardBusiness cardBusiness = new CardBusiness();
        final String finalKeyword = keyword;
        cardPaginator.setGetItemsCallback(new Paginator.IGetItems() {
            @Override
            public List getItems(int offset, int count) {
                return cardBusiness.searchIssuedCard(finalKeyword, offset, count);
            }
        });
        cardPaginator.setGetItemSizeCallback(new Paginator.IGetItemSize() {
            @Override
            public Long getItemSize() {
                return cardBusiness.searchIssuedCardCount(finalKeyword);
            }
        });

        r.equest.setAttribute("cardPaginator", cardPaginator);
        return new JspPage("staff/cards.jsp");
    }

    public ResponseObject getDetail(R r) {
        String cardId = r.equest.getParameter("cardId");

        // Filter
        final String strFilterBegin = r.equest.getParameter("filter-begin");
        final String strFilterEnd = r.equest.getParameter("filter-end");


        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        Date filterBegin = null;
        Date filterEnd = null;

        try {
            filterBegin = df.parse(strFilterBegin);
            filterEnd = df.parse(strFilterEnd);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            // e.printStackTrace();
        }

        // Get id from fail validation
        if (cardId == null) {
            cardId = (String) r.equest.getAttribute("cardId");
        }

        if (cardId == null) {
            return new RedirectTo("/error/404");
        }

        // Call to business
        final CardBusiness cardBusiness = new CardBusiness();
        final CardInstanceEntity cardInstance = cardBusiness.getLastActiveCardInnstance(cardId);


        if (filterBegin == null || filterEnd == null || filterBegin.equals(filterEnd)) {
            // No filter, get all the access log
            calPaginator.setGetItemsCallback(new Paginator.IGetItems() {
                @Override
                public List getItems(int offset, int count) {
                    return cardBusiness.getCardInstanceAccessLog(
                            cardInstance.getId(), offset, count);
                }
            });
            calPaginator.setGetItemSizeCallback(new Paginator.IGetItemSize() {
                @Override
                public Long getItemSize() {
                    return cardBusiness.getCardInstanceAccessLogCount(
                            cardInstance.getId());
                }
            });
        } else {
            // There is filter to the access log
            final Date finalFilterBegin = filterBegin;
            final Date finalFilterEnd = filterEnd;
            calPaginator.setGetItemsCallback(new Paginator.IGetItems() {
                @Override
                public List getItems(int offset, int count) {
                    return cardBusiness.searchCardInstanceAccessLog(
                            cardInstance.getId(), finalFilterBegin, finalFilterEnd,
                            offset, count);
                }
            });
            calPaginator.setGetItemSizeCallback(new Paginator.IGetItemSize() {
                @Override
                public Long getItemSize() {
                    return cardBusiness.searchCardInstanceAccessLogCount(
                            cardInstance.getId(), finalFilterBegin, finalFilterEnd);
                }
            });
        }

        List<CardInstanceEntity> listInstance = cardBusiness.getAllCardInstancesByCardID(cardId);

        r.equest.setAttribute("calPaginator", calPaginator);
        r.equest.setAttribute("CARD", cardInstance);
        r.equest.setAttribute("INSTANCES", listInstance);
        return new JspPage("staff/card-detail.jsp");
    }

    public ResponseObject getNewCardRequest(R r) {
        final StaffBusiness staffBus = new StaffBusiness();

        Paginator p = (Paginator) r.equest.getAttribute("requestPaginator");

        if (p != null) {
            requestPaginator = p;
        } else {
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
        }
        CardBusiness cardBusiness = new CardBusiness();
        Map<Integer, String> newCardMappingRequest
                = cardBusiness.getMappingWithNewCardRequest();
        ConfigUtils configUtils = new ConfigUtils();
        r.equest.setAttribute("config", configUtils);
        r.equest.setAttribute("newCardFee", configUtils.getNewCardFee());
        r.equest.setAttribute("deliveryFee", configUtils.getDeliveryFee());

        r.equest.setAttribute("map", newCardMappingRequest);
        r.equest.setAttribute("requestPaginator", requestPaginator);
        r.equest.setAttribute("unresolvedRequestCount", staffBus.getUnresolvedNewCardRequestCount());
        return new JspPage("staff/new-card-requests.jsp");
    }

    public ResponseObject getNewCardRequestSearch(R r) {
        String keyword = r.equest.getParameter("keyword");
        if (keyword == null) {
            keyword = "";
        }
        keyword = keyword.trim();
        final StaffBusiness staffBus = new StaffBusiness();
        final String finalKeyword = keyword;
        requestPaginator.setGetItemsCallback(new Paginator.IGetItems() {
            @Override
            public List getItems(int offset, int count) {
                return staffBus.searchOnePageNewCardRequest(finalKeyword, offset, count);
            }
        });
        requestPaginator.setGetItemSizeCallback(new Paginator.IGetItemSize() {
            @Override
            public Long getItemSize() {
                return staffBus.searchAllNewCardRequestCount(finalKeyword);
            }
        });
        r.equest.setAttribute("requestPaginator", requestPaginator);

        return getNewCardRequest(r);
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

    public ResponseObject postCreateNewCardPayment(R r) {
        CreateNewCardPaymentDto createNewCardPaymentDto = (CreateNewCardPaymentDto) r.ead.entity(CreateNewCardPaymentDto.class, "createNewCardPayment");
        List errors = r.ead.validate(createNewCardPaymentDto);
        // If there is validation errors
        if (errors.size() > 0) {
            // Send error messages to JSP page
            r.equest.setAttribute("validateErrors", errors);
            // This is a form in a popup, we don't need to display data again since
            // the popup will not automatically open when the page is reloaded
            r.equest.setAttribute("content", r.equest.getParameter("content"));
            r.equest.setAttribute("amount", r.equest.getParameter("amount"));
            r.equest.setAttribute("submitted", createNewCardPaymentDto);
            // Re-call the contract detail page
            return getNewCardRequest(r);
        }
        PaymentBusiness paymentBusiness = new PaymentBusiness();
        String contractCode = createNewCardPaymentDto.getContractCode();
        // neu thanh toan thanh cong
        if (null != paymentBusiness.createNewCardRequestPayment(createNewCardPaymentDto, ((StaffEntity) getLoggedInUser()).getStaffCode())) {
            // cap nhat request da paid
            CardBusiness cardBusiness = new CardBusiness();
            if (null != cardBusiness.updatePaidNewCardRequest(contractCode)) {
                isSuccess = true;
                msg = "Đã thêm thông tin thanh toán thành công";
            } else {
                isSuccess = false;
                msg = "Thêm thông tin thanh toán thất bại";
            }
        } else {
            isSuccess = false;
            msg = "Thêm thông tin thanh toán thất bại";
        }
        // Set contract code to request scope. Use it in message page.
        r.equest.setAttribute("CODE", contractCode);
        r.equest.setAttribute("MESSAGE", msg);
        r.equest.setAttribute("SUCCESS", isSuccess);
        return new JspPage("staff/message.jsp");
        //return new RedirectTo("/staff/card?action=newCardRequest");
    }

    public ResponseObject postCancelNewCardRequest(R r) {
        CardBusiness cardBusiness = new CardBusiness();
        String contractCode = r.equest.getParameter("contractCode");
        if (cardBusiness.cancelNewCardRequest(contractCode)) {
            // thanh cong
            return new RedirectTo("/staff/card?action=newCardRequest&info=cancelNewCardRequestSuccess");
        } else {
            return new RedirectTo("/staff/card?action=newCardRequest&info=fail&code=");
        }

    }
}
