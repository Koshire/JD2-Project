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

function sendComment() {
    let userId = JSON.parse(window.sessionStorage.getItem("authUser"));
    let courseId = document.getElementById("id").value;
    let comment = document.getElementById("commentText").value;

    let request = {
        userId: userId.id,
        courseId: courseId,
        comment: comment
    };

    let url = "createcomment";

    fetch(url, {
        method: "POST",
        body: JSON.stringify(request)
    }).then(value => {
        return value.json();
    }).then(data => {
        document.getElementById("userGratis").innerText = data.resp;
        showComments();
    })
}

function radioClick() {
    let pageSize = getPageSize();
    getComments(pagination(0, pageSize, 0, document.getElementById("id").value, "", "", ""));
}


function back() {
    let totalPages = document.getElementById("of").innerText;
    let pageSize = getPageSize();
    let page = document.getElementById("page").innerText;
    if (page > 1) {
        getComments(pagination(page - 2, pageSize, 0, document.getElementById("id").value, "", "", ""));
    }
}

function forward() {
    let totalPages = document.getElementById("of").innerText;
    let pageSize = getPageSize();
    let page = document.getElementById("page").innerText;
    if (page !== totalPages) {
        getComments(pagination(page, pageSize, 0, document.getElementById("id").value, "", "", ""));
    }
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

function showComments() {
    if (document.getElementById("divComments").hidden === true) {
        document.getElementById("divComments").hidden = false;
        let req = pagination(
            0,
            3,
            0,
            document.getElementById("id").value,
            "",
            "",
            ""
        );
        document.getElementById("comments").innerText = "";
        getComments(req);
    } else {
        document.getElementById("comments").innerText = "";
        document.getElementById("divComments").hidden = true;
    }
}

function getComments(request) {
    let url = "coursecomments";
    let resp = {};
    return fetch(url, {
        method: "POST",
        body: JSON.stringify(request)
    }).then(value => {
        return value.json();
    }).then(value => {
        let comments = document.getElementById("comments");
        comments.innerText = "";
        document.getElementById("page").innerText = value.pageable.page + 1;
        document.getElementById("of").innerText = Math.ceil(value.total / value.pageable.size);
        value.content.forEach(function (item, i, arr) {

            let childComment = document.createElement('p');
            childComment.innerText = item.comment;
            comments.appendChild(childComment);
            comments.appendChild(document.createElement("br"));
            let childUser = document.createElement('span');
            childUser.innerText = "> " + item.user.name + " " + item.user.last;
            comments.appendChild(childUser);
            comments.appendChild(document.createElement("hr"));
        });
    });
}

function getUser() {
    let lang = sessionStorage.getItem("language");
    let translate = JSON.parse(sessionStorage.getItem("hometranslate"));
    let data = JSON.parse(window.sessionStorage.getItem("authUser"));
    document.getElementById("userGratis").innerText =
        (lang !== "en_US" ? translate.ru_RU.gratis : translate.en_US.gratis) + data.name + " "
        + data.middle + " " + data.last;
}

function courseDelete() {
    let id = document.getElementById("id").value;
    let url = "deletecourse/" + id;
    fetch(url, {
        method: "GET",
    }).then(val => {
        return val.json();
    }).then(data => {
        document.getElementById("userGratis").innerText = data.resp;
        let course = document.getElementById("course");
        course.innerText = "";
        let href = document.createElement("a");
        href.setAttribute("href", "/web/home");
        href.innerText = data.mess;
        course.appendChild(href);
    })
}

function courseUpdate() {
    let url = "updatecourse";
    let type = document.getElementById("type").value;

    let course = {
        id: document.getElementById("id").value,
        name: document.getElementById("name").value,
        description: document.getElementById("name").value,
        type: type === "" ? document.getElementById("typeView").value : type,
        startDate: document.getElementById("startDate").value,
        duration: document.getElementById("duration").value,
        plan: document.getElementById("plan").value,
    };
    fetch(url, {
        method: "post",
        body: JSON.stringify(course)
    }).then(val => {
        return val.json();
    }).then(data => {
        document.getElementById("userGratis").innerText = data.resp;
        let id = document.getElementById("id").value;
        let course = document.getElementById("course");
        course.innerText = "";
        let href = document.createElement("a");
        href.setAttribute("href", "/web/course?id=" + id);
        href.innerText = data.mess;
        course.appendChild(href);
    })
}

function courseEdit() {
    let check = document.getElementById("deleteButton");
    let update = document.getElementById("updateButton");
    if (document.getElementById("type").hidden === true) {
        document.getElementById("id").disabled = false;
        document.getElementById("name").disabled = false;
        document.getElementById("description").disabled = false;
        document.getElementById("startDate").disabled = false;
        document.getElementById("duration").disabled = false;
        document.getElementById("plan").disabled = false;
        if (check !== null) {
            document.getElementById("deleteButton").disabled = false;
        }
        if (update !== null) {
            document.getElementById("updateButton").disabled = false;
        }
        document.getElementById("type").hidden = false;
    } else {
        document.getElementById("id").disabled = true;
        document.getElementById("name").disabled = true;
        document.getElementById("description").disabled = true;
        document.getElementById("startDate").disabled = true;
        document.getElementById("duration").disabled = true;
        document.getElementById("plan").disabled = true;
        if (check !== null) {
            document.getElementById("deleteButton").disabled = true;
        }
        if (update !== null) {
            document.getElementById("updateButton").disabled = true;
        }
        document.getElementById("type").hidden = true;
    }
}

function beStudentParam(userId) {
    let url = "bestudent";
    let course = {
        id: getId(),
        initUser: userId
    };
    fetch(url, {
        method: "POST",
        body: JSON.stringify(course)
    }).then(value => {
        return value.json();
    }).then(data => {
        document.getElementById("userGratis").innerText = data.mess;
        document.getElementById("teachers").innerText = "";
        document.getElementById("students").innerText = "";
        getCourse(getId());
    })
}

function beStudent() {
    let url = "bestudent";
    let course = {
        id: getId(),
        initUser: JSON.parse(window.sessionStorage.getItem("authUser")).id
    };
    fetch(url, {
        method: "POST",
        body: JSON.stringify(course)
    }).then(value => {
        return value.json();
    }).then(data => {
        document.getElementById("userGratis").innerText = data.mess;
        document.getElementById("teachers").innerText = "";
        document.getElementById("students").innerText = "";
        getCourse(getId());
    })
}

function beTeacherParam(userId) {
    let url = "beteacher";
    let course = {
        id: getId(),
        initUser: userId
    };
    fetch(url, {
        method: "POST",
        body: JSON.stringify(course)
    }).then(value => {
        return value.json();
    }).then(data => {
        document.getElementById("userGratis").innerText = data.mess;
        document.getElementById("teachers").innerText = "";
        document.getElementById("students").innerText = "";
        getCourse(getId());
    })
}

function beTeacher() {
    let url = "beteacher";
    let course = {
        id: getId(),
        initUser: JSON.parse(window.sessionStorage.getItem("authUser")).id
    };
    fetch(url, {
        method: "POST",
        body: JSON.stringify(course)
    }).then(value => {
        return value.json();
    }).then(data => {
        document.getElementById("userGratis").innerText = data.mess;
        document.getElementById("teachers").innerText = "";
        document.getElementById("students").innerText = "";
        getCourse(getId());
    })
}

function getId() {
    var url = window.location.search.substring(1);
    var searchParams = new URLSearchParams(url);
    return searchParams.get("id");
}

function getCourse(id) {
    let url = "getcourse/" + id;
    fetch(url, {
        method: "GET",
    })
        .then(function (value) {
            return value.json();
        }).then(function (data) {
        document.getElementById("id").setAttribute("value", data.id);
        document.getElementById("name").setAttribute("value", data.name);
        document.getElementById("description").innerText = data.description;
        document.getElementById("startDate").setAttribute("value", data.startDate);
        document.getElementById("duration").setAttribute("value", data.duration);
        document.getElementById("plan").innerText = data.plan;
        document.getElementById("typeView").setAttribute("value", data.type);

        let students = document.getElementById("students");
        data.students.forEach(function (item, i, arr) {
            let student = document.createElement("p");
            let stName = document.createElement("span");
            stName.innerText = item.name + " " + item.middle + " "
                + item.last;
            let stId = document.createElement("span");
            stId.innerText = item.id;
            stId.setAttribute("hidden", true);
            student.appendChild(stName);
            student.appendChild(stId);
            students.appendChild(student);
        });

        let teachers = document.getElementById("teachers");
        data.teachers.forEach(function (item, i, arr) {
            let teacher = document.createElement("p");
            let stName = document.createElement("span");
            stName.innerText = item.name + " " + item.middle + " "
                + item.last;
            let stId = document.createElement("span");
            stId.innerText = item.id;
            stId.setAttribute("hidden", true);
            teacher.appendChild(stName);
            teacher.appendChild(stId);
            teachers.appendChild(teacher);
        });
    });
}

function init() {
    getUser();
    getCourse(getId());
}
