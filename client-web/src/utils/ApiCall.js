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
        .then(result => {
            if (typeof result.error_code !== 'undefined') {
                errorCb(result);
            } else {
                successCb(result)
            }
            }, error => console.log("Fetch Error : " + error));
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
        .then(result => {
            if (typeof result.error_code !== 'undefined') {
                errorCb(result);
            } else {
                successCb(result)
            }
        }, error => errorCb(error));
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
        .then(result => {
            if (typeof result.error_code !== 'undefined') {
                errorCb(result);
            } else {
                successCb(result)
            }
        }, error => console.log("Fetch Error : " + error));
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
        .then(result => {
            if (result.error_code != null) {
                errorCb(result);
            } else {
                successCb(result)
            }
        }, error => console.log("Fetch Error : " + error));
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
    fetch(url).then(res => res.json()).then(result => {
        if (typeof result.error_code !== 'undefined') {
            errorCb(result);
        } else {
            successCb(result)
        }
    }, error => console.log("Fetch Error : " + error))
}

let token = null;
export function setToken(value) {
    token = value;
}

export function getToken() {

    return token;
}

export function login(username, password, successCb, errorCb) {
    const option = {
        method : "POST",
        body : JSON.stringify({username : username, password : password}),
        cache : "no-cache"
    };
    const url = '/api/login';
    fetch(url, option).then(res=>res.json())
        .then(result => {
            if (typeof result.error_code !== 'undefined') {
                errorCb(result);
            } else {
                token = result.token;
                successCb(result)
            }
        }, error => console.log("Fetch Error : " + error));
}

export function logout() {

}

export function register(username, password, succesCb, errorCb) {
    const option = {
        method : "PUT",
        body : JSON.stringify({username : username, password : password}),
        cache : "no-cache"
    };
    const url = '/api/register';
    fetch(url, option).then(res=>res.json())
        .then(result => {
            if (typeof result.error_code !== 'undefined') {
                errorCb(result);
            } else {
                successCb(result)
            }
        }, error => console.log("Fetch Error : " + error));
}