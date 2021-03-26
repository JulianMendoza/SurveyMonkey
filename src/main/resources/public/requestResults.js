$(document).ready(()=> {
    let surveyCode = {"data":$(".surveyCode").attr("id")};
	$.ajax({
        type:"POST",
        url:"/surveyResult",
        contentType: "application/json; charset=utf-8",
        data:surveyCode,
        dataType:'json',
        success:(e)=>{
            $('body').append('<div>' + e + '</div>');
			
    },fail:(e)=>{
            alert("Something went wrong!");
        }
	});
});