let x = 0;
function appendDiv(val){
    for (let i = x; i < x+val; i++) {
        let q="question-div"+i;
        let q1="question"+i;
        $("#question-section").append(
            "<div id="+q+">"+
            "<label htmlFor=\"question\">Enter question:</label>" +
            "<input type=\"text\" id="+q1+"><br>" +
            "<label htmlFor=\"type\">Enter question type: </label>" +
            "<select id=\"type\">" +
            "<option value=\"Histogram\">Histogram</option>" +
            "<option value=\"Open Ended\">Open Ended</option>" +
            "<option value=\"Option\">Option</option>" +
            "</select><br>"+
            "</div>");
    }
}
function deleteDiv(val){
    for (let i = x; i >=val; i--) {
        $("#question-section").find($("#question-div"+i)).remove();
    }
}
$(document).ready(()=> {
    $("#generateQuestions").click(() => {
        let num = parseInt($("#numQuestions").val());
        if(num>=1&&num<=15) {
            if (num > x) {
                appendDiv(num - x);
            } else if (num < x) {
                deleteDiv(num);
            }
            x = num;
        }
    });
});