require('file-loader?name=index.html!../html/index.html');
require('file-loader?name=style.css!../css/style.css');


import thunkMiddleware from 'redux-thunk'
import React from 'react';
import { render } from 'react-dom';
import { createStore,applyMiddleware,combineReducers } from 'redux';
import { Provider } from 'react-redux';
import reducer from './reducer';
import logger from 'redux-logger'

 
//import { TodoList } from './containers';
//import Phototool from './components/Phototool';
import PhototoolApp from './PhototoolApp';
import * as ACTION from './actions';

import { combineForms, modelReducer ,formReducer, createForms} from 'react-redux-form';

const initialProfile={
        "geoTag": false,
        "geoPoint": null,
        "autolevel": false,
        "border": false,
        "borderColor": {
          "value": -16777216,
          "frgbvalue": null,
          "fvalue": null,
          "falpha": 0.0,
          "cs": null
        },
        "borderSize": 20,
        "outDirName": "init",
        "resize": true,
        "resizeWidth": 1300,
        "addSignature": true,
        "sigFile": null,
        "sigGravity": "SouthWest",
        "sigGeometry": "+20+20",
        "sigResize": "x10",
        "customParams": "-quality  100%"
}

let reducers={
        main:reducer,
        ...createForms({profile:initialProfile})                
}

const store = createStore( combineReducers(reducers),applyMiddleware(thunkMiddleware,logger))


store.dispatch(ACTION.fetchProfile())

render(
  <Provider store={store}>
    <PhototoolApp />
  </Provider>,
  document.getElementById('app')
);

