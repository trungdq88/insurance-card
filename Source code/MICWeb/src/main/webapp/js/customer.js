/**
 * Created by PhucNH on 5/23/15.
 */

$(document).ready(function () {
    initialize();

    CheckDayAvailable();
    $('[data-toggle="tooltip"]').tooltip();
    if ($('#isFirstLogin').val() == 0) {
        $('#changePass').trigger("click");
    }
    $('#cancel').click(function () {
        var cutomerCode = $('#customerCode').val();
        $.ajax({
            url: '/ajax',
            method: 'post',
            data: {
                action: 'RejectChangePassword',
                customerCode: cutomerCode
            },
            dataType: 'json'
        }).done(function (msg) {

        });
    });
    $('#confirm').click(function () {
        var currentPass = $('#currentPass').val();
        var newPass = $('#newPass').val();
        var confirmPass = $('#confirmPass').val();
        $('#notify1').addClass('hide');
        $('#notify2').addClass('hide');
        if (currentPass != null && newPass != null && confirmPass != null) {
            if (confirmPass != newPass) {
                $('#notify2').removeClass('hide');
                return false;
            }
            if (currentPass == newPass) {
                $('#notify1').removeClass('hide');
                return false;
            }
        }
    });
    $('.textInFormation').removeClass('form-control')
    $('#btn_Modify').click(function () {
        $('.textInFormation').prop('disabled', false);
        $(this).addClass('hide');
        $('.textInFormation').removeClass('handleInput');
        $('.textInFormation').addClass('form-control')
        //$('#btn_Save').removeClass('hide');
        $('.saveInformation').removeClass('hide');
    });

    $('.tranformCost').removeClass('hide');
    $('.newCardCost').removeClass('hide');
    //$('#total_Cost').val(parseFloat($('#newCard_Cost').val()) + ' VND');
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
     ------------------------------------------Handle button contract detail------------------------------------------
     */
    handleShowingButton($('#contractStatus1').val());
    function handleShowingButton(status) {
        //Ready
        if (status == 'Ready') {
            if (DayDiff($('#expiredDate').val()) > 60) {
                $('#renew').addClass('hide');
            }
        }
        //Cancelled
        else if (status == 'Cancelled') {
            $('#renew').addClass('hide');
            $('#delete').addClass('hide');
        }
        //Expired
        else if (status == 'Expired') {
            if (DayDiff($('#expiredDate').val()) > 60) {
                $('#renew').addClass('hide');
            }
            $('#delete').addClass('hide');
        }
        //Pending
        else if (status == 'Pending') {
            $('#renew').addClass('hide');
            $('#delete').addClass('hide');
        }
        //Request cancel
        else if (status == 'Request cancel') {
            $('#renew').addClass('hide');
            $('#delete').addClass('hide');
        }
        //No card
        else if (status == 'No card') {
            if (DayDiff($('#expiredDate').val()) > 60) {
                $('#renew').addClass('hide');
            }
        }
    }

    /**
     -----------------------------------------------------------------------------------------------------------
     */
    /**
     ------------------------------------------Handle Cancel contract------------------------------------------
     */
    $('#rdbReason1').click(function () {
        $('.check').attr('checked', false);
        $('#reason').val('Xe cơ giới bị thu hồi đăng ký và biển số theo quy định của pháp luật');
        $('#notify').addClass('hide');
        $(this).prop('checked', true);
        $('#anotherReason').addClass('hide');

    });
    $('#rdbReason2').click(function () {
        $('.check').attr('checked', false);
        $('#reason').val('Xe cơ giới hết niên hạn sử dụng theo quy định của pháp luật');
        $('#notify').addClass('hide');
        $(this).prop('checked', true);
        $('#anotherReason').addClass('hide');
    });
    $('#rdbReason3').click(function () {
        $('.check').attr('checked', false);
        $('#reason').val('Xe cơ giới bị mất được cơ quan công an xác nhận');
        $('#notify').addClass('hide');
        $(this).prop('checked', true);
        $('#anotherReason').addClass('hide');
    });
    $('#rdbReason4').click(function () {
        $('.check').attr('checked', false);
        $('#reason').val('Xe cơ giới hỏng không sử dụng được hoặc bị phá huỷ do tai nạn giao thông được cơ quan công an xác nhận');
        $('#notify').addClass('hide');
        $(this).prop('checked', true);
        $('#anotherReason').addClass('hide');
    });
    $('#rdbAnother').click(function () {
        $('.check').attr('checked', false);
        $('#anotherReason').removeClass('hide');
        $('#notify').addClass('hide');
        $(this).prop('checked', true);
    });
    //$('#anotherReason').onkeydown(function(){
    //    if ($.trim($('#anotherReason').val()) == 0) {
    //        $('#notify').addClass('hide');
    //    }
    //});
    $("#anotherReason").keydown(function (event) {
        if ($.trim($('#anotherReason').val()) == 0) {
            $('#notify').addClass('hide');
        }
    });
    $('#deleteContract').click(function () {
        var result = false;
        $('.check').each(function () {
            if ($('#rdbAnother').is(":checked")) {
                if ($.trim($('#anotherReason').val()) == 0) {
                    return result;
                } else {
                    $('#reason').val($.trim($('#anotherReason').val()));
                    //alert('Bạn đã chọn lí co hủy là: ' + $('#anotherReason').val());
                    result = true;
                    return false;
                }
            }
            else if ($(this).is(":checked")) {
                //alert('Bạn đã chọn lí co hủy hợp đồng là: ' + $('#reason').val());
                $('#notify').addClass('hide');
                result = true;
                return false;
            }
            else {

                result = false;
            }
        });
        if (result == false) {
            //alert('Vui lòng chọn lí do hủy hợp đồng trước khi xác nhận! Cảm ơn!');
            $('#notify').removeClass('hide');
            return false;
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
    function DayDiff(date) {
        var oneDay = 24 * 60 * 60 * 1000;
        var timeNow = new Date();
        var expiredDate = new Date(date);
        return Math.round((expiredDate.getTime() - timeNow.getTime()) / (oneDay));
    }


    //DayDiff($('#expiredDate').val()) > 0 ? $('#dateAvailable').val("Còn hạn: " + Math.abs(DayDiff($('#expiredDate').val())) + ' ngày')
    //    : $('#dateAvailable').val("Quá hạn: " + Math.abs(DayDiff($('#expiredDate').val())) + ' ngày');

    function CheckDayAvailable() {
        var expiredDate = $('#expiredDate').val();

        if (DayDiff(expiredDate) >= 0) {
            $('#dateAvailable').val("Còn hạn: " + Math.abs(DayDiff($('#expiredDate').val())) + ' ngày');
        }
        else {
            $('#dateAvailable').val("Quá hạn: " + Math.abs(DayDiff($('#expiredDate').val())) + ' ngày');
        }
    }

    $('#renew').click(function () {
        if (DayDiff($('#expiredDate').val()) > 60) {
            $('#acceptRenew').prop('disabled', true);
            $('.alertRenew').removeClass('hide');
        }
        var status = $('#contractStatus').val();
        var myDate = null;
        if (status == 'Ready' || status == 'No card' || status == 'Pending') {
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
        var startDate = myDate;
        startDate = (startDate.getDate().toString().length > 1 ? startDate.getDate().toString() : '0' + startDate.getDate().toString()) + '/' +
            ((1 + startDate.getMonth()).toString().length > 1 ? (1 + startDate.getMonth()).toString() : '0' + (1 + startDate.getMonth()).toString()) + '/' +
            startDate.getFullYear();
        $('#content1').val("Gia hạn hợp đồng từ " + startDate + " đến " + day + '/' + month + '/' + year);
        $('#content2').val("Gia hạn hợp đồng từ " + startDate + " đến " + day + '/' + month + '/' + year);


    });
    /**
     ------------------------------------------------------------------------------------------------------------------
     */
    $('#payContract').click(function () {
        $('#payment1').val((parseFloat($('#payAmount1').val())));
        $('#paymentATM1').val((parseFloat($('#payAmount1').val())));
        var contractCode = $('#contractCode').val();
        $('#content3').val("Thanh toán cho hợp đồng " + contractCode);
    });
    /**
     * ---------------------------------Region add new punishment
     */
    $('.fp__btn').click(function () {
        $('#cancel').trigger("click");
    });

    $('#attImage').change(function () {
        $('#showPunishment').addClass('active');
        $('#punishments').addClass('active');
        $('#addPunishment').trigger("click");

    });

    $('input[type=file]').change(function (e) {
        var root = $(this).val();
        var array = root.split('\\');
        root = array[array.length - 1];
        $('#attch').val(root);
    });
    $("#contentPunishment").keydown(function (event) {
        if ($.trim($('#notifyPunishment').val()) == 0) {
            $('#notifyPunishment').addClass('hide');
        }
    });
    $('#confirmPunishment').click(function () {
        if ($.trim($('#contentPunishment').val()) == 0) {
            $('#notifyPunishment').removeClass('hide');
            return false;
        }
        if ($('#attachment').val() == '') {
            $('#notifyPunishment1').removeClass('hide');
            return false;
        }
        $('#titlePunishment').val($('#contentPunishment').val());

    });
    /**
     * ---------------------------------Add new accident
     * **/
    $('#addAccident').click(function () {
        $('#saveAccident').removeClass('hide');
        $('#addMore').removeClass('hide');
        $('#accidentDate').val(getDateNow());
        var title = $('#').val();
        $.ajax({
            url: '/ajax',
            method: 'post',
            data: {
                action: 'AddAccident',
                customerCode: cutomerCode
            },
            dataType: 'json'
        }).done(function (msg) {

        });
    });
    function getDateNow() {
        var myDate = new Date();
        var year = myDate.getFullYear();
        var month = (1 + myDate.getMonth()).toString();
        month = month.length > 1 ? month : '0' + month;
        var day = myDate.getDate().toString();
        day = day.length > 1 ? day : '0' + day;
        return day + '/' + month + '/' + year;
    }
});