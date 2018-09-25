require("file-loader?name=index.html!../html/index.html")
require("file-loader?name=style.css!../css/style.css")
require("file-loader?name=favicon.ico!../html/favicon.ico")

import thunkMiddleware from "redux-thunk"
import React from "react"
import { render } from "react-dom"
import { createStore, applyMiddleware, combineReducers, compose  } from "redux"
import { Provider } from "react-redux"
import reducer from "./reducer"
import logger from "redux-logger"
import "babel-polyfill"
import reduxCatch from "redux-catch"
import PhototoolApp from "./PhototoolApp"
import * as ACTION from "./actions"

import { combineForms, modelReducer, formReducer, createForms } from "react-redux-form"

const initialProfile = {
  /*directory: "xx",
  numberOfFiles: 0,
  geoTag: false,
  geoPoint: null,
  autolevel: false,
  border: false,
  borderColor: {
    value: -16777216,
    frgbvalue: null,
    fvalue: null,
    falpha: 0.0,
    cs: null
  },
  borderSize: 20,
  outDirName: "init",
  resize: true,
  resizeWidth: 1300,
  addSignature: true,
  sigFile: null,
  sigGravity: "SouthWest",
  sigGeometry: "+20+20",
  sigResize: "x10",
  customParams: "-quality  100%"*/
}

function errorHandler(error, getState, lastAction, dispatch) {
  console.log("ERROR", error)
}

let reducers = {
  main: reducer,
  ...createForms({ profile: initialProfile })
}

const composeEnhancers =
  typeof window === 'object' &&
  window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__ ?
    window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__({
      // Specify extension’s options like name, actionsBlacklist, actionsCreators, serialize...
    }) : compose;

const enhancer = composeEnhancers(
      applyMiddleware(reduxCatch(errorHandler), thunkMiddleware, logger)
      // other store enhancers if any
    );

const store = createStore(combineReducers(reducers), enhancer)

store.dispatch(ACTION.fetchJob())

render(
  <Provider store={store}>
    <PhototoolApp />
  </Provider>,
  document.getElementById("app")
)
