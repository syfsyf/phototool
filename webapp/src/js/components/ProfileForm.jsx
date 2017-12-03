import React from "react"
import PropTypes from "prop-types"
import { Control, Form, Errors, combineForms, actions } from "react-redux-form"
import GeoTagComponent from "./GeoTagComponent"

class ProfileForm extends React.Component {
  onSubmit(job) {
    this.props.startJob(job)
  }
  attachDispatch(dispatch) {
      this.formDispatch = dispatch;
  }
  
  onPointChanged(lat, lng) {
    console.log(this.props)
    console.log(lat, lng)
    this.formDispatch(actions.change('profile.geoPoint.lat',lat))
    this.formDispatch(actions.change('profile.geoPoint.lng',lng))
  }
  toNumber(value){
      return parseFloat(value) || 0;
  }

  render() {
    console.log("ProfileForm", this.props)

    let profile = this.props.profile.profile
    if (!profile.directory) {
      return <div>Loading...</div>
    }
    
    let showGeoTag = profile.geoTag.value
    
    let point = { lat: 50.014714, lng: 19.96059600000001 }
    try {
        point={lat:profile.geoPoint.lat.value,lng:profile.geoPoint.lng.value}
    }
    catch(e){}
    
    console.log('POINT',point)

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
        <Form model="profile" onSubmit={job => this.onSubmit(job)}  getDispatch={(dispatch) => this.formDispatch = dispatch}>
          <div>
            <span>outDir</span>
            <Control.text model=".outDirName" />
          </div>
          <div>
            <fieldset>
              <legend>
                <label>
                  dodaj podpis?
                  <Control.checkbox model=".addSignature" />
                </label>
              </legend>
                  <span>Plik z podpisem</span> <Control.text disabled={!profile.addSignature.value}  model=".sigFile" />
                  <span>gravity</span> <Control.text disabled={!profile.addSignature.value}  model=".sigGravity" />
                  <span>geometry</span> <Control.text disabled={!profile.addSignature.value}  model=".sigGeometry" />
                  <span>resize</span> <Control.text disabled={!profile.addSignature.value}  model=".sigResize" />
                  
              
            </fieldset>
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
                  
          <div>
            <label>
              autolevel
              <Control.checkbox model=".autolevel" />
            </label>
          </div>
          <div>
            <fieldset>
              <legend>
                <label>
                  geoTag?
                  <Control.checkbox model=".geoTag" />
                </label>
              </legend>
              lat:<Control.text  disabled={!profile.geoTag.value} parser={this.toNumber} model=".geoPoint.lat" />, 
              lng: <Control.text disabled={!profile.geoTag.value} parser={this.toNumber} model=".geoPoint.lng" />
              <label>
                  dodać geoTag do źródłowego pliku?
                  <Control.checkbox model=".addGeoTagToSourceFile" />
              </label>
                          
              <label>
                  dodać geoTag do konwertowanego pliku?
                  <Control.checkbox model=".addGeoTagToConvertedFile" />
              </label>
                  
              {showGeoTag ? (
                <GeoTagComponent
                  point={point}
                  points={profile.geoPoints.value}
                  onPointChanged={(lat,lng)=>this.onPointChanged(lat,lng)}
                />
              ) : null}
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
