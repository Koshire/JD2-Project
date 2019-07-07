function validate_email() {

    let lang = sessionStorage.getItem("language");
    let translate = JSON.parse(sessionStorage.getItem("hometranslate"));

    var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
    var address = document.getElementById('email').value;
    let checkurl = "checklogin";
    let result = "";
    fetch(checkurl, {
        method: "POST",
        body: address
    }).then(function (value) {
        return value.text();
    }).then(function (data) {
        if (data != "false") {
            document.getElementById('email').value = "";
            document.getElementById('message_email').style.color = 'red';
            document.getElementById('message_email').innerHTML =
                (lang !== "en_US" ? translate.ru_RU.userexist : translate.en_US.userexist);
        }
    });

    if (reg.test(address) === false) {
        document.getElementById('message_email').style.color = 'red';
        document.getElementById('message_email').innerHTML =
            (lang !== "en_US" ? translate.ru_RU.incorremail : translate.en_US.incorremail);
        return false;
    } else {
        document.getElementById('message_email').style.color = 'green';
        document.getElementById('message_email').innerHTML =
            (lang !== "en_US" ? translate.ru_RU.corremail : translate.en_US.corremail);
        return true;
    }
}

function validate_password() {
    let lang = sessionStorage.getItem("language");
    let translate = JSON.parse(sessionStorage.getItem("hometranslate"));
    var reg = /([A-Za-z0-9_\-\.\s])/;
    var password = document.getElementById('password').value;
    if (password === "" || password.length < 7 || reg.test(password) === false) {
        document.getElementById('message_password').style.color = 'red';
        document.getElementById('message_password').innerHTML =
            (lang !== "en_US" ? translate.ru_RU.incorrpass : translate.en_US.incorrpass);
        return false;
    } else {
        document.getElementById('message_password').style.color = 'green';
        document.getElementById('message_password').innerHTML =
            (lang !== "en_US" ? translate.ru_RU.corrpass : translate.en_US.corrpass);
        return true;
    }
}

function check_confirm_password () {
    let lang = sessionStorage.getItem("language");
    let translate = JSON.parse(sessionStorage.getItem("hometranslate"));
    if (document.getElementById('password').value ===
        document.getElementById('confirm_password').value) {
        document.getElementById('message_confirm').style.color = 'green';
        document.getElementById('message_confirm').innerHTML =
            (lang !== "en_US" ? translate.ru_RU.matchpass : translate.en_US.matchpass);
        return true;
    } else {
        document.getElementById('message_confirm').style.color = 'red';
        document.getElementById('message_confirm').innerHTML =
            (lang !== "en_US" ? translate.ru_RU.nomatchpass : translate.en_US.nomatchpass);
        return false;
    }
}

function ValidatePhoneNumber() {
    let lang = sessionStorage.getItem("language");
    let translate = JSON.parse(sessionStorage.getItem("hometranslate"));
    var reg = /^[0-9]{3}-[0-9]{2}-[0-9]{7}/;
    var phone = document.getElementById('phone').value;
    if (reg.test(phone) === true) {
        document.getElementById('message_phone').style.color = 'green';
        document.getElementById('message_phone').innerHTML =
            (lang !== "en_US" ? translate.ru_RU.phone : translate.en_US.phone);
        return true;
    } else {
        document.getElementById('message_phone').style.color = 'red';
        document.getElementById('message_phone').innerHTML =
            (lang !== "en_US" ? translate.ru_RU.nophone : translate.en_US.nophone);
        return false;
    }
}

function onSubmitRegistration() {

    if (!!(ValidatePhoneNumber() & check_confirm_password() &
        validate_password() & validate_email())) {
        sendRegistrationData();
        return true;
    } else {
        return false;
    }
}

function sendRegistrationData() {
    let role = document.getElementById("role").value;
    let email = document.getElementById("email").value;
    let password = document.getElementById("password").value;
    let name = document.getElementById("first_name").value;
    let middle = document.getElementById("middle_name").value;
    let last = document.getElementById("last_name").value;
    let phone = document.getElementById("phone").value;
    let language = document.getElementById("language").value;

    let regData = {
        role: role, email: email, password: password,
        name: name, middle: middle, last: last,
        phone: phone, language: language
    };
    let url = "registration";

    fetch(url, {
        method: "POST",
        headers: {
            "Content-type": "application/json; charset=UTF-8"
        },
        body: JSON.stringify(regData)
    }).then(function (value) {
        return value.json();
    }).then(function (data) {
        window.location.href = data.resp + "?message=" + data.mess;
    })
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

function initTranslate() {
    getTranslate();
}