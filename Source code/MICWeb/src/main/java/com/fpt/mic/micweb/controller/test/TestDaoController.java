package com.fpt.mic.micweb.controller.test;

import com.fpt.mic.micweb.framework.BasicController;
import com.fpt.mic.micweb.framework.responses.JsonString;
import com.fpt.mic.micweb.framework.responses.JspPage;
import com.fpt.mic.micweb.framework.R;
import com.fpt.mic.micweb.framework.responses.ResponseObject;
import com.fpt.mic.micweb.model.dao.CardInstanceDao;
import com.fpt.mic.micweb.model.dao.ContractDao;
import com.fpt.mic.micweb.model.dao.StaffDao;
import com.fpt.mic.micweb.model.entity.CardInstanceEntity;
import com.fpt.mic.micweb.model.entity.ContractEntity;
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
        CardInstanceDao cardInstanceDao = new CardInstanceDao();

        // Create card entity
        CardInstanceEntity cardEntity = new CardInstanceEntity();
        cardEntity.setCardId("123123");
        cardEntity.setActivatedDate(new Timestamp(123123123));
        // cardEntity.setDeactivatedDate(); // Don't have this value yet
        // cardEntity.setNewCardRequestId(); // Don't have this value yet
        cardEntity.setContractCode("CON001"); // Contract CON001 must be exists or there will be errors

        CardInstanceEntity result = cardInstanceDao.create(cardEntity);
        return new JspPage("test/testdao/newcard.jsp");

    }

    public ResponseObject getNewId(R r) {
        StaffDao staffDao = new StaffDao();
        String incrementId = staffDao.getIncrementId();
        return new JsonString(incrementId);
    }

    public ResponseObject getContract(R r) {
        ContractDao contractDao = new ContractDao();
        ContractEntity contract = contractDao.read("HD0ES7");
        r.equest.setAttribute("contract", contract);
        return new JsonString(contract);
    }
}
