/**
 * Created by dinhquangtrung on 5/23/15.
 */

$(function () {
    $('[data-toggle="tooltip"]').tooltip();
    $('[data-toggle="popover"]').popover();
});

function getCurrentDate() {
    var now = new Date();
    var curYear = now.getFullYear();
    var curYearMonth = now.getMonth() + 1;
    var curYearDay = now.getDate();
    var today = ("0000" + curYear.toString()).slice(-4) + "-"
        + ("00" + curYearMonth.toString()).slice(-2) + "-" + ("00" + curYearDay.toString()).slice(-2);
    return today;
}

function getCurrentDateInLastWeek() {
    var now = new Date();
    var curYear = now.getFullYear();
    var curYearMonth = now.getMonth() + 1;
    var curYearDay = now.getDate();
    var lastWeekDay = curYearDay - 7;
    var lastWeek = ("0000" + curYear.toString()).slice(-4) + "-"
        + ("00" + curYearMonth.toString()).slice(-2) + "-" + ("00" + lastWeekDay.toString()).slice(-2);
    return lastWeek;
}

function getCurrentDateInNextYear() {
    var now = new Date();
    var curYear = now.getFullYear();
    var curYearMonth = now.getMonth() + 1;
    var curYearDay = now.getDate();
    var nextYear = curYear + 1;
    if ((curYearMonth == 2) && (curYearDay == 29)) {
        curYearDay = 28;
    }
    var nextYearDisplay = ("0000" + nextYear.toString()).slice(-4) + "-"
        + ("00" + curYearMonth.toString()).slice(-2) + "-" + ("00" + curYearDay.toString()).slice(-2);
    return nextYearDisplay;
}

function getInputDateInNextYear(inputDate) {
    var curYear = inputDate.substring(0, 4);
    var curYearMonth = inputDate.substring(5, 7);
    var curYearDay = inputDate.substring(8, 10);
    curYear = parseInt(curYear);
    var nextYear = curYear + 1;
    if ((curYearMonth == 02) && (curYearDay == 29)) {
        curYearDay = 28;
    }
    var nextYearDisplay = (nextYear + "-" + curYearMonth + "-" + curYearDay);
    return nextYearDisplay;
}

Number.prototype.formatMoney = function(c, d, t){
    var n = this,
        c = isNaN(c = Math.abs(c)) ? 2 : c,
        d = d == undefined ? "." : d,
        t = t == undefined ? "," : t,
        s = n < 0 ? "-" : "",
        i = parseInt(n = Math.abs(+n || 0).toFixed(c)) + "",
        j = (j = i.length) > 3 ? j % 3 : 0;
    return s + (j ? i.substr(0, j) + t : "") + i.substr(j).replace(/(\d{3})(?=\d)/g, "$1" + t) + (c ? d + Math.abs(n - i).toFixed(c).slice(2) : "");
};

/**
 * Number.prototype.format(n, x, s, c)
 * Source: http://stackoverflow.com/questions/149055
 *
 * @param integer n: length of decimal
 * @param integer x: length of whole part
 * @param mixed   s: sections delimiter
 * @param mixed   c: decimal delimiter
 */
Number.prototype.format = function(n, x, s, c) {
    var re = '\\d(?=(\\d{' + (x || 3) + '})+' + (n > 0 ? '\\D' : '$') + ')',
        num = this.toFixed(Math.max(0, ~~n));

    return (c ? num.replace('.', c) : num).replace(new RegExp(re, 'g'), '$&' + (s || ','));
};
