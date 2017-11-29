import { List, Map } from 'immutable'
import * as ACTION from './actions'
import {track, actions} from 'react-redux-form'


const init = {
	path:'my path',
	list:['a','b']	
}

export default function reducer(state=init, action) {
	
  // console.log('state:',state);
  console.log('action:',action);
	
  switch(action.type) {
  
  case ACTION.RECEIVE_PROFILE:
	  console.log('ACTION.RECEIVE_PROFILE')	  
	  return {...state,profile:action.payload}
    default:
      return state
  }
}

