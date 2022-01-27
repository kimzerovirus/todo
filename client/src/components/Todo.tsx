import React, { useState } from 'react';
import {
	ListItem,
	ListItemText,
	InputBase,
	Checkbox,
	ListItemSecondaryAction,
	IconButton,
} from '@material-ui/core';
import { DeleteOutline } from '@material-ui/icons';

export interface ItemState {
	done: boolean;
	id: string;
	title: string;
}

interface TodoState extends ItemState {
	deleteEvent: (item: ItemState) => void; //함수의 타입을 어떻게 지정해야될까??
}

function Todo({ done, id, title, deleteEvent }: TodoState) {
	const [readOnly, setReadOnly] = useState(true);
	const [changetTitle, setChangetTitle] = useState(title);
	const [changeDone, setchangeDone] = useState(done);

	const deleteEventHandler = () => {
		deleteEvent({ id, done, title }); //item의 id를 넘겨서 삭제
	};

	const offReadOnlyMode = () => {
		setReadOnly(false);
	};

	const enterKeyEventHandler = (e: React.KeyboardEvent) => {
		if (e.key === 'Enter') setReadOnly(true);
	};

	const editEventHandler = (e: React.ChangeEvent<HTMLInputElement>) => {
		setChangetTitle(e.currentTarget.value);
		console.log(e.currentTarget.value);
	};

	const ckeckBoxEventHandler = (e: React.ChangeEvent<HTMLInputElement>) => {
		setchangeDone(!changeDone);
	};

	return (
		<ListItem>
			{/* 체크박스의 체크값 변경은 클릭하는거지만 checkbox의 기본 성질이여서 클릭이벤트가 아니라 체인지 이벤트 */}
			<Checkbox checked={changeDone} onChange={ckeckBoxEventHandler} />
			<ListItemText>
				<InputBase
					inputProps={{ 'aria-label': 'naked', readOnly }}
					id={id}
					name={id}
					value={changetTitle}
					multiline={true}
					fullWidth={true}
					onClick={offReadOnlyMode}
					onChange={editEventHandler}
					onKeyPress={enterKeyEventHandler}
				/>
			</ListItemText>

			<ListItemSecondaryAction>
				<IconButton arial-label="Delete todo" onClick={deleteEventHandler}>
					<DeleteOutline />
				</IconButton>
			</ListItemSecondaryAction>
		</ListItem>
	);
}

export default Todo;
