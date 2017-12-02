import React from "react"
import PropTypes from "prop-types"
import { Control, Form, Errors, combineForms, actions } from "react-redux-form"

class ProfileForm extends React.Component {
  onSubmit(job) {
    this.props.startJob(job)
  }

  render() {
    console.log("ProfileForm", this.props)
    if (!this.props.profile) {
      return <div>Loading...</div>
    }

    let profile = this.props.profile.profile

    return (
      <div>
        <div>
          <span>katalog: </span>
          <span>{profile.directory.value}</span>
        </div>
        <div>
          <span>ilość plików: </span>
          <span>{profile.numberOfFiles.value}</span>
        </div>
        <Form model="profile" onSubmit={job => this.onSubmit(job)}>
          <div>
            <span>outDir</span>
            <Control.text model=".outDirName" />
          </div>
          <div>
            <fieldset>
              <legend>
                <label>
                  resizować?
                  <Control.checkbox model=".resize" />
                </label>
              </legend>
              szerokość:<Control.text disabled={!profile.resize.value} className="smallInput" model=".resizeWidth" />
            </fieldset>
          </div>
          <button type="submit">GO</button>
        </Form>
      </div>
    )
  }
}

ProfileForm.propTypes = {
  profile: PropTypes.object
}

export default ProfileForm
