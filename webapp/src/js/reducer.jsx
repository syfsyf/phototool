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
  }
}

export default function reducer(state = init, action) {
  switch (action.type) {
    case ACTION.RECEIVE_JOB:
      return { ...state, profile: action.payload }
    case ACTION.JOB_STARTED_OK:
      return { ...state, jobStart: true }
    case ACTION.ERROR:
      return { ...state, error: action.payload }
    case ACTION.RECEIVE_STATUS:
      return { ...state, processStatus: action.payload }
    default:
      return state
  }
}
