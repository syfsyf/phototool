import React from "react"
import PropTypes from "prop-types"
import ProfileForm from "./ProfileForm"
import ErrorComponent from "./ErrorComponent"

class Phototool extends React.Component {
  componentDidMount() {
    this.pullStatus()
  }
  pullStatus() {
    if (this.props.main.jobStarted) {
      this.props.fetchStatus()
    }
    setTimeout(() => {
      this.pullStatus()
    }, 2000)
  }

  render() {
    console.log("PROPS", this.props)
    return (
      <div>
        <ErrorComponent error={this.props.main.error} />
        <ProfileForm
          profile={this.props.forms}
          main={this.props.main}
          startJob={this.props.startJob}
          jobStarted={this.props.jobStarted}          
        />
        <div>status:{this.props.processStatus}</div>
      </div>
    )
  }
}

Phototool.propTypes = {
  list: PropTypes.array,
  profile: PropTypes.object
}

export default Phototool
