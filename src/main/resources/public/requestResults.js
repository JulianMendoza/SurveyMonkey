$(document).ready(()=> {
    let surveyCode = {"data":$(".surveyCode").attr("id")}
	$.ajax({
        type:"POST",
        url:"/surveyResults",
        contentType: "application/json; charset=utf-8",
        data:surveyCode,
        dataType:'json',
        success:(e)=>{
            $('body').html(
});