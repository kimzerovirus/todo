import { API_BASE_URL } from './app-config';
import { ItemState } from '../components/Todo';

let options: any;

export function call(api: string, method: string, payload: ItemState | null) {
	options = {
		headers: new Headers({
			'Content-Type': 'application/json',
		}),
		url: API_BASE_URL + api,
		method: method,
	};

	if (payload) {
		//GET method
		options.body = JSON.stringify(payload);
	}

	return fetch(options.url, options).then(response =>
		response.json().then(json => {
			if (!response.ok) {
				// false => error
				return Promise.reject(json);
			}
			return json;
		}),
	);
}
