const merge = require('webpack-merge');
const common = require('./webpack.common.js');

var webpack = require('webpack');


module.exports = merge(common,{
	
	
	output: {
    path: __dirname+'/../target/classes/public',
    filename: 'bundle.js'
  },
    
  plugins:[
	new webpack.optimize.UglifyJsPlugin({
    compress: { warnings: false }
  })   
  ]
  
});
