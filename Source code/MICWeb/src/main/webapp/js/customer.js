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

});