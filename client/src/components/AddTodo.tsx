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

	return (
		<Paper style={{ margin: 16, padding: 16 }}>
			<Grid container>
				<Grid xs={11} md={11} item style={{ paddingRight: 16 }}>
					<TextField placeholder="Add Todo here" fullWidth onChange={inputHandler} value={item} />
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
