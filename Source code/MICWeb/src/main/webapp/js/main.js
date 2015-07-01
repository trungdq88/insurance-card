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

function getCurrentDateInNextWeek() {
    var now = new Date();
    var curYear = now.getFullYear();
    var curYearMonth = now.getMonth() + 1;
    var curYearDay = now.getDate();
    var nextWeekDay = curYearDay + 7;
    var nextWeek = ("0000" + curYear.toString()).slice(-4) + "-"
        + ("00" + curYearMonth.toString()).slice(-2) + "-" + ("00" + nextWeekDay.toString()).slice(-2);
    return nextWeek;
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

function getInputDateNextDate(inputDate) {
    var today = inputDate;
    var nextDate = new Date(today.getFullYear(), today.getMonth(), today.getDate() + 1);
    var nextDateDisplay = ("0000" + nextDate.getFullYear().toString()).slice(-4) + "-"
        + ("00" + (nextDate.getMonth() + 1).toString()).slice(-2) + "-" + ("00" + nextDate.getDate().toString()).slice(-2);
    return nextDateDisplay;
}

function getInputDateInNextYear(inputDate) {
    var curYear = inputDate.getFullYear();
    var curYearMonth = inputDate.getMonth() + 1;
    var curYearDay = inputDate.getDate();
    curYear = parseInt(curYear);
    var nextYear = curYear + 1;
    if ((curYearMonth == 02) && (curYearDay == 29)) {
        curYearDay = 28;
    }
    var nextYearDisplay = ("0000" + nextYear.toString()).slice(-4) + "-"
        + ("00" + curYearMonth.toString()).slice(-2) + "-" + ("00" + curYearDay.toString()).slice(-2);
    return nextYearDisplay;
}

function daysBetween(date1, date2) {
    //Get 1 day in milliseconds
    var one_day = 24 * 60 * 60 * 1000;

    if (date1 && date2) {
        // Covert both dates to milliseconds
        var date1_ms = date1.getTime();
        var date2_ms = date2.getTime();

        // Calculate the difference in milliseconds
        var diff_ms = date2_ms - date1_ms;

        // Convert back to days and return
        var diffDays = 0;
        if (diff_ms > 0) {
            diffDays = Math.ceil(diff_ms / one_day);
        }

        return diffDays;
    } else {
        return 0;
    }
}

function calculateContractFee(contractTerm, pricePerYear) {
    var nTerm = parseInt(contractTerm);
    var fPrice = parseFloat(pricePerYear);
    var contractFee = 0;

    if (nTerm == 365 || nTerm == 366) {
        contractFee = fPrice;
    } else if (nTerm >= 1 && nTerm <= 30) {
        contractFee = fPrice / 12;
    } else {
        contractFee = (fPrice / 365) * nTerm;
    }
    return contractFee;
}

/**
 * Number.prototype.format(c, d, t)
 * Source: http://stackoverflow.com/questions/149055
 *
 * @param integer c: length of decimal
 * @param integer d: length of sections
 * @param integer t:
 */
Number.prototype.formatMoney = function (c, d, t) {
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
 * Change label by contract code
 *
 * @param contractCode
 * @author KhaNC
 * @version 1.0
 */
function changeLabelByContractCode(contractStatus) {
    switch (contractStatus) {
        case "Pending":
            $('#contStatus').switchClass("label label-default", "label label-gray", 0);
            document.getElementById("contStatus").innerHTML = "Chưa kích hoạt";
            break;
        case "No card":
            $('#contStatus').switchClass("label label-default", "label label-primary", 0);
            document.getElementById("contStatus").innerHTML = "Chưa có thẻ";
            break;
        case "Ready":
            $('#contStatus').switchClass("label label-default", "label label-success", 0);
            document.getElementById("contStatus").innerHTML = "Sẵn sàng";
            break;
        case "Request cancel":
            $('#contStatus').switchClass("label label-default", "label label-warning", 0);
            document.getElementById("contStatus").innerHTML = "Yêu cầu huỷ";
            break;
        case "Expired":
            $('#contStatus').switchClass("label label-default", "label label-danger", 0);
            document.getElementById("contStatus").innerHTML = "Hết hạn";
            break;
        case "Cancelled":
            $('#contStatus').switchClass("label label-default", "label label-dark", 0);
            document.getElementById("contStatus").innerHTML = "Đã huỷ";
            break;
    }
}


function addZero(i) {
    if (i < 10) {
        i = "0" + i;
    }
    return i;
}

function getDateTime(longTime) {
    if (longTime) {
        var returnDate = new Date(longTime);

        var yearStr = returnDate.getFullYear();
        var monthStr = addZero(returnDate.getMonth() + 1);
        var dayStr = addZero(returnDate.getDay());
        var hourStr = addZero(returnDate.getHours());
        var minuteStr = addZero(returnDate.getMinutes());
        var secondStr = addZero(returnDate.getSeconds());

        return dayStr + "/" + monthStr + "/" + yearStr + " lúc " + hourStr + ":" + minuteStr + ":" + secondStr;
    }
}