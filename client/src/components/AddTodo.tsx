import React, { useState } from 'react';
import { TextField, Paper, Button, Grid } from '@material-ui/core';

function AddTodo({ add }: any) {
	const [item, setItem] = useState('');

	const inputHandler = (e: React.ChangeEvent<HTMLInputElement>) => {
		setItem(e.currentTarget.value);
		// console.log(e.currentTarget.value);
	};

	const submitHandler = () => {
		add({ title: item });
		setItem('');
		console.log(item);
	};

	// 엔터 이벤트는 해당 인풋에 걸어줘야된다.
	const enterEvent = (e: React.KeyboardEvent) => {
		if (e.key === 'Enter') {
			submitHandler();
		}
	};

	return (
		<Paper style={{ margin: 16, padding: 16 }}>
			<Grid container>
				<Grid xs={11} md={11} item style={{ paddingRight: 16 }}>
					<TextField
						placeholder="Add Todo here"
						fullWidth
						value={item}
						onChange={inputHandler}
						onKeyPress={enterEvent}
					/>
				</Grid>
				<Grid xs={1} md={1} item>
					<Button fullWidth color="secondary" variant="outlined" onClick={submitHandler}>
						+
					</Button>
				</Grid>
			</Grid>
		</Paper>
	);
}

export default AddTodo;
