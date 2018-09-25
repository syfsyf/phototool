import { List, Map } from "immutable"
import * as ACTION from "./actions"
import { track, actions } from "react-redux-form"

const init = {
  error: {
    message: null,
    details: null
  },
  jobStart: false,
  processStatus: {
    progressLabel: ""
  },
  appInfo:{
    "git.build.time":"2018",
    "git.commit.id":"git.commit.id",
    "git.build.version":"git.build.version",
    "git.commit.id.describe":"git.commit.id.describe"
  }
}

export default function reducer(state = init, action) {

  console.log('REDUCER state:',state);
  console.log('REDUCER action:',action);
  switch (action.type) {
    case ACTION.RECEIVE_JOB:
      return { ...state, profile: action.payload }
    case ACTION.JOB_STARTED_OK:
      return { ...state, jobStart: true }
    case ACTION.ERROR:
      return { ...state, error: action.payload }
    case ACTION.RECEIVE_STATUS:
      return { ...state, processStatus: action.payload }
    case ACTION.RECEIVE_APPINFO:
        return { ...state, appInfo: action.payload }
    default:
      return state
  }
}
