package com.fpt.mic.micweb.utils;

/**
 * FPT University - Capstone Project - Summer 2015 - MICWeb
 * Created by dinhquangtrung on 6/10/15.
 */
public class Constants {
    public static class ContractStatus {
        public static final String PENDING = "Pending";
        public static final String NO_CARD = "No card";
        public static final String READY = "Ready";
        public static final String EXPIRED = "Expired";
        public static final String REQUEST_CANCEL = "Request cancel";
        public static final String CANCELLED = "Cancelled";
    }

    public static class DueDate {
        public static final int PAYMENT_DUE_DATE = 7;
        public static final int RENEW_DUE_DATE = 7;
        public static final int NEARLY_EXCEED_EXPIRED_1 = 15;
        public static final int NEARLY_EXCEED_EXPIRED_2 = 8;
        public static final int NEARLY_EXCEED_EXPIRED_3 = 3;
    }

    public static class StaffConfiguration {
        public static final int START_DATE_BEFORE = 7;
        public static final int START_DATE_AFTER = 60;
        public static final int EXPIRED_DATE_AFTER = 1;
        public static final int CONTRACT_DEFAULT_TERM = 1;
        public static final int PAID_DATE_BEFORE = 7;
        public static final int PAID_DATE_AFTER = 7;
        public static final int CANCEL_DATE_BEFORE = 7;
        public static final int CANCEL_DATE_AFTER = 30;
    }

    public static class Session {
        public static final String USER_DTO = "userDto";
        public static final String CONCURRENCY = "concurrency_";
    }

    public static class ContractModify {
        public static final String CUSTOMER_REQUEST_CANCEL = "Khách hàng đã gửi yêu cầu hủy hợp đồng này trước đó";
        public static final String CUSTOMER_PAYMENT_CONTRACT = "Khách hàng đã thanh toán cho hợp đồng này trước đó";
        public static final String CUSTOMER_REJECT_CANCEL_REQUEST = "Khách hàng đã xóa yêu cầu hủy hợp đồng này trước đó";
        public static final String CUSTOMER_RENEW_CONTRACT = "Khách hàng đã thực hiện gia hạn hợp đồng này trước đó";
        public static final String CUSTOMER_REGISTER_CONTRACT = "Khách hàng đã đăng ký mới hợp đồng này";
        public static final String SCHEDULER_EXCEED_PAYMENT = "Hợp đồng này đã bị hủy vì quá hạn thanh toán";
        public static final String SCHEDULER_CONTRACT_EXPIRED = "Hợp đồng này đã hết hạn";
        public static final String SCHEDULER_CONTRACT_STATUS_CHANGED = "Trạng thái của hợp đồng này đã bị thay đổi trước đó";
        public static final String STAFF_CANCEL_CONTRACT = "Một nhân viên đã hủy hợp đồng này trước đó";
        public static final String STAFF_PAYMENT_CONTRACT = "Một nhân viên đã thanh toán cho hợp đồng này trước đó";
        public static final String STAFF_CREATE_CONTRACT = "Một nhân viên đã tạo mới hợp đồng này";
        public static final String STAFF_CHANGED_CONTRACT_INFO = "";
        public static final String STAFF_REJECT_CANCEL_CONTRACT = "Một nhân viên đã giải quyết yêu cầu hủy hợp đồng này trước đó";
        public static final String STAFF_RENEW_CONTRACT = "Một nhân viên đã gia hạn cho hợp đồng này trước đó";
    }
}
