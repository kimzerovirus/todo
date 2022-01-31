import React from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';

//components
import Main from './components/Main';
import Login from './components/Login';

const App = () => {
	return (
		<BrowserRouter basename={process.env.PUBLIC_URL}>
			<Routes>
				<Route path="/" element={<Main />} />
				<Route path="/login" element={<Login />} />
			</Routes>
		</BrowserRouter>
	);
};

export default App;
