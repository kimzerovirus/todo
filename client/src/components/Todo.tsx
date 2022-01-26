import React from 'react';
import { ListItem, ListItemText, InputBase, Checkbox } from '@material-ui/core';

export interface ItemState {
	done: boolean;
	id: string;
	title: string;
}

function Todo({ done, id, title }: ItemState) {
	return (
		<ListItem>
			<Checkbox checked={done} />
			<ListItemText>
				<InputBase
					inputProps={{ 'aria-label': 'naked' }}
					id={id}
					name={id}
					value={title}
					multiline={true}
					fullWidth={true}
				/>
			</ListItemText>
		</ListItem>
	);
}

export default Todo;
