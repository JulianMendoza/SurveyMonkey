$(document).ready(() => {
    /**
     * On load get the survey code requested if the user entered a valid password and valid survey code
     */
    let surveyCode = {"data": $(".surveyCode").attr("id")};
    if (surveyCode.data !== undefined) {
        let div = document.createElement("div");
        div.setAttribute("id", "results");
        $('.wrapper').append(div);
        $.ajax({
            type: "POST",
            url: "/surveyResultData",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(surveyCode),
            dataType: 'json',
            success: (e) => {
                let questions = e;
                for (let property in questions) {
                    switch (questions[property].question.questionType) {
                        case "Open Ended":
                            createOpenEndedView(questions[property].question, questions[property].answers);
                            break;
                        case "Histogram":
                            createHistogramView(questions[property].question, questions[property].answers);
                            break;
                        case "Option":
                            createOptionView(questions[property].question, questions[property].answers);
                            break;
                    }
                }
            }, fail: (e) => {
                alert("Something went wrong!");
            }
        });
    } else {
        goBack();
    }
});

/**
 * User entered wrong survey code/password
 */
function goBack() {
    let div = document.createElement("div");
    div.innerHTML = "Sorry, your survey or survey password is incorrect!\nSending you back!";
    $('.wrapper').append(div);
    setTimeout(function () {
        window.location.pathname = "/";
    }, 3000);
}
/**
 * Populate the openEndedView with bootstrap-4 table configs
 */
function createOpenEndedView(question, answers) {
    let table = document.createElement("table");
    table.setAttribute("class", "table mb-5 table-bordered");
    let tableTitle = document.createElement("th");
    tableTitle.setAttribute("colspan", "2");
    let title = document.createElement("h4");
    title.append(question.question);
    tableTitle.append(title);
    let head = document.createElement("thead");
    head.setAttribute("class", "thead-dark");
    let row = document.createElement("tr");
    row.append(tableTitle);
    head.append(row);
    table.append(head);
    let tbody = document.createElement("tbody");
    for (let answer in answers) {
        let tr = document.createElement("tr");
        let td = document.createElement("td");
        td.innerHTML = answers[answer].answer;
        tr.append(td);
        tbody.append(tr);
    }
    table.append(tbody);
    $('.wrapper').append(table);
}

/**
 * chartJS bar chart as histogram
 */
function createHistogramView(question, answers) {
    let div = document.createElement("div");
    div.setAttribute("class", "chart mb-5");
    let canvas = document.createElement("canvas");
    canvas.setAttribute("id", "histo" + question.questionId);
    div.append(canvas);
    $('.wrapper').append(div);
    let labels = [];
    for (let i = 1; i <= question.maxVal; i += question.stepSize) {
        labels.push(i);
    }
    let data = new Array(labels.length).fill(0);
    for (let i = 0; i < answers.length; i++) {
        data[Math.floor(((parseInt(answers[i].answer) - question.minVal) / question.stepSize))]++;
    }
    let ctx = document.getElementById("histo" + question.questionId).getContext("2d");
    let chart = new Chart(ctx, {
        type: "bar",
        options: {
            responsive: true,
            maintainAspectRatio: false,
            layout: {
                padding: 10,
            },
            legend: {
                position: "bottom",
            },
            title: {
                display: true,
                text: question.question,
                fontSize: 20
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
        data: {
            labels: labels,
            datasets: [{
                label: 'Number of people',
                data: data,
                backgroundColor: "rgba(75, 192, 192, 0.2)",
                borderColor: "rgb(75, 192, 192)",
                borderWidth: 1
            }],
        },
    });
}
/**
 * chartJS pie chart for optionChart
 */
function createOptionView(question, answers) {
    let div = document.createElement("div");
    div.setAttribute("class", "chart");
    let canvas = document.createElement("canvas");
    canvas.setAttribute("id", "option" + question.questionId);
    div.append(canvas);
    $('.wrapper').append(div);
    let labels = [];
    for (option in question.options) {
        labels.push(question.options[option]);
    }
    let data = new Array(labels.length).fill(0);
    for (let i = 0; i < answers.length; i++) {
        for (option in question.options) {
            if (answers[i].answer == question.options[option]) {
                data[option]++;
            }
        }
    }
    let colors = [];
    for (option in question.options) {
        r = Math.floor(Math.random() * 200);
        g = Math.floor(Math.random() * 200);
        b = Math.floor(Math.random() * 200);
        colors.push('rgb(' + r + ', ' + g + ', ' + b + ')');
    }
    let ctx = document.getElementById("option" + question.questionId).getContext("2d");
    let chart = new Chart(ctx, {
        type: "pie",
        options: {
            responsive: true,
            maintainAspectRatio: false,
            layout: {
                padding: 10,
            },
            legend: {
                position: "bottom",
            },
            title: {
                display: true,
                text: question.question,
                fontSize: 20
            },
        },
        data: {
            labels: labels,
            datasets: [{
                data: data,
                backgroundColor: colors
            }],
        },
    });


}