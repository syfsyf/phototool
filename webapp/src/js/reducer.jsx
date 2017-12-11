import { List, Map } from "immutable"
import * as ACTION from "./actions"
import { track, actions } from "react-redux-form"

const init = {
  error: {
    message: null,
    details: null
  },
  jobStarted: false,
  processStatus: "initial"
}

export default function reducer(state = init, action) {
  switch (action.type) {
    case ACTION.RECEIVE_JOB:
      return { ...state, profile: action.payload }
    case ACTION.JOB_STARTED_OK:
      return { ...state, jobStarted: true }
    case ACTION.JOB_START:
      return { ...state, processStatus: "--" }
    case ACTION.JOB_STARTED_FAIL:
      return { ...state, jobStarted: false }
    case ACTION.ERROR:
      return { ...state, error: action.payload }
    case ACTION.RECEIVE_STATUS:
      return {
        ...state,
        jobStarted: action.payload.jobStarted,
        processStatus: action.payload.processStatus
      }
    default:
      return state
  }
}
