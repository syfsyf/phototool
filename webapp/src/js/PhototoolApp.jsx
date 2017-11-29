import { connect } from 'react-redux';
import  Phototool from './components/Phototool';

const mapStateToProps = (state)=> {    
    return state
};
const mapDispatchToProps=(dispatch)=> {
    return {
        
    }
}

const PhototoolApp = connect(mapStateToProps,mapDispatchToProps)(Phototool)

export default PhototoolApp

