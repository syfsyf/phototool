import React from 'react'
import PropTypes from 'prop-types'
import ProfileForm from './ProfileForm'
import ErrorComponent from './ErrorComponent'


class Phototool extends React.Component {
       
    render(){               
        return <div>
            <ErrorComponent error={this.props.main.error}/>            
            <ProfileForm profile={this.props.forms} startJob={this.props.startJob}/>
        </div>;
    }
}

Phototool.propTypes={
        list:PropTypes.array,
        profile:PropTypes.object
}

export default Phototool;
