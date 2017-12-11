import { connect } from "react-redux"
import Phototool from "./components/Phototool"
import * as ACTION from "./actions"

const mapStateToProps = state => {
  console.log("mapStateToProps", state)
  return {
    main: state.main,
    forms: state.forms,
    jobStarted: state.main.jobStarted,
    processStatus: state.main.processStatus
  }
}
const mapDispatchToProps = dispatch => {
  return {
    startJob: job => dispatch(ACTION.startJob(job)),
    fetchStatus: () => dispatch(ACTION.fetchStatus())
  }
}

const PhototoolApp = connect(mapStateToProps, mapDispatchToProps)(Phototool)

export default PhototoolApp
