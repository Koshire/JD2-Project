function getUser() {
    let lang = sessionStorage.getItem("language");
    let translate = JSON.parse(sessionStorage.getItem("hometranslate"));
    let data = JSON.parse(window.sessionStorage.getItem("authUser"));
    document.getElementById("userGratis").innerText =
        (lang !== "en_US" ? translate.ru_RU.gratis : translate.en_US.gratis) + data.name + " "
        + data.middle + " " + data.last;
}

function saveCourse() {
    let url = "createcourse";
    let name = document.getElementById("name");
    let description = document.getElementById("description");
    let startDate = document.getElementById("startDate");
    let duration = document.getElementById("duration");
    let plan = document.getElementById("plan");
    let type = document.getElementById("type");

    let course = {
        name: name.value,
        description: description.value,
        startDate: startDate.value,
        duration: duration.value,
        plan: plan.value,
        type: type.value,
        initUser: JSON.parse(window.sessionStorage.getItem("authUser")).id
    };

    fetch(url, {
        method: "post",
        body: JSON.stringify(course)
    }).then(value =>  {
        return value.json();
    }).then(data=> {
        document.getElementById("userGratis").innerText = data.mess;
        document.getElementById("form").reset();
    })
}

function init() {
    getUser();
}
