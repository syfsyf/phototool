import { connect } from "react-redux"
import Phototool from "./components/Phototool"
import * as ACTION from "./actions"

const mapStateToProps = state => {
  return state
}
const mapDispatchToProps = dispatch => {
  return {
    startJob: job => dispatch(ACTION.startJob(job)),
    fetchStatus: () => dispatch(ACTION.fetchStatus())
  }
}

const PhototoolApp = connect(mapStateToProps, mapDispatchToProps)(Phototool)

export default PhototoolApp
