import { API_BASE_URL } from './app-config';
import { ItemState } from '../components/Todo';

const ACCESS_TOKEN = 'ACCESS_TOKEN';
let options: any;
let headers: any;

export function call(api: string, method: string, payload: ItemState | null) {
	headers = new Headers({
		'Content-Type': 'application/json',
	});
	const accessToken = localStorage.getItem(ACCESS_TOKEN);
	if (accessToken && accessToken !== null) {
		headers.append('Authorization', 'Bearer' + accessToken);
	}
	options = {
		headers,
		url: API_BASE_URL + api,
		method: method,
	};

	if (payload) {
		//GET method
		options.body = JSON.stringify(payload);
	}

	return fetch(options.url, options)
		.then(response =>
			response.json().then(json => {
				if (!response.ok) {
					// false => error
					return Promise.reject(json);
				}
				return json;
			}),
		)
		.catch(error => {
			// console.log(error.status);
			if (error.status === 403) {
				window.location.href = '/login'; //redirect
			}
			return Promise.reject(error);
		});
}

export function signin(userDTO: any) {
	return call('/auth/signin', 'POST', userDTO).then(res => {
		console.log('response: ', res);
		alert('로그인 토큰: ' + res.token);
		localStorage.setItem(ACCESS_TOKEN, res.token);
		if (res.token) window.location.href = '/';
	});
}

export function signout() {
	localStorage.removeItem(ACCESS_TOKEN);
	window.location.href = '/login';
}
