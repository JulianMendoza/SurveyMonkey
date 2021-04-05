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
function createHistogramView(question,answers){
    let div=document.createElement("div");
    let canvas=document.createElement("canvas");
    canvas.setAttribute("id","histo"+question.questionId);
    $('body').append(canvas);
    let labels=[];
    for(let i=1;i<=question.maxVal;i+=question.stepSize){
        labels.push(i);
    }
    let data=new Array(labels.length).fill(0);
    for(let i=0;i<answers.length;i++){
        data[Math.floor((parseInt(answers[i].answer)/question.stepSize)-1)]++;
    }
    console.log(data);
    console.log(labels);
    var ctx=document.getElementById("histo"+question.questionId).getContext("2d");
    var chart=new Chart(ctx,{
        type:"bar",
        options:{
            layout:{
                padding:10,
            },
            legend:{
                position:"bottom",
            },
            title:{
                display:true,
                text:question.question,
            },
            scales: {
                yAxes: [{
                    scaleLabel: {
                        display: true,
                        labelString: "Number of people",
                    }
                }],
            }
        },
        data:{
            labels:labels,
            datasets:[{
                label:'Value user entered',
                data:data,
                backgroundColor:"rgba(75, 192, 192, 0.2)",
                borderColor:"rgb(75, 192, 192)",
                borderWidth:1
            }],
        },
    });
}
function createOptionView(e){

}