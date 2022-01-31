import { useEffect, useState } from 'react';
import { Paper, List, Container } from '@material-ui/core';
import { isTemplateSpan } from 'typescript';
import Todo from './Todo';
import AddTodo from './AddTodo';
import { call } from '../api/apiService';

//types
import { ItemState } from './Todo';

const Main = () => {
	const [items, setItems] = useState<ItemState[]>();
	const [error, setError] = useState();

	// ?로 타입선언했어도 되는데 여기서만 배열이 null일 경우가 있으니깐 여기서 선언하는게 더 나은듯?
	const addItem = (item: ItemState) => {
		// item.id = 'ID-' + (items ? items.length : 0);
		// item.done = false;
		// setItems(items ? [...items, item] : [item]);
		call('/todo', 'POST', item).then(response => {
			setItems(response.data);
		});
	};
	const deleteItem = (item: ItemState) => {
		// console.log('beforeupdate', items);
		// const newitems = items?.filter(el => el.id !== item.id);
		// setItems(newitems);
		// console.log('afterupdate', items);
		call('/todo', 'DELETE', item).then(response => {
			setItems(response.data);
		});
	};

	useEffect(() => {
		// const requestOptions = {
		// 	method: 'GET',
		// 	headers: { 'Content-Type': 'Mainlication/json' },
		// };
		// fetch('http://localhost:8080/todo', requestOptions)
		// 	.then(res => res.json())
		// 	.then(
		// 		res => {
		// 			setItems(res.data);
		// 		},
		// 		err => {
		// 			setError(err);
		// 			alert(err);
		// 		},
		// 	);

		call('/todo', 'GET', null).then(response => {
			setItems(response.data);
			console.log(response.data);
		});
	}, []);

	return (
		<Container maxWidth="md">
			<AddTodo add={addItem} />
			{items && items.length > 0 && (
				<Paper style={{ margin: 16 }}>
					<List>
						{items.map((item, i) => (
							<Todo
								done={item.done}
								id={item.id}
								title={item.title}
								deleteEvent={deleteItem}
								key={i}
							/>
						))}
					</List>
				</Paper>
			)}
		</Container>
	);
};

export default Main;
