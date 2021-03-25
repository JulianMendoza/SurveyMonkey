let submission = {
        "answeredStored":[]
};

//if(typeof openEnded === 'class'){

if($(".openEnded").val()===""){
console.log("openEnded class is not empty");
}else{
console.log("openEnded class is empty");
$(".openEnded").forEach((e) =>{
    submission.answeredStored.append {[
        "questionId" : e.data("id");
        "answer" : e.val();
    ]}
  });
}
if($(".HistoQuestion").val() === ""){
 console.log("HistoQuestion class is not empty")
}else{
console.log("HistoQuestion class is empty");
$(".HistoQuestion").forEach((e) =>{
     submission.answeredStored.append {[
         "questionId" : e.data("id");
         "answer" : e.val();
     ]}
   });
}

if($(".OptionQuestion").val() === ""){
console.log("optionQuestion class is empty")
}else{
  $(".OptionQuestion").forEach((e) =>{
      submission.answeredStored.append {[
          "questionId" : e.data("id");
          "answer" : e.val();
      ]}
    });

}

console.log(JSON.stringify(submission));
    let submissionData = JSON.stringify(submission);
    $.ajax({
          type:"POST",
          url:"/answersStored",
          contentType: "application/json; charset=utf-8",
          data:submissionData,
          dataType:'json'
      });


