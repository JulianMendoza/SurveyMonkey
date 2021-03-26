$(document).ready(function(){
    $("#storeAnswersId").click(function(){
    storingAnswers();
    alert("Thank you for filling survey, we've received your answers!");

    })
})


function storingAnswers(){
let submission = {
        "answeredStored":[],
        "surveyCode":""
};

submission.surveyCode= $(".surveyCodeClass").attr("id");

if(!$(".openEnded").length){
}else{
[].concat($(".openEnded")).forEach((e) =>{
    submission.answeredStored.push({
        "questionId" : e.data("id"),
        "answer" : e.val()

    });
      console.log(e.data("id"));
      console.log(e.val());

  });
};

if(!$(".histoQuestion").length){
}else{
[].concat($(".histoQuestion")).forEach((e) =>{
     submission.answeredStored.push({
         "questionId" : e.data("id"),
         "answer" : e.val()
     });
   });
}

if(!$(".optionQuestion").length){
}else{
[].concat($(".optionQuestion")).forEach((e) =>{
    submission.answeredStored.push({
        "questionId" : e.data("id"),
        "answer" :e.find(":selected").text()
    });
  });
};


console.log(JSON.stringify(submission));
    let submissionData = JSON.stringify(submission);
    $.ajax({
          type:"POST",
          url:"/answersStored",
          contentType: "application/json; charset=utf-8",
          data:submissionData,
          dataType:'json',
          success: (e) => {
                     alert("Thank you for filling survey, we've received your answers!");
                     },
          fail:(e)=>{
                      console.log(e);
                      alert("SOMETHING WENT WRONG!");
                  }
      });
}

