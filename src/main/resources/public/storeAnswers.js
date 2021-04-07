$(document).ready(function () {
    $("#storeAnswersId").click(function () {
        if (validate()) {
            storingAnswers();
            alert("Thank you for filling survey, we've received your answers!");
        } else {
            alert("Please make sure you fill the fields!");
        }
    });
})

function validate() {
    for (i = 0; i < $(".numQuestions").attr('id'); i++) {
        if ($(".openEnded" + i).length) {
            if ($(".openEnded" + i).val() === "") {
                return false;
            }
        }
        if ($(".histoQuestion" + i).length) {
            if ($(".histoQuestion" + i).val() === "") {
                return false;
            }
        }
        if ($(".optionQuestion" + i).length) {
            if ($(".optionQuestion" + i).find(":selected").text() === "Select answer") {
                return false;
            }
        }
    }
    return true;
}

function storingAnswers() {
    let submission = {
        "answeredStored": [],
        "surveyCode": ""
    };
    submission.surveyCode = $(".surveyCodeClass").attr("id");
    for (i = 0; i < $(".numQuestions").attr('id'); i++) {
        if ($(".openEnded" + i).length) {
            [].concat($(".openEnded" + i)).forEach((e) => {
                submission.answeredStored.push({
                    "questionId": e.data("id"),
                    "answer": e.val()
                });
            });
        }
        if ($(".histoQuestion" + i).length) {
            [].concat($(".histoQuestion" + i)).forEach((e) => {
                submission.answeredStored.push({
                    "questionId": e.data("id"),
                    "answer": e.val()
                });
            });
        }
        if ($(".optionQuestion" + i).length) {
            [].concat($(".optionQuestion" + i)).forEach((e) => {
                submission.answeredStored.push({
                    "questionId": e.data("id"),
                    "answer": e.find(":selected").text()
                });
            });
        }
    }
    let submissionData = JSON.stringify(submission);
    console.log(submissionData);
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

