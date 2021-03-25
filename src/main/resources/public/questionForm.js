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
            "<label for="+q1+">Enter question:</label>" +
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
        $("#question-section").find("#question-div"+i).remove();
    }
}
function validate(){
    if($("#password").val()===""){
        alert("Please create a password!");
    }
    if($("#password").val()!==$("#password2").val()){
        alert("Please make sure your passwords match!");
    }
    if($("#numQuestions").val()<0||$("#numQuestions").val()>15||$("#numQuestions").val()===""){
        alert("Invalid amount of questions!")
    }
    console.log($("#question-div0").length);
    if($("#question-div0").length===0){
        alert("Please enter some questions!");
    }
    for(let i=0;i<x;i++){
        let qDiv=$("#question-div"+i);
        if(qDiv.find("#question"+i).val()===""){
            alert("One of your questions is blank!");
            return false;
        }
        switch($("select#type"+i).val()){
            case "Histogram":
                let histoQ=qDiv.find("#histogram");
                if(histoQ.find("#min").val()===""||histoQ.find("#max").val()===""||histoQ.find("#step").val()===""){
                    alert("One of your Histogram question fields is blank!");
                    return false;
                }
            case "Option":
                let optionQ=qDiv.find("#numOptions");
                if(optionQ.val()===""){
                    alert("One of your option questions has 0 options!");
                    return false;
                }else{
                    let num=parseInt(optionQ.val());
                    let options=qDiv.find("#options")
                    for(let i=0;i<num;i++){
                        console.log(options.find($("#option"+i)).val());
                        if(options.find("#option"+i).val()===""){
                            alert("One of your option fields is blank!");
                            return false;
                        }
                    }
                }
        }
        return true;
    }
}
function createJson(){
    console.log("CREATING JSON");
    let survey= {
        "title": "",
        "password": "",
        "questions": []
    };
    console.log($("#title").html());
    survey["title"]=$("#title").html();
    survey["password"]=$("#password").val();
    for(let i=0;i<x;i++){
        survey["questions"].push(questionTypeHelper(i));
    }
    console.log(JSON.stringify(survey));
    let surveyData=JSON.stringify(survey)
    $.ajax({
        type:"POST",
        url:"/create",
        contentType: "application/json; charset=utf-8",
        data:surveyData,
        dataType:'json',
        success:(e)=>{
            $('body').html(
                `
                <div class="container text-center mt-5 pt-5">
                <div class="card p-5">
    <div class="row px-5">
        <h1>${e.title} Survey Successfully Created !</h1>
    </div>

    <div class="row my-4 row px-5">
        <h4> Here is your official survey link</h4>

    </div>

    <div class="row row px-5">
        <p class="text-muted text-small">Share this with participants</p>
    </div>

    <div class="row my-4 row px-5">
        <div class="input-group mb-3">
            <input value="${window.location.hostname + '/survey/' + e.code}" type="text" id="copy"  style="border: solid 1px grey; border-radius: 3px" class="form-control-plaintext px-3" aria-label="Survey Link" aria-describedby="survey-link" readonly>
            <button class="btn btn-primary" type="button" onclick="copy()" data-toggle="tooltip" data-placement="top" title="Copied !">Copy !</button>
        </div>

    </div>

</div>

</div>
                `
            );
            console.log(e)

         },
        fail:(e)=>{
            console.log(e);
            alert("SOMETHING WENT WRONG!");
        }
    });
}


/**
Function used to copy the survey link to the clipboard
 */
function copy() {
    /* Get the text field */
    let copyText = document.getElementById("copy");

    /* Select the text field */
    copyText.select();
    copyText.setSelectionRange(0, 99999); /* For mobile devices */

    /* Copy the text inside the text field */
    document.execCommand("copy");

    /* Alert the copied text */
    alert("Link Copied!");

}





function questionTypeHelper(num){
    let qDiv=$("#question-div"+num);
    let question={
        "question":"",
        "questionType":"",
        "minVal":"",
        "maxVal":"",
        "stepSize":"",
        "choices":[]
    }
    question["question"]=qDiv.find("#question"+num).val();

    switch($("select#type"+num).val()){
        case "Histogram":
            let histoQ=qDiv.find("#histogram");
            question["minVal"]=parseInt(histoQ.find("#min").val());
            question["maxVal"]=parseInt(histoQ.find("#max").val());
            question["stepSize"]=parseInt(histoQ.find("#step").val());
        case "Option":
            let optionQ=qDiv.find("#numOptions");
            let num=parseInt(optionQ.val());
            let options=qDiv.find("#options")
            for(let i=0;i<num;i++){
                question["choices"].push(options.find($("#option"+i)).val());
            }
    }
    question["questionType"]=$("select#type"+num).val();
    return question;
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
    $("#surveyCreate").click(()=>{
        if(validate()){
            createJson();
        }
    });
});