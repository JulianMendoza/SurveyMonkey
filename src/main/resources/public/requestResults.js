$(document).ready(()=> {
    console.log($(".surveyCode").attr("id"));
    let surveyCode = {"data":$(".surveyCode").attr("id")};
	$.ajax({
        type:"POST",
        url:"/surveyResult",
        contentType: "application/json; charset=utf-8",
        data:JSON.stringify(surveyCode),
        dataType:'json',
        success:(e)=>{
            $('body').append('<div>' + JSON.stringify(e) + '</div>');
			
    },fail:(e)=>{
            alert("Something went wrong!");
        }
	});
});