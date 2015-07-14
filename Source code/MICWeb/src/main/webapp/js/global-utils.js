$(function () {
    $('form').on('submit', function () {
        $(this).find('[type=submit]').attr('disabled','disabled');
    });
});