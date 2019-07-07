function logout() {
    window.location.href = "/web/logout";
}

function changeLocale(newLocale) {
    let url = window.location.search;
    let result;
    let searchParams = new URLSearchParams(url);
    if (searchParams.has("language")) {
        searchParams.set("language", newLocale);
        result = searchParams.toString();
    } else {
        searchParams.append("language", newLocale);
        result = searchParams.toString();
    }
    sessionStorage.setItem("language", newLocale);
    return "http://" + window.location.host
        + window.location.pathname
        + "?"
        + result;
}

function initLang() {
    if (sessionStorage.getItem("language") === null) {
        sessionStorage.setItem("language", "en_US");
    }
}

function localeEn() {
    window.location.href = changeLocale("en_US");
}

function localeRu() {
    window.location.href = changeLocale("ru_RU");
}
