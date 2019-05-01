export function search (kind, values, options, successCb, errorCb) {
    let data = [];
    for (let key in values) {
        data.push({
            field : key,
            ops : "EQUAL",
            value : values[key]
        });
    }
    const option = {
        method : "POST",
        body : JSON.stringify(data),
        headers : {
            "token" : getToken()
        },
        cache : "no-cache"
    };
    let params = [];
    if (!!options.l) params.push("l=" + options.l);
    else params.push("l=10");

    if (!!options.o) params.push("o=" + options.o);

    if (!!options.cursor) {
        params.push("cursor=" + options.cursor);
    } else if (!!options.s) {
        params.push("s=" + options.s);
    }

    const  url = "/search?kind=" + kind + "&" + params.join("&");

    fetch(url, option).then(res => res.json())
        .then(items => successCb(items), error => errorCb(error));
}

export function create(kind, values, successCb, errorCb) {
    const option = {
        method : "PUT",
        body : JSON.stringify(values),
        headers : {
            "token" : getToken()
        },
        cache : "no-cache"
    };

    const  url = "/api/v0/" + kind;

    fetch(url, option).then(res => res.json())
        .then(items => successCb(items), error => errorCb(error));
}
//TODO : handle error of fetch
/*
    Error returned from server will be handle as data. ...
 */
export function update(kind, id, values, successCb, errorCb) {
    const option = {
        method : "POST",
        body : JSON.stringify(values),
        headers : {
            token : getToken()
        },
        cache : "no-cache"
    };
    const url = '/api/v0/' + kind + '/' + id;
    fetch(url, option).then(res=>res.json())
        .then((result) => successCb(result), error => errorCb(error));
}

export function deleteEntity(kind, id, successCb, errorCb) {
    const option = {
        method : "DELETE",
        headers : {
            token : getToken()
        },
        cache : "no-cache"
    };
    const url = '/api/v0/' + kind + "/" + id;
    fetch(url, option).then(res=>res.json())
        .then((result) => successCb(result), error => errorCb(error));
}

/**
 *
 * @param kind
 * @param id
 * @param fields : array of field names
 * @param successCb
 * @param errorCb
 */
export function get(kind, id, fields, successCb, errorCb) {
    const url = "/api/v0/" + kind + "/" + id + (fields != null ? "?fields=" + fields.join(",") : '');
    fetch(url).then(res => res.json()).then(result => successCb(result), error => errorCb(error))
}

let token = null;
export function setToken(value) {
    token = value;
}

export function getToken() {
    return token;
}