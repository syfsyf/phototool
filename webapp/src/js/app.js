require('file-loader?name=index.html!../html/index.html');
require('file-loader?name=style.css!../css/style.css');

import React from 'react';
import { render } from 'react-dom';
import { createStore } from 'redux';
import { Provider } from 'react-redux';
import reducer from './reducer';
import axios from 'axios';
 
//import { TodoList } from './containers';
//import Phototool from './components/Phototool';
import PhototoolApp from './PhototoolApp';


/*
axios.get('/api/profile/load').then(response=>{
	console.log(response);
});
*/


const store = createStore(reducer);

render(
  <Provider store={store}>
    <PhototoolApp />
  </Provider>,
  document.getElementById('app')
);

