import React, { useState } from 'react';
import { Paper, List, Container } from '@material-ui/core';
import { isTemplateSpan } from 'typescript';
import Todo from './components/Todo';
import AddTodo from './components/AddTodo';

//types
import { ItemState } from './components/Todo';

const App = () => {
	const [items, setItems] = useState<ItemState[]>();
	// ?로 타입선언했어도 되는데 여기서만 배열이 null일 경우가 있으니깐 여기서 선언하는게 더 나은듯?
	const addItem = (item: ItemState) => {
		item.id = 'ID-' + (items ? items.length : 0);
		item.done = false;
		setItems(items ? [...items, item] : [item]);
	};

	return (
		<Container maxWidth="md">
			<AddTodo add={addItem} />
			{items && (
				<Paper style={{ margin: 16 }}>
					<List>
						{items.map((item, i) => (
							<Todo done={item.done} id={item.id} title={item.title} key={i} />
						))}
					</List>
				</Paper>
			)}
		</Container>
	);
};

export default App;
