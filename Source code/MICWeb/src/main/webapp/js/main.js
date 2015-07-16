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

function getInputDateWithoutTime(inputDate) {
    var today = inputDate;
    var nextDate = new Date(today.getFullYear(), today.getMonth(), today.getDate());
    var withoutTime = ("0000" + nextDate.getFullYear().toString()).slice(-4) + "-"
        + ("00" + (nextDate.getMonth() + 1).toString()).slice(-2) + "-" + ("00" + nextDate.getDate().toString()).slice(-2);
    return withoutTime;
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
//  hàm + tháng vào date
function addMonth(startDate, defaultTerm) {
    var array = startDate.split("-");
    var month = parseInt(defaultTerm);// get tu config
    var newMonth = parseInt((parseInt(array[1])+month)% 12);
    var newYear = parseInt(array[0])+((parseInt(array[1])+month)-newMonth)/12;
    if(newMonth<10){
        newMonth = '0'+ newMonth;
    }
    return newYear+'-'+newMonth+'-'+array[2];
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

function addZero(i) {
    if (i < 10) {
        i = "0" + i;
    }
    return i;
}

/**
 * Return date time from input is millisecond
 * @param longTime
 *
 * @author KhaNC
 * @version 1.0
 */

function getDateTime(longTime) {
    if (longTime) {
        // create a new javascript Date object based on the timestamp
        var returnDate = new Date(longTime);
        // date part of the timestamp
        var date = addZero(returnDate.getDate());
        // months part of the timestamp
        var months = addZero(returnDate.getMonth() + 1);
        // year part of the timestamp
        var years = returnDate.getFullYear();
        // hours part from the timestamp
        var hours = addZero(returnDate.getHours());
        // minutes part from the timestamp
        var minutes = addZero(returnDate.getMinutes());
        // seconds part from the timestamp
        var seconds = addZero(returnDate.getSeconds());

        // will display time in dd/MM/yyyy lúc hh:mm:ss format
        return date + '/' + months + '/' + years + ' lúc ' + hours + ':' + minutes + ':' + seconds;
    }
}