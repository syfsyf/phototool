import React from 'react'
import PropTypes from 'prop-types'
import { Control,    Form,    Errors,    combineForms,    actions, } from 'react-redux-form';


class ProfileForm extends React.Component {
    
    
    
    render(){
        
        console.log('ProfileForm', this.props)
        if(!this.props.profile){
            return <div>Loading...</div>
        }
        return <Form model="profile" onSubmit={(vals) => console.log(vals)}>
            <div>
            <span>outDir</span>
            <Control.text  model=".outDirName"/>
            </div>
            <div>
            <span>outDir</span>
            <Control.text  model=".outDirName"/>
            </div>
            <button type="submit">Log In</button>
        </Form>
                
    }
}

ProfileForm.propTypes={
        profile:PropTypes.object
}

export default ProfileForm;
