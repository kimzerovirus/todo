# TODO Client
프로젝트 만들기
```
yarn create react-app . --template typescript
yarn add axios @types/axios
yarn add @material-ui/core @material-ui/icons
yarn add react-router @types/react-router react-router-dom @types/react-router-dom
yarn add -D eslint prettier 
yarn add -D @typescript-eslint/eslint-plugin @typescript-eslint/parser
yarn add -D eslint-config-prettier eslint-plugin-prettier
yarn add -D eslint-config-react-app
```

CORS Cross-Origin Resource Sharing
처음 리소소를 제공한 도메인과 현재 요청하는 도메인이 다를 경우 웹보안 방침에 따라 에러가 발생한다.

| 프론트엔드               | 백엔드          |                                |
|-----------------------|---------------|---------------------------------|
|localhost:3000 (origin)|localhost:8080 | 요청 origin이 서버와 다르므로 거절된다. |


XMLHttpRequest
```
var req = new XMLHttpRequest();
req.open("GET", "http://localhost:8080/todo");
req.onload = function(){
    //cb
    console.log(req.response)
}
req.send();
```
콜백 -> Promise
```
function ex(){
    return new Promise((resolve, reject) => {
        var req = new XMLHttpRequest();
        req.open("GET", "http://localhost:8080/todo");
        req.onload = function(){
            resolve(req.response); //resolve
        };
        req.onerror = function(){
            reject(req.response); //reject
        };
        req.send(); //pending
    });
}

ex()
    .then(r => { console.log("resolved"+r) })
    .catch(e=> { console.log("rejected"+e) });
```