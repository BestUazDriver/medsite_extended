<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script src="js/test.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.13.2/jquery-ui.min.js"></script>
<script>
	$(function() {
		$(".datepicker").datepicker({
			dateFormat: 'dd.mm.yy'
		});
		// var maxDatepickerDate = new Date((new Date($.now())).valueOf() - 1000*3600*24);
		// $(".datepicker").datepicker('option', 'maxDate', maxDatepickerDate);
	});
</script>
<div><form autocomplete="off">	От: <input type="text" name="startDate" class="datepicker" value="${startDate}" style="width: 200px;">
     			До: <input type="text" name="endDate" class="datepicker" value="${endDate}" style="width: 200px;"></form></div>