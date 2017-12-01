import React from 'react'
import PropTypes from 'prop-types'
import { Control,    Form,    Errors,    combineForms,    actions, } from 'react-redux-form';


class ProfileForm extends React.Component {
    
        
    onSubmit(job){
        console.log('submit')
        this.props.startJob(job)
    }
    
    render(){
        
        console.log('ProfileForm', this.props)
        if(!this.props.profile){
            return <div>Loading...</div>
        }
        return <Form model="profile" onSubmit={(job)=>this.onSubmit(job)}>
            <div>
            <span>outDir</span>
            <Control.text  model=".outDirName"/>
            </div>                        
            <button type="submit">GO</button>
        </Form>
                
    }
}

ProfileForm.propTypes={
        profile:PropTypes.object        
}

export default ProfileForm;
