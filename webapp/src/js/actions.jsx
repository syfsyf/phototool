import 'cross-fetch/polyfill'
import {track, actions} from 'react-redux-form'


export const REQUEST_JOB = 'REQUEST_JOB';
function requestJob(){
	return {
		type:REQUEST_JOB
	}
}

export const RECEIVE_JOB = 'RECEIVE_JOB';
function receiveJob(json){
	return {
		type:RECEIVE_JOB,
		payload:json
	}
}
export const FETCH_ERROR = 'FETCH_ERROR';
function fetchError(){
    return {
        type: FETCH_ERROR
    }
}

export const ERROR = 'ERROR';

function error(message,detail){
    return {
        type:ERROR,
        payload:{message:message,detail:detail}
    }
}

function fetchErrorHandle(response,dispatch,onJson){
    
    if(!response.ok){        
            response.json().then(json=>{                
                dispatch(error(json.message,json.details))
            }).catch(e=>{
                dispatch(error(response.status,response.statusText))
            })               
    }
    else {
        response.json().then(json=>onJson(json))
    }    
}

function fetchGet(url,dispatch,onJson){
    return fetch(url)
        .then(response=>{return fetchErrorHandle(response,dispatch,onJson)})
}

function fetchPost(url,data,dispatch,onJson){
    return fetch(url,{
        method:"POST",
        body:JSON.stringify(data)
        })
        .then(response=>{return fetchErrorHandle(response,dispatch,onJson)})
}


export function fetchJob(){    
    return function(dispatch){                      
        dispatch(requestJob())
        let onJson=json=>{
            console.log('json',json)
            dispatch(receiveJob(json))
            dispatch(actions.merge('profile',json))
        }
        return fetchGet('/api/loadJob',dispatch,onJson)                          
    }    
}

export const JOB_STARTED='JOB_STARTED'
function jobStarted(){ return {type:JOB_STARTED} }

export const JOB_STARTED_OK='JOB_STARTED_OK'
function jobStartedOk(){ return {type:JOB_STARTED_OK} }

export const JOB_STARTED_FAIL='JOB_STARTED_FAIL'
function jobStartedFail(error){ return {type:JOB_STARTED_FAIL,error:true,payload:error} }

export const JOB_START = 'JOB_START';
export function startJob(job){
        
    return function(dispatch){        
        dispatch(jobStarted())
        let onJson=json=>{
            dispatch(jobStartedOk())
        }
        fetchPost('/api/runJob',job,dispatch,onJson)                            	
    }
}
