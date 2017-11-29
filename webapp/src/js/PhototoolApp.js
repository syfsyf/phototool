import { connect } from 'react-redux';
import  Phototool from './components/Phototool';
//import { addTodo, toggleTodo } from './actions';


const mapStateToProps = (state)=> {
    console.log(state.toJS());
    return state.toJS();
};


const PhototoolApp = connect(mapStateToProps,null)(Phototool);

export default PhototoolApp;

