import { List, Map } from 'immutable'
import * as ACTION from './actions'
import {track, actions} from 'react-redux-form'


const init = {
	jobStart:false	
}

export default function reducer(state=init, action) {
	
  switch(action.type) {
  
  case ACTION.RECEIVE_JOB:	  	 
	  return {...state,profile:action.payload}
  case ACTION.JOB_START:
      return {...state,jobStart:true}
    default:
      return state
  }
}

