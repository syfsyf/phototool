import { List, Map } from 'immutable';
import * as ACTION from './actions';

// const init = List([]);

const init = Map({
	path:'my path',
	list:List(['a','b']),	
	profile:{}	
});




export default function reducer(state=init, action) {
	
  console.log('state:',state);	
  console.log('action:',action);	
	
  switch(action.type) {
  
  case ACTION.LOAD_PROFILE:
	  return {...state,profile:action.data}; 	  
    /*
	 * case 'ADD_TODO': return todos.push(Map(action.payload)); case
	 * 'TOGGLE_TODO': return todos.map(t => { if(t.get('id') === action.payload) {
	 * return t.update('isDone', isDone => !isDone); } else { return t; } });
	 */
    default:
      return state;
  }
}

