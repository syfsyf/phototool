import _ from "lodash"
import { compose, withProps, lifecycle } from "recompose"
import { withScriptjs, withGoogleMap, GoogleMap, Marker } from "react-google-maps"

import React from "react"
import SearchBox from "react-google-maps/lib/components/places/SearchBox"

const GooleMapComponent = compose(
  withProps({
    googleMapURL: "https://maps.googleapis.com/maps/api/js?v=3.exp&libraries=geometry,drawing,places",
    loadingElement: <div style={{ height: "100%" }} />,
    containerElement: <div style={{ height: "350px" }} />,
    mapElement: <div style={{ height: "100%" }} />
  }),
  withScriptjs,
  withGoogleMap,
  lifecycle({
    componentWillMount() {
      const refs = {}

      this.setState({
        bounds: null,
        //point: this.props.point,
        markers: [],
        onMapMounted: ref => {
          refs.map = ref
        },
        onBoundsChanged: () => {
          this.setState({
            bounds: refs.map.getBounds(),
            center: refs.map.getCenter()
          })
        },
        onSearchBoxMounted: ref => {
          refs.searchBox = ref
        },
        onMarkerMounted: ref => {
          refs.marker = ref
        },
        onPlacesChanged: () => {
          const places = refs.searchBox.getPlaces()
          const bounds = new google.maps.LatLngBounds()

          places.forEach(place => {
            if (place.geometry.viewport) {
              bounds.union(place.geometry.viewport)
            } else {
              bounds.extend(place.geometry.location)
            }
          })
          const nextMarkers = places.map(place => ({
            position: place.geometry.location
          }))
          const nextCenter = _.get(nextMarkers, "0.position", this.state.center)

          this.setState({
            point: nextCenter,
            markers: nextMarkers
          })
          this.props.onPointChanged(nextCenter.lat(), nextCenter.lng())
          return false
          // refs.map.fitBounds(bounds);
        },
        onInputChange: event => {
          if (event.which === 13) {
            event.preventDefault()
          }
        },
        onMarkerDragEnd: () => {
          let pos = refs.marker.getPosition()
          console.log("POS", pos.lat())
          this.props.onPointChanged(pos.lat(), pos.lng())
        },
        onMapDblClick: event => {
          console.log("DBL", event)
          this.props.onPointChanged(event.latLng.lat(), event.latLng.lng())
        }
      })
    }
  })
)(props => {
  return (
    <GoogleMap
      defaultZoom={16}
      defaultCenter={props.center}
      center={props.point}
      ref={props.onMapMounted}
      onBoundsChanged={props.onBoundsChanged}
      onDblClick={props.onMapDblClick}
      options={{ disableDoubleClickZoom: true, fullscreenControl: false, streetViewControl: false }}
    >
      <SearchBox
        ref={props.onSearchBoxMounted}
        bounds={props.bounds}
        controlPosition={google.maps.ControlPosition.TOP_LEFT}
        onPlacesChanged={props.onPlacesChanged}
      >
        <input
          type="text"
          placeholder="Szukaj..."
          onKeyPress={props.onInputChange}
          style={{
            boxSizing: "border-box",
            border: "1px solid transparent",
            width: "240px",
            height: "32px",
            marginTop: "27px",
            padding: "0 12px",
            borderRadius: "3px",
            boxShadow: "0 2px 6px rgba(0, 0, 0, 0.3)",
            fontSize: "14px",
            outline: "none",
            textOverflow: "ellipses"
          }}
        />
      </SearchBox>
      <Marker
        position={props.point}
        defaultDraggable={true}
        ref={props.onMarkerMounted}
        onDragEnd={props.onMarkerDragEnd}
      />
    </GoogleMap>
  )
})

class GeoTagComponent extends React.PureComponent {
  onPointChanged(lat, lng) {
    console.log("CHANGED", lat)
    this.props.onPointChanged(lat, lng)
  }

  render() {
    return (
      <table style={{ width: "100%" }}>
        <tbody>
          <tr>
            <td />
            <td style={{ width: "100%" }}>
              <GooleMapComponent
                point={this.props.point}
                onPointChanged={(lat, lng) => this.onPointChanged(lat, lng)}
              />
            </td>
          </tr>
        </tbody>
      </table>
    )
  }
}

export default GeoTagComponent
