$(document).ready(() => {
    let surveyCode = {"data": $(".surveyCode").attr("id")};
    $.ajax({
        type: "POST",
        url: "/surveyResult",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(surveyCode),
        dataType: 'json',
        success: (e) => {
            let questions=e;
            for(let property in questions){
                switch(questions[property].question.questionType){
                    case "Open Ended":createOpenEndedView(questions[property].question,questions[property].answers); break;
                    case "Histogram":createHistogramView(questions[property].question,questions[property].answers);break;
                    case "Option":createOptionView(questions[property].question,questions[property].answers);break;
                }
            }
        }, fail: (e) => {
            alert("Something went wrong!");
        }
    });
});
function createOpenEndedView(question,answers){
    let table=document.createElement("table");
    table.setAttribute("class","table table-bordered")
    let tableTitle=document.createElement("tr");
    tableTitle.setAttribute("colspan",2);
    let title=document.createElement("h3");
    let tr=document.createElement("tr");
    tr.setAttribute("scope","col");
    tr.innerHTML="Answers";
    title.append("Question:"+question.question);
    tableTitle.append(title);
    table.append(tableTitle);
    table.append(tr);
    for(let answer in answers){
        let row=document.createElement("tr");
        row.setAttribute("scope","row");
        row.innerHTML=answers[answer].answer;
        $(table).append(row);
    }
    $('body').append(table);
}
function createHistogramView(e){

}
function createOptionView(e){

}