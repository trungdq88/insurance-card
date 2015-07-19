/*
-- Query: SELECT * FROM mic_data.mic_business_rules
LIMIT 0, 1000

-- Date: 2015-07-05 16:13
*/
INSERT INTO `mic_business_rules` (`id`,`start_date_before`,`start_date_after`,`contract_min_term`,`contract_default_term`,`paid_date_before`,`paid_date_after`,`cancel_date_before`,`cancel_date_after`,`payment_due_date`,`nearly_exceed_expired_one`,`nearly_exceed_expired_two`,`nearly_exceed_expired_three`,`new_card_request_fee`,`delivery_fee`,`update_contract_due_date`) VALUES (1,7,60,1,12,7,30,7,30,7,15,7,3,50000,10000,30);
