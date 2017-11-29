//import axios from 'axios'
import fetch from 'cross-fetch'
import {track, actions} from 'react-redux-form'


const uid = () => Math.random().toString(34).slice(2);

export const REQUEST_PROFILE = 'REQUEST_PROFILE';
function requestProfile(){
	return {
		type:REQUEST_PROFILE
	}
}

export const RECEIVE_PROFILE = 'RECEIVE_PROFILE';
function receiveProfile(json){
	return {
		type:RECEIVE_PROFILE,
		payload:json
	}
}


export function fetchProfile(){
	
	return function(dispatch){
		dispatch(requestProfile);
		return fetch('/api/profile/load')
		    .then(response=>response.json())		    
			.then(json=> {			        
			        console.log('json',json)
				    //dispatch(receiveProfile(json))
				    dispatch(actions.merge('profile',json) )
			    }
			)
			
	}
	
	
}



export function loadProfile(dispatch){
	
	axios.get('/api/profile/load').then(response=>{
		console.log(response);
		dispatch({type:LOAD_PROFILE,payload:response.data});
	});	
	//return {type:LOAD_PROFILE_BEGIN};
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

