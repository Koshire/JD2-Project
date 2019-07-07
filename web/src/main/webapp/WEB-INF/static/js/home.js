function pagination(page, size, total, param1, param2, param3, param4) {
    return {
        page: page,
        pageSize: size,
        totalPages: total,
        parameter1: param1,
        parameter2: param2,
        parameter3: param3,
        parameter4: param4
    };
}

function getTranslate() {
    let url = "hometranslate";
    fetch(url, {
        method: "POST",
    })
        .then(value => {
            return value.json();
        })
        .then(data => {
            let request = data;
            sessionStorage.setItem("hometranslate", JSON.stringify(request));
        });
}

function getUser() {
    let lang = sessionStorage.getItem("language");
    let translate = JSON.parse(sessionStorage.getItem("hometranslate"));
    let url = "getuser";
    fetch(url, {
        method: "POST",
    })
        .then(function (value) {
            return value.json();
        }).then(function (data) {
        window.sessionStorage.setItem("authUser", JSON.stringify(data));
        document.getElementById("userGratis").innerText =
            (lang !== "en_US" ? translate.ru_RU.gratis : translate.en_US.gratis) + data.name + " "
            + data.middle + " " + data.last;
    });
}

function getPageSize() {
    if (document.getElementById("PageSize3").checked) {
        return document.getElementById("PageSize3").value;
    } else if (document.getElementById("PageSize6").checked) {
        return document.getElementById("PageSize6").value;
    } else if (document.getElementById("PageSize9").checked) {
        return document.getElementById("PageSize9").value;
    }
}

function getParam() {
    return {
        param1: document.getElementById("courseName").value,
        param2: document.getElementById("courseType").value,
        param3: document.getElementById("dateFrom").value,
        param4: document.getElementById("dateTo").value
    }
}

function radioClick() {
    let pageSize = getPageSize();
    let param = getParam();
    getCourses(pagination(0, pageSize, 0, param.param1, param.param2, param.param3, param.param4));
}

function back() {
    let totalPages = document.getElementById("of").innerText;
    let pageSize = getPageSize();
    let param = getParam();
    let page = document.getElementById("page").innerText;
    if (page > 1) {
        getCourses(pagination(page - 2, pageSize, 0, param.param1, param.param2, param.param3, param.param4));
    }
}

function forward() {
    let totalPages = document.getElementById("of").innerText;
    let pageSize = getPageSize();
    let param = getParam();
    let page = document.getElementById("page").innerText;
    if (page !== totalPages) {
        getCourses(pagination(page, pageSize, 0, param.param1, param.param2, param.param3, param.param4));
    }

}

function show() {
    radioClick();
}

function getCourses(request) {
    let url = "getcourses";
    let body = JSON.stringify(request);
    let lang = sessionStorage.getItem("language");
    let translate = JSON.parse(sessionStorage.getItem("hometranslate"));

    fetch(url, {
        method: "POST",
        body: body
    })
        .then(function (value) {
            return value.json();
        }).then(function (data) {
        let totalPages = Math.ceil(data.total / data.pageable.size);
        let page = data.pageable.page;

        document.getElementById("page").innerText = (page + 1);
        document.getElementById("of").innerText = totalPages;

        let courses = document.getElementById("courses");
        courses.innerText = "";
        data.content.forEach(function (item, i, arr) {
            let child = document.createElement('div');
            courses.appendChild(child);
            let childName = document.createElement('p');
            childName.innerText = (lang !== "en_US" ? translate.ru_RU.name : translate.en_US.name) + item.name;
            child.appendChild(childName);
            let childDescription = document.createElement('p');
            childDescription.innerText = (lang !== "en_US" ? translate.ru_RU.description : translate.en_US.description) + item.description;
            child.appendChild(childDescription);
            let childStartDate = document.createElement('p');
            childStartDate.innerText = (lang !== "en_US" ? translate.ru_RU.start : translate.en_US.start) + item.startDate;
            child.appendChild(childStartDate);
            let childDuration = document.createElement('p');
            childDuration.innerText = (lang !== "en_US" ? translate.ru_RU.duration : translate.en_US.duration) + item.duration;
            child.appendChild(childDuration);
            let childPromo = document.createElement('a');
            childPromo.setAttribute("href", "/web/course?id=" + item.id)
            childPromo.innerText = (lang !== "en_US" ? translate.ru_RU.details : translate.en_US.details);
            child.appendChild(childPromo);
            child.appendChild(document.createElement('hr'));
        });
    });
}

async function init() {
    let paginationHome = {
        page: 0,
        pageSize: 3,
        totalPages: 0,
        totalElements: 0,
        parameter1: "",
        parameter2: "",
        parameter3: "",
        parameter4: ""
    };
    await getTranslate();
    await getUser();
    await getCourses(paginationHome);
}
