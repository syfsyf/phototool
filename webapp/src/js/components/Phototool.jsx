import React from "react"
import PropTypes from "prop-types"
import ProfileForm from "./ProfileForm"
import ErrorComponent from "./ErrorComponent"
import { withStyles } from '@material-ui/core/styles';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import Typography from '@material-ui/core/Typography';

const styles = {
  root: {
    flexGrow: 1,
  },
};


class Phototool extends React.Component {



  componentDidMount() {
    this.pullStatus()
    this.props.fetchAppInfo()
  }
  pullStatus() {
    if (this.props.main.jobStart) {
      this.props.fetchStatus()
    }
    setTimeout(() => {
      this.pullStatus()
    }, 2000)
  }

  render() {
    console.log("PROPS", this.props)
    return (
      <div className={styles.root}>

            <AppBar position="static" color="default">
              <Toolbar>
                <Typography variant="title" color="inherit">
                  Phototool
                </Typography>
              </Toolbar>
            </AppBar>

        <ErrorComponent error={this.props.main.error} />
        <ProfileForm profile={this.props.forms} startJob={this.props.startJob} />
        <div>status:{this.props.main.processStatus.progressLabel}</div>
        <div className="buildInfo">build:{this.props.main.appInfo["git.commit.id.describe"]}/{this.props.main.appInfo["git.build.time"]}</div>
      </div>
    )
  }
}

Phototool.propTypes = {
  list: PropTypes.array,
  profile: PropTypes.object
}

export default Phototool
