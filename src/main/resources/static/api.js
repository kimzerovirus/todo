export async function apiCall(url, method, body) {
    const token = window.localStorage.getItem("token");
    const headers = new Headers({
        'Content-Type': 'application/json',
        'Authorization': 'Bearer ' + token
    })

    // [ERROR] Uncaught (in promise) SyntaxError: Unexpected end of JSON input
    // https://melonicedlatte.com/2022/03/13/010400.html
    return fetch(url, {
        headers,
        method,
        body: JSON.stringify(body)
    }).then(res => {
        if (res.status === 200) {
            return res.json();
        } else {
            alert('요청이 실패하였습니다.')
            return Promise.reject(res);
        }
    }).catch(err => {
        if (err.status === 401) {
            window.localStorage.removeItem('token')
            window.location.href = './login'
        }
    })
}