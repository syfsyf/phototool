import axios from 'axios';

const uid = () => Math.random().toString(34).slice(2);

export const LOAD_PROFILE = 'LOAD_PROFILE';


export function loadProfile(dispatch){
	
	axios.get('/api/profile/load').then(response=>{
		console.log(response);
		dispatch({type:LOAD_PROFILE,data:response.data});
	});
		
}


export function addTodo(text) {
  return {
    type: 'ADD_TODO',
    payload: {
      id: uid(),
      isDone: false,
      text: text
    }
  };
}

export function toggleTodo(id) {
  return {
    type: 'TOGGLE_TODO',
    payload: id
  };
}

