import fetch from 'cross-fetch'
import {track, actions} from 'react-redux-form'

export const REQUEST_JOB = 'REQUEST_JOB';
function requestProfile(){
	return {
		type:REQUEST_JOB
	}
}

export const RECEIVE_JOB = 'RECEIVE_JOB';
function receiveProfile(json){
	return {
		type:RECEIVE_JOB,
		payload:json
	}
}

export function fetchJob(){
    
    return function(dispatch){
        dispatch(requestProfile);
        return fetch('/api/loadJob')
            .then(response=>response.json())            
            .then(json=> {                  
                    console.log('json',json)
                    dispatch(receiveProfile(json))
                    dispatch(actions.merge('profile',json) )
                }
            )           
    }   
}

export const JOB_STARTED='JOB_STARTED'
function jobStarted(){ return {type:JOB_STARTED} }

export const JOB_START = 'JOB_START';
export function startJob(job){
    
    return dispatch=>{
        dispatch(jobStarted)
        fetch('/api/runJob',
                {
                    method:"POST",
                    body:JSON.stringify(job)
                }
        ).then(response=>{
            console.log(response)
        })            	
    }
}
