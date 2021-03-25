let submission = {
        "answeredStored":[]
};


if(!$(".openEnded").length){
}else{
[].concat($(".openEnded")).forEach((e) =>{
    submission.answeredStored.push({
        "questionId" : e.data("id"),
        "answer" : e.val()
    });
  });
};

//classes for histogram and option answers have not been created yet

//if($(".HistoQuestion").val() === ""){
// console.log("HistoQuestion class is not empty")
//}else{
//console.log("HistoQuestion class is empty");
//$(".HistoQuestion").forEach((e) =>{
//     submission.answeredStored.append({
//         "questionId" : e.data("id"),
//         "answer" : e.val()
//     });
//   });
//}

if(!$(".optionQuestion").length){
console.log("optionQuestion class is empty")
}else{
[].concat($(".optionQuestion")).forEach((e) =>{
    submission.answeredStored.push({
        "questionId" : e.data("id"),
        "answer" :e.find(":selected").text()
    });
  });
//console.log("data-id").find(":selected").text();
};


console.log(JSON.stringify(submission));
    let submissionData = JSON.stringify(submission);
    $.ajax({
          type:"POST",
          url:"/answersStored",
          contentType: "application/json; charset=utf-8",
          data:submissionData,
          dataType:'json',

      });


