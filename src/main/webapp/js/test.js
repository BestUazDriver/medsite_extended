function sout(){
alert('lol');
 alert('${message}');
}
$(function() {
   $('#selectActsInPeriod').click(function() {
        var start = $.datepicker.parseDate('dd.mm.yy', $('#startDate').val());
        var end = $.datepicker.parseDate('dd.mm.yy', $('#endDate').val());

        $('.print-docs tr').each(function() {
            var $this = $(this);
            var textDate = $this.find(".act-date").text();
            var curDate = $.datepicker.parseDate('dd.mm.yy', textDate);
            var isChecked = (curDate >= start && curDate < end);
            $this.find('input[type=checkbox]').prop('checked', isChecked);
        });

        $('#download-acts-form').submit();
    });
});