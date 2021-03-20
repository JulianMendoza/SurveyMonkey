let x = 0; //number of questions that the user wants to add
/**
 * adds the div to create a new question
 * @param val the number of questions that will be additionally added to the div
 */
function appendDiv(val){
    for (let i = x; i < x+val; i++) {
        //very messy can be easily refactored using DOM
        let q="question-div"+i;
        let q1="question"+i;
        let s="selection-div"+i;
        $("#question-section").append(
            "<div id="+q+">"+
            "<label for=\"question\">Enter question:</label>" +
            "<input type=\"text\" id="+q1+"><br>" +
            "<label for=\"type\">Select question type: </label>" +
            "<div id="+s+">"+
            "<select id='type"+i+"'>" +
            "<option value=\"Open Ended\">Open Ended</option>" +
            "<option value=\"Histogram\">Histogram</option>" +
            "<option value=\"Option\">Option</option>" +
            "</select><br>"+
            "</div>"+
            "</div>");
        //event handler for selection change
        $("#type"+i).change((e)=>{
            //options should be wrapped in a single div but since DOM wasn't used this is a workaround
            //remove any existing option (if any) underneath the selection so it doesn't over populate the view
            $("#"+s).find($("#options")).remove();
            $("#"+s).find($("#option-input"+i)).remove();
            $("#"+s).find($("#histogram")).remove();
            switch($("select#type"+i).val()){
                case "Histogram":
                    $("#"+s).append(
                        "<div id='histogram'>"+
                        "<label for=\"min\">Enter minimum value:</label>"+
                        "<input type=\"number\" id='min' ><br>" +
                        "<label for=\"max\">Enter maximum value: </label>"+
                        "<input type=\"number\" id='max' ><br>" +
                        "<label for=\"step\">Enter step size: </label>"+
                        "<input type=\"number\" id='step' ><br>"+
                        "</div>"
                    );
                    break;
                case "Option":
                    $("#"+s).append(
                        "<form onsubmit=\"return(false)\" id='option-input"+i+"'>"+
                        "<input type=\"number\" min=\"1\" max=\"15\" step=\"1\" id=\"numOptions\"/>"+
                        "<button id='button"+i+"'>Change options</button>"+
                        "</form>"
                    );
                    //Event handler for buttons similar to the original questions
                    $("#button"+i).click(()=> {
                            let num = parseInt($("#option-input" + i).find($("input")).val());
                            if (num > 0 && num <= 15) {
                                $("#" + s).find("#options").remove();
                                let d = document.createElement("div");
                                $(d).attr('id', 'options');
                                for (let i = 0; i < num; i++) {
                                    $(d).append(
                                        "<label for='option" + i + "'>Option " + i + "</label>" +
                                        "<input type='text' id='option" + i + "' ><br>"
                                    );
                                }
                                $("#" + s).append($(d));
                            }
                        }
                    );
                    break;
            }
        });
    }
}
//delete divs that are over the requested value
function deleteDiv(val){
    for (let i = x; i >=val; i--) {
        $("#question-section").find($("#question-div"+i)).remove();
    }
}
//onload handler
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