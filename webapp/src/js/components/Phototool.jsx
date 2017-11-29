import React from 'react'
import PropTypes from 'prop-types'
import ProfileForm from './ProfileForm'


class Phototool extends React.Component {
    
    addItem(){
                
    }

    render(){
        console.log('Phototool',this.props)        
        return <div>                        
            <ProfileForm profile={this.props.forms}/>
        </div>;
    }
}

Phototool.propTypes={
        list:PropTypes.array,
        profile:PropTypes.object
}

export default Phototool;
