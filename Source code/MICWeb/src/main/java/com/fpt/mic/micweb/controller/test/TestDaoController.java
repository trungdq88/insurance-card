package com.fpt.mic.micweb.controller.test;

import com.fpt.mic.micweb.framework.BasicController;
import com.fpt.mic.micweb.framework.JspPage;
import com.fpt.mic.micweb.framework.R;
import com.fpt.mic.micweb.framework.ResponseObject;
import com.fpt.mic.micweb.model.dao.CardDao;
import com.fpt.mic.micweb.model.dao.StaffDao;
import com.fpt.mic.micweb.model.entity.CardEntity;
import com.fpt.mic.micweb.model.entity.StaffEntity;

import javax.servlet.annotation.WebServlet;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by dinhquangtrung on 6/2/15.
 */
@WebServlet(name = "TestDaoController", urlPatterns =  {"/testdao"})
public class TestDaoController extends BasicController {
    StaffDao staffDao = new StaffDao();

    public ResponseObject getView(R r) {
        // Get all staffs
        List staffs = staffDao.getAllStaffs();
        r.equest.setAttribute("staffs", staffs);
        return new JspPage("test/testdao/view.jsp");
    }

    public ResponseObject getCreateStaff(R r) {
        StaffEntity staffEntity = new StaffEntity();

        // Create new staff entity
        // Notice: the staff code must not be duplicated!
        long randomId = Math.round(Math.random() * 1000); // This MIGHT BE duplicated
        staffEntity.setStaffCode("ABC123" + randomId);
        staffEntity.setEmail("email@email.com");
        staffEntity.setName("name");
        staffEntity.setPassword("password");
        staffEntity.setPhone("123123");
        StaffEntity result = staffDao.create(staffEntity);

        r.equest.setAttribute("result", result);
        return new JspPage("test/testdao/create.jsp");
    }

    public ResponseObject getUpdateStaff(R r) {
        // Get staff from database (make sure it exists before run this method)
        StaffEntity staff = staffDao.read("ABC123");

        staff.setEmail("UpdatedEmail@gmail.com");

        StaffEntity result = staffDao.update(staff);

        r.equest.setAttribute("result", result);
        return new JspPage("test/testdao/update.jsp");
    }

    public ResponseObject getDeleteStaff(R r) {
        // Get staff from database (make sure it exists before run this method or
        // it will show up errors)
        StaffEntity staff = staffDao.read("ABC123");
        staffDao.delete(staff);

        return new JspPage("test/testdao/delete.jsp");
    }

    /**
     * Customize query, example find staff by staff code
     * @param r
     * @return
     */
    public ResponseObject getFindStaff(R r) {
        // Get keyword
        String keyword = r.equest.getParameter("keyword");
        if (keyword == null || keyword.isEmpty()) keyword = "";

        List findResults = staffDao.findStaffsByStaffCode(keyword);
        r.equest.setAttribute("findResults", findResults);

        return new JspPage("test/testdao/find.jsp");
    }

    /**
     * This is an example of creating an entity with foreign key
     * @param r
     * @return
     */
    public ResponseObject getCreateNewCard(R r) {
        CardDao cardDao = new CardDao();

        // Create card entity
        CardEntity cardEntity = new CardEntity();
        cardEntity.setCardId("123123");
        cardEntity.setActivatedDate(new Timestamp(123123123));
        // cardEntity.setDeactivatedDate(); // Don't have this value yet
        // cardEntity.setNewCardRequestId(); // Don't have this value yet
        cardEntity.setContractCode("CON001"); // Contract CON001 must be exists or there will be errors

        CardEntity result = cardDao.create(cardEntity);
        return new JspPage("test/testdao/newcard.jsp");

    }

}
