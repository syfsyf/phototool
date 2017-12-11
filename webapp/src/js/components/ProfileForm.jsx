import React from "react"
import PropTypes from "prop-types"
import { Control, Form, Errors, combineForms, actions } from "react-redux-form"
import GeoTagComponent from "./GeoTagComponent"
import { ChromePicker, GithubPicker, TwitterPicker } from "react-color"

class ProfileForm extends React.Component {
  onSubmit(job) {
    this.props.startJob(job)
  }
  attachDispatch(dispatch) {
    this.formDispatch = dispatch
  }

  onPointChanged(lat, lng) {
    this.formDispatch(actions.change("profile.geoPoint.lat", lat))
    this.formDispatch(actions.change("profile.geoPoint.lng", lng))
  }

  onBorderColorChange(color) {
    console.log("color", color)
    this.formDispatch(actions.change("profile.borderColorHex", color.hex))
  }

  toNumber(value) {
    return parseFloat(value) || 0
  }

  render() {
    console.log("ProfileForm", this.props)

    let profile = this.props.profile.profile
    if (!profile.directory) {
      return <div>Loading...</div>
    }

    let showGeoTag = profile.geoTag.value
    let showAdditionalGeoTag = false

    let point = { lat: 50.014714, lng: 19.96059600000001 }
    try {
      point = { lat: profile.geoPoint.lat.value, lng: profile.geoPoint.lng.value }
    } catch (e) {}

    return (
      <div>
        <div>
          <span>ilość plików: </span>
          <span>{profile.numberOfFiles.value}</span>
        </div>
        <Form
          model="profile"
          onSubmit={job => this.onSubmit(job)}
          getDispatch={dispatch => (this.formDispatch = dispatch)}
        >
          <div>
            <label>
              katalog
              <Control.text className="longInput" model=".directory" />
            </label>
          </div>
          <table>
            <tbody>
              <tr>
                <td>
                  <fieldset>
                    <legend>
                      <label>
                        resizować?
                        <Control.checkbox model=".resize" />
                      </label>
                    </legend>
                    <span>szerokość</span>
                    <Control.text disabled={!profile.resize.value} className="smallInput" model=".resizeWidth" />
                  </fieldset>
                  <br />
                  <fieldset>
                    <legend>
                      <label>
                        dodaj podpis?
                        <Control.checkbox model=".addSignature" />
                      </label>
                    </legend>
                    <span>Plik z podpisem</span>
                    <Control.text disabled={!profile.addSignature.value} model=".sigFile" />
                    <br />
                    <span>gravity</span> <Control.text disabled={!profile.addSignature.value} model=".sigGravity" />
                    <br />
                    <span>geometry</span> <Control.text disabled={!profile.addSignature.value} model=".sigGeometry" />
                    <br />
                    <span>resize</span> <Control.text disabled={!profile.addSignature.value} model=".sigResize" />
                  </fieldset>
                  <div>
                    <label>
                      Dodatkowe parametry
                      <Control.text model=".customParams" />
                    </label>
                  </div>
                  <div>
                    <label>
                      autolevel
                      <Control.checkbox model=".autolevel" />
                    </label>
                  </div>
                </td>
                <td>
                  <fieldset>
                    <legend>
                      <label>
                        ramka?
                        <Control.checkbox model=".border" />
                      </label>
                    </legend>
                    <span>szerokość</span>
                    <Control.text
                      disabled={!profile.border.value}
                      parser={this.toNumber}
                      className="smallInput"
                      model=".borderSize"
                    />
                    <ChromePicker
                      color={profile.borderColorHex.value}
                      onChangeComplete={color => this.onBorderColorChange(color)}
                    />
                  </fieldset>
                </td>
                <td>
                  <fieldset style={{width:'370px'}}>
                    <legend>
                      <label>
                        geoTag?
                        <Control.checkbox model=".geoTag" />
                      </label>
                    </legend>
                    <span>lat</span>
                    <Control.text disabled={!profile.geoTag.value} parser={this.toNumber} model=".geoPoint.lat" className="geoInput"/>
                    <span>lng</span>
                    <Control.text disabled={!profile.geoTag.value} parser={this.toNumber} model=".geoPoint.lng" className="geoInput"/>
                    {showAdditionalGeoTag ? (
                      <div>
                        <label>
                          <span>dodać geoTag do źródłowego pliku?</span>
                          <Control.checkbox model=".addGeoTagToSourceFile" disabled={!profile.geoTag.value} />
                        </label>
                        <label>
                          <span>dodać geoTag do konwertowanego pliku?</span>
                          <Control.checkbox model=".addGeoTagToConvertedFile" disabled={!profile.geoTag.value} />
                        </label>
                      </div>
                    ) : null}
                    {showGeoTag ? (
                      <GeoTagComponent
                        point={point}
                        points={profile.geoPoints.value}
                        onPointChanged={(lat, lng) => this.onPointChanged(lat, lng)}
                      />
                    ) : null}
                  </fieldset>
                </td>
              </tr>
            </tbody>
          </table>
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
