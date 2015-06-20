$(document).ready(function () {


    $('#btn_Modify').click(function () {
        $('.textInFormation').prop('disabled', false);
        $(this).addClass('hide');
        $('#btn_Save').removeClass('hide');
    });
    $('#btn_Save').click(function () {
        $('.textInFormation').prop('disabled', true);
        $(this).addClass('hide');
        $('#btn_Modify').removeClass('hide');
    });


    $('#radio_CDO').attr('checked', true);
    $('#total_Cost').val(parseFloat($('#newCard_Cost').val()) + ' VND');
    $('#radio_Paypal').change(function () {
        $('.tranformCost').removeClass('hide');
        $('.newCardCost').removeClass('hide');
        $('#radio_CDO').attr('checked', false);
        $('#total_Cost').val(parseFloat($('#newCard_Cost').val()) + parseFloat($('#transform_Cost').val()) + ' VND');
    });
    $('#radio_CDO').change(function () {
        $('.tranformCost').addClass('hide');
        $('#radio_Paypal').attr('checked', false);
        $('#total_Cost').val(parseFloat($('#newCard_Cost').val()) + ' VND');
    });

    //for punishment page
    for (var i = 1; i <= 10; i++) {
        $('#showPunishment_' + i).click(function () {
            window.open('http://img.tintuc.vietgiaitri.com/2014/2/25/phu-phep-700-bien-ban-hang-loat-can-bo-so-gtvt-bi-bat-394b03.jpeg', "_blank", "toolbar=yes, scrollbars=yes, resizable=yes, top=500, left=500, width=400, height=400");

        });
    }
    /**
     *********************************************contract detail **********************************************
     */
    /**
     ------------------------------------------Handle Cancel contract------------------------------------------
     */
    $('#rdbReason1').click(function () {
        $('.check').attr('checked', false);
        $('#reason').val('Xe cơ giới bị thu hồi đăng ký và biển số theo quy định của pháp luật');
        $(this).prop('checked', true);
        $('#anotherReason').addClass('hide');

    });
    $('#rdbReason2').click(function () {
        $('.check').attr('checked', false);
        $('#reason').val('Xe cơ giới hết niên hạn sử dụng theo quy định của pháp luật');
        $(this).prop('checked', true);
        $('#anotherReason').addClass('hide');
    });
    $('#rdbReason3').click(function () {
        $('.check').attr('checked', false);
        $('#reason').val('Xe cơ giới bị mất được cơ quan công an xác nhận');
        $(this).prop('checked', true);
        $('#anotherReason').addClass('hide');
    });
    $('#rdbReason4').click(function () {
        $('.check').attr('checked', false);
        $('#reason').val('Xe cơ giới hỏng không sử dụng được hoặc bị phá huỷ do tai nạn giao thông được cơ quan công an xác nhận');
        $(this).prop('checked', true);
        $('#anotherReason').addClass('hide');
    });
    $('#rdbAnother').click(function () {
        $('.check').attr('checked', false);
        $('#anotherReason').removeClass('hide');
        $('#reason').val($.trim($('#anotherReason').val()));
        $(this).prop('checked', true);
    });
    $('#reason').val($('#anotherReason').val());

    $('#deleteContract').click(function () {
        var result = false;
        $('.check').each(function () {
            if ($('#rdbAnother').is(":checked")) {
                if ($.trim($('#anotherReason').val()) == 0) {
                    return result;
                } else {
                    alert('Bạn đã chọn lí co hủy là: ' + $('#anotherReason').val());
                    result = true;
                    return false;
                }
            }
            else if ($(this).is(":checked")) {
                alert('Bạn đã chọn lí co hủy hợp đồng là: ' + $('#reason').val());
                result = true;
                return false;
            }
            else {
                result = false;
            }
        });
        if (result == false) {
            alert('Vui lòng chọn lí do hủy hợp đồng trước khi xác nhận! Cảm ơn!');
            return false;
        }
        if (result == true) {
            var r = confirm("Bạn thực sự muốn hủy hợp đồng này?");
            if (r == false) {
                return false;
            }
        }

    });
    $('#cancelAction').click(function () {
        $('.check').attr('checked', false);
        $('#anotherReason').val('');
        $('#anotherReason').addClass('hide');
    });
    /**
     ------------------------------------------------------------------------------------------------------------
     */
    /**
     -----------------------------------------Handle Renew Contract----------------------------------------------
     */
    $('#renew').click(function () {
        var status = $('#contractStatus').val();
        var myDate = null;
        if (status == 'Ready') {
            myDate = new Date($('#newStartDate').val());
        }
        else if (status == 'Expired') {
            myDate = new Date();
        }
        var year = myDate.getFullYear() + 1;
        var month = (1 + myDate.getMonth()).toString();
        month = month.length > 1 ? month : '0' + month;
        var day = myDate.getDate().toString();
        day = day.length > 1 ? day : '0' + day;
        $('#newExpiredDate').val(day + '/' + month + '/' + year);


        $('#payment').val((parseFloat($('#payAmount').val())));
        $('#paymentATM').val((parseFloat($('#payAmount').val())));
        /////
        var startDate = new Date($('#newStartDate').val());
        startDate = (startDate.getDate().toString().length > 1 ? startDate.getDate().toString() : '0' + startDate.getDate().toString()) + '/' +
            ((1 + startDate.getMonth()).toString().length > 1 ? (1 + startDate.getMonth()).toString() : '0' + (1 + startDate.getMonth()).toString()) + '/' +
            startDate.getFullYear();

        $('#content').val("Renew contract from " + startDate + " to " + day + '/' + month + '/' + year);
    });
    /**
     ------------------------------------------------------------------------------------------------------------------
     */
});


