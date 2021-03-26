$(document).ready(function () {
    $("#storeAnswersId").click(function () {
        storingAnswers();
        alert("Thank you for filling survey, we've received your answers!");

    })
})


function storingAnswers() {
    let submission = {
        "answeredStored": [],
        "surveyCode": ""
    };
    submission.surveyCode = $(".surveyCodeClass").attr("id");
    for (i = 0; i < $(".numQuestions").attr('id'); i++) {
        if (!$(".openEnded").length) {
            console.log("openEnded class is empty")
        } else {
            [].concat($(".openEnded")).forEach((e) => {
                submission.answeredStored.push({
                    "questionId": e.data("id"),
                    "answer": e.val()

                });
            });
        }

        if (!$(".histoQuestion").length) {
            console.log("HistoQuestion class is empty")
        } else {
            [].concat($(".histoQuestion")).forEach((e) => {
                submission.answeredStored.push({
                    "questionId": e.data("id"),
                    "answer": e.val()
                });
            });
        }

        if (!$(".optionQuestion").length) {
            console.log("optionQuestion class is empty")
        } else {
            [].concat($(".optionQuestion")).forEach((e) => {
                submission.answeredStored.push({
                    "questionId": e.data("id"),
                    "answer": e.find(":selected").text()
                });
            });
        }
    }

    console.log(JSON.stringify(submission));
    let submissionData = JSON.stringify(submission);
    $.ajax({
        type: "POST",
        url: "/answersStored",
        contentType: "application/json; charset=utf-8",
        data: submissionData,
        dataType: 'json',
        success: (e) => {
            alert("Thank you for filling survey, we've received your answers!");
        },
        fail: (e) => {
            alert("SOMETHING WENT WRONG!");
        }
    });
}

