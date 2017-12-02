import React from 'react'
import PropTypes from 'prop-types'

class ErrorComponent extends React.Component {
              
    render(){                   
        if(this.props.error && this.props.error.message){
            return <div>
                error:{this.props.error.message}
            </div>
        }
        
        return <div/>
                               
    }
}

Error.propTypes={
        error:PropTypes.object        
}

export default ErrorComponent;
